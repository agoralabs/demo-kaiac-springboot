#!/bin/bash

THE_DATE=$(date '+%Y-%m-%d %H:%M:%S')
echo "Build started on $THE_DATE"

appenvsubstr(){
    p_template=$1
    p_destination=$2
    envsubst '$TF_VAR_ENV_APP_GL_SCRIPT_MODE' < $p_template \
    | envsubst '$TF_VAR_ENV_APP_GL_NAMESPACE' \
    | envsubst '$TF_VAR_ENV_APP_GL_NAME' \
    | envsubst '$TF_VAR_ENV_APP_GL_STAGE' \
    | envsubst '$TF_VAR_ENV_APP_GL_REPO_PHP_NAME' \
    | envsubst '$TF_VAR_ENV_APP_GL_REPO_PHP_TAG' \
    | envsubst '$TF_VAR_ENV_APP_GL_DOCKER_REPOSITORY' \
    | envsubst '$TF_VAR_ENV_APP_GL_AWS_REGION' \
    | envsubst '$TF_VAR_ENV_APP_GL_AWS_REGION_ECR' \
    | envsubst '$TF_VAR_ENV_APP_BE_DOMAIN_NAME' \
    | envsubst '$TF_VAR_ENV_APP_BE_URL' \
    | envsubst '$TF_VAR_ENV_APP_BE_LOCAL_PORT' \
    | envsubst '$TF_VAR_ENV_APP_BE_LOCAL_SOURCE_FOLDER' \
    | envsubst '$TF_VAR_ENV_APP_BE_EKS_CLUSTER_NAME' \
    | envsubst '$TF_VAR_ENV_APP_BE_KUBERNETES_NAMESPACE' \
    | envsubst '$TF_VAR_ENV_APP_BE_SSL_CERT_ARN' \
    | envsubst '$TF_VAR_ENV_APP_DB_HOST' \
    | envsubst '$TF_VAR_ENV_APP_DB_PORT' \
    | envsubst '$TF_VAR_ENV_APP_DB_NAME' \
    | envsubst '$TF_VAR_ENV_APP_DB_USERNAME' \
    | envsubst '$TF_VAR_ENV_APP_DB_PASSWORD' > $p_destination
}


appenvsubstr devops/application.yml.template src/main/resources/application.yml

chmod +x ./mvnw
./mvnw clean install -DskipTests

echo "Generating Dockerfile..."
appenvsubstr devops/Dockerfile.template Dockerfile

echo "Generating app-deployment.yaml..."
appenvsubstr devops/app-deployment.yaml.template app-deployment.yaml

echo "Generating app-service.yaml..."
appenvsubstr devops/app-service.yaml.template app-service.yaml

echo "Login into ecr..."
aws ecr get-login-password --region $TF_VAR_ENV_APP_GL_AWS_REGION_ECR | docker login --username AWS --password-stdin $TF_VAR_ENV_APP_GL_DOCKER_REPOSITORY

echo "Building the Docker image..."
docker build -t $TF_VAR_ENV_APP_GL_NAME:${TF_VAR_ENV_APP_GL_NAMESPACE}_${TF_VAR_ENV_APP_GL_STAGE} .

echo "Create $TF_VAR_ENV_APP_GL_NAME repository..."
aws ecr describe-repositories --repository-names $TF_VAR_ENV_APP_GL_NAME --region $TF_VAR_ENV_APP_GL_AWS_REGION_ECR || aws ecr create-repository --repository-name $TF_VAR_ENV_APP_GL_NAME --region $TF_VAR_ENV_APP_GL_AWS_REGION_ECR
aws ecr delete-repository --repository-name $docker_hub_repo --force
aws ecr create-repository --repository-name $TF_VAR_ENV_APP_GL_NAME --region $TF_VAR_ENV_APP_GL_AWS_REGION_ECR

echo "Tag your image with the Amazon ECR registry..."
docker tag $TF_VAR_ENV_APP_GL_NAME:${TF_VAR_ENV_APP_GL_NAMESPACE}_${TF_VAR_ENV_APP_GL_STAGE} $TF_VAR_ENV_APP_GL_DOCKER_REPOSITORY/$TF_VAR_ENV_APP_GL_NAME:$TF_VAR_ENV_APP_GL_NAMESPACE'_'$TF_VAR_ENV_APP_GL_STAGE

echo "Push the image to ecr..."
docker push $TF_VAR_ENV_APP_GL_DOCKER_REPOSITORY/$TF_VAR_ENV_APP_GL_NAME:$TF_VAR_ENV_APP_GL_NAMESPACE'_'$TF_VAR_ENV_APP_GL_STAGE

echo "Updating kubeconfig..."
aws eks update-kubeconfig --region $TF_VAR_ENV_APP_GL_AWS_REGION --name $TF_VAR_ENV_APP_BE_EKS_CLUSTER_NAME

cat /root/.kube/config
cat app-deployment.yaml
cat app-service.yaml

echo "Affichage de la version..."
kubectl version

echo "Trying kubectl apply -f app-deployment.yaml -n ${TF_VAR_ENV_APP_BE_KS8_NAMESPACE}"
kubectl apply -f app-deployment.yaml -n ${TF_VAR_ENV_APP_BE_KS8_NAMESPACE}

echo "Trying kubectl apply -f app-service.yaml -n ${TF_VAR_ENV_APP_BE_KS8_NAMESPACE}"
kubectl apply -f app-service.yaml -n ${TF_VAR_ENV_APP_BE_KS8_NAMESPACE}

THE_DATE_END=$(date '+%Y-%m-%d %H:%M:%S')
echo "Build ended on $THE_DATE_END"
#!/bin/bash

THE_DATE=$(date '+%Y-%m-%d %H:%M:%S')
echo "Build started on $THE_DATE"

appenvsubstr(){
    p_template=$1
    p_destination=$2
    envsubst '$TF_VAR_ENV_APP_NAME' < $p_template \
    | envsubst '$TF_VAR_ENV_APP_ENV_NAME' \
    | envsubst '$TF_VAR_ENV_APP_BACKEND_NAMESPACE' \
    | envsubst '$TF_VAR_ENV_LOCAL_BACKEND_SOURCE_FOLDER' \
    | envsubst '$TF_VAR_ENV_LOCAL_BACKEND_PORT' \
    | envsubst '$TF_VAR_ENV_APP_BACKEND_URL' \
    | envsubst '$TF_VAR_ENV_APP_DATABASE_HOST' \
    | envsubst '$TF_VAR_ENV_APP_DATABASE_NAME' \
    | envsubst '$TF_VAR_ENV_APP_DATABASE_USERNAME' \
    | envsubst '$TF_VAR_ENV_APP_DATABASE_PASSWORD' \
    | envsubst '$TF_VAR_ENV_APP_DATABASE_PORT' \
    | envsubst '$TF_VAR_ENV_APP_NAMESPACE' \
    | envsubst '$TF_VAR_ENV_APP_AWS_ACCOUNT_ID' \
    | envsubst '$TF_VAR_ENV_PHP_REPO_NAME' \
    | envsubst '$TF_VAR_ENV_PHP_REPO_TAG_APACHE' \
    | envsubst '$TF_VAR_ENV_APP_AWS_REGION' \
    | envsubst '$TF_VAR_ENV_PUSHER_APP_KEY' \
    | envsubst '$TF_VAR_ENV_PUSHER_HOST' \
    | envsubst '$TF_VAR_ENV_PUSHER_PORT' \
    | envsubst '$TF_VAR_ENV_PUSHER_SCHEME' \
    | envsubst '$TF_VAR_ENV_PUSHER_APP_CLUSTER' \
    | envsubst '$TF_VAR_ENV_SCRIPT_MODE' \
    | envsubst '$TF_VAR_ENV_APP_BACKEND_EKS_CLUSTER_NAME' \
    | envsubst '$TF_VAR_ENV_APP_BACKEND_DOMAIN_NAME' \
    | envsubst '$TF_VAR_ENV_APP_BACKEND_SSL_CERT_ARN' \
    | envsubst '$TF_VAR_ENV_JDK_REPO_NAME' \
    | envsubst '$TF_VAR_ENV_JDK_REPO_TAG' > $p_destination
}


appenvsubstr devops/appspec.yml.template appspec.yml
appenvsubstr devops/appspec.sh.template devops/appspec.sh
chmod 777 devops/appspec.sh

if [ "$TF_VAR_ENV_SCRIPT_MODE" == "CLOUDOCKER" ] 
then

    appenvsubstr devops/Dockerfile.template Dockerfile
    appenvsubstr devops/docker-compose.yml.template docker-compose.yml

elif [ "$TF_VAR_ENV_SCRIPT_MODE" == "CLOUDEKS" ] 
then
    echo "Generating Dockerfile..."
    appenvsubstr devops/Dockerfile.template Dockerfile

    echo "Generating app-kubernetes.yaml..."
    appenvsubstr devops/app-kubernetes.yaml.template app-kubernetes.yaml

    echo "Generating app-service.yaml..."
    appenvsubstr devops/app-service.yaml.template app-service.yaml

    echo "Login into ecr..."
    aws ecr get-login-password --region $TF_VAR_ENV_APP_AWS_REGION | docker login --username AWS --password-stdin $TF_VAR_ENV_APP_AWS_ACCOUNT_ID.dkr.ecr.$TF_VAR_ENV_APP_AWS_REGION.amazonaws.com

    echo "Building the Docker image..."
    docker build -t $TF_VAR_ENV_APP_NAME:$TF_VAR_ENV_APP_BACKEND_NAMESPACE'_'$TF_VAR_ENV_APP_NAME .

    echo "Create $TF_VAR_ENV_APP_NAME repository..."
    aws ecr describe-repositories --repository-names $TF_VAR_ENV_APP_NAME || aws ecr create-repository --repository-name $TF_VAR_ENV_APP_NAME

    echo "Tag your image with the Amazon ECR registry..."
    docker tag $TF_VAR_ENV_APP_NAME:$TF_VAR_ENV_APP_BACKEND_NAMESPACE'_'$TF_VAR_ENV_APP_NAME $TF_VAR_ENV_APP_AWS_ACCOUNT_ID.dkr.ecr.$TF_VAR_ENV_APP_AWS_REGION.amazonaws.com/$TF_VAR_ENV_APP_NAME:$TF_VAR_ENV_APP_BACKEND_NAMESPACE'_'$TF_VAR_ENV_APP_NAME

    echo "Push the image to ecr..."
    docker push $TF_VAR_ENV_APP_AWS_ACCOUNT_ID.dkr.ecr.$TF_VAR_ENV_APP_AWS_REGION.amazonaws.com/$TF_VAR_ENV_APP_NAME:$TF_VAR_ENV_APP_BACKEND_NAMESPACE'_'$TF_VAR_ENV_APP_NAME

    echo "Updating kubeconfig..."
    aws eks update-kubeconfig --region $TF_VAR_ENV_APP_AWS_REGION --name $TF_VAR_ENV_APP_BACKEND_EKS_CLUSTER_NAME
    
    cat app-kubernetes.yaml
    cat app-service.yaml

    echo "Trying kubectl apply -f app-kubernetes.yaml..."
    kubectl apply -f app-kubernetes.yaml -n ${TF_VAR_ENV_APP_BACKEND_KUBERNETES_NAMESPACE}
    
    echo "Trying kubectl apply -f app-service.yaml..."
    kubectl apply -f app-service.yaml -n ${TF_VAR_ENV_APP_BACKEND_KUBERNETES_NAMESPACE}

fi




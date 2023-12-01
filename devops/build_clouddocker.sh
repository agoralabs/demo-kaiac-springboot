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
    | envsubst '$TF_VAR_ENV_APP_GL_REPO_JDK_NAME' \
    | envsubst '$TF_VAR_ENV_APP_GL_REPO_JDK_TAG' \
    | envsubst '$TF_VAR_ENV_APP_GL_DOCKER_REPOSITORY' \
    | envsubst '$TF_VAR_ENV_APP_GL_AWS_REGION' \
    | envsubst '$TF_VAR_ENV_APP_GL_AWS_REGION_ECR' \
    | envsubst '$TF_VAR_ENV_APP_BE_DOMAIN_NAME' \
    | envsubst '$TF_VAR_ENV_APP_BE_URL' \
    | envsubst '$TF_VAR_ENV_APP_BE_LOCAL_PORT' \
    | envsubst '$TF_VAR_ENV_APP_BE_LOCAL_SOURCE_FOLDER' \
    | envsubst '$TF_VAR_ENV_APP_DB_HOST' \
    | envsubst '$TF_VAR_ENV_APP_DB_PORT' \
    | envsubst '$TF_VAR_ENV_APP_DB_NAME' \
    | envsubst '$TF_VAR_ENV_APP_DB_USERNAME' \
    | envsubst '$TF_VAR_ENV_APP_DB_PASSWORD' > $p_destination
}

appenvsubstr devops/appspec.yml.template appspec.yml
appenvsubstr devops/appspec_clouddocker.sh.template devops/appspec.sh
appenvsubstr devops/application.yml.template src/main/resources/application.yml
chmod 777 devops/appspec.sh

chmod +x ./mvnw
./mvnw clean install -DskipTests

appenvsubstr devops/Dockerfile.template Dockerfile
appenvsubstr devops/docker-compose.yml.template docker-compose.yml

THE_DATE_END=$(date '+%Y-%m-%d %H:%M:%S')
echo "Build ended on $THE_DATE_END"

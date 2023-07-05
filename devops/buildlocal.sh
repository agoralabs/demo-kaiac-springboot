#!/bin/bash

THE_DATE=$(date '+%Y-%m-%d %H:%M:%S')
echo "Build started on $THE_DATE"

source_folder=$TF_VAR_ENV_LOCAL_BACKEND_SOURCE_FOLDER

mkdir -p $source_folder/tmp
chmod 777 $source_folder/tmp

arraytpl=($(ls -d $source_folder/devops/*.template))

for template in "${arraytpl[@]}"
do
    pattern=${template%.template}
    generated=${pattern##*/}
    log_msg "generate $generated file..."
    pattern=${template%.template}
    appenvsubstr $template $source_folder/$generated
done

#For spring boot only
mvnw_file=$source_folder/mvnw
if [ -f "$mvnw_file" ]; then
    chmod +x $mvnw_file
    log_msg "Build spring boot app with $mvnw_file..."
    $mvnw_file clean install -DskipTests -f $source_folder/pom.xml
fi
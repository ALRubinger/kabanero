#!/usr/bin/env bash
set -e

APP_NAME=$1

echo "============== LOGGING INTO CLOUD FOUNDRY =============="
cf login -a=$IBM_CLOUD_API -s=$IBM_CLOUD_SPACE -o=$IBM_CLOUD_ORGANIZATION -u=$IBM_CLOUD_USER -p=$IBM_CLOUD_PASSWORD

# ==== VARIABLE SETUP ====
#APP=`echo $ROUTE | sed -e 's,\..*,,'`
echo "App name is $APP_NAME"

# ==== DEPLOYMENT ====
cf push $APP_NAME -f ./.travis/travis_manifest.yml
#!/usr/bin/env bash
set -e

#echo "============== INSTALLING CLOUD FOUNDRY CLI CLIENT =============="
## https://github.com/cloudfoundry/cli/releases
#wget --max-redirect=1 --output-document=cf_cli_6.35.0.tgz "https://cli.run.pivotal.io/stable?release=linux64-binary&version=6.35.0&source=github-rel"
#gunzip cf_cli_6.35.0.tgz
#tar -xvf cf_cli_6.35.0.tar

echo "============== LOGGING INTO CLOUD FOUNDRY =============="
cf login -a=$IBM_CLOUD_API -s=$IBM_CLOUD_SPACE -o=$IBM_CLOUD_ORGANIZATION -u=$IBM_CLOUD_USER -p=$IBM_CLOUD_PASSWORD

# ==== VARIABLE SETUP ====
#APP=`echo $ROUTE | sed -e 's,\..*,,'`
APP=code-conjuring
echo "App name is $APP"

# ==== DEPLOYMENT ====
cf push $APP -f ./.travis/travis_manifest.yml
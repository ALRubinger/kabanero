# deploy to IBM Kubernetes Service

CUR_DIR="$(cd $(dirname $0) && pwd)"
CLUSTER_NAME=$1
REGION=$2
NAMESPACE=$3
IMAGE_NAME=$4
IMAGE_TAG=$5
IBM_CLOUD_API_KEY=$6

ibmcloud login -a https://test.cloud.ibm.com -r "$REGION" --apikey "$IBM_CLOUD_API_KEY"
ibmcloud ks region-set "$REGION"

# Output from cluster-config gives us an export command to use to set KUBECONFIG
EXPORT_COMMAND=$(ibmcloud ks cluster-config "$CLUSTER_NAME" | sed -n 6p)
# EXPORT_COMMAND example output: export KUBECONFIG=/root/.bluemix/plugins/container-service/clusters/code-conjure/kube-config-dal09-code-conjure.yml

# get just the path from the EXPORT_COMMAND output to avoid using eval
EXPORT_PATH=${EXPORT_COMMAND#*=}
export KUBECONFIG="$EXPORT_PATH"

# change some templated variables in deploy file, such as the image to pull down
sed -i -e "s/@DOCKER_REGISTRY@/$DOCKER_REGISTRY/g" "$CUR_DIR"/kube-deploy.yml
sed -i -e "s/@NAMESPACE@/$NAMESPACE/g" "$CUR_DIR"/kube-deploy.yml
sed -i -e "s/@IMAGE_NAME@/$IMAGE_NAME/g" "$CUR_DIR"/kube-deploy.yml
sed -i -e "s/@IMAGE_TAG@/$IMAGE_TAG/g" "$CUR_DIR"/kube-deploy.yml

kubectl create secret docker-registry regcred --docker-server=$DOCKER_REGISTRY --docker-username=$DOCKER_REGISTRY_USER --docker-password=$DOCKER_REGISTRY_PASSWORD

kubectl apply -f "$CUR_DIR"/kube-deploy.yml

ibmcloud cs workers "$CLUSTER_NAME"
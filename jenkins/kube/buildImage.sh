#!/bin/bash -e

CUR_DIR="$(cd $(dirname $0) && pwd)"

ARTIFACTS_DIR=$1
DOCKER_IMAGE_TAG=$2
DOCKER_IMAGE_NAME=code-conjuring-prod
NAMESPACE=icp4a
GIT_REVISION=$(cd "$CUR_DIR" && git rev-parse HEAD)

CONTEXT_DIR=$CUR_DIR/dockercontext

mkdir "$CONTEXT_DIR"
trap "rm -rf $CONTEXT_DIR" EXIT

if [ -z "$ARTIFACTS_DIR" ]; then
    ARTIFACTS_DIR="$CUR_DIR/../.."
fi

if [ -z "$DOCKER_IMAGE_TAG" ]; then
    DOCKER_IMAGE_TAG=latest
fi

if [ "$USE_CACHE" = "false" ]; then
    DOCKER_OPTS="--no-cache"
fi

# Using CUR_DIR for the docker build context directory
cp $ARTIFACTS_DIR/target/openliberty.war $CONTEXT_DIR
cp $ARTIFACTS_DIR/src/main/wlp/server.xml $CONTEXT_DIR
cp $ARTIFACTS_DIR/LICENSE $CONTEXT_DIR

echo "Building image"
docker build $DOCKER_OPTS --pull --build-arg GIT_REVISION=$GIT_REVISION -t "$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG" "$CUR_DIR"

# publish to our registry
#if [ -n "$DOCKER_REGISTRY" ] && [ -n "$DOCKER_REGISTRY_USER" ] && [ -n "$DOCKER_REGISTRY_PASSWORD" ]; then
#    docker tag "$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG" "$DOCKER_REGISTRY/$NAMESPACE/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG"
#    echo "Connecting to $DOCKER_REGISTRY Docker registry as $DOCKER_REGISTRY_USER"
#    docker login "--username=$DOCKER_REGISTRY_USER" "--password=$DOCKER_REGISTRY_PASSWORD" "$DOCKER_REGISTRY"
#    echo "Pushing $DOCKER_REGISTRY/$NAMESPACE/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG image to Docker registry"
#    docker push "$DOCKER_REGISTRY/$NAMESPACE/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG"
#fi
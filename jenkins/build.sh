#  Modified version of build_jekyll_maven.sh

# Exit immediately if a simple command exits with a non-zero status.
set -e
CUR_DIR="$(cd $(dirname $0) && pwd)"
JEKYLL_BUILD_FLAGS=""

echo "Ruby version:"
ruby -v

# Guides that are ready to be published to the Code Conjuring site
#echo "Cloning repositories with name starting with guide or iguide..."
#ruby ./scripts/build_clone_guides.rb

# Development environment only actions
if [ "$JEKYLL_ENV" != "production" ]; then 
    echo "Not in production environment..."
    echo "Adding robots.txt"
    cp "$CUR_DIR"/../robots.txt src/main/content/robots.txt

    # Development environments with draft docs/guides
    if [ "$JEKYLL_DRAFT_GUIDES" == "true" ]; then
        echo "Clone draft guides for test environments..."
        #ruby "$CUR_DIR"/../scripts/build_clone_guides.rb "draft-guide"    

        # Need to make sure there are draft-iguide* folders before using the find command
        # If we don't, the find command will fail because the path does not exist
        if [ $(find src/main/content/guides -type d -name "draft-iguide*" | wc -l ) != "0" ] ; then
            echo "Moving any js and css files from draft interactive guides..."
            find "$CUR_DIR"/../src/main/content/guides/draft-iguide* -d -name js -exec cp -R '{}' src/main/content/_assets \;
            find "$CUR_DIR"/../src/main/content/guides/draft-iguide* -d -name css -exec cp -R '{}' src/main/content/_assets \;
        fi
        #"$CUR_DIR"/../scripts/build_clone_docs.sh "draft" # Argument is branch name of ICP4APPs/docs
    else
        echo "not cloning draft guides"
    fi
else
    # Production!
    echo "Clone published docs!"
    #"$CUR_DIR"/../scripts/build_clone_docs.sh "master" # Argument is branch name of ICP4APPs/docs
fi

# Development environments that enable the draft blogs in the _draft directory.
if [ "$JEKYLL_DRAFT_BLOGS" == "true" ]; then
    # Include draft blog posts for non production environments
    JEKYLL_BUILD_FLAGS="--drafts"
fi

# Special handling for javadocs
"$CUR_DIR"/../scripts/modify_javadoc.sh

echo "Copying guide images to /img/guide"
mkdir -p "$CUR_DIR"/../src/main/content/img/guide
# Check if any draft guide images exist first
if [ -e "$CUR_DIR"/../src/main/content/guides/draft-guide*/assets/* ]
 then cp "$CUR_DIR"/../src/main/content/guides/draft-guide*/assets/* src/main/content/img/guide/
fi
# Check if any published guide images exist first
if [ -e "$CUR_DIR"/../src/main/content/guides/guide*/assets/* ]
 then cp "$CUR_DIR"/../src/main/content/guides/guide*/assets/* src/main/content/img/guide/
fi

# Move any js/css files from guides to the _assets folder for jekyll-assets minification.
echo "Moving any js and css files published interactive guides..."
# Assumption: There is _always_ iguide* folders
#find "$CUR_DIR"/../src/main/content/guides/iguide* -d -name js -exec cp -R '{}' src/main/content/_assets \;
#find "$CUR_DIR"/../src/main/content/guides/iguide* -d -name css -exec cp -R '{}' src/main/content/_assets \;

# Build draft and published blogs
"$CUR_DIR"/../scripts/build_clone_blogs.sh

# Jekyll build
echo "Building with jekyll..."
echo `jekyll -version`
mkdir -p "$CUR_DIR"/../target/jekyll-webapp
jekyll build $JEKYLL_BUILD_FLAGS --source "$CUR_DIR"/../src/main/content --destination "$CUR_DIR"/../target/jekyll-webapp

#python3 "$CUR_DIR"/../scripts/parse-feature-toc.py

# Maven packaging
echo "Running maven (mvn)..."
mvn -B package

# Fix permissions since the workspace will be run as a mounted volume in a docker container (root user) in Jenkins.
# Without this, the Jenkins user on the slave won't be able to read the target/wlp/usr dir
chmod -R 755 "$CUR_DIR"/../target/liberty/wlp/usr
pushd src/main/content

# For development purposes, lets always delete previously created folders
# so that you can run this script to refresh your blog files
rm -rf _posts _drafts
rm -rf img/blog

echo "Start cloning blogs repository..."

git clone git@github.ibm.com:ICP4APPs/blogs.git --branch master blogs_temp

mv blogs_temp/drafts/ .
mv drafts/ _drafts
mv blogs_temp/publish/ .
mv publish/ _posts

mv blogs_temp/img/blog img

rm -rf blogs_temp
popd
echo "Done cloning blogs repository!"🚫

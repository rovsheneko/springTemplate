arr=($(docker ps | cut -d ' ' -f1 | sed 's#\n# #g'))
for id in "${arr[@]}"
do
    echo "docker kill $id"
    docker kill $id
done

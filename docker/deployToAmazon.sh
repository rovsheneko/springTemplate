command="$(aws ecr get-login --no-include-email --region us-east-1)"
`$command`
docker tag art-app-image-v1:latest 827782363643.dkr.ecr.us-east-1.amazonaws.com/rov:latest 
docker push 827782363643.dkr.ecr.us-east-1.amazonaws.com/rov:latest


LAST_CONT_ID=$(docker ps -l -q)
docker inspect $LAST_CONT_ID | grep -e "IPAddress"

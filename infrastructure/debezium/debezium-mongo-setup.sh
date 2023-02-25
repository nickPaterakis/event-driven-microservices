# Start a new bash process in running container
docker exec -it mongo mongosh

# Initialize MongoDB replica set
rs.initiate({_id: "debezium", members:[{_id: 0, host: "localhost:27017"}]})

# Create a user profile
use admin
db.createUser({user: "admin", pwd: "admin", roles: ["dbOwner"]})
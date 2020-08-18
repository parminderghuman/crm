package com.parminder.crm.bo;

import java.util.Set;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.Data;

@MongoEntity(collection = "users")
@Data
public class User extends Generic {

	

	String name;

	String username;

	String password;

	String role;

	Set<String> roles;

}

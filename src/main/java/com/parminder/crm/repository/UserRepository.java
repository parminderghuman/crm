package com.parminder.crm.repository;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import com.parminder.crm.bo.User;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class UserRepository implements ReactivePanacheMongoRepositoryBase<User,Integer>{
	
	public Uni<User> findById(String id) {
		return find("_id",id).firstResult();
	}
	
	public Uni<User> findByUserName(String  username) {
		return find("username",username).firstResult();
	}
	
	public User addUser(User user) {
		
		 persist(user).subscribe().asCompletionStage();
		 
		 return user;
	}

}

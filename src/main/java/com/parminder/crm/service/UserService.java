package com.parminder.crm.service;

import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.reactivestreams.client.MongoClient;
import com.parminder.crm.bo.User;
import com.parminder.crm.repository.UserRepository;

import org.bson.Document;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class UserService {
	
	@Inject UserRepository userRepository;
	
	public User getUserById(Long id){
		return null;
	}

	public User saveUser(User user) {
		return userRepository.addUser(user);
	}

	public User getUserByUserName(String userName) throws InterruptedException, ExecutionException {
		return userRepository.findByUserName(userName).subscribeAsCompletionStage().get();
		
	}
}

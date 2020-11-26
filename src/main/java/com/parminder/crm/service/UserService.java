package com.parminder.crm.service;

import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.reactivestreams.client.MongoClient;
import com.parminder.crm.bo.User;
import com.parminder.crm.repository.UserRepository;

import org.bson.Document;
import org.bson.types.ObjectId;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class UserService {
	
	@Inject UserRepository userRepository;
	


	public User saveUser(User user) {
		return userRepository.addUser(user);
	}

	public User getUserByUserName(String userName) throws InterruptedException, ExecutionException {
		return userRepository.findByUserName(userName).subscribeAsCompletionStage().get();
		
	}

	public User findById(String id) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		//userRepository.findByIdOptional(id);
		return userRepository.findByIdOptional(new ObjectId(id)).subscribeAsCompletionStage().get().get();
	}

	public Long count() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return userRepository.count().subscribeAsCompletionStage().get();
	}
}

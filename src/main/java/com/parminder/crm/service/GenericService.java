package com.parminder.crm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.InsertOneResult;
import com.parminder.crm.bo.PaginationResult;

import io.quarkus.mongodb.panache.reactive.runtime.ReactiveMongoOperations;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.quarkus.mongodb.reactive.ReactiveMongoDatabase;

@ApplicationScoped
public class GenericService {

	@Inject
	ReactiveMongoClient reactiveMongocl;

	private ReactiveMongoCollection getCollection(Class c, String tableName) {
		return reactiveMongocl.getDatabase("crm").getCollection(tableName, c);
	}

	public Object save(String tableName, HashMap object) throws InterruptedException, ExecutionException {
		ReactiveMongoCollection collection = getCollection(object.getClass(), tableName);// reactiveMongocl.getDatabase("crm").getCollection(tablename,object.getClass());
		if(object.get("_id") != null) {
			String id =object.get("_id")+"" ;
			object.put("_id",new ObjectId(id));
			 collection.replaceOne(new Document("_id", new ObjectId(id) ), object).subscribe().asCompletionStage().get();
			return collection.find(new Document("_id", new ObjectId(id))).subscribe().asStream().findFirst().get();

		}
		else{
			Object insertedId = ((InsertOneResult) collection.insertOne(object).subscribe().asCompletionStage().get())
		
				.getInsertedId();
		
		return collection.find(new Document("_id", insertedId)).subscribe().asStream().findFirst().get();
		}
	}

	public Object findByID(String tableName, String objectId) throws InterruptedException, ExecutionException {
		ReactiveMongoCollection collection = getCollection(HashMap.class, tableName);
		ReactiveMongoOperations reactiveMongoOperations = new ReactiveMongoOperations();
		return collection.find(new Document("_id", new ObjectId(objectId))).subscribe().asStream().findFirst().get();
	}

	public Object findAll(String tableName, int page, int pageSize) {
		ReactiveMongoCollection collection = getCollection(HashMap.class, tableName);
		long total = collection.find().subscribe().asStream().count();
		Object[] object = collection.find().subscribe().asStream().skip(page * pageSize).limit(pageSize).toArray();
		List result = Arrays.asList(object);
		return new PaginationResult<Object>(total, (int) (total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1), page,
				pageSize, result);

	}
}

package com.parminder.crm.bo;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.Data;


@Data
public class Generic extends ReactivePanacheMongoEntityBase {

	public Generic() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	@BsonId
	ObjectId id;
	Date createdAt;
	Date updatedAt;

}

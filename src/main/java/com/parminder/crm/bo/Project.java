package com.parminder.crm.bo;

import java.util.List;

import org.bson.types.ObjectId;

import com.parminder.crm.bo.Table.TableStatus;

import io.quarkus.mongodb.panache.MongoEntity;
import lombok.Data;

@MongoEntity(collection = "_System_projects")
@Data
public class Project extends Generic{
	
	String name;
	
	String desc;
	

}

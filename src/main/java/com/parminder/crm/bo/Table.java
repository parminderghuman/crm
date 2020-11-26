package com.parminder.crm.bo;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.MongoEntity;
import lombok.Data;

@MongoEntity(collection = "_System_tables")
@Data
public class Table extends Generic{

	
	public enum TableStatus { Live,Draft}
	
	String uniqueId;
	
	String tableName;

	String name;
	
	String parentTable;
	
	TableStatus tableStatus;
	
	ObjectId parentId;
	
	List<Column> columns;
	
	String beforeSaveFunction; 
	
	List<Constraint> constraints;
	
	String primaryDisplay;
	
	String secondaryDisplay;
	
	List<String> displayColumns;
	
	String matIcon;
	
}

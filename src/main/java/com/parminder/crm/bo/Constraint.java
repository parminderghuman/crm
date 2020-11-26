package com.parminder.crm.bo;

import java.util.List;

import lombok.Data;

@Data
public class Constraint {
	public enum ConstraintType{
		Unique, primaryKey
	}
	
	String name;
	
	ConstraintType constraintType;
	
	List<String> columns;
}

package com.parminder.crm.bo;

import java.util.Map;

import lombok.Data;

@Data
public class Column extends Generic{
	
	public enum ColumnType{
		String,Text,Number,Password,MultiSelect,Select,Boolean,Refrence,MultiRefrence,Address,Date,Time, DateTime,File,Section
	}
	public enum ColumnStatus{
	     Live,Draft	
	}
	String hiddenName;
	
	String name;
	
	ColumnType columnType;
	
	String targetClass;
	
	String options;
	
	String defaultValue;
	
	String maxValue;
	
	String minValue;
	
	String validationRegex;
	
	String validateFunction;
	
	Boolean isRequired;
	
	Boolean isNull;
	
	ColumnStatus columnStatus;
	
	Integer maxLength;
	
	Integer minLenght;
		
	String errorMessage;
	
	Boolean isSaveInTable;
	Boolean isCreateInLine;
	
}

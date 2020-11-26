package com.parminder.crm.bo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationResult <T>{

	
	Long totalCount;
	Integer pageCount;
	int currentpage;
	int pageSize;
	List<T> results;
}

package com.parminder.crm.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.parminder.crm.bo.PaginationResult;
import com.parminder.crm.bo.Table;
import com.parminder.crm.repository.TableRepository;

@ApplicationScoped
public class TableService {

	@Inject
	TableRepository tableRepository;

	public Table savetable(Table table) {
			tableRepository.persistOrUpdate(table).subscribe().asCompletionStage();		
		return table;
	}
	
	
	public PaginationResult<Table> findAll(int pageIndex,int pageSize) throws InterruptedException, ExecutionException {
		 Long totalCount = tableRepository.findAll().page(pageIndex, pageSize).count().subscribeAsCompletionStage().get();
		Integer pageCount =  tableRepository.findAll().page(pageIndex, pageSize).pageCount().subscribeAsCompletionStage().get();
		List<Table> tables =  tableRepository.findAll().page(pageIndex, pageSize).list().subscribeAsCompletionStage().get();
		return new PaginationResult<Table>(totalCount,pageCount,pageIndex,pageSize,tables);
	}
	public PaginationResult<Table> findAllByParentId(String parentId,int pageIndex,int pageSize) throws InterruptedException, ExecutionException {
		 Long totalCount = tableRepository.find(new Document().append("parentTable", parentId)).page(pageIndex, pageSize).count().subscribeAsCompletionStage().get();
		Integer pageCount =  tableRepository.find(new Document().append("parentTable",parentId)).page(pageIndex, pageSize).pageCount().subscribeAsCompletionStage().get();
		List<Table> tables =  tableRepository.find(new Document().append("parentTable", parentId)).page(pageIndex, pageSize).list().subscribeAsCompletionStage().get();
		return new PaginationResult<Table>(totalCount,pageCount,pageIndex,pageSize,tables);
	}
	
	public Table findById(String id) throws InterruptedException, ExecutionException {
		return tableRepository.findByIdOptional(new ObjectId(id)).subscribeAsCompletionStage().get().get();
	}

	public Long count() throws InterruptedException, ExecutionException {
		return tableRepository.count().subscribeAsCompletionStage().get();
	}
}

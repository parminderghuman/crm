package com.parminder.crm.repository;

import javax.enterprise.context.ApplicationScoped;

import com.parminder.crm.bo.Project;
import com.parminder.crm.bo.Table;
import com.parminder.crm.bo.User;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;

@ApplicationScoped
public class ProjectRepository  implements ReactivePanacheMongoRepositoryBase<Project,Integer>{
	

}

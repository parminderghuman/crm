package com.parminder.crm.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.types.ObjectId;

import com.parminder.crm.bo.Project;
import com.parminder.crm.bo.Table;
import com.parminder.crm.repository.ProjectRepository;
import com.parminder.crm.repository.TableRepository;

@ApplicationScoped
public class ProjectService {

	@Inject
	ProjectRepository projectRepository;

	
	public Project saveProject(Project project) {
		
		this.projectRepository.persist(project).subscribe().asCompletionStage();
		return project;
		
	}
}

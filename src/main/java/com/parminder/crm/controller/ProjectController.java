package com.parminder.crm.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.parminder.crm.bo.Project;
import com.parminder.crm.bo.Table;
import com.parminder.crm.service.ProjectService;

@Path("project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectController {

	
	@Inject
	ProjectService projectService;
	
	@POST
	@Path("/")
	public Response saveTable(Project project) {

		project = projectService.saveProject(project);

		return Response.ok(project).build();
	}
}

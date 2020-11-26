package com.parminder.crm.controller;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.parminder.crm.bo.Project;
import com.parminder.crm.bo.Table;
import com.parminder.crm.service.GenericService;
import com.parminder.crm.service.TableService;

@Path("entity")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenricController {
	@Inject
	TableService tableService;
	@Inject
	GenericService genericService;

	@POST
	@Path("/{entity}")
	public Response saveTable(@org.jboss.resteasy.annotations.jaxrs.PathParam String entity,
			HashMap object) throws InterruptedException, ExecutionException {
		Table table = tableService.findById(entity);
		Object res = genericService.save(table.getTableName(), object);
		return Response.ok(res).build();
	}
	@GET
	@Path("/{entity}/{id}")
	public Response findTable(@org.jboss.resteasy.annotations.jaxrs.PathParam String entity,
			@org.jboss.resteasy.annotations.jaxrs.PathParam  String id) throws InterruptedException, ExecutionException {
		Table table = tableService.findById(entity);
		Object object = genericService.findByID(table.getTableName(), id);
		return Response.ok(object).build();
	}
	
	@GET
	@Path("/{entity}")
	public Response listTable(@org.jboss.resteasy.annotations.jaxrs.PathParam String entity,@QueryParam(value = "page") int page,
			@QueryParam(value = "pageSize")int pageSize) throws InterruptedException, ExecutionException {
		Table table = tableService.findById(entity);
		Object object = genericService.findAll(table.getTableName(),page,pageSize);
		return Response.ok(object).build();
	}
}

	package com.parminder.crm.controller;

import java.security.Principal;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.parminder.crm.bo.Table;
import com.parminder.crm.bo.User;
import com.parminder.crm.service.TableService;
import com.parminder.crm.service.UserService;

@Path("table")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TableController {

	@Inject
	TableService tableService;

	@Inject
	UserService userService;
	
	@POST
	@Path("/")
	public Response saveTable(Table table) {

		table = tableService.savetable(table);

		return Response.ok(table).build();
	}

	@GET
	@Path("/{id}")
	public Response getTable(@org.jboss.resteasy.annotations.jaxrs.PathParam String id) {

		Table table;
		try {
			table = tableService.findById(id);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}

		return Response.ok(table).build();

	}

	@GET
	@Path("/")
	public Response getTable(@QueryParam(value = "page") int page,
			@QueryParam(value = "pageSize")int pageSize) {
		try {
			return Response.ok(tableService.findAll(page,pageSize)).build();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@GET
	@Path("/")
	public Response getTable(@Context SecurityContext ctx,@QueryParam(value = "page") int page,
			@QueryParam(value = "pageSize")int pageSize) {
		try {
			  Principal caller =  ctx.getUserPrincipal();
		        String name = caller == null ? "anonymous" : caller.getName();
		        String helloReply = String.format("hello + %s, isSecure: %s, authScheme: %s", name, ctx.isSecure(), ctx.getAuthenticationScheme());
				
		        System.out.println(helloReply);
			return Response.ok(tableService.findAll(page,pageSize)).build();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@GET
	@Path("/user")
	public Response getUserTables(@Context SecurityContext ctx,@QueryParam(value = "page") int page,
			@QueryParam(value = "pageSize")int pageSize) {
		try {
	        if(ctx.isUserInRole("ADMIN")) {
	        	return Response.ok(tableService.findAll(page,pageSize)).build();
	        }
	        return Response.ok(tableService.findAll(page,pageSize)).build();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}

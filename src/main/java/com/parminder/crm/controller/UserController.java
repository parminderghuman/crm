package com.parminder.crm.controller;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.parminder.crm.bo.User;
import com.parminder.crm.dto.AuthRequest;
import com.parminder.crm.dto.AuthResponse;
import com.parminder.crm.service.UserService;
import com.parminder.crm.service.utils.PBKDF2Encoder;
import com.parminder.crm.service.utils.TokenUtils;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	@Inject
	UserService userService;

	@Inject
	PBKDF2Encoder pBKDF2Encoder;

	@ConfigProperty(name = "com.parminder.quarkusjwt.jwt.duration")
	public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;
	long count = 0;

	@GET
	@Path("/admin/count")
	public Response checkAdminUser() {
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		try {
			if (count > 0) {
				result.put("isAdmin", true);

			} else {

				count = userService.count();
				if (count > 0) {
					result.put("isAdmin", true);

				} else {
					result.put("isAdmin", false);
				}
			}

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(result).build();
	}

	@POST
	public Response saveUser(User user) {
		try {
			User dbUser = userService.getUserByUserName(user.getUsername());
			if (dbUser != null) {
				return Response.status(Status.CONFLICT.getStatusCode(), "User Already Exists").build();
			}
			user.setPassword(pBKDF2Encoder.encode(user.getPassword()));
			user = userService.saveUser(user);

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(user).build();
	}

	@GET
	@Path("/{id}")
	@RolesAllowed({ "User", "Admin" })

	public Response get(@org.jboss.resteasy.annotations.jaxrs.PathParam String id) {
		User user;
		try {
			user = userService.findById(id);
		} catch (InterruptedException | ExecutionException e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		return Response.ok(user).build();
	}

	@PermitAll
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response login(AuthRequest authrequest) {
		try {
			User user;

			user = userService.getUserByUserName(authrequest.getUsername());

			if (user != null && user.getPassword().equals(pBKDF2Encoder.encode(authrequest.getPassword()))) {
				try {
					return Response
							.ok(new AuthResponse(
									TokenUtils.generateToken(user.getUsername(), user.getRoles(), duration, issuer)))
							.build();

				} catch (Exception e) {
					return Response.status(Status.UNAUTHORIZED).build();

				}
			} else {
				return Response.status(Status.UNAUTHORIZED).entity("Invalid Credentials!").build();

			}
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			return Response.status(Status.UNAUTHORIZED).build();

		}
	}
}

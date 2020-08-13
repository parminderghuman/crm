package com.parminder.crm.controller;

import java.util.concurrent.ExecutionException;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
public class UserController {

	@Inject
	UserService userService;

	@Inject
	PBKDF2Encoder pBKDF2Encoder;

	@ConfigProperty(name = "com.parminder.quarkusjwt.jwt.duration")
	public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;

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

	@PermitAll
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response login(AuthRequest authrequest) {
		try {
			User user;

			user = userService.getUserByUserName(authrequest.getUsername());

			if (user != null || user.getPassword().equals(pBKDF2Encoder.encode(authrequest.getPassword()))) {
				try {
					return Response
							.ok(new AuthResponse(
									TokenUtils.generateToken(user.getUsername(), user.getRoles(), duration, issuer)))
							.build();

				} catch (Exception e) {
					return Response.status(Status.UNAUTHORIZED).build();

				}
			} else {
				return Response.status(Status.UNAUTHORIZED).build();

			}
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			return Response.status(Status.UNAUTHORIZED).build();

		}
	}
}

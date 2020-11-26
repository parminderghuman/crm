package com.parminder.crm;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) {
//    	System.out.println(context.getUriInfo().getPath());
//    	System.out.println(context.getMethod());
//    	System.out.println(context.getSecurityContext().getUserPrincipal());

        if ("/secret".equals(context.getUriInfo().getPath())) {
            context.abortWith(Response.accepted("forbidden!").build());
        }
    }
}
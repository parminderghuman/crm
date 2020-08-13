package com.parminder.crm;

import io.quarkus.hibernate.orm.runtime.RequestScopedEntityManagerHolder;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

import java.util.function.Supplier;

//@ApplicationScoped
public class RolesAugmentor 
//implements SecurityIdentityAugmentor
{
//
//    @Override
//    public int priority() {
//        return 0;
//    }
//
//    @Override
//    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
//        return context.runBlocking(build(identity));
//    }
//
//    private Supplier<SecurityIdentity> build(SecurityIdentity identity) {
//    	System.out.println("XYZ");
//    	
//        if(identity.isAnonymous()) {
//            return () -> identity;
//        } else {
//            // create a new builder and copy principal, attributes, credentials and roles from the original
//            QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder()
//                    .setPrincipal(identity.getPrincipal())
//                    .addAttributes(identity.getAttributes())
//                    .addCredentials(identity.getCredentials())
//                    .addRoles(identity.getRoles());
//
//            // add custom role source here
//            builder.addRole("dummy");
//            return builder::build;
//        }
//    }
}
package com.flipkart.rest;

import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserOperation;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class CRSApplicationRestController {
    @GET
    @Path("/{id}/{password}")
    public Response login(@PathParam ("id") String userid, @PathParam ("password") String password) {
        String loggedin;
        try {
            loggedin = String.valueOf(userInterface.verifyCredentials(userid, password));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            loggedin = e.getMessage();
        }
        return Response.ok(loggedin+ "--------" + userid + "-----" + password).build();
    }
    UserInterface userInterface = UserOperation.getInstance();
}
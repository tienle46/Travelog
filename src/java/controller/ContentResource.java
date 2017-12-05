/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URISyntaxException;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import model.Users;

/**
 * REST Web Service
 *
 * @author macbook
 */
@Path("Content")
@Stateful
@SessionScoped
public class ContentResource {
    
    @Context private HttpServletRequest request;
    
    @EJB
    DBManager dm;
    
    private boolean status;
    
    public ContentResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo() {
        model.Users u = null;
        int id;
        String name;
        if (request.getSession() != null) {
            status = true;
            u = (Users) request.getSession().getAttribute("user");
            id = u.getId();
            name = u.getUsername();
        } else {
            status = false;
            name ="";
            id = 0;
            String out = Json.createObjectBuilder()
            .add("status", status)
            .build()
            .toString();
            return Response.status(Response.Status.FORBIDDEN).entity(out).build();
        }
        String out = Json.createObjectBuilder()
            .add("id",id)
            .add("status", status)
            .add("user", name)
            .build()
            .toString();
            //return out;
            return Response.status(Response.Status.ACCEPTED).entity(out).build();
    }
    
    @POST
        @Path("signup")
	public Response signup(@FormParam("signup_uname") String user,@FormParam("signup_psw") String password,@FormParam("signup_email") String email) throws URISyntaxException {
        
            if (dm.getUserByName(user) == null) {
                
                Users u = dm.createUser(user, password, email);
                java.net.URI location = new java.net.URI("../index.html");
                return Response.temporaryRedirect(location).build();
                
            } else {
                return null;
            }
        }
    
    @POST
        @Path("login")
	public Response login(@FormParam("uname") String username,@FormParam("psw") String password) throws URISyntaxException {
            
                model.Users user = dm.getUserByName(username);
                
                    if ( user!= null && password.equals(user.getPw())) {
                        
                        java.net.URI location = new java.net.URI("../index.html?id=" + user.getId().toString());
                        return Response.temporaryRedirect(location).build();
                    
                    } else {
                        return null;
                    }
                }
        
}

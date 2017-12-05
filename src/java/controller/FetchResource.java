/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.Posts;
import model.Users;

/**
 * REST Web Service
 *
 * @author macbook
 */
@Path("Fetch")
public class FetchResource {
    @EJB
    private DBManager dm;
    
    @GET
    @Path("postData")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Posts> getPost() {
        
        return dm.getAllPosts();
    }
    
    @GET
    @Path("userData")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getUser() {
        
        return dm.getAllUser();
    }
    
}

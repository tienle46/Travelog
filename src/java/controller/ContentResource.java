/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Users;
import java.net.URISyntaxException;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author macbook
 */
@Path("Content")
@Stateful
public class ContentResource {

    @EJB
    private DBManager dm;
    
    
    public ContentResource(){
        
    }
    @POST
        @Path("Signup")
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
        @Path("Login")
	public Response login(@FormParam("uname") String username,@FormParam("psw") String password) throws URISyntaxException {
            
                Model.Users user = dm.getUserByName(username);
                
                    if ( user!= null && password.equals(user.getPw())) {
                        
                        java.net.URI location = new java.net.URI("../index.html?id=" + user.getUserId().toString());
                        return Response.temporaryRedirect(location).build();
                    
                    } else {
                        return null;
                    }
                }
        
        
    @POST
    @Path("Comment")
        public Response comment(@FormParam("userId") Integer userid, @FormParam("postId") Integer postid, @FormParam("comment") String comment) throws URISyntaxException {
            Model.Users user = dm.getUserById(userid);
            Model.Posts post = dm.getPostByID(postid);
            dm.insertComment(comment, user, post);
            java.net.URI location = new java.net.URI("../view.html");
            return Response.temporaryRedirect(location).build();
    }
        
    @POST
    @Path("Like")
    public Response like(@FormParam("userId1") Integer userid, @FormParam("postId1") Integer postid) throws URISyntaxException {
        Model.Users user = dm.getUserById(userid);
        Model.Posts post = dm.getPostByID(postid);
        
        if (dm.checkLike(user, post) == false) {
            dm.insertLike(user, post);
        }
        java.net.URI location = new java.net.URI("../view.html");
            return Response.temporaryRedirect(location).build();
    }
}

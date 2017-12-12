/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Comments;
import Model.Posts;
import Model.Tags;
import Model.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author macbook
 */
@Path("Fetch")
@Stateful
public class FetchResource {
    
    @EJB
    private DBManager dm;

    
    public FetchResource() {
    }
    
    
    //Create Json-format String file since cannot generate a true Json file.
    private String JSONBuilder(List<JsonObjectBuilder> objs) {
        StringBuilder s = new StringBuilder("[");
        String pre = "";
        for (JsonObjectBuilder o : objs) {
            s.append(pre);
            pre = ",";
            s.append(o.build().toString());
        }
        s.append("]");
        return s.toString();
    }

    
    //Get all users
    @GET
    @Path("AllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser() {
        JsonObjectBuilder out;
        List<JsonObjectBuilder> list = new ArrayList<>();
        for (Users u : dm.getAllUser()) {
       
            out= Json.createObjectBuilder()
                .add("id", u.getUserId())
                .add("email", u.getEmail())
                .add("username", u.getUsername());
            list.add(out);
        }
        return JSONBuilder(list);
    }
    
    //Get all posts
    @GET
    @Path("AllPost")
    @Produces(MediaType.APPLICATION_JSON)
    public String allPost() {
        JsonObjectBuilder out;
        List<JsonObjectBuilder> list = new ArrayList<>();
        for (Posts t:dm.getAllPosts()) {
       
            out= Json.createObjectBuilder()
                .add("id", t.getPostId())
                .add("path", t.getPath())
                .add("title", t.getTitle())
                .add("description", t.getDescription())
                .add("date", t.getDate().toString())
                .add("owner", t.getOwner().getUsername())
                .add("tag", t.getTag().getTagname());
            list.add(out);
        }
        
        return JSONBuilder(list);
    }
    
    //
    @GET
    @Path("Comment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String commentsByMediaId(@PathParam("id") int i) {
        List<Comments> object = dm.getCommentForPost(dm.getPostByID(i));

        
        JsonObjectBuilder out;
        List<JsonObjectBuilder> list = new ArrayList<>();
        for (Comments c: object) {
            
            out= Json.createObjectBuilder()
                .add("id",c.getCommentId())
                .add("comment", c.getComment())
                .add("user", c.getOwner().getUsername())
                .add("post",c.getPost().getPostId());
            list.add(out);

        }
        
        return JSONBuilder(list);
    }
    
    @GET
    @Path("Like/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNumberOfLike(@PathParam("id") int i) {
       JsonObjectBuilder out = Json.createObjectBuilder()
               .add("likenumber", dm.getNumberOfLikeForPost(dm.getPostByID(i)));
        List<JsonObjectBuilder> list = new ArrayList<>();
        list.add(out);
       return JSONBuilder(list);
    }
    
    @GET
    @Path("TagByPost/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTagByPost(@PathParam("id") int i) {
        List<JsonObjectBuilder> list = new ArrayList<>();
        List<Tags> object = dm.getTagByPost(dm.getPostByID(i));
        JsonObjectBuilder out;
        for (Tags p : object) {
            out = Json.createObjectBuilder()
                    .add("Tagname", p.getTagname());
            list.add(out);
        }
        return JSONBuilder(list);
          
    }
    
    
    @GET
    @Path("PostByTag/{tag1}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPostByTag(@PathParam("tag1") String tag) {
        List<JsonObjectBuilder> list = new ArrayList<>();
        List<Posts> object = dm.getPostByTag(dm.getTagByName(tag));
        JsonObjectBuilder out;
        for (Posts p : object) {
            out = Json.createObjectBuilder()
                    .add("id", p.getPostId())
                    .add("path", p.getPath())
                    .add("title", p.getTitle())
                    .add("description", p.getDescription())
                    .add("owner", p.getOwner().getUsername())
                    .add("tag", p.getTag().getTagname());
            list.add(out);
        }
        return JSONBuilder(list);
    }
    
    @GET
    @Path("PostByUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPostByUser(@PathParam("id") int i) {
    List<JsonObjectBuilder> list = new ArrayList<>();
    List<Posts> object = dm.getPostByUser(dm.getUserById(i));
    JsonObjectBuilder out;
    for (Posts p : object) {
        out = Json.createObjectBuilder()
                .add("id", p.getPostId())
                .add("path", p.getPath())
                .add("title", p.getTitle())
                .add("description", p.getDescription())
                .add("tag", p.getTag().getTagname());
        list.add(out);
    }
    return JSONBuilder(list);
    }
    
    
    
}

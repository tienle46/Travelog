/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Comments;
import Model.Likes;
import Model.Posts;
import Model.Tags;
import Model.Users;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author macbook
 */
@Stateless
public class DBManager {
    @PersistenceContext(name ="Travelog-finalPU")
    private EntityManager em;
    
    public List<Users> getAllUser(){
        return em.createNamedQuery("Users.findAll").getResultList();
    }
    
    public Users getUserById(int id){
        return em.find(Users.class, id);
    }
    
    public Users getUserByName (String name) {
        List<Users> userList = getAllUser();
        for (Users user : userList){
            if (user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
        
    }
    
    
    
    //Insert to database
    public void insertUser(Users user){
        em.persist(user);
    }
    
    //Update to database
    public void updateUser(Users user){
        em.merge(user);
    }
    
    //get comments of matching id
    //reverses the order so the newest on top
    public List<Comments> getCommentForPost(Posts postId){
        Query query = em.createNamedQuery("Comments.findByPost");
        query.setParameter("post", postId);
        List<Comments> list = query.getResultList();
        
        //ArrayList<Comments> list = new ArrayList<>(getPostByID(postId).getCommentsCollection());
        Collections.reverse(list);
        return list;
    }
    
    //inserts the feedback to the back-end
    public Comments insertComment(Comments c){
        em.persist(c);
        return c;
    }
    
    //Return image by id
    public Posts getPostByID(int id) {
        return em.find(Posts.class, id);
    }
    
    //gets image from comment to the front-end 
    //it fetches it from comment class by id
     public Comments getCommentByID(int id) {
        return em.find(Comments.class, id);
    }

    //creates a list of all images to the fron-end and
    //reverses the order so the newest is on top
    public List<Posts> getAllPosts() {
        List<Posts> post = em.createNamedQuery("Posts.findAll").getResultList();
        ArrayList<Posts> newList = new ArrayList<Posts>(post);
        Collections.reverse(newList);
        return newList;
    }
    
    //creates a list of comments with the oldest on top
    //comments are shown under the images
    public List<Comments> getAllComments() {
        return em.createNamedQuery("Comments.findAll").getResultList();
    }
    

    //inserts image in database
    public Posts insertPost(Posts post) {
        em.persist(post);
        return post;
    }

    //udates to the database
    public void updatePost(Posts post) {
        em.merge(post);
    }

    //removes the image from database
    public void deletePost(Posts post) {
        em.remove(em.merge(post));
    }
    
    //removes the user from the database
    public void deleteUser(Users user) {
        em.remove(em.merge(user));
    }
    
    //removes feedback from database
    public void deleteComment(Comments user) {
        em.remove(em.merge(user));
    }
    
    
    public List<Tags> getAllTag() {
        return em.createNamedQuery("Tags.findAll").getResultList();
    }
    
    public Tags getTagById(int id){
        return em.find(Tags.class, id);
    }
    
    public Tags getTagByName (String name) {
        List<Tags> tagList = getAllTag();
        for (Tags tag : tagList){
            if (tag.getTagname().equals(name)){
                return tag;
            }
        }
        return null;
        
    }
    
    public void insertTag(Tags tag) {
        em.persist(tag);
    }
    
    public void updateTag(Tags tag) {
        em.merge(tag);
    }
    
    public void deleteTag(Tags tag) {
        em.remove(em.merge(tag));
    }
    
    
    public Users createUser(String name, String password, String email) {
        
        
        Users u = new Users();
        u.setUsername(name);
        u.setPw(password);
        u.setEmail(email);
        em.persist(u);
        return u;
        
    }
    
    public Integer getNumberOfLikeForPost(Posts post) {
        //ArrayList<Likes> list = new ArrayList<>(post.getLikesCollection());
        Query query = em.createNamedQuery("Likes.findByPost");
        query.setParameter("post", post);
        List<Likes> list = query.getResultList();
        Integer i = 0;
        for (Likes c : list) {
            i++;
        }
        return i;
    }
    
    public List<Tags> getTagByPost(Posts post) {
        Query query = em.createNamedQuery("Tags.findByPost");
        query.setParameter("post", post);
        List<Tags> list = query.getResultList();
        return list;
        
    }
    
    public List<Posts> getPostByTag(Tags tag) {
        Query query = em.createNamedQuery("Posts.findByTag");
        query.setParameter("tag", tag);
        List<Posts> list = query.getResultList();
        return list;
    }
    
    public List<Posts> getPostByUser(Users user) {
        Query query = em.createNamedQuery("Posts.findByOwner");
        query.setParameter("owner", user);
        List<Posts> list = query.getResultList();
        return list;
    }
    
    public Comments insertComment(String comment, Users user, Posts post) {
        Comments c = new Comments();
        c.setComment(comment);
        c.setOwner(user);
        c.setPost(post);
        em.persist(c);
        return c;
    }
    
    public Likes insertLike(Users user, Posts post) {
        Likes l = new Likes();
        l.setUser(user);
        l.setPost(post);
        em.persist(l);
        return l;
    }
  
    public boolean checkLike(Users user, Posts post) {
        List<Likes> list = em.createNamedQuery("Likes.findAll").getResultList();
        
        for (Likes i : list ) {
            if (i.getUser() == user && i.getPost() == post) {
                return true;
            }
        }
        return false;
    }
    
    public List<Posts> search(String keyword) {
        Query query = em.createQuery("SELECT p FROM Posts p WHERE (p.title = "+ keyword +" OR p.owner = " + keyword +" OR p.tag = "+keyword+")");
        List<Posts> list = query.getResultList();
        return list;
    }
}

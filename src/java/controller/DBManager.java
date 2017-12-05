/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Comments;
import model.Posts;
import model.Tags;
import model.Users;

/**
 *
 * @author macbook
 */
@Stateless
public class DBManager {

    @PersistenceContext(name = "TravelogPU")
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
    public List<Comments> getCommentForPost(int postId){
        return em.createNamedQuery("Comments.findAll").getResultList();
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
    //and the oldest on bottom
    public List<Posts> getAllImages() {
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
    
    //if multiple posts have same amount of comments
    //it chooses the newest one.
    //shows only one result
    public Posts getOneOfPostWithMostComment(){
        return (Posts)em.createNamedQuery("Posts.findOneByMaxFeedback").getSingleResult();
    }
    
    //creates a group of images and goes through which one has the most comments
    //if multiple images have same amount of comments, the function above activates
    //shows only one result
    public List<Comments> getPostMostComment(){
        return em.createNamedQuery("Comments.findMostCommentedPost").getResultList();
    }
    
    //makes a list of users and sees which one has the most comments
    //the one with most comments is on top
    public List<Comments> getUserMostFeedback(){
        return em.createNamedQuery("Comments.findMostActiveUser").getResultList();
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
    
    public Collection<Posts> getPostByTag(String name){
        return getTagByName(name).getPostsCollection();
    }
    
    public Users createUser(String name, String password, String email) {
        
        
        Users u = new Users();
        u.setUsername(name);
        u.setPw(password);
        u.setEmail(email);
        em.persist(u);
        return u;
        
    }
}

    
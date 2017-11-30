/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Posts;
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
        return em.createNamedQuery("Usr.findAll").getResultList();
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
    
    public Collection<Posts> getPostByUserName(String name){
        return getUserByName(name).getPostCollection();
    }
}

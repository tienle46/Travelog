/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import model.Posts;
import model.Tags;
import model.Users;

/**
 *
 * @author macbook
 */
@MultipartConfig(location = "/var/www/html/img") 
@WebServlet(name = "upload", urlPatterns = {"/upload"})
public class Upload extends HttpServlet {
    @EJB
    private DBManager dm;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        Tags tag = new Tags();
        tag.setTagname(request.getParameter("tag"));
        if (dm.getTagByName(request.getParameter("tag")) == null){
        dm.insertTag(tag);
        }
        Users u = (Users) dm.getUserById(Integer.parseInt(request.getParameter("userId")));
        System.out.print(u);
        String filename = u.getId().toString() + "_" + request.getPart("file").getSubmittedFileName();
        Posts post = new Posts();
        post.setPath("//10.114.32.133/img/" + filename);
        post.setTitle(request.getParameter("title"));
        post.setDescription(request.getParameter("des"));
        post.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        post.setTagId(tag);
        post.setOwnerId(u);
        dm.insertPost(post);
        request.getPart("file").write(filename);
        response.sendRedirect("index.html");
        }
        
        catch (Exception pokemon) {
            java.util.logging.Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, pokemon);
        } 
        
    }
    
    
}
    

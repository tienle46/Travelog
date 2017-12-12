/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Posts;
import Model.Tags;
import Model.Users;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig(location ="/var/www/html/img")
@WebServlet(name ="upload", urlPatterns = {"/upload"})
public class Upload extends HttpServlet {
    @EJB
    private DBManager dm;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Tags tag = new Tags();
            
            if (dm.getTagByName(request.getParameter("tag")) == null) {
                tag.setTagname(request.getParameter("tag"));
                dm.insertTag(tag);
            } else tag = dm.getTagByName(request.getParameter("tag"));
            Users u = (Users) dm.getUserById(Integer.parseInt(request.getParameter("userId")));
        System.out.print(u);
        String filename = u.getUserId().toString() + "_" + request.getPart("file").getSubmittedFileName();
        Posts post = new Posts();
        post.setPath("http://10.114.32.35/img/" + filename);
        post.setTitle(request.getParameter("title"));
        post.setDescription(request.getParameter("des"));
        post.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        post.setTag(tag);
        post.setOwner(u);
        dm.insertPost(post);
        request.getPart("file").write(                                                                                                                                                  filename);
        response.sendRedirect("index.html");
        } catch (IOException | NumberFormatException | ServletException pokemon) {
            java.util.logging.Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, pokemon);
        } 
    }

   
}

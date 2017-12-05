package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Comments;
import model.Likes;
import model.Posts;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-05T12:39:28")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile CollectionAttribute<Users, Posts> postsCollection;
    public static volatile SingularAttribute<Users, String> pw;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile CollectionAttribute<Users, Comments> commentsCollection;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;
    public static volatile SingularAttribute<Users, Likes> likes;

}
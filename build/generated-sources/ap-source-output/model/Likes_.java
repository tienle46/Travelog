package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Posts;
import model.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-05T12:39:28")
@StaticMetamodel(Likes.class)
public class Likes_ { 

    public static volatile SingularAttribute<Likes, Posts> postId;
    public static volatile SingularAttribute<Likes, Integer> userId;
    public static volatile SingularAttribute<Likes, Users> users;

}
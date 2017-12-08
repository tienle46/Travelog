package Model;

import Model.Posts;
import Model.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-08T16:00:48")
@StaticMetamodel(Likes.class)
public class Likes_ { 

    public static volatile SingularAttribute<Likes, Posts> post;
    public static volatile SingularAttribute<Likes, Integer> likeId;
    public static volatile SingularAttribute<Likes, Users> user;

}
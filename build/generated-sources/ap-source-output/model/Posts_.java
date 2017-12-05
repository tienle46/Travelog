package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Comments;
import model.Likes;
import model.Tags;
import model.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-05T12:39:28")
@StaticMetamodel(Posts.class)
public class Posts_ { 

    public static volatile SingularAttribute<Posts, Date> date;
    public static volatile SingularAttribute<Posts, String> path;
    public static volatile SingularAttribute<Posts, Tags> tagId;
    public static volatile SingularAttribute<Posts, String> description;
    public static volatile SingularAttribute<Posts, Integer> postId;
    public static volatile CollectionAttribute<Posts, Comments> commentsCollection;
    public static volatile SingularAttribute<Posts, String> title;
    public static volatile SingularAttribute<Posts, Users> ownerId;
    public static volatile CollectionAttribute<Posts, Likes> likesCollection;

}
package Model;

import Model.Comments;
import Model.Likes;
import Model.Tags;
import Model.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-11T14:59:25")
@StaticMetamodel(Posts.class)
public class Posts_ { 

    public static volatile SingularAttribute<Posts, Date> date;
    public static volatile SingularAttribute<Posts, Users> owner;
    public static volatile SingularAttribute<Posts, String> path;
    public static volatile CollectionAttribute<Posts, Tags> tagsCollection;
    public static volatile SingularAttribute<Posts, String> description;
    public static volatile SingularAttribute<Posts, Integer> postId;
    public static volatile CollectionAttribute<Posts, Comments> commentsCollection;
    public static volatile SingularAttribute<Posts, Tags> tag;
    public static volatile SingularAttribute<Posts, String> title;
    public static volatile CollectionAttribute<Posts, Likes> likesCollection;

}
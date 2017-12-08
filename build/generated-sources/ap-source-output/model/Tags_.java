package Model;

import Model.Posts;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-08T16:00:48")
@StaticMetamodel(Tags.class)
public class Tags_ { 

    public static volatile SingularAttribute<Tags, String> tagname;
    public static volatile SingularAttribute<Tags, Posts> post;
    public static volatile SingularAttribute<Tags, Integer> tagId;
    public static volatile CollectionAttribute<Tags, Posts> postsCollection;

}
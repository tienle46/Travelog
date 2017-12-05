package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Posts;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-05T15:17:52")
@StaticMetamodel(Tags.class)
public class Tags_ { 

    public static volatile SingularAttribute<Tags, String> tagname;
    public static volatile SingularAttribute<Tags, Integer> tagId;
    public static volatile CollectionAttribute<Tags, Posts> postsCollection;

}
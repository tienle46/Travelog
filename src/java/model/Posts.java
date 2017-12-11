/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author macbook
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p")
    , @NamedQuery(name = "Posts.findByPostId", query = "SELECT p FROM Posts p WHERE p.postId = :postId")
    , @NamedQuery(name = "Posts.findByPath", query = "SELECT p FROM Posts p WHERE p.path = :path")
    , @NamedQuery(name = "Posts.findByTitle", query = "SELECT p FROM Posts p WHERE p.title = :title")
    , @NamedQuery(name = "Posts.findByDescription", query = "SELECT p FROM Posts p WHERE p.description = :description")
    , @NamedQuery(name = "Posts.findByDate", query = "SELECT p FROM Posts p WHERE p.date = :date")
    , @NamedQuery(name = "Posts.findByTag", query = "SELECT p FROM Posts p WHERE p.tag = :tag")
    , @NamedQuery(name = "Posts.findByOwner", query = "SELECT p FROM Posts p WHERE p.owner = :owner")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_id")
    private Integer postId;
    @Size(max = 255)
    @Column(name = "path")
    private String path;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(mappedBy = "post")
    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "owner", referencedColumnName = "user_id")
    @ManyToOne
    private Users owner;
    @JoinColumn(name = "tag", referencedColumnName = "tag_id")
    @ManyToOne
    private Tags tag;
    @OneToMany(mappedBy = "post")
    private Collection<Likes> likesCollection;
    @OneToMany(mappedBy = "post")
    private Collection<Tags> tagsCollection;

    public Posts() {
    }

    public Posts(Integer postId) {
        this.postId = postId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }

    @XmlTransient
    public Collection<Likes> getLikesCollection() {
        return likesCollection;
    }

    public void setLikesCollection(Collection<Likes> likesCollection) {
        this.likesCollection = likesCollection;
    }

    @XmlTransient
    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Posts[ postId=" + postId + " ]";
    }
    
}

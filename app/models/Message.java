package models;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import play.data.validation.Constraints;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Message extends Model {
    @Id
    private Long id;
    @Constraints.Required
    @Constraints.MaxLength(100)
    @Column(length = 100)
    private String name;
    @Constraints.Email
    @Constraints.MaxLength(255)
    private String mail;
    @Constraints.MaxLength(255)
    private String message;

    @Column(name = "postdate")
    @WhenCreated
    private Date postdate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Date getPostdate() { return postdate; }
    public void setPostdate(Date postdate) { this.postdate = postdate; }

    @Override
    public String toString(){
        return "id: " + id
                + ", name: " + name
                + ", mail: " + mail
                + ", message: " + message
                + ", postdate: " + postdate;
    }
}

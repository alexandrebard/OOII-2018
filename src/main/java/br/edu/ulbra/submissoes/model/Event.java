package br.edu.ulbra.submissoes.model;

import br.edu.ulbra.submissoes.input.EventInput;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endingDate;

    @ManyToOne(optional = false)
    private User user;

    @OneToMany(mappedBy="event", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Submission> submissions;

    public Event() {
    }

    public Event(String name, Date startingDate, Date endingDate, User user) {
        this.name = name;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.user = user;
    }

    public Event(EventInput eventInput, User user) {
        this.name = eventInput.getName();
        this.creationDate = new Date();
        this.startingDate = new Date();
        this.endingDate = new Date();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}

package br.edu.ulbra.submissoes.model;

import br.edu.ulbra.submissoes.input.SubmissionInput;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Submission {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String synopsis;
    
    @CreationTimestamp
    private Date sendDate;

    @Column(nullable = false)
    private String file;

    @ManyToOne(optional = false)
    private Event event;

    @ManyToOne(optional = false)
    private User user;

    public Submission() {
    }

    public Submission(Event event, User user, SubmissionInput input) {
        this.user = user;
        this.event = event;
        this.title = input.getTitle();
        this.synopsis = input.getSynopsis();
        this.file = input.getFile();
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

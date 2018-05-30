package br.edu.ulbra.submissoes.model;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String responsavel;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String dataAbertura;

    @Column(nullable = false)
    private String dataFechamento;


    @ManyToMany
    @JoinTable(name="event_user",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> roles3;

    public List<User> getRoles() {
        return roles3;
    }

    public void setRoles(List<User> roles2) {
        this.roles3 = roles2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getUsername() {
        return nome;
    }

    public void setUsername(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String data) {
        this.dataFechamento = dataFechamento;
    }
}

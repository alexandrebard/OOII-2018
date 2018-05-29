package br.edu.ulbra.submissoes.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Submissoes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String evento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String resumo;
    
    @Column(nullable = false)
    private String dataEnvio;

    @Column(nullable = false)
    private String arquivo;

    @ManyToMany
    @JoinTable(name="submissoes_user",
            joinColumns = @JoinColumn(name = "submissoes_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
            )
    private List<User> roles2;

    public List<User> getRoles() {
        return roles2;
    }

    public void setRoles(List<User> roles2) {
        this.roles2 = roles2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return nome;
    }

    public void setUsername(String nome) {
        this.nome = nome;
    }

    public String getEvento() {
        return evento;
    }

    public void setEmail(String evento) {
        this.evento = evento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    
    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}

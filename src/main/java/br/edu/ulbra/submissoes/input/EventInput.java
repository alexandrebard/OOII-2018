package br.edu.ulbra.submissoes.input;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EventInput {

        @ApiModelProperty(example = "1", notes = "Identificador")
        private Long id;
        @ApiModelProperty(example = "name1", notes = "name")
        private String name;
        @ApiModelProperty(example = "2018-06-20", notes = "Data Criação")
        private Date creationDate;
        @ApiModelProperty(example = "2018-06-20", notes = "Data Inicio")
        private Date startingDate;
        @ApiModelProperty(example = "2018-06-20", notes = "Data Fim")
        private Date endingDate;
        @ApiModelProperty(example = "user1", notes = "Usuario responsavel")
        private String user;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

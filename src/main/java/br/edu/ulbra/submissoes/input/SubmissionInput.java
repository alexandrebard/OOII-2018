package br.edu.ulbra.submissoes.input;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(description = "Informações da Submissão")
public class SubmissionInput {

    private String title;
    private String synopsis;
    private Date sendDate;
    private String file;

    public SubmissionInput() {
    }

    public SubmissionInput(String title, String synopsis, Date sendDate, String file) {
        this.title = title;
        this.synopsis = synopsis;
        this.sendDate = sendDate;
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

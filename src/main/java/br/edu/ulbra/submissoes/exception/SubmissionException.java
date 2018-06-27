package br.edu.ulbra.submissoes.exception;

public class SubmissionException extends RuntimeException {

    public SubmissionException(){}

    public SubmissionException(String message){
        super(message);
    }
}

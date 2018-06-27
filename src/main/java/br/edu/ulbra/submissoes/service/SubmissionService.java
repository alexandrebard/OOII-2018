package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.Submission;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.SubmissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository, ModelMapper modelMapper){
        this.submissionRepository = submissionRepository;
        this.modelMapper = modelMapper;
    }

    public List<Submission> findAllByEvent(Event event) {
        return submissionRepository.findAllByEvent(event);
    }

    public void save(Submission submission) {
        this.submissionRepository.save(submission);
    }
}

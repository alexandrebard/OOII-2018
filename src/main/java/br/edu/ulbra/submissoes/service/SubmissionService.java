package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.repository.SubmissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SubmissionService(
            SubmissionRepository submissionRepository,
            ModelMapper modelMapper
    ){
        this.submissionRepository = submissionRepository;
        this.modelMapper = modelMapper;
    }
}

package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.repository.SubmissionsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    private final SubmissionsRepository submissionsRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SubmissionService(SubmissionsRepository submissionsRepository, ModelMapper modelMapper){
        this.submissionsRepository = submissionsRepository;
        this.modelMapper = modelMapper;
    }
}

package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    List<Submission> findAllByEvent(Event event);
}

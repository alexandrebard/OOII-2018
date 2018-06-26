package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.model.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
}

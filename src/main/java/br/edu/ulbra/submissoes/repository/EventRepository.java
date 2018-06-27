package br.edu.ulbra.submissoes.repository;

import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    Event findById(Long id);

    @Query(nativeQuery = true, value = "Select * from event where user_id = :user_id")
    List<Event> findByUser(@Param("user_id") Long userId);
}

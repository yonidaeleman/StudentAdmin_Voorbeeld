package be.ucll.repository;

import be.ucll.model.Inschrijving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InschrijvingRepository extends JpaRepository<Inschrijving, Long> {
}

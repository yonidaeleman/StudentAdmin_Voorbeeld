package be.ucll.repository;

import be.ucll.model.Cursus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursusRepository extends JpaRepository<Cursus, Long> {
}

package be.ucll.repository;

import be.ucll.model.Cursus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursusRepository extends JpaRepository<Cursus, Long> {
    Optional<Cursus> findCursusByNameIgnoreCase(String name);
}

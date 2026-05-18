package be.ucll.repository;

import be.ucll.model.Richting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RichtingRepository extends JpaRepository<Richting, Long> {

    Optional<Richting> findRichtingByNameIgnoreCase(String course);
}

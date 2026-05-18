package be.ucll.repository;

import be.ucll.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentsByNameContainsIgnoreCase(String name);

    Optional<Student> findStudentByNameIgnoreCase(String name);

    List<Student> findByRichting_Cursussen_NameIgnoreCase(String courseName);

    List<Student> findByRichting_NameIgnoreCase(String richting);
}

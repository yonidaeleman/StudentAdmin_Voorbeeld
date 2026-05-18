package be.ucll.repository;

import be.ucll.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByNameIgnoreCase(String name);

    Optional<Student> findStudentByNameIgnoreCase(String name);

    List<Student> findByInschrijving_Cursussen_Name(String courseName);

    List<Student> findByInschrijving_Course(String admission);

}

package be.ucll.service;

import be.ucll.model.Cursus;
import be.ucll.model.Inschrijving;
import be.ucll.model.Student;
import be.ucll.repository.CursusRepository;
import be.ucll.repository.InschrijvingRepository;
import be.ucll.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final InschrijvingRepository inschrijvingRepository;
    private final CursusRepository cursusRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, InschrijvingRepository inschrijvingRepository, CursusRepository cursusRepository) {
        this.studentRepository = studentRepository;
        this.inschrijvingRepository = inschrijvingRepository;
        this.cursusRepository = cursusRepository;
    }

    public List<Student> findAllStudentsOrByName(String name){
        if(!name.equals("none")){
            return studentRepository.findStudentsByNameIgnoreCase(name);
        }
        return studentRepository.findAll();
    }

    public List<Student> finStudentsByName(String name) {
        return studentRepository.findStudentsByNameIgnoreCase(name);
    }

    public List<Student> findByCourse(String courseName) {
        return studentRepository.findByInschrijving_Cursussen_Name(courseName);
    }

    public List<Student> findByAdmission(String admission) {
        return studentRepository.findByInschrijving_Course(admission);
    }

    public List<Cursus> findAllCourses() {
        return cursusRepository.findAll();
    }

    public List<Inschrijving> findAllAdmissions() {
        return inschrijvingRepository.findAll();
    }

    public Student addStudent( Student student) {
        Optional<Inschrijving> foundInschrijving = Optional.of(inschrijvingRepository.findInschrijvingByCourseIgnoreCase(student.getInschrijving().getCourse()).orElseThrow(() -> new RuntimeException("Inschrijving not found.")));
        student.setInschrijving(foundInschrijving.get());
        return studentRepository.save(student);
    }

    public Inschrijving addInschrijving( Inschrijving inschrijving) {
        List<Cursus> savedCursussen = new ArrayList<>();

        for(Cursus cursus : inschrijving.getCursussen()) {

            Optional<Cursus> foundCursus = Optional.ofNullable(cursusRepository.findCursusByNameIgnoreCase(cursus.getName())).orElseThrow(() -> new RuntimeException("Cursus not found."));

            savedCursussen.add(foundCursus.get());
        }

        inschrijving.setCursussen(savedCursussen);

        return inschrijvingRepository.save(inschrijving);

    }

    public Cursus addCursus( Cursus cursus) {
        return cursusRepository.save(cursus);
    }

    public Student changeUser( Student student, String name) {
        Student foundStudent = studentRepository.findStudentByNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Student not found."));
        foundStudent.setName(student.getName());
        foundStudent.setAge(student.getAge());
        foundStudent.setEmail(student.getEmail());
        foundStudent.setPassword(student.getPassword());
        foundStudent.setInschrijving(student.getInschrijving());
        studentRepository.save(foundStudent);
        return foundStudent;
    }

}

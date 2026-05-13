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

import java.util.List;

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
            return studentRepository.findStudentByNameIgnoreCase(name);
        }
        return studentRepository.findAll();
    }

    public List<Student> finStudentsByName(String name) {
        return studentRepository.findStudentByNameIgnoreCase(name);
    }

    public List<Student> findByCourse(String courseName) {
        return studentRepository.findByInschrijving_Cursussen_Name(courseName);
    }

    public List<Student> findByAdmission(String admission) {
        return studentRepository.findByInschrijving_Course(admission);
    }

    public Student addStudent(@Valid Student student) {
        return studentRepository.save(student);
    }

    public Inschrijving addInschrijving(@Valid Inschrijving inschrijving) {
        return inschrijvingRepository.save(inschrijving);
    }

    public Cursus addCursus(@Valid Cursus cursus) {
        return cursusRepository.save(cursus);
    }
}

package be.ucll.service;

import be.ucll.model.Cursus;
import be.ucll.model.Richting;
import be.ucll.model.Student;
import be.ucll.repository.CursusRepository;
import be.ucll.repository.RichtingRepository;
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
    private final RichtingRepository richtingRepository;
    private final CursusRepository cursusRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, RichtingRepository richtingRepository, CursusRepository cursusRepository) {
        this.studentRepository = studentRepository;
        this.richtingRepository = richtingRepository;
        this.cursusRepository = cursusRepository;
    }

    public List<Student> findAllStudentsOrByName(String name){
        if(!name.equals("none")){
            return studentRepository.findStudentsByNameContainsIgnoreCase(name);
        }
        return studentRepository.findAll();
    }

    //public Optional<Student> finStudentsByName(String name) {
    //    Student foundstudent = studentRepository.findStudentByNameContainingIgnoreCase(name).orElseThrow(() -> new RuntimeException("Student not found."));
    //    return Optional.of(foundstudent);
    //}

    public List<Student> findByCourse(String courseName) {
        return studentRepository.findByRichting_Cursussen_NameIgnoreCase(courseName);
    }

    public List<Student> findByRichting(String richting) {
        return studentRepository.findByRichting_NameIgnoreCase(richting);
    }

    public List<Cursus> findAllCourses() {
        return cursusRepository.findAll();
    }

    public List<Richting> findAllAdmissions() {
        return richtingRepository.findAll();
    }

    public Student addStudent( Student student) {
        Optional<Richting> foundRichting = Optional.of(richtingRepository.findRichtingByNameIgnoreCase(student.getrichting().getName()).orElseThrow(() -> new RuntimeException("Richting not found.")));
        student.setrichting(foundRichting.get());
        return studentRepository.save(student);
    }

    public Richting addRichting(Richting richting) {
        richting.setCursussen(findCursusen(richting));
        return richtingRepository.save(richting);

    }

    public Cursus addCursus( Cursus cursus) {
        return cursusRepository.save(cursus);
    }

    public Student changeStudent( Student student, String name) {
        Student foundStudent = studentRepository.findStudentByNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Student not found."));
        Richting foundRichting = richtingRepository.findRichtingByNameIgnoreCase(student.getrichting().getName()).orElseThrow(() -> new RuntimeException("Richting not found."));
        foundStudent.setName(student.getName());
        foundStudent.setAge(student.getAge());
        foundStudent.setEmail(student.getEmail());
        foundStudent.setPassword(student.getPassword());
        foundStudent.setrichting(foundRichting);
        studentRepository.save(foundStudent);
        return studentRepository.save(foundStudent);
    }

    public Richting changeRichting(String richting, @Valid Richting richtingToChange) {
        Richting foundRichting = richtingRepository.findRichtingByNameIgnoreCase(richting).orElseThrow(() -> new RuntimeException("Richting not found."));
        foundRichting.setName(richtingToChange.getName());
        foundRichting.setBedrag(richtingToChange.getBedrag());
        foundRichting.setDuration(richtingToChange.getDuration());
        List<Cursus> savedCursussen1 = findCursusen(richtingToChange);
        foundRichting.setCursussen(savedCursussen1);
        return richtingRepository.save(foundRichting);

    }

    private List<Cursus> findCursusen(@Valid Richting richtingToChange) {
        List<Cursus> savedCursussen = new ArrayList<>();

        for(Cursus cursus : richtingToChange.getCursussen()) {

            Optional<Cursus> foundCursus = Optional.ofNullable(cursusRepository.findCursusByNameIgnoreCase(cursus.getName())).orElseThrow(() -> new RuntimeException("Cursus not found."));

            savedCursussen.add(foundCursus.get());
        }
        return savedCursussen;
    }

    public Cursus changeCursus(String cursus, @Valid Cursus cursusToChange) {
        Cursus foundCursus = cursusRepository.findCursusByNameIgnoreCase(cursus).orElseThrow(() -> new RuntimeException("Cursus not found."));
        foundCursus.setName(cursusToChange.getName());
        foundCursus.setCoursepoints(cursusToChange.getCoursepoints());
        foundCursus.setCoordinator(cursusToChange.getCoordinator());
        return cursusRepository.save(foundCursus);
    }
}

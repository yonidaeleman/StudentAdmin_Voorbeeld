package be.ucll.repository;

import be.ucll.model.Cursus;
import be.ucll.model.Richting;
import be.ucll.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInitializer {

    private final StudentRepository studentRepository;
    private final RichtingRepository richtingRepository;
    private final CursusRepository cursusRepository;

    public DbInitializer(StudentRepository studentRepository, RichtingRepository richtingRepository, CursusRepository cursusRepository) {
        this.studentRepository = studentRepository;
        this.richtingRepository = richtingRepository;
        this.cursusRepository = cursusRepository;
    }

    @PostConstruct
    public void initialize(){

        Cursus backend = new Cursus("BackEnd", 6, "Elke Steegmans");
        Cursus FrontEnd = new Cursus("FrontEnd", 6, "Johan Pieck");
        Cursus p2 = new Cursus("Progamming 2", 6, "Ruben Naudts");
        Cursus cn = new Cursus("Computer Networks", 6, "Pieter Hollevoet");
        Cursus goederenrecht = new Cursus("goederenrecht", 3, "Johan Pieck");
        Cursus verbintenisrecht = new Cursus("verbintenisRecht", 4, "Johan Pieck");
        Cursus manegemant = new Cursus("manegemant", 5, "Johan Pieck");
        List<Cursus> ti = new ArrayList<>();
        List<Cursus> rechten = new ArrayList<>();
        ti.add(backend);
        ti.add(FrontEnd);
        ti.add(p2);
        ti.add(cn);
        rechten.add(goederenrecht);
        rechten.add(verbintenisrecht);
        rechten.add(manegemant);


        Richting TI = new Richting("TI", 3, 1123, ti);
        Richting Rechten = new Richting("Rechten", 3, 1498, rechten);

        cursusRepository.save(backend);
        cursusRepository.save(FrontEnd);
        cursusRepository.save(p2);
        cursusRepository.save(cn);
        cursusRepository.save(goederenrecht);
        cursusRepository.save(verbintenisrecht);
        cursusRepository.save(manegemant);
        richtingRepository.save(TI);
        richtingRepository.save(Rechten);
        studentRepository.save(new Student("Victor vdw", 19, "victorvdw@gmail.com", "vdw8976666", TI));
        studentRepository.save(new Student("Bram VQ", 19, "bramvq@gmail.com", "bvq1456744448", TI));
        studentRepository.save(new Student("Yinthe", 18, "yinthe@gmail.com", "yin12348955596", Rechten));
        studentRepository.save(new Student("yonidae", 22, "yonidae@icloud.com", "yoloy1966619", TI));
    }
}

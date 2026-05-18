package be.ucll.repository;

import be.ucll.model.Cursus;
import be.ucll.model.Inschrijving;
import be.ucll.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInitializer {

    private final StudentRepository studentRepository;
    private final InschrijvingRepository inschrijvingRepository;
    private final CursusRepository cursusRepository;

    public DbInitializer(StudentRepository studentRepository, InschrijvingRepository inschrijvingRepository, CursusRepository cursusRepository) {
        this.studentRepository = studentRepository;
        this.inschrijvingRepository = inschrijvingRepository;
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

        Inschrijving victor = new Inschrijving("TI", "13/08/2025", 3, 1123, ti);
        Inschrijving bram = new Inschrijving("TI", "18/08/2025", 3, 1123, ti);
        Inschrijving yoni = new Inschrijving("TI", "29/08/2025", 3, 1123, ti);
        Inschrijving yinthe = new Inschrijving("Rechten", "13/08/2025", 3, 1498, rechten);

        cursusRepository.save(backend);
        cursusRepository.save(FrontEnd);
        cursusRepository.save(p2);
        cursusRepository.save(cn);
        cursusRepository.save(goederenrecht);
        cursusRepository.save(verbintenisrecht);
        cursusRepository.save(manegemant);
        inschrijvingRepository.save(victor);
        inschrijvingRepository.save(bram);
        inschrijvingRepository.save(yoni);
        inschrijvingRepository.save(yinthe);
        studentRepository.save(new Student("Victor vdw", 19, "victorvdw@gmail.com", "vdw8976666", victor));
        studentRepository.save(new Student("Bram VQ", 19, "bramvq@gmail.com", "bvq1456744448", bram));
        studentRepository.save(new Student("Yinthe", 18, "yinthe@gmail.com", "yin12348955596", yinthe));
        studentRepository.save(new Student("yonidae", 22, "yonidae@icloud.com", "yoloy1966619", yoni));
    }
}

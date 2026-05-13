package be.ucll.component;

import be.ucll.repository.DbInitializer;
import be.ucll.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Sql("classpath:schema.sql")
public class StudentTest {

    private final WebTestClient webTestClient;
    private final StudentRepository studentRepository;

    public StudentTest(WebTestClient webTestClient, StudentRepository studentRepository) {
        this.webTestClient = webTestClient;
        this.studentRepository = studentRepository;
    }

    @Autowired
    private DbInitializer dbInitializer;

    @BeforeEach
    public void setup(){dbInitializer.initialize();}

    @Test
    public void given4users_whenGetStudents_then4studentsReturned(){
        webTestClient
                .get()
                .uri("/students")
                .exchange().expectStatus().isOk()
                .expectBody()
                .json("""
[
    {
        "id": 1,
        "name": "Victor vdw",
        "age": 19,
        "email": "victorvdw@gmail.com",
        "password": "vdw8976666"
    },
    {
        "id": 2,
        "name": "Bram VQ",
        "age": 19,
        "email": "bramvq@gmail.com",
        "password": "bvq1456744448"
    },
    {
        "id": 3,
        "name": "Yinthe",
        "age": 18,
        "email": "yinthe@gmail.com",
        "password": "yin12348955596"
    },
    {
        "id": 4,
        "name": "yonidae",
        "age": 22,
        "email": "yonidae@icloud.com",
        "password": "yoloy1966619"
    }
]""");
    }
}

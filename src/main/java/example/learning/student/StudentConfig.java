package example.learning.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student john = new Student(
                    "John",
                    "john@mail.com",
                    LocalDate.of(1999, Month.APRIL, 4)
            );
            Student wick = new Student(
                    "Wick",
                    "Wick@mail.com",
                    LocalDate.of(1399, Month.AUGUST, 23)
            );

            repository.saveAll(List.of(john, wick));
        };
    }
}

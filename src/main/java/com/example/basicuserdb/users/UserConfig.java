package com.example.basicuserdb.users;
     
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repo) {
        return args -> {
            User mane = new User(
                "mane",
                "mane@bol.com.br",
                "1234",
                LocalDate.of(1989, Month.MAY, 01)
            );
            User tonho = new User(
                "tonho",
                "tonho@yahoo.com.br",
                "curintia",
                LocalDate.of(1968, Month.JANUARY, 20)
            );
            User mariazinha = new User(
                "mariazinha",
                "mariazinha@live.com.br",
                "palmera",
                LocalDate.of(2002, Month.OCTOBER, 10)
            );
            repo.saveAll(List.of(mane, tonho, mariazinha));
        };
    }
}

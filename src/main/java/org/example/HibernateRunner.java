package org.example;


import org.example.Converter.BirthdayConverter;
import org.example.entity.Birthday;
import org.example.entity.Role;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, System.getenv("DATABASE_URL"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, System.getenv("DATABASE_USER"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, System.getenv("DATABASE_PASSWORD"));
        configuration.configure();
        //configuration.addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            User user = User.builder()
                    .username("ksusha1@mail.ru")
                    .firstname("Kseniya")
                    .lastname("Tarasova")
                    .birthDate(new Birthday(LocalDate.of(2004, 2, 12)))
                    .role(Role.ADMIN)
                    .build();

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }
}

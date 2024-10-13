package org.example;


import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        //configuration.addAnnotatedClass(User.class);
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();){
            session.beginTransaction();

            session.save(User.builder()
                            .username("ksusha@mail.ru")
                            .firstname("Kseniya")
                            .lastname("Tarasova")
                            .birthDate(LocalDate.of(2004, 2, 12))
                            .age(19)
                    .build());

            session.getTransaction().commit();
        }
    }
}

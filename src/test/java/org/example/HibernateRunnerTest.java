package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.example.entity.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
        User user = User.builder()
                .username("ksusha@mail.ru")
                .firstname("Kseniya")
                .lastname("Tarasova")
                .birthDate(LocalDate.of(2004, 2, 12))
                .age(19)
                .build();
        var sql = """
                insert into
                %s
                (%s)
                values
                (%s)
                """;

        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());
        Field[] fields = user.getClass().getDeclaredFields();
        var columnNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name).orElse(field.getName())
                ).collect(Collectors.joining(", "));
        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hibernate",
                "postgres", "70262057113233Kk");
        PreparedStatement ps = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            ps.setObject(i + 1, fields[i].get(user));
        }
        System.out.println(ps);

        ps.executeUpdate();
        ps.close();
        connection.close();

    }
}
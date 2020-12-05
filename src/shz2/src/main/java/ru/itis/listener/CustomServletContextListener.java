package ru.itis.listener;

import ru.itis.datasource.SimpleDataSource;
import ru.itis.repository.PersonRepository;
import ru.itis.repository.PersonRepositoryImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/shz2";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = new SimpleDataSource(DB_URL, DB_USERNAME, DB_PASSWORD, DB_DRIVER);

        PersonRepository personRepository = new PersonRepositoryImpl(dataSource);

        sce.getServletContext().setAttribute("personRepository", personRepository);
    }
}

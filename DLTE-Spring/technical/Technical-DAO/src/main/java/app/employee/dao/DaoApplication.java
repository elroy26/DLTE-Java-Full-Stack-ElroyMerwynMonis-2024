package app.employee.dao;

import app.employee.dao.services.EmployeeDbRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class DaoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DaoApplication.class, args);
        ConfigurableApplicationContext context=  SpringApplication.run(DaoApplication.class, args);
        EmployeeDbRepo dbRepo=context.getBean(EmployeeDbRepo.class);
        try {
            System.out.println(dbRepo.outputEmployeeProfile());
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}

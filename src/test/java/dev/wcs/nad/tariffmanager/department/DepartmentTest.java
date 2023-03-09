package dev.wcs.nad.tariffmanager.department;

import dev.wcs.nad.tariffmanager.persistence.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void shouldReturnAllDepartments() {
        departmentRepository.findAll().forEach(it -> {
            System.out.println(it.getName());
        });
    }
}

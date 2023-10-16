package com.example.school_management_system.repository;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {
    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:8.1.0");
        MY_SQL_CONTAINER.start();
    }

}

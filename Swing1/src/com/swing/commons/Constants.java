package com.swing.commons;

public class Constants {

    public static final String EXIT_CONFIRMATION_MESSAGE = "Do you really want to exit the application?";
    public static final String EXIT_CONFIRMATION_TITLE = "Confirm Exit";
    public static final String IMPORT_ERROR_MESSAGE = "Unable to load data from file";
    public static final String IMPORT_ERROR_MESSAGE_TITLE = "Import error";
    public static final String EXPORT_ERROR_MESSAGE = "Unable to save data to file";
    public static final String EXPORT_ERROR_MESSAGE_TITLE = "Export error";
    public static final String USER_PREFERENCE_KEY = "user";
    public static final String PASSWORD_PREFERENCE_KEY = "password";
    public static final String PORT_PREFERENCE_KEY = "port";
    public static final int PORT_PREFERENCE_DEFAULT_VALUE = 3306;

    public static final String MYSQL_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DRIVER_NOT_FOUND_ERROR_MSG = "Driver not found";
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/swingtest";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "admin";
    public static final String OPEN_DATABASE_CONNECTION_ERROR_MSG = "Unable to open connection with db";
    public static final String OPEN_DATABASE_CONNECTION_ERROR_TITLE = "Database connection error";
    public static final String SAVE_TO_DATABASE_ERROR_MSG = "Unable to save data";
    public static final String SAVE_TO_DATABASE_ERROR_TITLE = "Save data error";
    public static final String LOAD_DATA_ERROR_MSG = "Unable to load data";
    public static final String LOAD_DATA_ERROR_TITLE = "Load data error";
    public static final String CLOSE_CONNECTION_ERROR_MSG = "Can't close connection to db";
    public static final String CONNECTION_SUCCESS = "Successfully connected to database";
    public static final String DISCONNECTION_SUCCESS = "Successfully disconnected from database";

    public static final String COUNT_PERSONS_BY_ID_SQL_STMT =
            "SELECT COUNT(*) AS COUNT FROM PEOPLE WHERE ID = ?";

    public static final String INSERT_PERSON_SQL_STMT =
            "INSERT INTO PEOPLE (id, name, occupation, age, employment_status, tax_id, us_citizen, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_PEOPLE_SQL_STMT =
            "UPDATE PEOPLE SET name = ?, occupation = ?, age = ?, employment_status = ?, tax_id = ?, us_citizen = ?, gender = ? WHERE id = ?";

    public static final String SELECT_PEOPLE_STMT = "SELECT * FROM PEOPLE ORDER BY name";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_OCCUPATION = "occupation";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_EMPLOYMENT_STATUS = "employment_status";
    public static final String COLUMN_TAX_ID = "tax_id";
    public static final String COLUMN_US_CITIZEN = "us_citizen";
    public static final String COLUMN_GENDER = "gender";

}

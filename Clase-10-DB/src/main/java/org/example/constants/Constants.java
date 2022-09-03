package org.example.constants;

public class Constants {
    /**
     * SQL querys person entity constant.
     */
    public static final String SQL_SELECT_PERSON = "SELECT id_persona, nombre, apellido, edad, direccion FROM personas";
    public static final String SQL_SELECT_PERSON_BY_ID = "SELECT id_persona FROM personas WHERE id_persona = ?";
    public static final String SQL_INSERT_PERSON = "INSERT INTO personas(nombre, apellido, edad, direccion) VALUES(?, ?, ?, ?)";
    public static final String SQL_UPDATE_PERSON = "UPDATE personas SET nombre = ?, apellido = ?, edad = ?, direccion = ? WHERE id_persona = ?";
    public static final String SQL_DELETE_PERSON = "DELETE FROM personas WHERE id_persona = ?";

    /**
     * Variables de conexion a la base de datos.     */

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/curso_ayi_db?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "28158598";

}

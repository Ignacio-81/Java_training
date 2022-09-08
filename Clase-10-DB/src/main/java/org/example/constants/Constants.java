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

    public static final String SQL_GET_PERSON_BY_ID = "SELECT id_persona, nombre, apellido, edad, direccion FROM personas WHERE id_persona = ?";
    public static final String SQL_SELECT_EMPLEADO = "SELECT idempleado, sueldo, id_persona FROM empleados";
    public static final String SQL_SELECT_EMPLEADO_BY_ID = "SELECT idempleado FROM empleados WHERE idempleado = ?";
    public static final String SQL_INSERT_EMPLEADO = "INSERT INTO empleados(sueldo, id_persona) VALUES(?, ?)";
    public static final String SQL_UPDATE_EMPLEADO = "UPDATE empleados SET  sueldo = ?, id_persona = ? WHERE idempleado = ?";
    public static final String SQL_DELETE_EMPLEADO = "DELETE FROM empleados WHERE idempleado = ?";
    public static final String SQL_SELECT_CLIENTE = "SELECT id_cliente, vip, id_persona FROM clientes";
    public static final String SQL_SELECT_CLIENTE_BY_ID = "SELECT id_cliente FROM clientes WHERE id_cliente = ?";
    public static final String SQL_INSERT_CLIENTE = "INSERT INTO clientes(vip, id_persona) VALUES(?, ?)";
    public static final String SQL_UPDATE_CLIENTE = "UPDATE clientes SET  vip = ?, id_persona = ? WHERE id_cliente = ?";
    public static final String SQL_DELETE_CLIENTE = "DELETE FROM clientes WHERE id_cliente = ?";

    /**
     * Variables de conexion a la base de datos.     */

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/curso_ayi_db?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "28158598";

}

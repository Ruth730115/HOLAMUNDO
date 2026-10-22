/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.holamundo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Ruth Azucena Flores
 */
public class InsertarDB {
    
    public static void insertarAlumno() throws SQLException {
        System.out.println("+++Ejecutando operaciones en tabla Alumnos+++");
        //obtener la conexion
        Connection cn = ConexionDB1.getConexion();
        if(cn == null){
            System.out.println("No se puede establecer la conexion");
            return;
        }
        //Declarar los objetos de SQL para base de datos
        PreparedStatement psInsertar = null;
        PreparedStatement psSeleccionar = null;
        PreparedStatement psConsulta = null;
        PreparedStatement psEliminar = null;
        ResultSet rs = null;
        ResultSet cs = null;
        ResultSet el = null;
        try{
            System.out.println("\n 1. Intentando insertar nuevo Alumno...");
            String psInsert = "INSERT INTO Alumnos(nombre,correo) VALUES(?,?)";
            psInsertar = cn.prepareStatement(psInsert);
            
            //asignaremos valores a nuestros parametros
            psInsertar.setString(1, "Vozhina");
            psInsertar.setString(2, "vozhina@uth.hn");
            
            //ejecutar la insercion
            int filasAfectadas = psInsertar.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("!Alumno insertado con exito!");
            }
            //Parte B Seleccionar y visualizar datos en pantalla
            System.out.println("\n 2. Consulta de lista de Alumnos en la Base de Datos");
            String sqlSelect = "SELECT id, nombre, correo FROM Alumnos";
            psSeleccionar = cn.prepareStatement(sqlSelect);
            rs = psSeleccionar.executeQuery();
            System.out.println("\n=======================================");
            System.out.println("ID     |  Nombre              | Correo   ");
            System.out.println("=========================================");
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                //Imprimir la tabla virtual
                System.out.printf("%-3d  | %-20s | %s\n", id, nombre, correo);
            }
            //Parte C Consultar y visualizar datos en pantalla
            System.out.println("\n 3. Consulta de lista de Alumnos en la Base de Datos");
            String sqlSelectC = "SELECT * FROM Alumnos WHERE nombre = 'Erling Haaland'";
            psConsulta = cn.prepareStatement(sqlSelectC);
            cs = psConsulta.executeQuery();
            if(cs.next()){
               int id = cs.getInt("id");
               String nombre = cs.getString("nombre");
               String correo = cs.getString("correo");
               System.out.printf("%-3d  | %-20s | %s\n", id, nombre, correo);
        }else{
                System.out.println("Registro no existe");
                }
            //Parte D Eliminar registro de la Base de Datos Alumnos
            System.out.println("\n 4. Eliminar registro de Base de Datos Alumnos");
            String sqlDelete = "DELETE FROM Alumnos WHERE nombre = 'Lionel Messi'";
            psEliminar = cn.prepareStatement(sqlDelete);
            int filasEliminadas = psEliminar.executeUpdate();
            if(filasEliminadas > 0){
               System.out.println("Registro Eliminado");
            }else{
               System.out.println("No se encontro registro");
               }
            System.out.println("\n5. Lista actualizada de alumnos");
            String sqlVerificar = "SELECT id, nombre, correo FROM Alumnos";
            PreparedStatement psVerificar = cn.prepareStatement(sqlVerificar);
            ResultSet rsVerificar = psVerificar.executeQuery();
            System.out.println("=========================================");
            System.out.println("ID     | Nombre              | Correo");
            System.out.println("=========================================");
            while(rsVerificar.next()){
                int id = rsVerificar.getInt("id");
                String nombre = rsVerificar.getString("nombre");
                String correo = rsVerificar.getString("correo");
                System.out.printf("%-3d | %-20s | %s%n", id, nombre, correo);
}
rsVerificar.close();
psVerificar.close();

          }catch(SQLException e){
            System.err.println("Ocurrio un error con la base de datos"
                    + e.getMessage());
        }finally{        
            try{
            //cerrar la conexion de manera obligatoria
            if(rs != null){
                rs.close();
            }
            if(psInsertar != null)psInsertar.close();
            if(psSeleccionar != null)psSeleccionar.close();
            if(psConsulta != null)psConsulta.close();
            if(psEliminar != null)psEliminar.close();
            ConexionDB1.cerrarConexion();
        }catch (SQLException e){
            System.err.println("Error al cerrar recursos" + e.getMessage());
        }
    }
   }
}

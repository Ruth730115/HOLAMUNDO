/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.holamundo;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * Ruth Azucena Flores
 */
public class HOLAMUNDO {

    public static void main(String[] args) throws SQLException {
        System.out.println("+++Probando conexion a SQL Server+++");
        Connection cn = ConexionDB1.getConexion();
        try{
            if(cn != null && !cn.isClosed()){
                System.out.println("Estado: Conectado y listo para usarse");
            } else{
                System.out.println("Estado: No se puede conectar");
            }
        }catch(SQLException e){
            System.err.println("Error en la verificacion" 
            + e.getMessage());
        }finally{
            ConexionDB1.cerrarConexion();
        }
        InsertarDB.insertarAlumno();
    }
}

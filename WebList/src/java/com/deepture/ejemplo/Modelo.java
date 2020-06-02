package com.deepture.ejemplo;

import java.sql.*;
import javax.sql.DataSource;

/**
 *
 * @author DeepTure
 */
public class Modelo {
    
    //instanciamos una variable de tipo DataSource para obtener el poolConnection
    private DataSource origenData;
    
    //generamos el constructor 
    public Modelo(DataSource origenData) {
        this.origenData = origenData;
    }
    /*
        Con el constructor obtenemos el pool desde el controlador, y para obtener la conexion con la base de datos
        se ejecuta lo siguiente "Connection conexion = origenData.getConnection();" y en la variable conexion obtenemos obtenemos la 
        conexion para acceder a la base de datos; En caso de tener dudas revisar el ejemplo que mandé, videos o preguntar
    */

    
}

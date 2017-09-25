/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author Eugen
 */
public class SQLHelper {

    public static SQLServerDataSource createDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("VirgoHospital");
        ds.setUser("Virgo");
        ds.setPassword("pass123");
        
        return ds;
    }
}

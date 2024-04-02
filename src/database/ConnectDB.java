 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;

/**
 *
 * @author Hoàng Khang
 */
public class ConnectDB {

    public static Connection conn = null;

    public static void connect() throws SQLException {
        /*
        Sử dụng TLSv1 để connect(java ver 11 trở lui)
         String url = "jdbc:sqlserver://localhost:1433;databasename=OmegaBook";
        * */

        String url = "jdbc:sqlserver://localhost:1433;databasename=OmegaBook;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "sapassword"; //123456aA@$

        conn = DriverManager.getConnection(url, user, password);

    }

    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Triss
 */
public class KetNoi {
    
    public KetNoi(){}
    Connection con;
    public Connection ketNoi()
    {
        try {
            String user="sa";
            String pass="123456";
            String url="jdbc:sqlserver://localhost:1433;databaseName=QL_CUAHANGNOITHAT";
            con = DriverManager.getConnection(url, user,pass);
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Statement createStatement() throws SQLException {
        if (con == null) {
            throw new SQLException("Connection is not established.");
        }
        return con.createStatement();
    }

    public Connection getConnection() {     
        return con;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankingApplication;

/**
 *
 * @author Ellis
 */
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.sql.Statement;



public class sql {
    public static void create() {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "system";
        String pass = "root";
        
        //Create commands with keys
	String sql = "CREATE TABLE CUSTOMER (ACCOUTNUM NUMBER(10) NOT NULL,BALANCE number, PROTYPE VARCHAR2(50), CNAME VARCHAR2(50), ADDR VARCHAR2(50),AGE NUMBER)";
        String sql1 = "CREATE TABLE ACCOUNT (TYPE VARCHAR(), BALANCE NUMBER, ACCOUNTNUM NUMBER)";
        String sql2 = "ALTER TABLE ACCOUNT ADD CONSTRAINT accNo PRIMARY KEY (ACCOUNTNUM)";
        String sql3 = "ALTER TABLE CUSTOMER ADD CONSTRAINT accNo_1 FOREIGN KEY (ACCOUNTNUM)";

	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
            e.printStackTrace();
	} 

	Connection con = null;
	Statement stmt = null;

        //form connection then excecute
	try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
        } catch (SQLException e) {
            e.printStackTrace();
	} finally {
            try {
                stmt.close();
		con.close();
            } catch (SQLException e) {
		e.printStackTrace();
            }
	}
	System.out.println("Tables created successfully...");
    }
    
    public static void drop() {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "system";
        String pass = "root";
	String sql = "DROP TABLE CUSTOMER";
        String sql1 = "DROP TABLE ACCOUNT";

	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
            e.printStackTrace();
	} 

	Connection con = null;
	Statement stmt = null;

	try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
	} finally {
            try {
                stmt.close();
		con.close();
            } catch (SQLException e) {
		e.printStackTrace();
            }
	}
	System.out.println("Table created successfully...");
    }

    public static void insertAccountByPrepare(Account z,String type) {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "SYSTEM";
	String pass = "root!";
	String sql = "insert into Customer values(?,?,?,?,?,?)";
	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  //load JDBC driver with specific database
	} catch (ClassNotFoundException e) {
            e.printStackTrace();
	} 
	Connection con=null;
	PreparedStatement stmt=null;
	try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, type);
            stmt.setDouble(2, z.balance);
            stmt.setInt(6, z.checkingAccount);
            
            stmt.executeUpdate();   // first insert
        } catch (SQLException e) {
            e.printStackTrace();
	}finally {
            try {
		stmt.close();
		con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}		
	System.out.println("insert record successfully...");
    }
    
    public static void insertCustomerByPrepare(Customer z) {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "SYSTEM";
	String pass = "root!";
	String sql = "insert into Account values(?,?,?)";
	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  //load JDBC driver with specific database
	} catch (ClassNotFoundException e) {
            e.printStackTrace();
	} 
	Connection con=null;
	PreparedStatement stmt=null;
	try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, z.getCustomerAccount().getCheckingAccount());
            stmt.setDouble(2, z.amount);
            stmt.setString(3, z.professionalStatus);
            stmt.setString(4, z.custName);
            stmt.setString(5, z.address);
            stmt.setInt(6, z.age);
            
            stmt.executeUpdate();   // first insert
        } catch (SQLException e) {
            e.printStackTrace();
	}finally {
            try {
		stmt.close();
		con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}		
	System.out.println("insert record successfully...");
    }
}

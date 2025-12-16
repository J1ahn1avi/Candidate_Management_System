package com.wipro.candidate.util;

import java.sql.DriverManager;

import java.sql.Connection;
public class DBUtil {
public static Connection getDBConn() {
Connection con = null;
try {
Class.forName("com.mysql.cj.jdbc.Driver");
con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/candidate_db",
"root",
""
);
} catch (Exception e) {
e.printStackTrace();
}
return con;
}
}
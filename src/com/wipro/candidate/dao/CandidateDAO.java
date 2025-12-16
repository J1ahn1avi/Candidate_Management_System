package com.wipro.candidate.dao;


import java.sql.*;
import java.util.ArrayList;


import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;


public class CandidateDAO {


public String addCandidate(CandidateBean cb) {
try {
Connection con = DBUtil.getDBConn();
String sql = "INSERT INTO CANDIDATE_TBL VALUES (?,?,?,?,?,?,?)";
PreparedStatement ps = con.prepareStatement(sql);


ps.setString(1, cb.getId());
ps.setString(2, cb.getName());
ps.setInt(3, cb.getM1());
ps.setInt(4, cb.getM2());
ps.setInt(5, cb.getM3());
ps.setString(6, cb.getResult());
ps.setString(7, cb.getGrade());


int res = ps.executeUpdate();
con.close();
return res > 0 ? "SUCCESS" : "FAIL";
} catch (Exception e) {
return "FAIL";
}
}
public ArrayList<CandidateBean> getByResult(String criteria) {
ArrayList<CandidateBean> list = new ArrayList<>();
try {
Connection con = DBUtil.getDBConn();
String sql;


if (criteria.equals("ALL"))
sql = "SELECT * FROM CANDIDATE_TBL";
else
sql = "SELECT * FROM CANDIDATE_TBL WHERE RESULT=?";


PreparedStatement ps = con.prepareStatement(sql);
if (!criteria.equals("ALL"))
ps.setString(1, criteria);


ResultSet rs = ps.executeQuery();
while (rs.next()) {
CandidateBean cb = new CandidateBean();
cb.setId(rs.getString(1));
cb.setName(rs.getString(2));
cb.setM1(rs.getInt(3));
cb.setM2(rs.getInt(4));
cb.setM3(rs.getInt(5));
cb.setResult(rs.getString(6));
cb.setGrade(rs.getString(7));
list.add(cb);
}
con.close();
return list.isEmpty() ? null : list;
} catch (Exception e) {
return null;
}
}


public String generateCandidateId(String name) {
String id = "";
try {
Connection con = DBUtil.getDBConn();
Statement st = con.createStatement();
st.execute("INSERT INTO CANDID_SEQ VALUES ()");
ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
if (rs.next()) {
int seq = rs.getInt(1);
id = name.substring(0, 2).toUpperCase() + seq;
}
con.close();
} catch (Exception e) {
e.printStackTrace();
}
return id;
}
}
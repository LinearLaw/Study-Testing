package com.linear.W29_JDBC_Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    29.2、使用JDBCUtils，查询数据库，获取数据库内容并返回。
 */
public class D02_JDBC_use {

    public static void main(String[] args){
        List<Emp> list = new D02_JDBC_use().findAll();
        System.out.println(list);
        System.out.println(list.size());
    }

    public List<Emp> findAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = null;

        try {
            conn = D01_JDBCUtils.getConnection();

            String sql = "select * from emp";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            Emp emp = null;
            list = new ArrayList<Emp>();

            while(rs.next()){
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                double salary = rs.getDouble("salary");
                Date date = rs.getDate("join_date");
                int dept_id = rs.getInt("dept_id");

                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setJoindate(date);
                emp.setDept_id(dept_id);

                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            D01_JDBCUtils.close(rs,stmt,conn);
        }

        return list;
    }
}

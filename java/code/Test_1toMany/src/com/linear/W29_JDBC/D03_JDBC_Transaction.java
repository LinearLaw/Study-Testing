package com.linear.W29_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    29.3、JDBC开启事务
        先获取一个Connection，
        然后将Connection的setAutoCommit(false)关闭自动提交事务
        之后的sql操作，在executeUpdate之后，需要进行手动的commit()
        如果sql操作在commit之前报错，需要手动rollback()

        - conn.setAutoCommit(boolean isAutoCommit)
        - conn.commit()
        - conn.rollback()
 */
public class D03_JDBC_Transaction {

    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement ptmt1 = null;
        PreparedStatement ptmt2 = null;

        try {
            conn = D01_JDBCUtils.getConnection();

            // 关闭自动提交，改为手动提交
            conn.setAutoCommit(false);
            String sql1 = "update emp set salary = salary - ? where id = ?";
            String sql2 = "update emp set salary = salary + ? where id = ?";

            ptmt1 = conn.prepareStatement(sql1);
            ptmt2 = conn.prepareStatement(sql2);

            ptmt1.setDouble(1,500);
            ptmt1.setInt(2,2);

            ptmt2.setDouble(1,300);
            ptmt2.setInt(2,2);

            ptmt1.executeUpdate();
            ptmt2.executeUpdate();

            // 提交事务
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();

            // 出错，事务进行回滚
            if(conn != null){
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }finally {
            // 关闭申请的资源
            D01_JDBCUtils.close(ptmt1,conn);
            D01_JDBCUtils.close(ptmt2,null);
        }


    }


}

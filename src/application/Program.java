package application;

import java.sql.*;
import db.DB;
import db.DbException;
//import db.DbIntegrityException;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            // int x = 1;
            // if (x < 2) {throw new SQLException("Fake error");}

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("rows1 " + rows1);
            System.out.println("rows2 " + rows2);

        } catch (SQLException e) {
            // throw new DbIntegrityException(e.getMessage()); // Custom exception
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        } finally {
            // DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}

// import java.text.ParseException;
// import java.text.SimpleDateFormat;

// PreparedStatement st = null;
// ResultSet rs = null;
// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

// Retrieving data
// st = conn.createStatement();
// rs = st.executeQuery("select * from department");
// while (rs.next()) {System.out.println(rs.getInt("Id") + ", " +
// rs.getString("Name"));}

// Inserting data
/*
 * st = conn.prepareStatement("INSERT INTO seller " +
 * "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES " +
 * "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
 * 
 * st.setString(1, "John Doe"); st.setString(2, "johndoe@gmail.com");
 * st.setDate(3, new java.sql.Date(sdf.parse("01/01/1995").getTime()));
 * st.setDouble(4, 4000.00); st.setInt(5, 3);
 */

// Inserting 2 Ids demo
// st = conn.prepareStatement("insert into department (Name) values
// ('D1'),('D2')",
// Statement.RETURN_GENERATED_KEYS);

/*
 * if (rowsAffected > 0) { rs = st.getGeneratedKeys(); while (rs.next()) { int
 * id = rs.getInt(1); System.out.println("Done! Id = " + id); } } else {
 * System.out.println("No rows affected!"); }
 */

// Updating data
/*
 * st = conn.prepareStatement( "UPDATE seller " +
 * "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(DepartmentId = ?)");
 * 
 * st.setDouble(1, 200.00); st.setInt(2, 2);
 */

// Deleting data
/*
 * st = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");
 * st.setInt(1, 2);
 * 
 * int rowsAffected = st.executeUpdate();
 * 
 * System.out.println("Done! Rows affected: " + rowsAffected);
 */
package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.*;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        // ResultSet rs = null;
        // Statement st = null;
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DB.getConnection();

            // Retrieve data query
            // st = conn.createStatement();
            // rs = st.executeQuery("select * from department");
            // while (rs.next()) {System.out.println(rs.getInt("Id") + ", " +
            // rs.getString("Name"));}

            // Insert data operation
            /*
             * st = conn.prepareStatement("INSERT INTO seller " +
             * "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES " +
             * "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
             * 
             * st.setString(1, "John Doe"); st.setString(2, "johndoe@gmail.com");
             * st.setDate(3, new java.sql.Date(sdf.parse("01/01/1995").getTime()));
             * st.setDouble(4, 4000.00); st.setInt(5, 3);
             */

            // 2 Ids demo
            // st = conn.prepareStatement("insert into department (Name) values
            // ('D1'),('D2')",
            // Statement.RETURN_GENERATED_KEYS);

            /*
             * if (rowsAffected > 0) { rs = st.getGeneratedKeys(); while (rs.next()) { int
             * id = rs.getInt(1); System.out.println("Done! Id = " + id); } } else {
             * System.out.println("No rows affected!"); }
             */

            // Update data
            /*
             * st = conn.prepareStatement( "UPDATE seller " +
             * "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(DepartmentId = ?)");
             * 
             * st.setDouble(1, 200.00); st.setInt(2, 2);
             */

            // Deleting data
            st = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");
            st.setInt(1, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage()); // Custom exception
        }
        /*
         * catch (ParseException e) { e.printStackTrace(); }
         */
        finally {
            // DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}

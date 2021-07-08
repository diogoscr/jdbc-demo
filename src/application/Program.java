package application;

import db.DB;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        // Statement st = null;

        try {
            conn = DB.getConnection();

            // Retrieve data query
            /*
             * st = conn.createStatement(); rs =
             * st.executeQuery("select * from department"); while (rs.next())
             * {System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));}
             */

            // Insert data operation
            st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "John Doe");
            st.setString(2, "johndoe@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("01/01/1995").getTime()));
            st.setDouble(4, 4000.00);
            st.setInt(5, 3);

            // 2 Ids demo
            // st = conn.prepareStatement("insert into department (Name) values
            // ('D1'),('D2')",
            // Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}

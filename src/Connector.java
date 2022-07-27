import java.sql.*;

public class Connector {


    static Connection con = null;

    public static Connection getCon(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_entry", "root", "" );
            System.out.println("Success...");
        }catch (Exception ex){

        }

        return con;
    }

}

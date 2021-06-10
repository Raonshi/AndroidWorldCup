package utill;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtill {
	 public static Connection getConnection() {
         try {
            String dbURL = "jdbc:mysql://localhost:3306/mobileworldcup?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String dbID = "root";
            String dbPassword = "1234";
            Connection con = null;
            con=DriverManager.getConnection(dbURL, dbID, dbPassword);
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("연결 완료");
            return con;
         } catch (Exception e) {
            e.printStackTrace();
         }
         return null;
      }

}

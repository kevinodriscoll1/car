package ie.cork;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/CarpoolServlet")
public class CarpoolServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private Statement stmt;
    private Connection conn;
    private String testString;
    
    public CarpoolServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String dbItem = getFromDB();
    	response.getOutputStream().print(dbItem);
    }
 
    private String getFromDB() {
    	try{
            Class.forName("com.mysql.jdbc.Driver");
            //this.conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/r00072609a?user=r00072609a&password=garfield12");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?user=Kev&password=garfield12");

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM TestTable";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
               testString = rs.getString("testString");
            }

            rs.close();
            stmt.close();
            conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }catch(Exception e){
            e.printStackTrace();
         }finally{
            try{
               if(stmt!=null)
                  stmt.close();
            }catch(SQLException se2){
            try{
               if(conn!=null)
               conn.close();
            }catch(SQLException se){
               se.printStackTrace();
            }
            }
         }
		return testString;

	}
 
}
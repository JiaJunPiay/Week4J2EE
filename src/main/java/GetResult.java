import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.OracleDriver;

/**
 * Servlet implementation class GetResult
 */
public class GetResult extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = null;
			PreparedStatement pstmt;
			ResultSet rs;
			
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("Driver register sucessfully!");
			
			//Connection to database
			con = DriverManager.getConnection("jdbc:oracle:thin:@//127.0.0.1:1521/XE", "SYSTEM", "Car.fix-99");
			System.out.println("Connected to the database");
			
			pstmt = con.prepareStatement("select * from STUDENT where id = ?");
			//getParameter is the name of the html elements via the etc(name="id" at the input tag)
			//So when getting the parameter on java, it can be linked to the id
			int tempId = Integer.parseInt(request.getParameter("id"));
			pstmt.setInt(1, tempId);
		
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int marks1 = rs.getInt(3);
				int marks2 = rs.getInt(4);
				int marks3 = rs.getInt(5);
				
				PrintWriter pw = response.getWriter();
				pw.print(id + "	" + name+"	" +marks1 + "	" + marks2 +"	" + marks3 );
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

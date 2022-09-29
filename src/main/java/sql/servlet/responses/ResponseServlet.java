package sql.servlet.responses;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/sendQuery")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static final String ADD_EMPLOYEE_QUERY = 
            "INSERT INTO employees VALUES (?, ?, ?, 'x4611', 'mpatterso@classicmodelcars.com', 1, 1002, 'VP Sales')";
    private static final String DISPLAY_TABLE_QUERY = "SELECT employeeNumber, lastName, firstName FROM employees"; 
      @Override
      public void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {

         PrintWriter writer = null;
         Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         boolean flag = false;
         
         // Query Variables
         int empNum = 0;
         String lastName = "";
         String firstName = "";

         // set content type
         res.setContentType("text/html");
         // get Writer
         writer = res.getWriter();

         // Get data from the form
         empNum = Integer.parseInt(req.getParameter("empNum"));
         lastName = req.getParameter("lastName");
         firstName = req.getParameter("firstName");

	   //Build and establish a connection to the MySQL database
	   String url = "jdbc:mysql://localhost:3306/classicmodels"; // Database to link to
	   // MySQL credentials
	   String username = "root";
	   String password = "root";
	   
	   
	   // Imports styling for the table display
	   writer.print("<!DOCTYPE html>\r\n"
	   		+ "<html>\r\n"
	   		+ "\r\n"
	   		+ "<head>\r\n"
	   		+ "    <meta charset=\"ISO-8859-1\">\r\n"
	   		+ "    <title>Query Result Page</title>\r\n"
	   		+ "    <style>\r\n"
	   		+ "    .hidden {\r\n"
	   		+ "\r\n"
	   		+ "        display: none;\r\n"
	   		+ "    }\r\n"
	   		+ "    .displayTable{\r\n"
	   		+ "            border: 1px solid black;\r\n"
	   		+ "            border-collapse: collapse;\r\n"
	   		+ "        \r\n"
	   		+ "\r\n"
	   		+ "    }\r\n"
	   		+ "    </style>\r\n"
	   		+ "</head>");

         try {
            // register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // create JDBC connection
            con = DriverManager.getConnection(url, username, password);
            // compile SQL query and store it in
            // PreparedStatement object
            if (con != null)
               ps = con.prepareStatement(ADD_EMPLOYEE_QUERY);
            // set input value to query parameter
            if (ps != null)
               ps.setInt(1, empNum);
               ps.setString(2, firstName);
               ps.setString(3, lastName);
               
            // Add the employee
            if (ps != null)
               ps.executeUpdate();
            
            // Print and display the table
            Statement st = con.createStatement();
            if (con != null)
            	rs = st.executeQuery(DISPLAY_TABLE_QUERY);
            
            writer.print("<body>");
            if (rs != null) {
            	writer.print("<table class=\"displayTable\">\r\n"
                  		+ "        <tr class=\"displayTable\">\r\n"
                  		+ "            <th class=\"displayTable\">Employee Number</th>\r\n"
                  		+ "            <th class=\"displayTable\">Last Name</th>\r\n"
                  		+ "            <th class=\"displayTable\">First Name</th>\r\n"
                  		+ "        </tr>");
               while (rs.next()) {
                  // Creates a table with HTML and inputs the query values
                  flag = true;
                  writer.print("<tr class=\"displayTable\" >\r\n"
                  		+ "<td class=\"displayTable\">" + rs.getInt("employeeNumber") + "</td>\r\n"
                  		+ "<td class=\"displayTable\">" + rs.getString("lastName") + "</td>\r\n"
                  		+ "<td class=\"displayTable\">" + rs.getString("firstName") +"</td>\r\n"
                  		+ "</tr>");
               }
            }

            // Item not found
            if (!flag) {
               writer.println("<h1>Employee Not Found</h1>");
            }

         } catch (SQLException se) {
            se.printStackTrace();
            writer.println("Error Occured");
         } catch (Exception e) {
            e.printStackTrace();
            writer.println("Unknown Exception Occured");
         } finally {
            // close JDBC connection
            try {
               if (rs != null)
                  rs.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
            try {
               if (ps != null)
                  ps.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }
            try {
               if (con != null)
                  con.close();
            } catch (SQLException se) {
               se.printStackTrace();
            }

            // Link to home
            writer.println("<h3><a href='http://localhost:8080/ServletWebProject/index.jsp'>Submit Another Query?</a></h3>");
            writer.print("</body>\r\n"
            		+ "</html>");
            // close stream
            writer.close();
         }
      }

      @Override
      public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
         doGet(req, res);
      }

}

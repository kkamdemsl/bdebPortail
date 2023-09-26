package porteilbdeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {

	// Cr�er une connexion de pool (DataSource) pour �viter des encombrements lors des sollicitations

	// 1- D�finir un DataSource (ou Connection Pool = pool de connexion)
	@Resource(name="jdbc/bdebstudents") // Injection de ressource (mapping) %context.xml
	private DataSource datasource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1- Configurer PrintWriter pour afficher le r�sultat de la requ�te sql
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");  //text/plain ==> donn�es de la BD 
		
		//2- �tablir la connexion � la BD
		Connection con = null;
		Statement stat = null;
		ResultSet result = null;
		
		try {
			con = datasource.getConnection();
			
		//3- Cr�er la requ�te sql pour chercher les donn�es transport�s par statement
			String requete = "SELECT * FROM student";
			stat = con.createStatement(); 
		
		//4- Ex�cuter la requ�te sql
			result = stat.executeQuery(requete);
		
		//5- Traiter le r�sultat
			while(result.next()) {
				String email = result.getString("email"); 
				out.println(email);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

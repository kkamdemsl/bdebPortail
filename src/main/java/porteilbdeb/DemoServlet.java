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

	// Créer une connexion de pool (DataSource) pour éviter des encombrements lors des sollicitations

	// 1- Définir un DataSource (ou Connection Pool = pool de connexion)
	@Resource(name="jdbc/bdebstudents") // Injection de ressource (mapping) %context.xml
	private DataSource datasource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1- Configurer PrintWriter pour afficher le résultat de la requête sql
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");  //text/plain ==> données de la BD 
		
		//2- Établir la connexion à la BD
		Connection con = null;
		Statement stat = null;
		ResultSet result = null;
		
		try {
			con = datasource.getConnection();
			
		//3- Créer la requête sql pour chercher les données transportés par statement
			String requete = "SELECT * FROM student";
			stat = con.createStatement(); 
		
		//4- Exécuter la requête sql
			result = stat.executeQuery(requete);
		
		//5- Traiter le résultat
			while(result.next()) {
				String email = result.getString("email"); 
				out.println(email);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package porteilbdeb;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	//Créer un StudentModel
	private StudentModel studentModel;
	
	//Injecter DataSource 
	@Resource(name="jdbc/bdebstudents") //
	private DataSource dataSource;
	
	//
	@Override
	public void init() {
		studentModel = new StudentModel(dataSource); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Récupérer la valeur du paramètre "command"
		String maCommande = request.getParameter("command");
		
		// Si la commande n'est pas spécifiée
		if(maCommande==null) {
			maCommande = "LISTE";
		}
		
		// Rediriger vers la méthode appropriée
		switch(maCommande){
		case "LISTE":			
			listEtudiants(request, response);
			break;
		case "ADD":			
			try {
				addStudent(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default :
			listEtudiants(request, response);
		}
		
		
		
		//Créer une méthode
		listEtudiants(request, response);
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		// Lire les données de l'étudiant à ajouter à partir du formulaire
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// Créer un nouveau Objet de type Student pour contenir les données récupérées
		Student myStudent = new Student(firstName, lastName, email); 
		
		// Ajouter l'étudiant dans la BD
		studentModel.addDaoStudent(myStudent);
		
	}

	private void listEtudiants(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> students = studentModel.getStudents();
		request.setAttribute("liste_etudiants", students); //l'objet request transporte la liste students sous l'étiquette liste_etudiant
		
		//Créer l'objet de type RequestDispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listeetudiant.jsp");
		
		//
		dispatcher.forward(request, response);	
		
	}	 

}

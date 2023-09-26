package porteilbdeb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentModel {
	// Cr�er DataSource = pool de connexion � la BD
	private DataSource datasource;

	//Constructeur vide
	public StudentModel() {

	}

	//Constructeur avec parm�tres
	public StudentModel(DataSource datasource) {

		this.datasource = datasource;
	}

	// Method pour recup�rer des donn�es dans BD
	/*
	 * Quelle est la diff�rence entre List et ArrayList? 1) Dans un tableau (Array)
	 * les donn�es sont type homog�ne et sa dimension est statique (fixe) 2) La List
	 * sont de type h�t�rog�ne et dynamique, List est une interface 3) ArrayList est
	 * une classe impl�mentant l'interface List
	 */

	public List<Student> getStudents() {		

		List<Student> students = new ArrayList<>(); // application du polymorphisme
		Connection con = null;
		Statement stat = null;
		ResultSet result = null;

		try {
			con = datasource.getConnection();

			// 3- Cr�er la requ�te sql pour chercher les donn�es transport�s par statement
			String requete = "SELECT * FROM student";
			stat = con.createStatement();

			// 4- Ex�cuter la requ�te sql
			result = stat.executeQuery(requete);

			// 5- Traiter le r�sultat
			while (result.next()) {
				// R�cup�rer les donn�es de la table student
				String email = result.getString("email");
				int id = result.getInt("id");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");

				// Cr�er l'objet student
				Student etudiant = new Student(id, firstName, lastName, email); // ne pas confondre last_name

				// Ajouter l'�tudiant � la liste students
				students.add(etudiant);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(con, stat, result); //pour fermer les ressources lorsqu'on a fini de l'utiliser
		}
		return students;
	}

	private void Close(Connection con, Statement stat, ResultSet result) {
		try {
			if (con != null) {
				con.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDaoStudent(Student myStudent) throws Exception {
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		try {
			//�tablir la connexion avec la BD
			myConnection = datasource.getConnection();
			
			//Cr�er la req�te sql pour inserrer l'�tudiant dans la BD			
			String sql = "insert into student "
					   + "(first_name, last_name, email) "
					   + "values (?, ?, ?)";
			myStatement = myConnection.prepareStatement(sql);
			
			//Assigner les valeurs des param�tres pour l'objet �tudiant cr��
			myStatement.setString(1, myStudent.getFirstName());
			myStatement.setString(2, myStudent.getLastName());
			myStatement.setString(3, myStudent.getEmail());
			
			//Ex�cuter la requ�te sql
			myStatement.execute();
		}
		finally {
			myStatement.close();
			myConnection.close();
		}
		
		
		
	}
}

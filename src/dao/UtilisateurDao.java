package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Utilisateur;

public class UtilisateurDao {
		
	private static String jdbcURL = "jdbc:mysql://localhost:3306/gestionutilisateur?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "";

    private static final String INSERT_USERS = "INSERT INTO utilisateur" + "  (nom, prenom, login, password) VALUES " +
        " (?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,nom,prenom,login, password from utilisateur where id =?";
    private static final String SELECT_ALL_USERS = "select * from utilisateur";
    private static final String DELETE_USERS = "delete from utilisateur where id = ?;";
    private static final String UPDATE_USERS = "update utilisateur set nom = ?,prenom= ?, login =?, password =? where id = ?;";
    private static final String SELECT_LOGIN = "select * from utilisateur where login=? and password=?";
    public UtilisateurDao() {}
    
    
    
    private static Connection getConnection() {
    	Connection connection = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return connection;
    }
    
    public static List <Utilisateur> listAllUsers() {
    	List <Utilisateur> utilisateurs = new ArrayList <Utilisateur> ();
    	try (Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);)
    	{
    		System.out.println(preparedStatement);
    		ResultSet rs = preparedStatement.executeQuery();
    		
    		while (rs.next()) {
    			int id = rs.getInt("id");
    			String nom = rs.getString("nom");
    			String prenom = rs.getString("prenom");
    			String login = rs.getString("login");
    			String password = rs.getString("password");
    			
    			utilisateurs.add(new Utilisateur(id, nom, prenom, login,password));
    			
    		}
    	}
    		catch(SQLException e) {
    			printSQLException(e);
    		}
    		return utilisateurs;
    	}
    
    public static void ajoutUser(Utilisateur user) throws SQLException {
    		System.out.println("Insertion utilisateur");
    		try (Connection connection = getConnection();
    			PreparedStatement preparedStatement	= connection.prepareStatement(INSERT_USERS)){
    			
    			preparedStatement.setString(1, user.getNom());
    			preparedStatement.setString(2, user.getPrenom());
    			preparedStatement.setString(3, user.getLogin());
    			preparedStatement.setString(4, user.getPassword());
    			
    			System.out.println(preparedStatement);
    			preparedStatement.executeUpdate();
    		} catch (SQLException e) {
    			printSQLException(e);
    		}	
    }
    
    public  static Utilisateur getById(int id) {
    	Utilisateur user = null;
    	
    	try(Connection connection = getConnection();
    			PreparedStatement prepareStatement	= connection.prepareStatement(SELECT_USER_BY_ID);){
    			
    			prepareStatement.setInt(1, id);
    			System.out.println(prepareStatement);
    			ResultSet rs = prepareStatement.executeQuery();
    		
    			while (rs.next()) {
   
    				String nom = rs.getString("nom");
        			String prenom = rs.getString("prenom");
        			String login = rs.getString("login");
        			String password = rs.getString("password");
        			
        			user = new Utilisateur(id, nom, prenom, login,password);
    			}			
    	} catch (SQLException e) {
    		printSQLException(e);
    	}
    	return user;
    }
    
    public static boolean modifierUser(Utilisateur user) throws SQLException {
    	boolean isUpdate;
    	
    	try(Connection connection = getConnection();
    			PreparedStatement statement	= connection.prepareStatement(UPDATE_USERS);){
    			
    		statement.setString(1, user.getNom());
			statement.setString(2, user.getPrenom());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getId());
			
			isUpdate = statement.executeUpdate()>0;
			
    	} 
    	System.out.print(isUpdate);
    	return  isUpdate;
    }
    
    public static boolean supprimerUser(int id) throws SQLException {
    	boolean isdeleted;
    	
    	try(Connection connection = getConnection();
    			PreparedStatement prepareStatement	= connection.prepareStatement(DELETE_USERS);) {
    		
    			prepareStatement.setInt(1,  id);
    			isdeleted = prepareStatement.executeUpdate() > 0;
     	}
    			return isdeleted;
    }
    
    public static boolean loginUser(Utilisateur user) throws ClassNotFoundException {
    	
    	boolean status = false;
    	
    	try(Connection connection = getConnection();
    			PreparedStatement statement	= connection.prepareStatement(SELECT_LOGIN);){
    	statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());

            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            status = rs.next();
    	}
    	catch (SQLException e) {
            
            printSQLException(e);
        }
        return status;
	}


    
    
    
    private  static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

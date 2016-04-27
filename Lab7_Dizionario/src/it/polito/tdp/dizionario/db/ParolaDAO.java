package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ParolaDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";
	
	private List<String> permutazioni =new LinkedList<String>();
	
	public List<String> FindAllWords(int n){
		List<String> words = new LinkedList<String>();
		Connection conn; 
		try { 
			conn = DriverManager.getConnection(jdbcURL); 
			String sql= "SELECT * FROM parola WHERE LENGTH(nome)=?";			 
			 
			PreparedStatement st = conn.prepareStatement(sql); 
			 
 			st.setInt(1, n);
			 
			ResultSet res=st.executeQuery(); 
			 
			while(res.next()){ 
				words.add(res.getString("nome"));
			} 
			
			res.close(); 
			conn.close(); 
 			return words;
			 
		} catch (SQLException e) { 
 			// TODO Auto-generated catch block 
 			e.printStackTrace(); 
 		}
		return null;		 	
	}
	
	
	public List<String> FindSimilarWords(String word){
	
		List<String> similarWords = new LinkedList<String>();
		Connection conn; 
		try { 
			conn = DriverManager.getConnection(jdbcURL); 
			String sql= "SELECT nome FROM parola WHERE nome LIKE?";			 
			 
			PreparedStatement st = conn.prepareStatement(sql); 
			 
 			st.setString(1, word);
			 
			ResultSet res=st.executeQuery(); 
			 
			while(res.next()){
				if(!permutazioni.contains(res.getString("nome")))
					similarWords.add(res.getString("nome"));
			} 
			
			res.close(); 
			conn.close(); 
 			return similarWords;
			 
		} catch (SQLException e) { 
 			// TODO Auto-generated catch block 
 			e.printStackTrace(); 
 		}
		return null;	
	}
}

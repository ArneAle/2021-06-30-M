package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interaction;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	
	public List<Integer> getAllChromosome(){
		String sql = "SELECT DISTINCT Chromosome FROM Genes where Chromosome!=0";
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

						
				result.add(res.getInt("Chromosome"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	public List<Interaction>  getSumInteractions(){
		String sql="SELECT tab1.cr1 AS cromosoma1,tab1.cr2 AS cromosoma2,SUM(tab1.Expression_corr) as somma "
				+ "FROM (SELECT i.GeneID1 AS gene1,i.GeneID2 AS gene2,i.`Type` AS tipo,g1.Chromosome AS cr1,g2.Chromosome AS cr2,i.Expression_Corr "
				+ "FROM interactions AS i "
				+ "INNER JOIN genes AS g1 ON i.GeneID1=g1.GeneID "
				+ "INNER JOIN genes AS g2 ON i.GeneID2=g2.GeneID "
				+ "WHERE g1.Chromosome!=0 AND g2.Chromosome!=0 AND  g1.Chromosome!=g2.Chromosome "
				+ "GROUP BY gene1,gene2) as tab1 "
				+ "GROUP BY cr1,cr2";
		
		List<Interaction> result = new ArrayList<Interaction>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

						
				result.add(new Interaction(res.getInt("cromosoma1"),res.getInt("cromosoma2"),res.getDouble("somma")));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		
		
		
	}
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	


	
}

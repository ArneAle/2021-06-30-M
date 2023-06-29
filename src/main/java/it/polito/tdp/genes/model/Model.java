package it.polito.tdp.genes.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	private double min=0;
	private double max=0;
	private Interaction minVal=null;
	private Interaction maxVal=null;
	private GenesDao dao;
	private Graph<Integer, DefaultWeightedEdge> graph; 

	
	
	public Model() {
		super();
		this.dao = new GenesDao();
		
		System.out.println(Eleva(2,10));

		
	}
	public  int Eleva(int base,int esponente) {
		if(esponente==1) return 1;
		return base*Eleva(base,esponente-1);
	}



	public void creaGrafo() {
		graph=new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(graph,dao.getAllChromosome());
		List<Interaction> lista=dao.getSumInteractions();
		
		for(Interaction i:lista) {
			if(i.getSomma()>max) {
				max=i.getSomma();
				maxVal=i;
			}
			if(i.getSomma()<min) {
				min=i.getSomma();
				minVal=i;
			}
			DefaultWeightedEdge edge = Graphs.addEdgeWithVertices(graph, i.getGene1(), i.getGene2());
	        graph.setEdgeWeight(edge,i.getSomma());
		}
		System.out.println(graph.vertexSet().size());
		System.out.println(graph.edgeSet().size());
		
		
	}
	public boolean Soglia(double valore){
		if(valore<=max && valore>=min) {
			return true;
		}
		return false;
	}
	
	
	public String stampaMaggiori(double valore) {
		String stampa="Gli archi con valore maggiore sono: \n";
		List<Interaction> lista=dao.getSumInteractions();
		
		for(Interaction i:lista) {
		  if(valore<i.getSomma()) stampa+=i.getGene1()+" "+i.getGene2()+" con peso: "+i.getSomma()+"\n";
		}
		stampa+="Gli archi con valore minore sono: \n";
		for(Interaction i:lista) {
			  if(valore>i.getSomma()) stampa+=i.getGene1()+" "+i.getGene2()+" con peso: "+i.getSomma()+"\n";
			}
		
		
		return stampa;
	}



	public Interaction getMinVal() {
		return minVal;
	}



	public void setMinVal(Interaction minVal) {
		this.minVal = minVal;
	}



	public Interaction getMaxVal() {
		return maxVal;
	}



	public void setMaxVal(Interaction maxVal) {
		this.maxVal = maxVal;
	}

}
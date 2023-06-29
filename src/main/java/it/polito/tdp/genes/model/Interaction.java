package it.polito.tdp.genes.model;

public class Interaction {
	
	private int gene1;
	private int gene2;
	private double somma;
	
	public Interaction(int gene1, int gene2, double somma) {
		super();
		this.gene1 = gene1;
		this.gene2 = gene2;
		this.somma = somma;
	}
	public int getGene1() {
		return gene1;
	}
	public void setGene1(int gene1) {
		this.gene1 = gene1;
	}
	public int getGene2() {
		return gene2;
	}
	public void setGene2(int gene2) {
		this.gene2 = gene2;
	}
	public double getSomma() {
		return somma;
	}
	public void setSomma(double somma) {
		this.somma = somma;
	}
	

}

package it.polito.tdp.dizionario.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.ParolaDAO;

public class ParolaModel {

	private List<String> words;
	private ParolaDAO pDAO = new ParolaDAO();
	private SimpleGraph<String, DefaultEdge> grafoParole = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

	public ParolaModel() {
		this.words = new LinkedList<String>();
	}
	
	public void FindAllWords(int n){
		words = pDAO.FindAllWords(n);
		for(String str: words)
			grafoParole.addVertex(str);
		
		for(String s: words)
			SimilarWords(s);	
		System.out.println(grafoParole.edgeSet());
	}
	
	public void SimilarWords(String s){
		for(int i=0; i<s.length(); i++){
			String a = s.substring(0, i);
			String b = s.substring(i+1, s.length());
			String result = a+"_"+b;
			for(String tempS:pDAO.FindSimilarWords(result)){
				if(!grafoParole.containsEdge(s, tempS) && tempS.compareTo(s)!=0)
					grafoParole.addEdge(s, tempS);
				
			}
		}
	}
	
		
	
	
}

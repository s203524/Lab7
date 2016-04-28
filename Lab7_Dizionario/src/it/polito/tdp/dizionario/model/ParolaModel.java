package it.polito.tdp.dizionario.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

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
		System.out.println(grafoParole.vertexSet().size());
		System.out.println(grafoParole.edgeSet().size());
	}
	
	public void SimilarWords(String s){
		for(int i=0; i<s.length(); i++){
			String a = s.substring(0, i);
			String b = s.substring(i+1, s.length());
			String result = a+"_"+b;
			for(String tempS: words){
				if(s.compareTo(tempS)!=0){
					String a1 = tempS.substring(0, i);
					String b1 = tempS.substring(i+1, s.length());
					String result1 = a1+"_"+b1;
					if(result.compareTo(result1)==0 && !grafoParole.containsEdge(s, tempS))
						grafoParole.addEdge(s, tempS);
				}
			}
		}
	}
	
	public List<String> FindNeighbor(String daCercare){
		return Graphs.neighborListOf(grafoParole, daCercare);
	}
	
	public List<String> GraphExplorer(String daCercare){
		
		List<String> connected = new LinkedList<String>();
		GraphIterator<String, DefaultEdge> dfv = new DepthFirstIterator<String, DefaultEdge>(grafoParole, daCercare);
		while(dfv.hasNext()){
			connected.add(dfv.next());
		}
		return connected;
	}
}

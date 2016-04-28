package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.ParolaModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	
	private ParolaModel model;
	
	public void setModel(ParolaModel parolaModel) {
		model=parolaModel;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumLettere;

    @FXML
    private TextField txtCercaParola;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnTrovaTutti;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnReset;

    @FXML
    void doFindTuttiConnessi(ActionEvent event) {
    	txtOutput.setText("");
    	String stampa = "";
    	for(String s: model.GraphExplorer(txtCercaParola.getText())){
    		stampa += s + "\n";
    	}
    	txtOutput.setText(stampa);
    }

    @FXML
    void doFindVicini(ActionEvent event) {
    	String s = txtCercaParola.getText();
    	String stampa="";
    	if(s.length()!=Integer.parseInt(txtNumLettere.getText())){
    		txtOutput.setText("Parola con numero lettere errato!");
    		return;
    	}
    	for(String tempS: model.FindNeighbor(s)){
    		stampa+=tempS + "\n"; 
    	}
    	txtOutput.setText(stampa);
    }

    @FXML
    void doGenera(ActionEvent event) {
    	int n = -1;
    	String s = txtNumLettere.getText();
    	try{
    		n = Integer.parseInt(s);
    	}
    	catch(NumberFormatException nfe){
    		txtOutput.setText("Errore nell'inserimento dei dati");
    		return;
    	}
    	if(n!=-1){
    		model.FindAllWords(n);
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtNumLettere.setText("");
    	txtCercaParola.setText("");
    	txtOutput.setText("");
    }

    @FXML
    void initialize() {
        assert txtNumLettere != null : "fx:id=\"txtNumLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtCercaParola != null : "fx:id=\"txtCercaParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnTrovaTutti != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Dizionario.fxml'.";

    }
}

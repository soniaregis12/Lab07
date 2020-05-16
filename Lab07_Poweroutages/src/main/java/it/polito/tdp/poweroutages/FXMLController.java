package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private ComboBox<Nerc> comboBoxNERC;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnWorstCase;

    @FXML
    private TextArea txtResult;

    @FXML
    void doWorstCase(ActionEvent event) {
    	txtResult.clear();
    	
    	model.setMAX_HOURS(Long.parseLong(txtHours.getText()));
    	model.setMAX_YEARS(Integer.parseInt(txtHours.getText()));
    	
    	List<Poweroutage> lista = model.trovaSequenza(comboBoxNERC.getValue());
    	
    	txtResult.appendText("TOT people affected: " + model.getCustomersAffectedHigher() + "\n");
    	txtResult.appendText("TOT hours of outage: " + model.getOreTotali() + "\n");
    	
    	for(Poweroutage p : lista) {
    		txtResult.appendText(p.toString());
    	}
    }

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboBoxNERC != null : "fx:id=\"comboBoxNERC\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWorstCase != null : "fx:id=\"btnWorstCase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	comboBoxNERC.getItems().addAll(this.model.getNercList());
    }
}

  
    

    
    

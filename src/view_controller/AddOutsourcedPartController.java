/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class AddOutsourcedPartController implements Initializable {
	Stage stage;
	Parent scene;
	
	@FXML
	private RadioButton partInHouseRBtn;
	@FXML
	private RadioButton partOutsourcedRBtn;
	@FXML
	private TextField partIdTxt;
	@FXML
	private TextField partNameTxt;
	@FXML
	private TextField partInvTxt;
	@FXML
	private TextField partPriceTxt;
	@FXML
	private TextField partMaxTxt;
	@FXML
	private TextField partMinTxt;
	@FXML
	private TextField partCompanyNameTxt;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		if (partIdTxt.getText() == "Auto Gen - Disabled") {
			partIdTxt.setText("Auto Gen - Disabled");
			partCompanyNameTxt.clear();
		}
	}	

	@FXML
	private void onActionSaveNewPart(ActionEvent event) throws IOException, IOException, IOException {
		try {
			int lastId = 0;
			for (Part part : Inventory.getAllParts()) {
				if (part.getId() > lastId)
					lastId = part.getId();
			}
			
			int id = lastId + 1;
			String name = partNameTxt.getText();
			int stock = Integer.parseInt(partInvTxt.getText());
			double price = Double.parseDouble(partPriceTxt.getText());
			int max = Integer.parseInt(partMaxTxt.getText());
			int min = Integer.parseInt(partMinTxt.getText());
			String companyName = partCompanyNameTxt.getText();

			Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
			
			stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
			stage.setScene(new Scene(scene));
			stage.show();
		}

		catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setContentText("Please enter a valid value for each field!");
			alert.showAndWait();
		}	
	}

	@FXML
	private void onActionDisplayMain(ActionEvent event) throws IOException {
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

    	@FXML
     	void onActionInhouseView(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view_controller/AddInsourcedPart.fxml"));
		loader.load();	
		stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
		Parent scene = loader.getRoot();
		stage.setScene(new Scene(scene));
		stage.show();
     	}	
}

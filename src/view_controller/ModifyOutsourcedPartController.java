/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class ModifyOutsourcedPartController implements Initializable {
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
	}
	
	@FXML
	private void onActionSaveNewPart(ActionEvent event) throws IOException, Exception {
		try {

			int id = Integer.parseInt(partIdTxt.getText());
			String name = partNameTxt.getText();
			int stock = Integer.parseInt(partInvTxt.getText());
			double price = Double.parseDouble(partPriceTxt.getText());
			int max = Integer.parseInt(partMaxTxt.getText());
			int min = Integer.parseInt(partMinTxt.getText());
			String companyName = partCompanyNameTxt.getText();

			Part searchPart = Inventory.lookupPart(id);
			ObservableList<Part> partList = Inventory.getAllParts();
			int index = partList.indexOf(searchPart);
			Inventory.updatePart(index, new Outsourced(id, name, price, stock, min, max, companyName));

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
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	@FXML
	void onActionInhouseView(ActionEvent event) throws IOException {
		
		
		int id = Integer.parseInt(partIdTxt.getText());
		String name = partNameTxt.getText();
		int stock = Integer.parseInt(partInvTxt.getText());
		double price = Double.parseDouble(partPriceTxt.getText());
		int max = Integer.parseInt(partMaxTxt.getText());
		int min = Integer.parseInt(partMinTxt.getText());
		int machineId = -1;
		
		Part part = new InHouse(id, name, price, stock, min, max, machineId); 
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view_controller/ModifyInsourcedPart.fxml"));
		loader.load();	
		ModifyInsourcedPartController partController = loader.getController();
		partController.sendPart(part);
		
		stage = (Stage) ((RadioButton) event.getSource()).getScene().getWindow();
		Parent scene = loader.getRoot();
		stage.setScene(new Scene(scene));
		stage.show();


	}

	public void sendPart(Part part) {
		partIdTxt.setText(String.valueOf(part.getId()));
		partNameTxt.setText(part.getName());
		partInvTxt.setText(String.valueOf(part.getStock()));
		partPriceTxt.setText(String.valueOf(part.getPrice()));
		partMaxTxt.setText(String.valueOf(part.getMax()));
		partMinTxt.setText(String.valueOf(part.getMin()));
		partCompanyNameTxt.setText(((Outsourced) part).getCompanyName());
	}
}

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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class MainMenuController implements Initializable {

	Stage stage;
	Parent scene;
	
	@FXML
	private TextField searchPartTxt;
	@FXML
	private TableView<?> partsTableView;
	@FXML
	private TableColumn<?, ?> partIdCol;
	@FXML
	private TableColumn<?, ?> partNameCol;
	@FXML
	private TableColumn<?, ?> partInventoryLvlCol;
	@FXML
	private TableColumn<?, ?> partPriceCol;
	@FXML
	private TextField searchProductTxt;
	@FXML
	private TableView<?> productsTableView;
	@FXML
	private TableColumn<?, ?> productIdCol;
	@FXML
	private TableColumn<?, ?> productNameCol;
	@FXML
	private TableColumn<?, ?> productInventoryLvlCol;
	@FXML
	private TableColumn<?, ?> productPriceCol;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void onActionSearchPart(ActionEvent event) {
	}

	@FXML
	private void onActionAddPart(ActionEvent event) throws IOException {
	        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/AddInsourcedPart.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();}

	@FXML
	private void onActionModifyPart(ActionEvent event) throws IOException {
	        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/ModifyInsourcedPart.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	@FXML
	private void onActionDeletePart(ActionEvent event) {
	}

	@FXML
	private void onActionSearchProduct(ActionEvent event) {
	}

	@FXML
	private void onActionAddProduct(ActionEvent event) throws IOException {
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/AddProduct.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	@FXML
	private void onActionModifyProduct(ActionEvent event) throws IOException {
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/ModifyProduct.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	@FXML
	private void onActionDeleteProduct(ActionEvent event) {
	}

	@FXML
	private void onActionExit(ActionEvent event) {
		System.exit(0);
	}

	
}

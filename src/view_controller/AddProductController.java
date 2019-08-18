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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class AddProductController implements Initializable {
	Stage stage;
	Parent scene;
	
	@FXML
	private TextField productIdTxt;
	@FXML
	private TextField productNameTxt;
	@FXML
	private TextField productInvTxt;
	@FXML
	private TextField productPriceTxt;
	@FXML
	private TextField productMaxTxt;
	@FXML
	private TextField productMinTxt;
	@FXML
	private TextField productSearchTxt;
	@FXML
	private TableView<?> productAddTableView;
	@FXML
	private TableColumn<?, ?> addPartIdCol;
	@FXML
	private TableColumn<?, ?> addPartNameCol;
	@FXML
	private TableColumn<?, ?> addInventoryLevelCol;
	@FXML
	private TableColumn<?, ?> addPriceCol;
	@FXML
	private TableView<?> productDeleteTableView;
	@FXML
	private TableColumn<?, ?> deletePartIdCol;
	@FXML
	private TableColumn<?, ?> deletePartNameCol;
	@FXML
	private TableColumn<?, ?> deleteInventoryLevelCol;
	@FXML
	private TableColumn<?, ?> deletePriceCol;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void onActionSearchProduct(ActionEvent event) {
	}

	@FXML
	private void onActionAddProduct(ActionEvent event) {
	}

	@FXML
	private void onActionSaveNewProduct(ActionEvent event) {
	}

	@FXML
	private void onActionDeleteProduct(ActionEvent event) {
	}

	@FXML
	private void onActionDisplayMain(ActionEvent event) throws IOException {
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}
	
}

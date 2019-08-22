/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class ModifyProductController implements Initializable {
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
	private TableView<Part> productAddTableView;
	@FXML
	private TableColumn<Part, Integer> addIdCol;
	@FXML
	private TableColumn<Part, String> addNameCol;
	@FXML
	private TableColumn<Part, Integer> addInventoryLevelCol;
	@FXML
	private TableColumn<Part, Double> addPriceCol;
	@FXML
	private TableView<Part> productDeleteTableView;
	@FXML
	private TableColumn<Part, Integer> deleteIdCol;
	@FXML
	private TableColumn<Part, String> deleteNameCol;
	@FXML
	private TableColumn<Part, Integer> deleteInventoryLevelCol;
	@FXML
	private TableColumn<Part, Double> deletePriceCol;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		productAddTableView.setItems(Inventory.getAllParts());
		addIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));	
		addNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));	
		addInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));	
		addPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));	
		
		
		deleteIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));	
		deleteNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));	
		deleteInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));	
		deletePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}	

	private boolean checkForInt(String checkedString) {
		try {
			Integer.parseInt(checkedString);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
	
		}
	}
	
	@FXML
	private void onActionSearchProduct(ActionEvent event) throws Exception {
	  	ObservableList<Part> filteredParts = FXCollections.observableArrayList();
		String searchTxt = productSearchTxt.getText();
		
		if (checkForInt(searchTxt))
			filteredParts.add(Inventory.lookupPart(Integer.parseInt(searchTxt)));
		else
			filteredParts = Inventory.lookupPart(searchTxt);
			
		productAddTableView.setItems(filteredParts);
	}

	@FXML
	private void onActionAddAssocPart(ActionEvent event) throws Exception {
		Part selectedPart = productAddTableView.getSelectionModel().getSelectedItem();	
		int id = 0, max = 0, min = 0, stock = 0;
		String name = "";
		double price = 0;
		
		Product tempProduct = new Product(id, name, price, stock, max, min);
		tempProduct.addAssociatedPart(selectedPart);
		
		if (!productDeleteTableView.getItems().isEmpty()) {
			productDeleteTableView.getItems().forEach((existingAssocParts) -> {
				tempProduct.addAssociatedPart(existingAssocParts);
			});
		}
		
		productDeleteTableView.setItems(tempProduct.getAllAssociatedParts());
		

	}


	@FXML
	private void onActionSave(ActionEvent event) throws IOException, Exception {
		try {
			int id = Integer.parseInt(productIdTxt.getText());
			String name = productNameTxt.getText();
			int stock = Integer.parseInt(productInvTxt.getText());
			double price = Double.parseDouble(productPriceTxt.getText());
			int max = Integer.parseInt(productMaxTxt.getText());
			int min = Integer.parseInt(productMinTxt.getText());
			
			Product newProduct = new Product(id, name, price, stock, max, min);
		

			productDeleteTableView.getItems().forEach((existingAssocParts) -> {
				newProduct.addAssociatedPart(existingAssocParts);});
			
			System.out.println(newProduct.getAllAssociatedParts().get(0));
			
			Product searchProduct = Inventory.lookupProduct(id);
			int index = Inventory.getAllProducts().indexOf(searchProduct);
			Inventory.updateProduct(index, newProduct);
			
			stage = (Stage)((Button)event.getSource()).getScene().getWindow();
			scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
			stage.setScene(new Scene(scene));
			stage.show();
		} catch (IndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No parts associated");
			alert.setContentText("Each product must have at least one associated part.  The bottom table contains this products currently associated parts.");
			
			alert.showAndWait();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setContentText("Please enter a valid value for each field!");
			alert.showAndWait();
		}	
	}

	@FXML
	private void onActionDeleteAssocPart(ActionEvent event) {
		Part selectedPart = productDeleteTableView.getSelectionModel().getSelectedItem();
		productDeleteTableView.getItems().remove(selectedPart);
	}

	@FXML
	private void onActionDisplayMain(ActionEvent event) throws IOException {
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}
	
	public void sendProduct(Product product) {
		productIdTxt.setText(String.valueOf(product.getId()));
		productNameTxt.setText(product.getName());
		productInvTxt.setText(String.valueOf(product.getStock()));
		productPriceTxt.setText(String.valueOf(product.getPrice()));
		productMaxTxt.setText(String.valueOf(product.getMax()));
		productMinTxt.setText(String.valueOf(product.getMin()));
		productDeleteTableView.setItems(product.getAllAssociatedParts());
	
	}
}

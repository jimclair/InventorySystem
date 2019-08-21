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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InhousePart;
import model.Inventory;
import model.Product;

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
	private TableView<Product> productAddTableView;
	@FXML
	private TableColumn<Product, Integer> addProductIdCol;
	@FXML
	private TableColumn<Product, String> addProductNameCol;
	@FXML
	private TableColumn<Product, Integer> addInventoryLevelCol;
	@FXML
	private TableColumn<Product, Double> addPriceCol;
	@FXML
	private TableView<Product> productDeleteTableView;
	@FXML
	private TableColumn<Product, Integer> deleteProductIdCol;
	@FXML
	private TableColumn<Product, String> deleteProductNameCol;
	@FXML
	private TableColumn<Product, Integer> deleteInventoryLevelCol;
	@FXML
	private TableColumn<Product, Double> deletePriceCol;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		productAddTableView.setItems(Inventory.getAllProducts());
		addProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));	
		addProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));	
		addInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));	
		addPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));	
		
		productDeleteTableView.setItems(Inventory.getAllProducts());
		deleteProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));	
		deleteProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));	
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
	  	ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
		String searchTxt = productSearchTxt.getText();
		
		if (checkForInt(searchTxt))
			filteredProducts.add(Inventory.lookupProduct(Integer.parseInt(searchTxt)));
		else
			filteredProducts = Inventory.lookupProduct(searchTxt);
			
		productAddTableView.setItems(filteredProducts);
		productDeleteTableView.setItems(filteredProducts);
	}

	@FXML
	private void onActionAddProduct(ActionEvent event) throws Exception {
		int id = Integer.parseInt(productIdTxt.getText());
		String name = productNameTxt.getText();
		int stock = Integer.parseInt(productInvTxt.getText());
		double price = Double.parseDouble(productPriceTxt.getText());
		int max = Integer.parseInt(productMaxTxt.getText());
		int min = Integer.parseInt(productMinTxt.getText());
		
		Product product = new Product(id, name, price, stock, max, min);
	

		if(Inventory.lookupProduct(id) == null) 
			Inventory.addProduct(product);	
		

	}

	@FXML
	private void onActionSaveNewProduct(ActionEvent event) throws IOException, Exception {
		int id = Integer.parseInt(productIdTxt.getText());
		String name = productNameTxt.getText();
		int stock = Integer.parseInt(productInvTxt.getText());
		double price = Double.parseDouble(productPriceTxt.getText());
		int max = Integer.parseInt(productMaxTxt.getText());
		int min = Integer.parseInt(productMinTxt.getText());
		
		Product product = new Product(id, name, price, stock, max, min);
		
		if(Inventory.lookupProduct(id) == null) 
			Inventory.addProduct(product);					
		
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	@FXML
	private void onActionDeleteProduct(ActionEvent event) {
		Product selectedProduct = productDeleteTableView.getSelectionModel().getSelectedItem();
		Inventory.deleteProduct(selectedProduct);
	}

	@FXML
	private void onActionDisplayMain(ActionEvent event) throws IOException {
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}
	
}

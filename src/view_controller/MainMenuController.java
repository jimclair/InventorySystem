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
import model.Part;
import model.Product;

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
	private TableView<Part> partsTableView;
	@FXML
	private TableColumn<Part, Integer> partIdCol;
	@FXML
	private TableColumn<Part, String> partNameCol;
	@FXML
	private TableColumn<Part, Integer> partInventoryLvlCol;
	@FXML
	private TableColumn<Part, Double> partPriceCol;
	@FXML
	private TextField searchProductTxt;
	@FXML
	private TableView<Product> productsTableView;
	@FXML
	private TableColumn<Product, Integer> productIdCol;
	@FXML
	private TableColumn<Product, String> productNameCol;
	@FXML
	private TableColumn<Product, Integer> productInventoryLvlCol;
	@FXML
	private TableColumn<Product, Double> productPriceCol;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		partsTableView.setItems(Inventory.getAllParts());
		partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));	
		partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));	
		partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));	
		partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));	
		
		
		productsTableView.setItems(Inventory.getAllProducts());
		productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));	
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));	
		productInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));	
		productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));	
		
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
	private void onActionSearchPart(ActionEvent event) throws Exception {
		ObservableList<Part> filteredParts = FXCollections.observableArrayList();
		String searchTxt = searchPartTxt.getText();
		
		if (checkForInt(searchTxt))
			filteredParts.add(Inventory.lookupPart(Integer.parseInt(searchTxt)));
		else
			filteredParts = Inventory.lookupPart(searchTxt);
			
		partsTableView.setItems(filteredParts);	
	}

	@FXML
	private void onActionAddPart(ActionEvent event) throws IOException {
	        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/view_controller/AddInsourcedPart.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();}

	@FXML
	private void onActionModifyPart(ActionEvent event) throws IOException {
		Part part = partsTableView.getSelectionModel().getSelectedItem();
		FXMLLoader loader = new FXMLLoader();	
		
		if (part instanceof InhousePart) {
			loader.setLocation(getClass().getResource("/view_controller/ModifyInsourcedPart.fxml"));
			loader.load();
			ModifyInsourcedPartController partController = loader.getController();
			partController.sendPart(part);
			

			
		}
		else {
			loader.setLocation(getClass().getResource("/view_controller/ModifyOutsourcedPart.fxml"));
			loader.load();
			ModifyOutsourcedPartController partController = loader.getController();
			partController.sendPart(part);

		}
		
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		Parent scene = loader.getRoot();
		stage.setScene(new Scene(scene));
		stage.show();

	}
			
	@FXML
	private void onActionDeletePart(ActionEvent event) {
		Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
		Inventory.deletePart(selectedPart);
	}

	@FXML
	private void onActionSearchProduct(ActionEvent event) throws Exception {
  		ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
		String searchTxt = searchProductTxt.getText();
		
		if (checkForInt(searchTxt))
			filteredProducts.add(Inventory.lookupProduct(Integer.parseInt(searchTxt)));
		else
			filteredProducts = Inventory.lookupProduct(searchTxt);
			
		productsTableView.setItems(filteredProducts);	
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

		Product product = productsTableView.getSelectionModel().getSelectedItem();
		FXMLLoader loader = new FXMLLoader();	
		loader.setLocation(getClass().getResource("/view_controller/ModifyProduct.fxml"));
		loader.load();
		ModifyProductController productController = loader.getController();
		productController.sendProduct(product);
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		Parent scene = loader.getRoot();
		stage.setScene(new Scene(scene));
		stage.show();
}

	@FXML
	private void onActionDeleteProduct(ActionEvent event) {
		Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
		Inventory.deleteProduct(selectedProduct);
	}

	@FXML
	private void onActionExit(ActionEvent event) {
		System.exit(0);
	}

	
}

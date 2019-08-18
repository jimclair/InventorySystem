/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author james.clair
 */
public class Inventory {
	private static ObservableList<Part> allParts = FXCollections.observableArrayList();
	private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

	public void addPart(Part newPart) {
		
	}

	public void addProduct(Product newProduct) {
	
	}

	public Part lookupPart() {
		return null;
	
	}

	public Product lookupProduct(int productId){
		return null;
	
	}

	public ObservableList<Part> lookupPart(String partName) {
		return null;
	
	}
	
	public ObservableList<Product> lookupProduct(String productName) {
		return null;
	
	}
	
	public void updatePart(int index, Part selectedPart) {
	
	}

	public void updateProduct(int index, Product selectedProduct) {
	
	}

	public void deletePart(Part selectedPart) {
	
	}

	public void deleteProduct(Product selectedProduct) {
	
	}

	public ObservableList<Part> getAllParts() {
		return null;
	
	}

	public ObservableList<Product> getAllProducts() {
		return null;
	
	}



}

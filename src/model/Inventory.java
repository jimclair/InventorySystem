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

	public static void addPart(Part newPart) {
		allParts.add(newPart);
	}

	public static void addProduct(Product newProduct) {
		allProducts.add(newProduct);
	}

	public static Part lookupPart(int partId) throws Exception {
		
		
		for (Part part : allParts)	{
			if (part.getId() == partId) {
				return part;	
			}	
		}
		return null;	
	}

	public static Product lookupProduct(int productId) throws Exception{
		return null;
	}
	public static ObservableList<Part> lookupPart(String partName) {
		ObservableList<Part> filteredParts = FXCollections.observableArrayList();	

		
		for (Part part : allParts)	{
			if (part.getName().contains(partName)) {
				filteredParts.add(part);	
			}	
		}
		return filteredParts;	
	}
	
	public static ObservableList<Product> lookupProduct(String productName) {
		return null;
	
	}
	
	public static void updatePart(int index, Part selectedPart) {
		allParts.remove(index);
		addPart(selectedPart);
	}

	public static void updateProduct(int index, Product selectedProduct) {
	
	}

	public static void deletePart(Part selectedPart) {
		allParts.remove(selectedPart);
	}

	public static void deleteProduct(Product selectedProduct) {
	
	}

	public static ObservableList<Part> getAllParts() {
		return allParts;
	
	}

	public static ObservableList<Product> getAllProducts() {
		return null;
	
	}



}

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
public class Product extends Inventory {
	
	ObservableList<Part> associatedParts = FXCollections.observableArrayList();
	int id;
	String name;
	double price;
	int stock;
	int min;
	int max;

	public Product(int id, String name, double price, int stock, int min, int max) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
	}
	
	public ObservableList<Part> getAllAssociatedParts() {
		return associatedParts;
	}

	public void addAssociatedPart(Part associatedPart) {
		associatedParts.add(associatedPart);
	}

	public void deleteAssociatedPart(Part associatedPart) {
		associatedParts.remove(associatedPart);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public void setAssociatedParts(ObservableList<Part> associatedParts) {
		this.associatedParts = associatedParts;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	
}

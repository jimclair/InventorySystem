/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_jamesclair;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InhousePart;
import model.Inventory;
import model.OutsourcedPart;
import model.Product;
/**
 *
 * @author james.clair
 */
public class InventorySystem extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view_controller/MainMenu.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Inventory.addPart(new InhousePart(1, "test part 1", 5.00, 5, 2, 3, 14));
		Inventory.addPart(new OutsourcedPart(2, "test part 2", 6.00, 5, 2, 3, "company name"));
		
		Inventory.addProduct(new Product(1, "test part 1", 5.00, 5, 2, 3));
		
		launch(args);
	}
	
}

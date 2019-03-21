/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Menu extends Application implements EventHandler<ActionEvent>{

    Stage menuAll;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menu");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 700, 500);
        // Start Element Node 
        HBox hBox = new HBox();
        Text text = new Text("Menu Vehicule");
        Label label = new Label("Insertion ===>");
        Label label2 = new Label("Recherche ===>");
        Label label3 = new Label("Modification ===>");
        Button button = new Button("Insertion");
        Button button2 = new Button("Recherche");
        Button button3 = new Button("Modification");
        // End Element Node 
        // Start Style Element //
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        root.setVgap(35);
        root.setHgap(15);
        hBox.setAlignment(Pos.CENTER);
        text.setFont(Font.font("Edwardian Script ITC", 80));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setUnderline(true);

        label.setStyle("-fx-font: normal bold 20px 'serif' ");
        label2.setStyle("-fx-font: normal bold 20px 'serif' ");
        label3.setStyle("-fx-font: normal bold 20px 'serif' ");
        button.setMinSize(100, 30);
        button.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        button2.setMinSize(100, 30);
        button2.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        button3.setMinSize(100, 30);
        button3.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        // End Style Element //

        //Action View
        //Recherche
        button.setOnAction((event) -> {
            Insertion in = new Insertion();
            menuAll.close();
            try {
                in.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error "+ex);
            }
        });
        button2.setOnAction((event) -> {
           
            menuAll.close();
            try {
                 FindElement fe = new FindElement();
                fe.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error "+ex);
            }
        });
          //Modification
        button3.setOnAction((event) -> {
            
            
            try {
                Modification modification = new Modification();
                primaryStage.close();
                modification.start(new Stage());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
        // End Action View
        hBox.getChildren().add(text);
        root.add(hBox, 1, 0, 1, 1);
        root.add(label, 1, 4);
        root.add(button, 2, 4);
        root.add(label2, 1, 5);
        root.add(button2, 2, 5);
        root.add(label3, 1, 6);
        root.add(button3, 2, 6);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        menuAll = primaryStage;
        //Element Action //
        //Insertion
       
      
    }

    @Override
    public void handle(ActionEvent event) {
    }

}

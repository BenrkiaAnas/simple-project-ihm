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
import javafx.scene.control.TextField;
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
public class Insertion extends Application implements EventHandler<ActionEvent> {

    Stage insertionAll;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Insertion");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 700, 650);
        // Start Element Node 
        GridPane gridPane = new GridPane();
        HBox hBox = new HBox();
        Text text = new Text("Insertion");
        Button menu = new Button("Menu");
        Label VEHICULE = new Label("INSERER VEHICULE");
        Label KILOMETRAGE = new Label("INSERER KILOMETRAGE");
        Label VIDENGE = new Label("INSERER VIDENGE");
        Label VIDENGETYPE = new Label("INSERER VIDENGE TYPE");
        Label TYPECARBURANT = new Label("INSERER TYPE DE CARBURANT");
        Label MODELE = new Label("INSERER MODELE");
        Label MARQUE = new Label("INSERER MARQUE");
        Button vehiculeBtn = new Button("Aller");
        Button KILOMETRAGEBtn = new Button("Aller");
        Button VIDENGEBtn = new Button("Aller");
        Button VIDENGETYPEBtn = new Button("Aller");
        Button TYPECARBURANTBtn = new Button("Aller");
        Button MODELEBtn = new Button("Aller");
        Button MARQUEBtn = new Button("Aller");

        // End Element Node 
        // Start Style Element //
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  20px 30px");
        root.setVgap(30);
        root.setHgap(15);
        hBox.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(15);
        hBox.setStyle("-fx-margin : 0 50px");
        text.setFont(Font.font("Edwardian Script ITC", 80));
        text.setUnderline(true);
        text.setTextAlignment(TextAlignment.CENTER);
        menu.setMinSize(100, 30);
        menu.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        VEHICULE.setStyle("-fx-font: normal bold 20px 'serif' ");
        KILOMETRAGE.setStyle("-fx-font: normal bold 20px 'serif' ");
        VIDENGE.setStyle("-fx-font: normal bold 20px 'serif' ");
        VIDENGETYPE.setStyle("-fx-font: normal bold 20px 'serif' ");
        TYPECARBURANT.setStyle("-fx-font: normal bold 20px 'serif' ");
        MODELE.setStyle("-fx-font: normal bold 20px 'serif' ");
        MARQUE.setStyle("-fx-font: normal bold 20px 'serif' ");
        vehiculeBtn.setMinSize(100, 30);
        vehiculeBtn.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        KILOMETRAGEBtn.setMinSize(100, 30);
        KILOMETRAGEBtn.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        VIDENGEBtn.setMinSize(100, 30);
        VIDENGEBtn.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        VIDENGETYPEBtn.setMinSize(100, 30);
        VIDENGETYPEBtn.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        TYPECARBURANTBtn.setMinSize(100, 30);
        TYPECARBURANTBtn.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        MODELEBtn.setMinSize(100, 30);
        MODELEBtn.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        MARQUEBtn.setMinSize(100, 30);
        MARQUEBtn.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        // End Style Element //
        hBox.getChildren().add(text);
        root.add(hBox, 1, 0, 1, 1);
        root.addRow(1, menu);
        root.add(gridPane, 1, 2);
        gridPane.addRow(0, VEHICULE, vehiculeBtn);
        gridPane.addRow(1, KILOMETRAGE, KILOMETRAGEBtn);
        gridPane.addRow(2, VIDENGE, VIDENGEBtn);
        gridPane.addRow(3, VIDENGETYPE, VIDENGETYPEBtn);
        gridPane.addRow(4, TYPECARBURANT, TYPECARBURANTBtn);
        gridPane.addRow(5, MODELE, MODELEBtn);
        gridPane.addRow(6, MARQUE, MARQUEBtn);
        primaryStage.setScene(scene);
        primaryStage.show();
        //        insertionAll = primaryStage;
        // Start The Action Button 
        menu.setOnAction((event) -> {
            primaryStage.close();
            try {
                Menu menu1 = new Menu();
                menu1.start(new Stage());

            } catch (Exception ex) {
                System.out.println("erore" + ex);
            }
        });

        vehiculeBtn.setOnAction((event) -> {

            primaryStage.close();
            try {
                VehiculeView vehiculeView = new VehiculeView();
                vehiculeView.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }
        });

        KILOMETRAGEBtn.setOnAction((event) -> {

            primaryStage.close();
            try {
                KilometrageView kilometrageView = new KilometrageView();
                kilometrageView.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }
        });
        VIDENGEBtn.setOnAction((event) -> {

            primaryStage.close();
            try {
                VidengeView videngeView = new VidengeView();
                videngeView.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }

        });
        VIDENGETYPEBtn.setOnAction((event) -> {

            primaryStage.close();
            try {
                VidengeTypeView videngeTypeView = new VidengeTypeView();
                videngeTypeView.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }
        });
        TYPECARBURANTBtn.setOnAction((event) -> {
            CarburantView carburantView = new CarburantView();
            primaryStage.close();
            try {
                carburantView.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }

        });
        MODELEBtn.setOnAction((event) -> {
            
            primaryStage.close();
            try {
                ModelView modelView = new ModelView();
                modelView.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }
        });
        MARQUEBtn.setOnAction((event) -> {
            MarqueView marqueView = new MarqueView();
            primaryStage.close();
            try {
                marqueView.start(new Stage());
            } catch (Exception ex) {
                System.out.println("error " + ex);
            }

        });

    }

    @Override
    public void handle(ActionEvent event) {
    }

}

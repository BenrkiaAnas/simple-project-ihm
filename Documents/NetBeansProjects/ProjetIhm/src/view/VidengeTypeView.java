/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.VidengeType;
import helper.VidengeTypeHelper;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import static javax.management.Query.value;
import service.VidengeTypeService;
import util.AlertUtil;

/**
 *
 * @author gouss
 */
public class VidengeTypeView extends Application {

    AlertUtil alertUtil = new AlertUtil();
    VidengeTypeService videngeTypeService = new VidengeTypeService();
    VidengeTypeHelper typeHelper;
    List<VidengeType> videngeTypes;

    public VidengeTypeView() throws Exception {
        init();
    }

    @Override
    public void init() throws Exception {
        initComponents();
        initHelper();
    }

    public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns().size()));
            });
        }

        AtomicLong hight = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
    }

    @Override
    public void start(Stage primaryStage) {
        tab.setMaxHeight(100);
        tab.setColumnResizePolicy((param) -> true);
        Platform.runLater(() -> customResize(tab));

        HBox hBox = new HBox();
        GridPane gridPane = new GridPane();

        titre.setFont(Font.font("Edwardian Script ITC", 80));
        // gridPane.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        gridPane.setVgap(30);
        gridPane.setHgap(15);

        titre.setTextAlignment(TextAlignment.CENTER);
        titre.setUnderline(true);
        menu.setMinSize(100, 30);
        menu.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        fieldType.setMinSize(80, 30);
        fieldType.setPromptText("Entrer Type De Carburant");
        button.setMinSize(100, 30);
        button.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        hBox.getChildren().add(titre);
        gridPane.add(hBox, 1, 0, 1, 1);
        gridPane.addRow(1, menu);
        gridPane.addRow(2, type, fieldType);
        gridPane.addRow(3, prixMax, fieldPrixMaw);
        gridPane.addRow(4, prixMin, fieldPrixMin);
        gridPane.add(button, 1, 5, 1, 1);

        GridPane root = new GridPane();

        root.addRow(0, gridPane);
        root.addRow(1, tab);
        root.setVgap(20);
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");

        Scene scene = new Scene(root);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        button.setOnAction(event -> {
            boolean typeV = verifie(fieldType);
            boolean prixMaxV = verifie(fieldPrixMaw);
            boolean prixMinV = verifie(fieldPrixMin);
            if (typeV == true && prixMaxV == true && prixMinV == true) {
                String typet = fieldType.getText();
                double prixMaxx = new Double(fieldPrixMaw.getText());
                double prixMinn = new Double(fieldPrixMin.getText());
                VidengeType videngeType = videngeTypeService.creatVidengeType(typet, prixMaxx, prixMinn);
                typeHelper.create(videngeType);
                alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " Type-Videnge inserer Avec Succés");
                fieldPrixMaw.clear();
                fieldPrixMin.clear();
                fieldType.clear();
                fieldPrixMaw.setStyle("-fx-background-color: white;");
                fieldPrixMin.setStyle("-fx-background-color: white;");
                fieldType.setStyle("-fx-background-color: white;");
            } else {
                alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "erroorr");
                fieldPrixMaw.clear();
                fieldPrixMin.clear();
                fieldType.clear();
                fieldPrixMaw.setStyle("-fx-background-color: white;");
                fieldPrixMin.setStyle("-fx-background-color: white;");
                fieldType.setStyle("-fx-background-color: white;");
            }

        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initComponents() {
        type = new Label("Type videnge");
        prixMax = new Label("prixMax");
        prixMin = new Label("prixMin");

        fieldType = new TextField();
        fieldPrixMaw = new TextField();
        fieldPrixMin = new TextField();

        tab = new TableView();

        titre = new Text("Type Videnge");

        button = new Button("inserer");
        menu = new Button("Menu");

    }
    Label type;
    Label prixMax;
    Label prixMin;

    TextField fieldType;
    TextField fieldPrixMaw;
    TextField fieldPrixMin;

    TableView tab;

    Text titre;

    Button button;
    Button menu;

    private void initHelper() {
        videngeTypes = videngeTypeService.findAll();

        typeHelper = new VidengeTypeHelper(tab, videngeTypes);

    }

    private boolean verifie(TextField field) {
        if (field.getText().isEmpty() || field.getText().equals("")) {
            field.setStyle("-fx-background-color: red;");
            return false;

        } else {
            field.setStyle("-fx-background-color: green;");
            return true;
        }
    }

}

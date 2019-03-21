/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.VehiculeMarque;
import bean.VehiculeModele;
import helper.ModelHelper;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.VehiculeMarqueServive;
import service.VehiculeModelService;
import util.AlertUtil;
import util.DateUtil;

/**
 *
 * @author Admin
 */
public final class ModelView extends Application {

    AlertUtil alertUtil = new AlertUtil();
    DateUtil dateUtil = new DateUtil();
    VehiculeModelService modelService = new VehiculeModelService();
    VehiculeMarqueServive marqueServive = new VehiculeMarqueServive();
    private List<VehiculeMarque> marques;
    ModelHelper modelHelper;
    List<VehiculeModele> modeles;

    public ModelView() throws Exception{
        init();
    }
    @Override
    public void init() throws Exception{
        initComponents();
        initComBoBoxMarque();
        initHelper();
    }
    // Element //
    Text text;
    Button menu;
    Label label;
    ComboBox<String> marque;
    Label model;
    TextField textField;
    Label dateM;
    TextField dateModel;
    TableView table;

    // Element //
    private void initComponents() {
        text = new Text("Model Vihecule");
        menu = new Button("Menu");
        label = new Label("Marque");
        marque = new ComboBox<>();
        model = new Label("Model");
        textField = new TextField();
        dateM = new Label("Date Model");
        dateModel = new TextField();
        table = new TableView();
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
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Model");
        GridPane root1 = new GridPane();
        GridPane root = new GridPane();
        root.addRow(0, root1);
        root.addRow(1, table);
        root.setVgap(10);

        table.setColumnResizePolicy((param) -> true);
        Platform.runLater(() -> customResize(table));

        Scene scene = new Scene(root, 700, 600);
        // Start Element Node 
        HBox hBox = new HBox();

        Button button = new Button("Inserer");
        // End Element Node 
        // Start Style Element //
        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        root1.setVgap(30);
        root1.setHgap(15);
        hBox.setAlignment(Pos.CENTER);
        text.setFont(Font.font("Edwardian Script ITC", 80));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setUnderline(true);
        menu.setMinSize(100, 30);
        menu.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        label.setStyle("-fx-font: normal bold 20px 'serif' ");
        model.setStyle("-fx-font: normal bold 20px 'serif' ");
        dateM.setStyle("-fx-font: normal bold 20px 'serif' ");
        textField.setPromptText("Entrer Model");
        marque.setMaxSize(400, 50);
        dateModel.setMaxSize(400, 50);
        marque.setPromptText("--------------------------------SELECT-------------------------");
        dateModel.setPromptText("DD-MM-AAAA");
        button.setMinSize(100, 30);
        button.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white;");
        // End Style Element //

        hBox.getChildren().add(text);
        root1.add(hBox, 1, 0, 1, 1);
        root1.addRow(1, menu);
        root1.addRow(5, label, marque);
        root1.addRow(6, model, textField);
        root1.addRow(7, dateM, dateModel);
        root1.add(button, 1, 8, 1, 1);
        primaryStage.setScene(scene);
        primaryStage.show();
        // Start The Action Part //
         menu.setOnAction((event) -> {
            Insertion insertion = new Insertion();
            primaryStage.close();
            try {
                insertion.start(new Stage());
            } catch (Exception ex) {
            }
        });
        button.setOnAction((event) -> {
            boolean modelV = verifie(textField);
            boolean dateV = verifie(dateModel);
            if (marque.getSelectionModel().getSelectedIndex() >= 0 && modelV == true && dateV == true) {
                String modelStr = textField.getText();
                Date dateModel32 = dateUtil.parse(dateModel.getText());

                VehiculeModele modele = modelService.creeveModele(modelStr, dateModel32, marques.get((marque.getSelectionModel().getSelectedIndex())));
                alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " Model Crée Avec Succés");
                modelHelper.create(modele);
                textField.clear();
                dateModel.clear();
                textField.setStyle("-fx-background-color: white;");
                dateModel.setStyle("-fx-background-color: white;");
            } else {
                alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "erroorr");
                textField.clear();
                dateModel.clear();
                textField.setStyle("-fx-background-color: white;");
                dateModel.setStyle("-fx-background-color: white;");
            }

        });
        // End The Action Part //
    }

    private void initComBoBoxMarque() {
        marques = marqueServive.findAll();
        marques.forEach((marq) -> {
            marque.getItems().add(marq.getMarque());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initHelper() {
        modeles = modelService.findAll();
        modelHelper = new ModelHelper(table, modeles);
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

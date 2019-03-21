/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Vehicule;
import bean.VehiculeVidenge;
import bean.Vehiculekilometrage;
import bean.VidengeType;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import service.VehiculeKilometrageService;
import service.VehiculeService;
import service.VehiculeVidengeService;
import service.VidengeTypeService;
import util.AlertUtil;
import util.DateUtil;

/**
 *
 * @author gouss
 */
public class VidengeView extends Application {

    Stage vidangeAll;
    VehiculeService vehiculeService = new VehiculeService();
    VehiculeVidengeService vehiculeVidengeService = new VehiculeVidengeService();
    VidengeTypeService videngeTypeService = new VidengeTypeService();
    VehiculeKilometrageService vehiculeKilometrageService = new VehiculeKilometrageService();
    DateUtil dateUtil = new DateUtil();
    AlertUtil alertUtil = new AlertUtil();
    List<Vehicule> vehicules;
    List<VidengeType> videngeTypes;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        initComponents();
        initComboMat();
        initComboType();
        initComboPrix();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root1 = new GridPane();
        GridPane root = new GridPane();
        root.addRow(0, root1);

        HBox hBox = new HBox();

        root.setStyle("-fx-background-color: BEIGE; -fx-padding :  0 30px");
        root1.setVgap(30);
        root1.setHgap(15);
        hBox.setAlignment(Pos.CENTER);
        text.setFont(Font.font("Edwardian Script ITC", 80));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setUnderline(true);
        btnMenue.setMinSize(100, 30);
        btnMenue.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        btnFerifier.setMinSize(100, 30);
        btnSave.setMinSize(100, 30);
        btnFerifier.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");
        btnSave.setStyle("-fx-background-color : darkslateblue; -fx-text-fill: white; ; ");

        labMatricule.setStyle("-fx-font: normal bold 20px 'serif' ");
        labkilomerage.setStyle("-fx-font: normal bold 20px 'serif' ");
        labKilometrageVidenge.setStyle("-fx-font: normal bold 20px 'serif' ");
        labTypeVidenge.setStyle("-fx-font: normal bold 20px 'serif' ");
        labPrixVidenge.setStyle("-fx-font: normal bold 20px 'serif' ");
        labDateVidenge.setStyle("-fx-font: normal bold 20px 'serif' ");

        fieldDateVid.setMaxWidth(360);
        fieldKilometrageVidenge.setMaxWidth(360);
        fieldkilometrage.setMaxWidth(360);
        dateVidenge.setMaxWidth(360);

        fieldDateVid.setPromptText("DD-MM-AAAA");

        comboMatr.setPromptText("--------------------------------SELECT-------------------------");
        comboPrixVid.setPromptText("--------------------------------SELECT-------------------------");
        comboTypeVid.setPromptText("--------------------------------SELECT-------------------------");

        hBox.getChildren().add(text);
        root1.add(hBox, 1, 0, 1, 1);
        root1.addRow(1, btnMenue);
        root1.addRow(3, labMatricule, comboMatr, btnFerifier);
        root1.addRow(4, labkilomerage, fieldkilometrage);
        root1.addRow(5, labKilometrageVidenge, fieldKilometrageVidenge);
        root1.addRow(6, labTypeVidenge, comboTypeVid);
        root1.addRow(7, labPrixVidenge, comboPrixVid);
        root1.addRow(8, labDateVidenge, fieldDateVid);
        root1.add(btnSave, 1, 9, 1, 1);

        btnFerifier.setOnAction((e) -> {
            if (comboMatr.getSelectionModel().getSelectedIndex() < 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "il faut selectionner une matricule");
            } else {
                String matricul = vehicules.get(comboMatr.getSelectionModel().getSelectedIndex()).getMatricule();
                VehiculeVidenge vehiculeVidenge = vehiculeVidengeService.findVidengeByMatricul(matricul);
                Vehiculekilometrage vehiculekilometrage = vehiculeKilometrageService.findKilometrageByMatricule(matricul);
                if (vehiculekilometrage == null) {
                    alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "la vehicule n'a pas de kilometrage");
                } else {
                    if (vehiculeVidenge == null) {
                        if (vehiculekilometrage.getKilometrage() < 10000) {
                            if (vehiculekilometrage.getKilometrage() > 9000) {
                                alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " la vehicule doit avoir un videnge");
                            } else {
                                alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "la vehicule n'a pas de videnge pour le moment");
                            }
                        } else {
                            alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " la vehicule doit avoir un videnge");
                        }
                    } else {
                        int res = vehiculeVidengeService.vefierVidenge(matricul);
                        if (res == 1) {
                            alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " la vehicule doit avoir un videnge");
                        } else {
                            alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "la vehicule n'a pas de videnge pour le moment");
                        }
                    }
                }
            }

        });

        btnSave.setOnAction((e) -> {

            if (comboMatr.getSelectionModel().getSelectedIndex() < 0) {
                alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "il faut selectionner une matricule");
            } else {
                Vehiculekilometrage vehiculekilometrage = vehiculeKilometrageService.findKilometrageByMatricule(vehicules.get(comboMatr.getSelectionModel().getSelectedIndex()).getMatricule());
                if (vehiculekilometrage == null) {
                    alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "la vehicule n'a aucun kilometrage");
                } else {

                    if (comboTypeVid.getSelectionModel().getSelectedIndex() < 0) {
                        alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "il faut selectionné un type de vidange");
                    } else {
                        double prixVidenge = 0;
                        VidengeType videngeType = videngeTypes.get(comboTypeVid.getSelectionModel().getSelectedIndex());
                        double indexPrix = comboPrixVid.getSelectionModel().getSelectedIndex();
                        if (indexPrix == 0) {
                            prixVidenge = videngeType.getPrixMin();
                        } else {
                            prixVidenge = videngeType.getPrixMax();
                        }
                        boolean dateV = verifie(fieldDateVid);
                        if (indexPrix < 0 || dateV == false) {
                            alertUtil.showAlert(Alert.AlertType.ERROR, "erroor", "il faut completer les information");
                            fieldDateVid.clear();
                            fieldDateVid.setStyle("-fx-background-color: white;");
                        } else {
                            VehiculeVidenge videngePeced = vehiculeVidengeService.findVidengeByMatricul(vehicules.get(comboMatr.getSelectionModel().getSelectedIndex()).getMatricule());
                            if (videngePeced == null) {
                                Date dateVidengee = dateUtil.parse(fieldDateVid.getText());
                                VehiculeVidenge vehiculeVidenge = vehiculeVidengeService.creeVehiculeVidenge(videngeType, dateVidengee, prixVidenge, new Double(fieldkilometrage.getText()), vehicules.get(comboMatr.getSelectionModel().getSelectedIndex()));
                                if (vehiculeVidenge != null) {
                                    alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " le videnge est inseré avec succee");

                                } else {
                                    alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "eroooooorr");
                                }
                            } else {
                                if (videngePeced.getDateVidenge().before(dateUtil.parse(fieldDateVid.getText()))) {
                                    VehiculeVidenge vehiculeVidenge = vehiculeVidengeService.creeVehiculeVidenge(videngeType, dateUtil.parse(fieldDateVid.getText()), prixVidenge, new Double(fieldkilometrage.getText()), vehicules.get(comboMatr.getSelectionModel().getSelectedIndex()));
                                    alertUtil.showAlert(Alert.AlertType.CONFIRMATION, "INFO", " le videnge est inseré avec succee");

                                } else {
                                    alertUtil.showAlert(Alert.AlertType.ERROR, "eroor", "il faut regler les dates");

                                }
                            }
                            fieldDateVid.clear();
                            fieldDateVid.setStyle("-fx-background-color: white;");

                        }
                    }
                }
            }

        });

        Scene scene = new Scene(root);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        vidangeAll = primaryStage;

        btnMenue.setOnAction((event) -> {
            Insertion insertion = new Insertion();
            vidangeAll.close();
            try {
                insertion.start(new Stage());
            } catch (Exception ex) {
            }
        });
    }
    Text text;

    Label labMatricule;
    Label labkilomerage;
    Label labKilometrageVidenge;
    Label labTypeVidenge;
    Label labPrixVidenge;
    Label labDateVidenge;

    ComboBox<String> comboMatr;
    ComboBox<String> comboTypeVid;
    ComboBox<String> comboPrixVid;

    TextField fieldkilometrage;
    TextField fieldKilometrageVidenge;
    TextField fieldDateVid;

    Button btnFerifier;
    Button btnSave;
    Button btnMenue;

    TableView table;

    DatePicker dateVidenge;

    private void initComponents() {
        text = new Text("Videnge Vehicule");

        labMatricule = new Label("Matricule");
        labkilomerage = new Label("Kilometrage");
        labKilometrageVidenge = new Label("Kilometrage-Videnge");
        labTypeVidenge = new Label("Type-Videnge");
        labPrixVidenge = new Label("prix-Videnge");
        labDateVidenge = new Label("Date-videnge");

        comboMatr = new ComboBox<>();
        comboTypeVid = new ComboBox<>();
        comboPrixVid = new ComboBox<>();

        fieldkilometrage = new TextField();
        fieldKilometrageVidenge = new TextField();
        fieldDateVid = new TextField();

        btnFerifier = new Button("ferifier");
        btnSave = new Button("Save");
        btnMenue = new Button("Menue");

        table = new TableView();
        dateVidenge = new DatePicker();

    }

    private void initComboMat() {

        vehicules = vehiculeService.findAll();
        vehicules.forEach((v) -> {
            comboMatr.getItems().add(v.getMatricule());
        });
        comboMatr.valueProperty().addListener((e) -> setKilometrage());
    }

    private void initComboPrix() {
        comboPrixVid.getItems().clear();
        int i = comboTypeVid.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            VidengeType type = videngeTypes.get(i);
            comboPrixVid.getItems().add("Prix-Min " + type.getPrixMin());
            comboPrixVid.getItems().add("Prix-Max " + type.getPrixMin());

        }
    }

    private void initComboType() {
        videngeTypes = videngeTypeService.findAll();

        videngeTypes.forEach((t) -> {
            comboTypeVid.getItems().add(t.getType());
        });
        comboTypeVid.valueProperty().addListener((e) -> initComboPrix());
    }

    private void setKilometrage() {
        int i = comboMatr.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            Vehicule vehicule = vehicules.get(i);
            Vehiculekilometrage kilometrage = vehiculeKilometrageService.findKilometrageByMatricule(vehicule.getMatricule());
            VehiculeVidenge kilometrageVidenge = vehiculeVidengeService.findVidengeByMatricul(vehicule.getMatricule());
            if (kilometrage == null) {
                fieldkilometrage.setText("null");

            } else {
                fieldkilometrage.setText(kilometrage.getKilometrage() + "");
            }
            if (kilometrageVidenge == null) {
                fieldKilometrageVidenge.setText("null");
            } else {
                fieldKilometrageVidenge.setText(kilometrageVidenge.getKilometrageVidenge() + "");
            }

        }
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

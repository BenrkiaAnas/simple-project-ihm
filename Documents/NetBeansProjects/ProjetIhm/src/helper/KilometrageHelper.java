/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.VehiculeModele;
import bean.Vehiculekilometrage;
import bean.VidengeType;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author gouss
 */
public class KilometrageHelper extends AbstractFxHelper<Vehiculekilometrage>{
       private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("kilometrage", "kilometrage"),
            new AbstractFxHelperItem("dateDebut", "dateDebut"),
            new AbstractFxHelperItem("dateFin", "dateFin")};
    }

    public KilometrageHelper(TableView<Vehiculekilometrage> table, List<Vehiculekilometrage> list) {
        super(titres, table, list);
    }

    public KilometrageHelper(TableView<Vehiculekilometrage> table) {
        super(titres, table);
    }
  
    
}

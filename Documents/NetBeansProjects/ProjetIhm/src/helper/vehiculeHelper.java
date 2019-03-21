/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Vehicule;
import bean.Vehicule_;
import bean.VidengeType;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author gouss
 */
public class vehiculeHelper extends AbstractFxHelper<Vehicule>{
      private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Matricule", "matricule"),
            new AbstractFxHelperItem("Date-Achat", "DateVehicule"),
            new AbstractFxHelperItem("Model", "vehiculeModele.modele"),
            new AbstractFxHelperItem("Type-DeCarburant", "vehiculeTypeCarburant")  };
    }

    public vehiculeHelper(TableView<Vehicule> table, List<Vehicule> list) {
        super(titres, table, list);
    }

    public vehiculeHelper(TableView<Vehicule> table) {
        super(titres, table);
    }
    
}

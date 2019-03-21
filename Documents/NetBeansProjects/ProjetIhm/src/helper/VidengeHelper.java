/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.VehiculeVidenge;
import bean.VidengeType;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author gouss
 */
public class VidengeHelper extends AbstractFxHelper<VehiculeVidenge>{
     private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("dateVidenge", "dateVidenge"),
            new AbstractFxHelperItem("kilometrage-Videnge", "kilometrageVidenge"),
            new AbstractFxHelperItem("prix-Videnge", "prixVidenge")};
    }

    public VidengeHelper(TableView<VehiculeVidenge> table, List<VehiculeVidenge> list) {
        super(titres, table, list);
    }

    public VidengeHelper(TableView<VehiculeVidenge> table) {
        super(titres, table);
    }
    
}

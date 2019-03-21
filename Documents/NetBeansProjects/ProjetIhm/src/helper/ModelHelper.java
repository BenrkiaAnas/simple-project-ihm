/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.VehiculeModele;
import bean.VidengeType;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author gouss
 */
public class ModelHelper extends AbstractFxHelper<VehiculeModele>{
      private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Model", "modele"),
            new AbstractFxHelperItem("Marque", "vehiculeMarque"),
            new AbstractFxHelperItem("Date-Model", "dateModele")};
    }

    public ModelHelper(TableView<VehiculeModele> table, List<VehiculeModele> list) {
        super(titres, table, list);
    }

    public ModelHelper(TableView<VehiculeModele> table) {
        super(titres, table);
    }
    public Object getValueAt(int rowIndex, int columnIndex){
        if (list != null && rowIndex < list.size()){
            for(AbstractFxHelperItem abstractFxHelperItem : abstractFxHelperItem){
                if(columnIndex==1){
                    return list.get(rowIndex).getVehiculeMarque().getMarque();
                }else{
                    return null;
                }
            }
        }
        return null;
    }
    
}

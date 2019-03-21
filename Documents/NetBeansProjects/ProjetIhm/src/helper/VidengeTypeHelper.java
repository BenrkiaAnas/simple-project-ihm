/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.VidengeType;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author gouss
 */
public class VidengeTypeHelper extends AbstractFxHelper<VidengeType>{
     private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Type-videnge", "type"),
            new AbstractFxHelperItem("Prix-Max", "prixMax"),
            new AbstractFxHelperItem("Prix-Min", "prixMin")};
    }

    public VidengeTypeHelper(TableView<VidengeType> table, List<VidengeType> list) {
        super(titres, table, list);
    }

    public VidengeTypeHelper(TableView<VidengeType> table) {
        super(titres, table);
    }
    
}

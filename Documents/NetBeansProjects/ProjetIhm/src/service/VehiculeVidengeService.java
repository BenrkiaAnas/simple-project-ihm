/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Vehicule;
import bean.VehiculeVidenge;
import bean.VehiculeVidenge_;
import bean.Vehiculekilometrage;
import bean.VidengeType;
import java.util.Date;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author LENOVO
 */
public class VehiculeVidengeService extends AbstractFacade<VehiculeVidenge> {

    VehiculeService vehiculeService = new VehiculeService();
    VidengeTypeService videngeTypeService = new VidengeTypeService();
    VehiculeKilometrageService vehiculeKilometrageService = new VehiculeKilometrageService();

    public VehiculeVidengeService() {
        super(VehiculeVidenge.class);
    }
    public List<VehiculeVidenge> findListVidengeByMatricule(String matricule){
        return getMultipleResult("SELECT videnge FROM VehiculeVidenge videnge WHERE videnge.vehicule.matricule='" + matricule + "'");
    }

    public VehiculeVidenge findVidengeByMatricul(String matricule) {
        return getSingleResult("SELECT videnge FROM VehiculeVidenge videnge WHERE videnge.vehicule.matricule='" + matricule + "' ORDER BY videnge.dateVidenge DESC");
    }
    public int supprimerVidengeByMatricule(String matricule){
        List<VehiculeVidenge> vehiculeVidenges=findListVidengeByMatricule(matricule);
      if(vehiculeVidenges==null){
          return -1;
      }else{
          for (VehiculeVidenge vehiculeVidenge : vehiculeVidenges) {
              remove(vehiculeVidenge);
             
          }
           return 1;
      }
      
    }

    public int vefierVidenge(String matricule) {
        Vehiculekilometrage kilometrageVehicule = vehiculeKilometrageService.findKilometrageByMatricule(matricule);
        VehiculeVidenge kilometrageVidenge = findVidengeByMatricul(matricule);
        double kilometrageVid = kilometrageVidenge.getKilometrageVidenge();
        double kilometrageVehi = kilometrageVehicule.getKilometrage();

        if ((kilometrageVehi - kilometrageVid) > 9000) {
            return 1;
        } else {
            return -1;
        }

    }

    private String querySerchbydat(String matricule, Date dateMax, Date dateMin) {
        String query = "SELECT v FROM VehiculeVidenge v WHERE v.vehicule.matricule='" + matricule + "' AND 1=1";
        if (dateMax != null) {
            query += SearchUtil.addConstraintDate("v", "dateVidenge", "<=", dateMax);
        }
        if (dateMin != null) {
            query += SearchUtil.addConstraintDate("v", "dateVidenge", ">=", dateMin);

        }
        return query;

    }

    public List<VehiculeVidenge> searchVidengeByDate(String matricule, Date dateMax, Date dateMin) {
        String query = querySerchbydat(matricule, dateMax, dateMin);
        return getMultipleResult(query);
    }

    public VehiculeVidenge creeVehiculeVidenge(VidengeType videngeType, Date dateVidenge, double prixVidenge, double kilometrageVidenge, Vehicule vehicule) {
        VehiculeVidenge vehiculeVidenge = new VehiculeVidenge(videngeType, dateVidenge, prixVidenge, kilometrageVidenge, vehicule);
        create(vehiculeVidenge);
        return vehiculeVidenge;

    }

}

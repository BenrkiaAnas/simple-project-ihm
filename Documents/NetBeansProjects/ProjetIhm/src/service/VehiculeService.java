/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Vehicule;
import bean.VehiculeModele;
import bean.VehiculeTypeCarburant;
import bean.VehiculeVidenge;
import bean.Vehiculekilometrage;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class VehiculeService extends AbstractFacade<Vehicule> {

    public VehiculeService() {
        super(Vehicule.class);
    }

    public Vehicule insererVheicule(String matricule, Date DateVehicule, Long puissance, Long pois, int nbrPlace, VehiculeTypeCarburant vehiculeTypeCarburant, VehiculeModele vehiculeModele) {
        Vehicule vehicule = new Vehicule(matricule, DateVehicule, puissance, pois, nbrPlace, vehiculeTypeCarburant, vehiculeModele);
        create(vehicule);
        return vehicule;
    }

    public Vehicule findVehiculeByMatricule(String matricule) {
        return getSingleResult("SELECT v FROM Vehicule v WHERE v.matricule='" + matricule + "'");
    }
    public List<Vehicule> findVehiculeBymodele(String modele){
        return getMultipleResult("SELECT v FROM Vehicule v WHERE v.vehiculeModele.modele='"+modele+"'");
    }
  
    public List<Vehicule> findVehiculeByCarburant(String type){
        return getMultipleResult("SELECT v FROM Vehicule v WHERE v.vehiculeTypeCarburant.type='"+type+"'");
    }
    public String queryVehiculeParTout( String modele, String type){
        String query="SELECT v FROM Vehicule v  WHERE  v.vehiculeModele.modele='"+modele+"' AND v.vehiculeTypeCarburant.type='"+type+"'";
       
    
        return query;
    }  
    
    public List<Vehicule> findVehiculesbyTout( String modele, String type){
       String query=queryVehiculeParTout(modele, type);
        return getMultipleResult(query);
    }
    public int deleteVehiculeByMatricule(String matricule) {
        VehiculeKilometrageService vehiculeKilometrageService = new VehiculeKilometrageService();
        VehiculeVidengeService vehiculeVidengeService = new VehiculeVidengeService();
        Vehicule vehicule = findVehiculeByMatricule(matricule);

        Vehiculekilometrage vehiculekilometrage = vehiculeKilometrageService.findKilometrageByMatricule(matricule);
        vehiculeKilometrageService.remove(vehiculekilometrage);
        List<VehiculeVidenge> vehiculeVidenges = vehiculeVidengeService.findListVidengeByMatricule(matricule);
        for (VehiculeVidenge vehiculeVidenge : vehiculeVidenges) {
            vehiculeVidengeService.remove(vehiculeVidenge);
        }
        remove(vehicule);
        return 1;
    }

}

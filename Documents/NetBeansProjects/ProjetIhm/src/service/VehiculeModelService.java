/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.VehiculeMarque;
import bean.VehiculeModele;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class VehiculeModelService extends AbstractFacade<VehiculeModele>{
    VehiculeMarqueServive vehiculeMarqueServive=new VehiculeMarqueServive();
    
    public VehiculeModelService() {
        super(VehiculeModele.class);
    }
    public VehiculeModele creeveModele(String modele, Date dateModele, VehiculeMarque vehiculeMarque){
     VehiculeModele vehiculeModele=new VehiculeModele(modele, dateModele, vehiculeMarque);
        create(vehiculeModele);
        
    
     return vehiculeModele;
    }
    public List<VehiculeModele> findModelByMarque(String marque){
     
      return  getMultipleResult("SELECT m FROM VehiculeModele m WHERE m.vehiculeMarque.marque='"+marque+"'");
    }
}

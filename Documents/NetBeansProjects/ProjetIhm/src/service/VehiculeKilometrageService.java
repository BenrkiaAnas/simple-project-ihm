/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Vehicule;
import bean.Vehiculekilometrage;
import java.util.Date;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author LENOVO
 */
public class VehiculeKilometrageService extends AbstractFacade<Vehiculekilometrage>{
    VehiculeService vehiculeService=new VehiculeService();
    
    public VehiculeKilometrageService() {
        super(Vehiculekilometrage.class);
    }
    
   public Vehiculekilometrage creeKilometrage(double kilometrage, Date dateDebut, Date dateFin,Vehicule vehicule) {
       Vehiculekilometrage vehiculekilometrage=new Vehiculekilometrage(kilometrage, dateDebut, dateFin, vehicule);
       create(vehiculekilometrage);
       return vehiculekilometrage;
   }
   public  Vehiculekilometrage findKilometrageByMatricule(String matricule){
     return getSingleResult("SELECT kilo FROM Vehiculekilometrage kilo WHERE kilo.vehicule.matricule='"+matricule+"'");
      
   }
   public String queryfindKilometrageByKilometrage(double kilometrageMax,double kilometrageMin){
       String query="SELECT k FROM Vehiculekilometrage k WHERE 1=1";
       query+=SearchUtil.addConstraintMinMax("k","kilometrage", kilometrageMin, kilometrageMax);
       return query;
   }
   public List<Vehiculekilometrage> findKilometrageByKilometrage(double kilometrageMax,double kilometrageMin){
      String query=queryfindKilometrageByKilometrage(kilometrageMax, kilometrageMin);
      return getMultipleResult(query);
   }
 
//   public int modifierkilometr(double kilometrage, Date dateDebut, Date dateFin,String matricule){
//       return execUpdate("UPDATE FROM Vehiculekilometrage kilo SET kilo.kilometrage='"+kilometrage+"' , kilo.dateDebut='"+dateDebut+"' , kilo.dateFin='"+dateFin+"' WHERE kilo.vehicule.matricule='"+matricule+"'");
//   }
   public int modifierKilometrageByMatricule(double kilometrage, Date dateDebut, Date dateFin,String matricule){
     Vehiculekilometrage vehiculekilometrage=findKilometrageByMatricule(matricule);
     if(vehiculekilometrage==null){
         return -1;
     }else{
     vehiculekilometrage.setKilometrage(kilometrage);
     vehiculekilometrage.setDateDebut(dateDebut);
     vehiculekilometrage.setDateFin(dateFin);
         edit(vehiculekilometrage);
     return 1;
     }
   }
   public int deleteKilometrageByMatricul(String matricule){
       Vehiculekilometrage vehiculekilometrage=findKilometrageByMatricule(matricule);
       if(vehiculekilometrage==null){
           return -1;
       }else{
           remove(vehiculekilometrage);
           return 1;
       }
   }
   
//   public int deleteKilometrageByMatricul(String matricule){
//       return execUpdate("DELETE FROM Vehiculekilometrage kilo WHERE kilo.vehicule.matricule='"+matricule+"'");
//   }
   
   
   
}

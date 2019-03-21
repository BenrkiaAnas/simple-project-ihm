/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.VehiculeMarque;

/**
 *
 * @author LENOVO
 */
public class VehiculeMarqueServive extends AbstractFacade<VehiculeMarque>{
    
    public VehiculeMarqueServive() {
        super(VehiculeMarque.class);
        
    }
   public VehiculeMarque issererMarque(String marque){
       VehiculeMarque vehiculeMarque = new VehiculeMarque( marque);
       create(vehiculeMarque);
       return vehiculeMarque;
   }
}

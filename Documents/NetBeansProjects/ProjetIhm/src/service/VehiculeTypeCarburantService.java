/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.VehiculeTypeCarburant;

/**
 *
 * @author LENOVO
 */
public class VehiculeTypeCarburantService extends AbstractFacade<VehiculeTypeCarburant>{
    
    public VehiculeTypeCarburantService() {
        super(VehiculeTypeCarburant.class);
    }
    public VehiculeTypeCarburant creeCarburant(String type){
        VehiculeTypeCarburant vehiculeTypeCarburant=new VehiculeTypeCarburant(type);
        create(vehiculeTypeCarburant);
        return vehiculeTypeCarburant;
        
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.VidengeType;

/**
 *
 * @author LENOVO
 */
public class VidengeTypeService extends AbstractFacade<VidengeType>{
    
    public VidengeTypeService() {
        super(VidengeType.class);
    }
    public VidengeType creatVidengeType(String type, double prixMax,double prixMin){
        VidengeType videngeType=new VidengeType(type, prixMax,prixMin);
        create(videngeType);
        return videngeType;
    }
    
}

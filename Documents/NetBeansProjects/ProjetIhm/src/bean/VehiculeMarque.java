/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Admin
 */
@Entity
public class VehiculeMarque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    
    private String marque;

    public VehiculeMarque(String marque) {
        this.marque = marque;
    }

    public VehiculeMarque() {
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
    

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marque != null ? marque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculeMarque)) {
            return false;
        }
        VehiculeMarque other = (VehiculeMarque) object;
        if ((this.marque == null && other.marque != null) || (this.marque != null && !this.marque.equals(other.marque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VehiculeMarque{" + "marque=" + marque + '}';
    }
    
}

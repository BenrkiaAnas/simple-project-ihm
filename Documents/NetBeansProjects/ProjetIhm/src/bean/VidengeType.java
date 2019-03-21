/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Admin
 */
@Entity
public class VidengeType implements Serializable {

  private static final long serialVersionUID = 1L;
    @Id
    private String type;
    private double prixMax;
    private double prixMin;

    public VidengeType(String type, double prixMax, double prixMin) {
        this.type = type;
        this.prixMax = prixMax;
        this.prixMin = prixMin;
    }

    @Override
    public String toString() {
        return "VidengeType{" + "type=" + type + ", prixMax=" + prixMax + ", prixMin=" + prixMin + ", vehiculeVidenge=" + vehiculeVidenge + '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }

    public double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

    public VehiculeVidenge getVehiculeVidenge() {
        return vehiculeVidenge;
    }

    public void setVehiculeVidenge(VehiculeVidenge vehiculeVidenge) {
        this.vehiculeVidenge = vehiculeVidenge;
    }
    
    
    
    
    @ManyToOne
    private VehiculeVidenge vehiculeVidenge;

    public VidengeType() {
    }

    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VidengeType other = (VidengeType) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    
}

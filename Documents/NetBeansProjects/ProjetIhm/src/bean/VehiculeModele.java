/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Admin
 */
@Entity
public class VehiculeModele implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    private String modele;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateModele;

    @ManyToOne
    private VehiculeMarque vehiculeMarque;

    public VehiculeModele() {
    }

    @Override
    public String toString() {
        return "VehiculeModele{" + "modele=" + modele + ", dateModele=" + dateModele + ", vehiculeMarque=" + vehiculeMarque + '}';
    }

    public VehiculeModele(String modele, Date dateModele, VehiculeMarque vehiculeMarque) {
        this.modele = modele;
        this.dateModele = dateModele;
        this.vehiculeMarque = vehiculeMarque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Date getDateModele() {
        return dateModele;
    }

    public void setDateModele(Date dateModele) {
        this.dateModele = dateModele;
    }

    public VehiculeMarque getVehiculeMarque() {
        return vehiculeMarque;
    }

    public void setVehiculeMarque(VehiculeMarque vehiculeMarque) {
        this.vehiculeMarque = vehiculeMarque;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.modele);
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
        final VehiculeModele other = (VehiculeModele) obj;
        if (!Objects.equals(this.modele, other.modele)) {
            return false;
        }
        return true;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Admin
 */
@Entity
public class VehiculeVidenge implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private VidengeType videngeType;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateVidenge;
    private double prixVidenge;
    private double kilometrageVidenge;

    @ManyToOne
    private Vehicule vehicule;
    @OneToMany(mappedBy = "vehiculeVidenge")
    private List<VidengeType> videngeTypes;

    public VehiculeVidenge() {
    }

    public VehiculeVidenge(VidengeType videngeType, Date dateVidenge, double prixVidenge, double kilometrageVidenge, Vehicule vehicule) {
        this.videngeType = videngeType;
        this.dateVidenge = dateVidenge;
        this.prixVidenge = prixVidenge;
        this.kilometrageVidenge = kilometrageVidenge;
        this.vehicule = vehicule;
    }

    public VehiculeVidenge(VidengeType videngeType, Date dateVidenge, double prixVidenge, Vehicule vehicule) {
        this.videngeType = videngeType;
        this.dateVidenge = dateVidenge;
        this.prixVidenge = prixVidenge;
        this.vehicule = vehicule;
    }

    public double getKilometrageVidenge() {
        return kilometrageVidenge;
    }

    public void setKilometrageVidenge(double kilometrageVidenge) {
        this.kilometrageVidenge = kilometrageVidenge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VidengeType getVidengeType() {
        return videngeType;
    }

    public void setVidengeType(VidengeType videngeType) {
        this.videngeType = videngeType;
    }

    public Date getDateVidenge() {
        return dateVidenge;
    }

    public void setDateVidenge(Date dateVidenge) {
        this.dateVidenge = dateVidenge;
    }

    public double getPrixVidenge() {
        return prixVidenge;
    }

    public void setPrixVidenge(double prixVidenge) {
        this.prixVidenge = prixVidenge;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public List<VidengeType> getVidengeTypes() {
        return videngeTypes;
    }

    public void setVidengeTypes(List<VidengeType> videngeTypes) {
        this.videngeTypes = videngeTypes;
    }

    @Override
    public String toString() {
        return "VehiculeVidenge{" + "id=" + id + ", videngeType=" + videngeType + ", dateVidenge=" + dateVidenge + ", prixVidenge=" + prixVidenge + ", kilometrageVidenge=" + kilometrageVidenge + ", vehicule=" + vehicule + ", videngeTypes=" + videngeTypes + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
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
        final VehiculeVidenge other = (VehiculeVidenge) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}

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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Admin
 */
@Entity
public class Vehicule implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    private String matricule;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateVehicule ;
    private Long puissance;
    private Long pois;
    private int nbrPlace;
    @OneToMany(mappedBy = "vehicule")
    private List<VehiculeVidenge> vehiculeVidenges;

    @OneToOne(mappedBy = "vehicule")
    private Vehiculekilometrage vehiculekilometrage;
    @ManyToOne
    private VehiculeTypeCarburant vehiculeTypeCarburant;
   
    @ManyToOne
    private VehiculeModele vehiculeModele;

    public Vehicule() {
    }

    public Vehicule(String matricule, Date DateVehicule, Long puissance, Long pois, int nbrPlace, VehiculeModele vehiculeModele) {
        this.matricule = matricule;
        this.DateVehicule = DateVehicule;
        this.puissance = puissance;
        this.pois = pois;
        this.nbrPlace = nbrPlace;
        this.vehiculeModele = vehiculeModele;
    }

    public Vehicule(String matricule, Date DateVehicule, Long puissance, Long pois, int nbrPlace, VehiculeTypeCarburant vehiculeTypeCarburant, VehiculeModele vehiculeModele) {
        this.matricule = matricule;
        this.DateVehicule = DateVehicule;
        this.puissance = puissance;
        this.pois = pois;
        this.nbrPlace = nbrPlace;
        this.vehiculeTypeCarburant = vehiculeTypeCarburant;
        this.vehiculeModele = vehiculeModele;
    }
    
    
    
    public Vehicule(Vehiculekilometrage vehiculekilometrage, String matricule, Date DateVehicule, Long puissance, Long pois, int nbrPlace, VehiculeTypeCarburant vehiculeTypeCarburant, VehiculeModele vehiculeModele) {
        this.vehiculekilometrage = vehiculekilometrage;
        this.matricule = matricule;
        this.DateVehicule = DateVehicule;
        this.puissance = puissance;
        this.pois = pois;
        this.nbrPlace = nbrPlace;
        this.vehiculeTypeCarburant = vehiculeTypeCarburant;
        this.vehiculeModele = vehiculeModele;
    }

    public Vehicule(String matricule, Date DateVehicule, Long puissance, Long pois, int nbrPlace) {
        this.matricule = matricule;
        this.DateVehicule = DateVehicule;
        this.puissance = puissance;
        this.pois = pois;
        this.nbrPlace = nbrPlace;
    }
    
    
    public Vehicule(List<VehiculeVidenge> vehiculeVidenges, Vehiculekilometrage vehiculekilometrage, String matricule, Date DateVehicule, Long puissance, Long pois, int nbrPlace, VehiculeTypeCarburant vehiculeTypeCarburant, VehiculeModele vehiculeModele) {
        this.vehiculeVidenges = vehiculeVidenges;
        this.vehiculekilometrage = vehiculekilometrage;
        this.matricule = matricule;
        this.DateVehicule = DateVehicule;
        this.puissance = puissance;
        this.pois = pois;
        this.nbrPlace = nbrPlace;
        this.vehiculeTypeCarburant = vehiculeTypeCarburant;
        this.vehiculeModele = vehiculeModele;
    }

    public List<VehiculeVidenge> getVehiculeVidenges() {
        return vehiculeVidenges;
    }

    public void setVehiculeVidenges(List<VehiculeVidenge> vehiculeVidenges) {
        this.vehiculeVidenges = vehiculeVidenges;
    }

    public Vehiculekilometrage getVehiculekilometrage() {
        return vehiculekilometrage;
    }

    public void setVehiculekilometrage(Vehiculekilometrage vehiculekilometrage) {
        this.vehiculekilometrage = vehiculekilometrage;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Date getDateVehicule() {
        return DateVehicule;
    }

    public void setDateVehicule(Date DateVehicule) {
        this.DateVehicule = DateVehicule;
    }

    public Long getPuissance() {
        return puissance;
    }

    public void setPuissance(Long puissance) {
        this.puissance = puissance;
    }

    public Long getPois() {
        return pois;
    }

    public void setPois(Long pois) {
        this.pois = pois;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public VehiculeTypeCarburant getVehiculeType() {
        return vehiculeTypeCarburant;
    }

    public void setVehiculeType(VehiculeTypeCarburant vehiculeType) {
        this.vehiculeTypeCarburant = vehiculeType;
    }

    public VehiculeModele getVehiculeModele() {
        return vehiculeModele;
    }

    public void setVehiculeModele(VehiculeModele vehiculeModele) {
        this.vehiculeModele = vehiculeModele;
    }

    public VehiculeTypeCarburant getVehiculeTypeCarburant() {
        return vehiculeTypeCarburant;
    }

    public void setVehiculeTypeCarburant(VehiculeTypeCarburant vehiculeTypeCarburant) {
        this.vehiculeTypeCarburant = vehiculeTypeCarburant;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "vehiculeVidenges=" + vehiculeVidenges + ", vehiculekilometrage=" + vehiculekilometrage + ", matricule=" + matricule + ", DateVehicule=" + DateVehicule + ", puissance=" + puissance + ", pois=" + pois + ", nbrPlace=" + nbrPlace + ", vehiculeTypeCarburant=" + vehiculeTypeCarburant + ", vehiculeModele=" + vehiculeModele + '}';
    }

   

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the matricule fields are not set
        if (!(object instanceof Vehicule)) {
            return false;
        }
        Vehicule other = (Vehicule) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

   
    
}

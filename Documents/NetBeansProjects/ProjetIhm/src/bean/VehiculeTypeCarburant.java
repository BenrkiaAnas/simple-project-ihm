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

/**
 *
 * @author Admin
 */
@Entity
public class VehiculeTypeCarburant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    private String type;

    public VehiculeTypeCarburant() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.type);
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
        final VehiculeTypeCarburant other = (VehiculeTypeCarburant) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    public VehiculeTypeCarburant(String type) {
        this.type = type;
    }

}

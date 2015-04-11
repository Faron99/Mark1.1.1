/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cristian
 */
@Entity
@Table(name = "core_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoreTeam.findAll", query = "SELECT c FROM CoreTeam c"),
    @NamedQuery(name = "CoreTeam.findByIdcoreTeam", query = "SELECT c FROM CoreTeam c WHERE c.idcoreTeam = :idcoreTeam"),
    @NamedQuery(name = "CoreTeam.findByName", query = "SELECT c FROM CoreTeam c WHERE c.name = :name"),
    @NamedQuery(name = "CoreTeam.findByCharge", query = "SELECT c FROM CoreTeam c WHERE c.charge = :charge")})
public class CoreTeam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcore_team")
    private Integer idcoreTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 105)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "charge")
    private String charge;
    @JoinColumn(name = "TAC_idtac", referencedColumnName = "idtac")
    @ManyToOne(optional = false)
    private Tac tACidtac;

    public CoreTeam() {
    }

    public CoreTeam(Integer idcoreTeam) {
        this.idcoreTeam = idcoreTeam;
    }

    public CoreTeam(Integer idcoreTeam, String name, String charge) {
        this.idcoreTeam = idcoreTeam;
        this.name = name;
        this.charge = charge;
    }

    public Integer getIdcoreTeam() {
        return idcoreTeam;
    }

    public void setIdcoreTeam(Integer idcoreTeam) {
        this.idcoreTeam = idcoreTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Tac getTACidtac() {
        return tACidtac;
    }

    public void setTACidtac(Tac tACidtac) {
        this.tACidtac = tACidtac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcoreTeam != null ? idcoreTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoreTeam)) {
            return false;
        }
        CoreTeam other = (CoreTeam) object;
        if ((this.idcoreTeam == null && other.idcoreTeam != null) || (this.idcoreTeam != null && !this.idcoreTeam.equals(other.idcoreTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CoreTeam[ idcoreTeam=" + idcoreTeam + " ]";
    }
    
}

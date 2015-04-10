/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmercadoco
 */
@Entity
@Table(name = "core_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoreTeam.findAll", query = "SELECT c FROM CoreTeam c"),
    @NamedQuery(name = "CoreTeam.findByIdCoreTeam", query = "SELECT c FROM CoreTeam c WHERE c.coreTeamPK.idCoreTeam = :idCoreTeam"),
    @NamedQuery(name = "CoreTeam.findByName", query = "SELECT c FROM CoreTeam c WHERE c.name = :name"),
    @NamedQuery(name = "CoreTeam.findByCharge", query = "SELECT c FROM CoreTeam c WHERE c.charge = :charge"),
    @NamedQuery(name = "CoreTeam.findByGroupCoreTeamidGroup", query = "SELECT c FROM CoreTeam c WHERE c.coreTeamPK.groupCoreTeamidGroup = :groupCoreTeamidGroup")})
public class CoreTeam implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoreTeamPK coreTeamPK;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Charge")
    private String charge;
    @JoinColumn(name = "Group_Core_Team_idGroup", referencedColumnName = "idGroup", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GroupCoreTeam groupCoreTeam;

    public CoreTeam() {
    }

    public CoreTeam(CoreTeamPK coreTeamPK) {
        this.coreTeamPK = coreTeamPK;
    }

    public CoreTeam(int idCoreTeam, String groupCoreTeamidGroup) {
        this.coreTeamPK = new CoreTeamPK(idCoreTeam, groupCoreTeamidGroup);
    }

    public CoreTeamPK getCoreTeamPK() {
        return coreTeamPK;
    }

    public void setCoreTeamPK(CoreTeamPK coreTeamPK) {
        this.coreTeamPK = coreTeamPK;
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

    public GroupCoreTeam getGroupCoreTeam() {
        return groupCoreTeam;
    }

    public void setGroupCoreTeam(GroupCoreTeam groupCoreTeam) {
        this.groupCoreTeam = groupCoreTeam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coreTeamPK != null ? coreTeamPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoreTeam)) {
            return false;
        }
        CoreTeam other = (CoreTeam) object;
        if ((this.coreTeamPK == null && other.coreTeamPK != null) || (this.coreTeamPK != null && !this.coreTeamPK.equals(other.coreTeamPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.CoreTeam[ coreTeamPK=" + coreTeamPK + " ]";
    }
    
}

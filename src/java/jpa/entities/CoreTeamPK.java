/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mmercadoco
 */
@Embeddable
public class CoreTeamPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCore_Team")
    private int idCoreTeam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "Group_Core_Team_idGroup")
    private String groupCoreTeamidGroup;

    public CoreTeamPK() {
    }

    public CoreTeamPK(int idCoreTeam, String groupCoreTeamidGroup) {
        this.idCoreTeam = idCoreTeam;
        this.groupCoreTeamidGroup = groupCoreTeamidGroup;
    }

    public int getIdCoreTeam() {
        return idCoreTeam;
    }

    public void setIdCoreTeam(int idCoreTeam) {
        this.idCoreTeam = idCoreTeam;
    }

    public String getGroupCoreTeamidGroup() {
        return groupCoreTeamidGroup;
    }

    public void setGroupCoreTeamidGroup(String groupCoreTeamidGroup) {
        this.groupCoreTeamidGroup = groupCoreTeamidGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCoreTeam;
        hash += (groupCoreTeamidGroup != null ? groupCoreTeamidGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoreTeamPK)) {
            return false;
        }
        CoreTeamPK other = (CoreTeamPK) object;
        if (this.idCoreTeam != other.idCoreTeam) {
            return false;
        }
        if ((this.groupCoreTeamidGroup == null && other.groupCoreTeamidGroup != null) || (this.groupCoreTeamidGroup != null && !this.groupCoreTeamidGroup.equals(other.groupCoreTeamidGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.CoreTeamPK[ idCoreTeam=" + idCoreTeam + ", groupCoreTeamidGroup=" + groupCoreTeamidGroup + " ]";
    }
    
}

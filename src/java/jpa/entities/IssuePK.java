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
public class IssuePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idIssue")
    private int idIssue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "Group_Core_Team_idGroup")
    private String groupCoreTeamidGroup;

    public IssuePK() {
    }

    public IssuePK(int idIssue, String groupCoreTeamidGroup) {
        this.idIssue = idIssue;
        this.groupCoreTeamidGroup = groupCoreTeamidGroup;
    }

    public int getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(int idIssue) {
        this.idIssue = idIssue;
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
        hash += (int) idIssue;
        hash += (groupCoreTeamidGroup != null ? groupCoreTeamidGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IssuePK)) {
            return false;
        }
        IssuePK other = (IssuePK) object;
        if (this.idIssue != other.idIssue) {
            return false;
        }
        if ((this.groupCoreTeamidGroup == null && other.groupCoreTeamidGroup != null) || (this.groupCoreTeamidGroup != null && !this.groupCoreTeamidGroup.equals(other.groupCoreTeamidGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.IssuePK[ idIssue=" + idIssue + ", groupCoreTeamidGroup=" + groupCoreTeamidGroup + " ]";
    }
    
}

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
public class TacsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idTACS")
    private int idTACS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "Group_Core_Team_idGroup")
    private String groupCoreTeamidGroup;

    public TacsPK() {
    }

    public TacsPK(int idTACS, String name, String groupCoreTeamidGroup) {
        this.idTACS = idTACS;
        this.name = name;
        this.groupCoreTeamidGroup = groupCoreTeamidGroup;
    }

    public int getIdTACS() {
        return idTACS;
    }

    public void setIdTACS(int idTACS) {
        this.idTACS = idTACS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (int) idTACS;
        hash += (name != null ? name.hashCode() : 0);
        hash += (groupCoreTeamidGroup != null ? groupCoreTeamidGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TacsPK)) {
            return false;
        }
        TacsPK other = (TacsPK) object;
        if (this.idTACS != other.idTACS) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        if ((this.groupCoreTeamidGroup == null && other.groupCoreTeamidGroup != null) || (this.groupCoreTeamidGroup != null && !this.groupCoreTeamidGroup.equals(other.groupCoreTeamidGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.TacsPK[ idTACS=" + idTACS + ", name=" + name + ", groupCoreTeamidGroup=" + groupCoreTeamidGroup + " ]";
    }
    
}

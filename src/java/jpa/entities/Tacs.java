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
@Table(name = "tacs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tacs.findAll", query = "SELECT t FROM Tacs t"),
    @NamedQuery(name = "Tacs.findByIdTACS", query = "SELECT t FROM Tacs t WHERE t.tacsPK.idTACS = :idTACS"),
    @NamedQuery(name = "Tacs.findByName", query = "SELECT t FROM Tacs t WHERE t.tacsPK.name = :name"),
    @NamedQuery(name = "Tacs.findByDescription", query = "SELECT t FROM Tacs t WHERE t.description = :description"),
    @NamedQuery(name = "Tacs.findByGroupCoreTeamidGroup", query = "SELECT t FROM Tacs t WHERE t.tacsPK.groupCoreTeamidGroup = :groupCoreTeamidGroup")})
public class Tacs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TacsPK tacsPK;
    @Size(max = 100)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Group_Core_Team_idGroup", referencedColumnName = "idGroup", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GroupCoreTeam groupCoreTeam;

    public Tacs() {
    }

    public Tacs(TacsPK tacsPK) {
        this.tacsPK = tacsPK;
    }

    public Tacs(int idTACS, String name, String groupCoreTeamidGroup) {
        this.tacsPK = new TacsPK(idTACS, name, groupCoreTeamidGroup);
    }

    public TacsPK getTacsPK() {
        return tacsPK;
    }

    public void setTacsPK(TacsPK tacsPK) {
        this.tacsPK = tacsPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (tacsPK != null ? tacsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tacs)) {
            return false;
        }
        Tacs other = (Tacs) object;
        if ((this.tacsPK == null && other.tacsPK != null) || (this.tacsPK != null && !this.tacsPK.equals(other.tacsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Tacs[ tacsPK=" + tacsPK + " ]";
    }
    
}

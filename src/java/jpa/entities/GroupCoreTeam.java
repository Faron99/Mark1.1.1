/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmercadoco
 */
@Entity
@Table(name = "group_core_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupCoreTeam.findAll", query = "SELECT g FROM GroupCoreTeam g"),
    @NamedQuery(name = "GroupCoreTeam.findByIdGroup", query = "SELECT g FROM GroupCoreTeam g WHERE g.idGroup = :idGroup")})
public class GroupCoreTeam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "idGroup")
    private String idGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCoreTeam")
    private Collection<Issue> issueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCoreTeam")
    private Collection<CoreTeam> coreTeamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCoreTeam")
    private Collection<Tacs> tacsCollection;

    public GroupCoreTeam() {
    }

    public GroupCoreTeam(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    @XmlTransient
    public Collection<Issue> getIssueCollection() {
        return issueCollection;
    }

    public void setIssueCollection(Collection<Issue> issueCollection) {
        this.issueCollection = issueCollection;
    }

    @XmlTransient
    public Collection<CoreTeam> getCoreTeamCollection() {
        return coreTeamCollection;
    }

    public void setCoreTeamCollection(Collection<CoreTeam> coreTeamCollection) {
        this.coreTeamCollection = coreTeamCollection;
    }

    @XmlTransient
    public Collection<Tacs> getTacsCollection() {
        return tacsCollection;
    }

    public void setTacsCollection(Collection<Tacs> tacsCollection) {
        this.tacsCollection = tacsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupCoreTeam)) {
            return false;
        }
        GroupCoreTeam other = (GroupCoreTeam) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.GroupCoreTeam[ idGroup=" + idGroup + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author cristian
 */
@Entity
@Table(name = "tac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tac.findAll", query = "SELECT t FROM Tac t"),
    @NamedQuery(name = "Tac.findByIdtac", query = "SELECT t FROM Tac t WHERE t.idtac = :idtac"),
    @NamedQuery(name = "Tac.findByName", query = "SELECT t FROM Tac t WHERE t.name = :name"),
    @NamedQuery(name = "Tac.findByDescription", query = "SELECT t FROM Tac t WHERE t.description = :description")})
public class Tac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtac")
    private Integer idtac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tACidtac")
    private List<Issue> issueList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tACidtac")
    private List<CoreTeam> coreTeamList;
    @JoinColumn(name = "PM_idpm", referencedColumnName = "idpm")
    @ManyToOne(optional = false)
    private Pm pMidpm;

    public Tac() {
    }

    public Tac(Integer idtac) {
        this.idtac = idtac;
    }

    public Tac(Integer idtac, String name, String description) {
        this.idtac = idtac;
        this.name = name;
        this.description = description;
    }

    public Integer getIdtac() {
        return idtac;
    }

    public void setIdtac(Integer idtac) {
        this.idtac = idtac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Issue> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    @XmlTransient
    public List<CoreTeam> getCoreTeamList() {
        return coreTeamList;
    }

    public void setCoreTeamList(List<CoreTeam> coreTeamList) {
        this.coreTeamList = coreTeamList;
    }

    public Pm getPMidpm() {
        return pMidpm;
    }

    public void setPMidpm(Pm pMidpm) {
        this.pMidpm = pMidpm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtac != null ? idtac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tac)) {
            return false;
        }
        Tac other = (Tac) object;
        if ((this.idtac == null && other.idtac != null) || (this.idtac != null && !this.idtac.equals(other.idtac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tac[ idtac=" + idtac + " ]";
    }
    
}

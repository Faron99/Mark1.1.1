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
@Table(name = "issue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),
    @NamedQuery(name = "Issue.findByIdIssue", query = "SELECT i FROM Issue i WHERE i.issuePK.idIssue = :idIssue"),
    @NamedQuery(name = "Issue.findByIssue", query = "SELECT i FROM Issue i WHERE i.issue = :issue"),
    @NamedQuery(name = "Issue.findByDescription", query = "SELECT i FROM Issue i WHERE i.description = :description"),
    @NamedQuery(name = "Issue.findByRootCause", query = "SELECT i FROM Issue i WHERE i.rootCause = :rootCause"),
    @NamedQuery(name = "Issue.findByRecommend", query = "SELECT i FROM Issue i WHERE i.recommend = :recommend"),
    @NamedQuery(name = "Issue.findByGroupCoreTeamidGroup", query = "SELECT i FROM Issue i WHERE i.issuePK.groupCoreTeamidGroup = :groupCoreTeamidGroup")})
public class Issue implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IssuePK issuePK;
    @Size(max = 45)
    @Column(name = "Issue")
    private String issue;
    @Size(max = 200)
    @Column(name = "Description")
    private String description;
    @Size(max = 200)
    @Column(name = "Root_Cause")
    private String rootCause;
    @Size(max = 200)
    @Column(name = "Recommend")
    private String recommend;
    @JoinColumn(name = "Group_Core_Team_idGroup", referencedColumnName = "idGroup", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GroupCoreTeam groupCoreTeam;

    public Issue() {
    }

    public Issue(IssuePK issuePK) {
        this.issuePK = issuePK;
    }

    public Issue(int idIssue, String groupCoreTeamidGroup) {
        this.issuePK = new IssuePK(idIssue, groupCoreTeamidGroup);
    }

    public IssuePK getIssuePK() {
        return issuePK;
    }

    public void setIssuePK(IssuePK issuePK) {
        this.issuePK = issuePK;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
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
        hash += (issuePK != null ? issuePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.issuePK == null && other.issuePK != null) || (this.issuePK != null && !this.issuePK.equals(other.issuePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Issue[ issuePK=" + issuePK + " ]";
    }
    
}

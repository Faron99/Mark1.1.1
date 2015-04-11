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
@Table(name = "issue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),
    @NamedQuery(name = "Issue.findByIdissue", query = "SELECT i FROM Issue i WHERE i.idissue = :idissue"),
    @NamedQuery(name = "Issue.findByIssue", query = "SELECT i FROM Issue i WHERE i.issue = :issue"),
    @NamedQuery(name = "Issue.findByRootCause", query = "SELECT i FROM Issue i WHERE i.rootCause = :rootCause"),
    @NamedQuery(name = "Issue.findByRecommend", query = "SELECT i FROM Issue i WHERE i.recommend = :recommend"),
    @NamedQuery(name = "Issue.findByDescription", query = "SELECT i FROM Issue i WHERE i.description = :description")})
public class Issue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idissue")
    private Integer idissue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issue")
    private int issue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "root_cause")
    private String rootCause;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "recommend")
    private String recommend;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "TAC_idtac", referencedColumnName = "idtac")
    @ManyToOne(optional = false)
    private Tac tACidtac;

    public Issue() {
    }

    public Issue(Integer idissue) {
        this.idissue = idissue;
    }

    public Issue(Integer idissue, int issue, String rootCause, String recommend, String description) {
        this.idissue = idissue;
        this.issue = issue;
        this.rootCause = rootCause;
        this.recommend = recommend;
        this.description = description;
    }

    public Integer getIdissue() {
        return idissue;
    }

    public void setIdissue(Integer idissue) {
        this.idissue = idissue;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (idissue != null ? idissue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.idissue == null && other.idissue != null) || (this.idissue != null && !this.idissue.equals(other.idissue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Issue[ idissue=" + idissue + " ]";
    }
    
}

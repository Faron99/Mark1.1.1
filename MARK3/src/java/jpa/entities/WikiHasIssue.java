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
@Table(name = "wiki_has_issue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WikiHasIssue.findAll", query = "SELECT w FROM WikiHasIssue w"),
    @NamedQuery(name = "WikiHasIssue.findByWikiidWiki", query = "SELECT w FROM WikiHasIssue w WHERE w.wikiHasIssuePK.wikiidWiki = :wikiidWiki"),
    @NamedQuery(name = "WikiHasIssue.findByIssueIdissue", query = "SELECT w FROM WikiHasIssue w WHERE w.wikiHasIssuePK.issueIdissue = :issueIdissue"),
    @NamedQuery(name = "WikiHasIssue.findByDemas", query = "SELECT w FROM WikiHasIssue w WHERE w.demas = :demas")})
public class WikiHasIssue implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WikiHasIssuePK wikiHasIssuePK;
    @Size(max = 45)
    @Column(name = "demas")
    private String demas;
    @JoinColumn(name = "Wiki_idWiki", referencedColumnName = "idWiki", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Wiki wiki;
    @JoinColumn(name = "issue_idissue", referencedColumnName = "idtac", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tac tac;

    public WikiHasIssue() {
    }

    public WikiHasIssue(WikiHasIssuePK wikiHasIssuePK) {
        this.wikiHasIssuePK = wikiHasIssuePK;
    }

    public WikiHasIssue(int wikiidWiki, int issueIdissue) {
        this.wikiHasIssuePK = new WikiHasIssuePK(wikiidWiki, issueIdissue);
    }

    public WikiHasIssuePK getWikiHasIssuePK() {
        return wikiHasIssuePK;
    }

    public void setWikiHasIssuePK(WikiHasIssuePK wikiHasIssuePK) {
        this.wikiHasIssuePK = wikiHasIssuePK;
    }

    public String getDemas() {
        return demas;
    }

    public void setDemas(String demas) {
        this.demas = demas;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public Tac getTac() {
        return tac;
    }

    public void setTac(Tac tac) {
        this.tac = tac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wikiHasIssuePK != null ? wikiHasIssuePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WikiHasIssue)) {
            return false;
        }
        WikiHasIssue other = (WikiHasIssue) object;
        if ((this.wikiHasIssuePK == null && other.wikiHasIssuePK != null) || (this.wikiHasIssuePK != null && !this.wikiHasIssuePK.equals(other.wikiHasIssuePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.WikiHasIssue[ wikiHasIssuePK=" + wikiHasIssuePK + " ]";
    }
    
}

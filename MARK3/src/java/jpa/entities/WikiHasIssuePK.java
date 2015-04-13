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

/**
 *
 * @author mmercadoco
 */
@Embeddable
public class WikiHasIssuePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Wiki_idWiki")
    private int wikiidWiki;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issue_idissue")
    private int issueIdissue;

    public WikiHasIssuePK() {
    }

    public WikiHasIssuePK(int wikiidWiki, int issueIdissue) {
        this.wikiidWiki = wikiidWiki;
        this.issueIdissue = issueIdissue;
    }

    public int getWikiidWiki() {
        return wikiidWiki;
    }

    public void setWikiidWiki(int wikiidWiki) {
        this.wikiidWiki = wikiidWiki;
    }

    public int getIssueIdissue() {
        return issueIdissue;
    }

    public void setIssueIdissue(int issueIdissue) {
        this.issueIdissue = issueIdissue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) wikiidWiki;
        hash += (int) issueIdissue;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WikiHasIssuePK)) {
            return false;
        }
        WikiHasIssuePK other = (WikiHasIssuePK) object;
        if (this.wikiidWiki != other.wikiidWiki) {
            return false;
        }
        if (this.issueIdissue != other.issueIdissue) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.WikiHasIssuePK[ wikiidWiki=" + wikiidWiki + ", issueIdissue=" + issueIdissue + " ]";
    }
    
}

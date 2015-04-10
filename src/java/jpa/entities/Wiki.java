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
@Table(name = "wiki")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wiki.findAll", query = "SELECT w FROM Wiki w"),
    @NamedQuery(name = "Wiki.findByIdWiki", query = "SELECT w FROM Wiki w WHERE w.idWiki = :idWiki"),
    @NamedQuery(name = "Wiki.findByName", query = "SELECT w FROM Wiki w WHERE w.name = :name"),
    @NamedQuery(name = "Wiki.findByModel", query = "SELECT w FROM Wiki w WHERE w.model = :model"),
    @NamedQuery(name = "Wiki.findByDescription", query = "SELECT w FROM Wiki w WHERE w.description = :description"),
    @NamedQuery(name = "Wiki.findByImage", query = "SELECT w FROM Wiki w WHERE w.image = :image"),
    @NamedQuery(name = "Wiki.findByDisp", query = "SELECT w FROM Wiki w WHERE w.disp = :disp"),
    @NamedQuery(name = "Wiki.findByTec", query = "SELECT w FROM Wiki w WHERE w.tec = :tec"),
    @NamedQuery(name = "Wiki.findByEnt", query = "SELECT w FROM Wiki w WHERE w.ent = :ent")})
public class Wiki implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWiki")
    private Integer idWiki;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Model")
    private String model;
    @Size(max = 100)
    @Column(name = "Description")
    private String description;
    @Size(max = 70)
    @Column(name = "Image")
    private String image;
    @Size(max = 45)
    @Column(name = "Disp")
    private String disp;
    @Size(max = 45)
    @Column(name = "Tec")
    private String tec;
    @Size(max = 45)
    @Column(name = "Ent")
    private String ent;

    public Wiki() {
    }

    public Wiki(Integer idWiki) {
        this.idWiki = idWiki;
    }

    public Integer getIdWiki() {
        return idWiki;
    }

    public void setIdWiki(Integer idWiki) {
        this.idWiki = idWiki;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public String getTec() {
        return tec;
    }

    public void setTec(String tec) {
        this.tec = tec;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWiki != null ? idWiki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wiki)) {
            return false;
        }
        Wiki other = (Wiki) object;
        if ((this.idWiki == null && other.idWiki != null) || (this.idWiki != null && !this.idWiki.equals(other.idWiki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Wiki[ idWiki=" + idWiki + " ]";
    }
    
}

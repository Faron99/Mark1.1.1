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
@Table(name = "pm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pm.findAll", query = "SELECT p FROM Pm p"),
    @NamedQuery(name = "Pm.findByIdpm", query = "SELECT p FROM Pm p WHERE p.idpm = :idpm"),
    @NamedQuery(name = "Pm.findByName", query = "SELECT p FROM Pm p WHERE p.name = :name"),
    @NamedQuery(name = "Pm.findByOffice", query = "SELECT p FROM Pm p WHERE p.office = :office"),
    @NamedQuery(name = "Pm.findByWorkcall", query = "SELECT p FROM Pm p WHERE p.workcall = :workcall"),
    @NamedQuery(name = "Pm.findByEmail", query = "SELECT p FROM Pm p WHERE p.email = :email"),
    @NamedQuery(name = "Pm.findByImage", query = "SELECT p FROM Pm p WHERE p.image = :image")})
public class Pm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpm")
    private Integer idpm;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "office")
    private String office;
    @Size(max = 45)
    @Column(name = "workcall")
    private String workcall;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pMidpm")
    private List<Tac> tacList;

    public Pm() {
    }

    public Pm(Integer idpm) {
        this.idpm = idpm;
    }

    public Pm(Integer idpm, String image) {
        this.idpm = idpm;
        this.image = image;
    }

    public Integer getIdpm() {
        return idpm;
    }

    public void setIdpm(Integer idpm) {
        this.idpm = idpm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getWorkcall() {
        return workcall;
    }

    public void setWorkcall(String workcall) {
        this.workcall = workcall;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public List<Tac> getTacList() {
        return tacList;
    }

    public void setTacList(List<Tac> tacList) {
        this.tacList = tacList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpm != null ? idpm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pm)) {
            return false;
        }
        Pm other = (Pm) object;
        if ((this.idpm == null && other.idpm != null) || (this.idpm != null && !this.idpm.equals(other.idpm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Pm[ idpm=" + idpm + " ]";
    }
    
}

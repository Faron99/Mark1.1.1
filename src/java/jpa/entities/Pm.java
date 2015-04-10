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
@Table(name = "pm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pm.findAll", query = "SELECT p FROM Pm p"),
    @NamedQuery(name = "Pm.findByIdPM", query = "SELECT p FROM Pm p WHERE p.idPM = :idPM"),
    @NamedQuery(name = "Pm.findByName", query = "SELECT p FROM Pm p WHERE p.name = :name"),
    @NamedQuery(name = "Pm.findByImage", query = "SELECT p FROM Pm p WHERE p.image = :image"),
    @NamedQuery(name = "Pm.findByOffice", query = "SELECT p FROM Pm p WHERE p.office = :office"),
    @NamedQuery(name = "Pm.findByEmail", query = "SELECT p FROM Pm p WHERE p.email = :email"),
    @NamedQuery(name = "Pm.findByCallWork", query = "SELECT p FROM Pm p WHERE p.callWork = :callWork")})
public class Pm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPM")
    private Integer idPM;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 70)
    @Column(name = "Image")
    private String image;
    @Size(max = 45)
    @Column(name = "Office")
    private String office;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "Email")
    private String email;
    @Size(max = 45)
    @Column(name = "CallWork")
    private String callWork;

    public Pm() {
    }

    public Pm(Integer idPM) {
        this.idPM = idPM;
    }

    public Integer getIdPM() {
        return idPM;
    }

    public void setIdPM(Integer idPM) {
        this.idPM = idPM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCallWork() {
        return callWork;
    }

    public void setCallWork(String callWork) {
        this.callWork = callWork;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPM != null ? idPM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pm)) {
            return false;
        }
        Pm other = (Pm) object;
        if ((this.idPM == null && other.idPM != null) || (this.idPM != null && !this.idPM.equals(other.idPM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Pm[ idPM=" + idPM + " ]";
    }
    
}

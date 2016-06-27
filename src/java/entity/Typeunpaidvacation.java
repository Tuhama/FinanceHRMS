/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
 * @author TUHAMA
 */
@Entity
@Table(name = "typeunpaidvacation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeunpaidvacation.findAll", query = "SELECT t FROM Typeunpaidvacation t"),
    @NamedQuery(name = "Typeunpaidvacation.findById", query = "SELECT t FROM Typeunpaidvacation t WHERE t.id = :id"),
    @NamedQuery(name = "Typeunpaidvacation.findByName", query = "SELECT t FROM Typeunpaidvacation t WHERE t.name = :name")})
public class Typeunpaidvacation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeunpaidvacationId")
    private Collection<EmpUnpaidvacation> empUnpaidvacationCollection;

    public Typeunpaidvacation() {
    }

    public Typeunpaidvacation(Short id) {
        this.id = id;
    }

    public Typeunpaidvacation(Short id, String name) {
        this.id = id;
        this.name = name;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<EmpUnpaidvacation> getEmpUnpaidvacationCollection() {
        return empUnpaidvacationCollection;
    }

    public void setEmpUnpaidvacationCollection(Collection<EmpUnpaidvacation> empUnpaidvacationCollection) {
        this.empUnpaidvacationCollection = empUnpaidvacationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeunpaidvacation)) {
            return false;
        }
        Typeunpaidvacation other = (Typeunpaidvacation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Typeunpaidvacation[ id=" + id + " ]";
    }
    
}

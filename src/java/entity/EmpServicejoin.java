/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUHAMA
 */
@Entity
@Table(name = "emp_servicejoin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpServicejoin.findAll", query = "SELECT e FROM EmpServicejoin e"),
    @NamedQuery(name = "EmpServicejoin.findByDaysduration", query = "SELECT e FROM EmpServicejoin e WHERE e.daysduration = :daysduration"),
    @NamedQuery(name = "EmpServicejoin.findByMonthsduration", query = "SELECT e FROM EmpServicejoin e WHERE e.monthsduration = :monthsduration"),
    @NamedQuery(name = "EmpServicejoin.findByPlaceofservice", query = "SELECT e FROM EmpServicejoin e WHERE e.placeofservice = :placeofservice"),
    @NamedQuery(name = "EmpServicejoin.findByDoctype", query = "SELECT e FROM EmpServicejoin e WHERE e.doctype = :doctype"),
    @NamedQuery(name = "EmpServicejoin.findByDocnumber", query = "SELECT e FROM EmpServicejoin e WHERE e.docnumber = :docnumber"),
    @NamedQuery(name = "EmpServicejoin.findByDocdate", query = "SELECT e FROM EmpServicejoin e WHERE e.docdate = :docdate"),
    @NamedQuery(name = "EmpServicejoin.findById", query = "SELECT e FROM EmpServicejoin e WHERE e.id = :id")})
public class EmpServicejoin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "daysduration")
    private int daysduration;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monthsduration")
    private short monthsduration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "placeofservice")
    private String placeofservice;
    @Size(max = 45)
    @Column(name = "doctype")
    private String doctype;
    @Size(max = 45)
    @Column(name = "docnumber")
    private String docnumber;
    @Column(name = "docdate")
    @Temporal(TemporalType.DATE)
    private Date docdate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeId;

    public EmpServicejoin() {
    }

    public EmpServicejoin(Integer id) {
        this.id = id;
    }

    public EmpServicejoin(Integer id, int daysduration, short monthsduration, String placeofservice) {
        this.id = id;
        this.daysduration = daysduration;
        this.monthsduration = monthsduration;
        this.placeofservice = placeofservice;
    }

    public int getDaysduration() {
        return daysduration;
    }

    public void setDaysduration(int daysduration) {
        this.daysduration = daysduration;
    }

    public short getMonthsduration() {
        return monthsduration;
    }

    public void setMonthsduration(short monthsduration) {
        this.monthsduration = monthsduration;
    }

    public String getPlaceofservice() {
        return placeofservice;
    }

    public void setPlaceofservice(String placeofservice) {
        this.placeofservice = placeofservice;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public void setDocnumber(String docnumber) {
        this.docnumber = docnumber;
    }

    public Date getDocdate() {
        return docdate;
    }

    public void setDocdate(Date docdate) {
        this.docdate = docdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof EmpServicejoin)) {
            return false;
        }
        EmpServicejoin other = (EmpServicejoin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpServicejoin[ id=" + id + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUHAMA
 */
@Entity
@Table(name = "emp_healthleav")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpHealthleav.findAll", query = "SELECT e FROM EmpHealthleav e"),
    @NamedQuery(name = "EmpHealthleav.findByYear", query = "SELECT e FROM EmpHealthleav e WHERE e.year = :year"),
    @NamedQuery(name = "EmpHealthleav.findByDayscount", query = "SELECT e FROM EmpHealthleav e WHERE e.dayscount = :dayscount"),
    @NamedQuery(name = "EmpHealthleav.findByStartdate", query = "SELECT e FROM EmpHealthleav e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "EmpHealthleav.findByEnddate", query = "SELECT e FROM EmpHealthleav e WHERE e.enddate = :enddate"),
    @NamedQuery(name = "EmpHealthleav.findById", query = "SELECT e FROM EmpHealthleav e WHERE e.id = :id")})
public class EmpHealthleav implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;
    @Column(name = "dayscount")
    private Short dayscount;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "typehealthleave_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Typehealthleave typehealthleaveId;

    public EmpHealthleav() {
    }

    public EmpHealthleav(Integer id) {
        this.id = id;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Short getDayscount() {
        return dayscount;
    }

    public void setDayscount(Short dayscount) {
        this.dayscount = dayscount;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public Typehealthleave getTypehealthleaveId() {
        return typehealthleaveId;
    }

    public void setTypehealthleaveId(Typehealthleave typehealthleaveId) {
        this.typehealthleaveId = typehealthleaveId;
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
        if (!(object instanceof EmpHealthleav)) {
            return false;
        }
        EmpHealthleav other = (EmpHealthleav) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpHealthleav[ id=" + id + " ]";
    }
    
}

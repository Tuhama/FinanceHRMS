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
@Table(name = "emp_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpEvent.findAll", query = "SELECT e FROM EmpEvent e"),
    @NamedQuery(name = "EmpEvent.findById", query = "SELECT e FROM EmpEvent e WHERE e.id = :id"),
    @NamedQuery(name = "EmpEvent.findByName", query = "SELECT e FROM EmpEvent e WHERE e.name = :name"),
    @NamedQuery(name = "EmpEvent.findByCategory", query = "SELECT e FROM EmpEvent e WHERE e.category = :category"),
    @NamedQuery(name = "EmpEvent.findBySalary", query = "SELECT e FROM EmpEvent e WHERE e.salary = :salary"),
    @NamedQuery(name = "EmpEvent.findByStartdate", query = "SELECT e FROM EmpEvent e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "EmpEvent.findByDoctype", query = "SELECT e FROM EmpEvent e WHERE e.doctype = :doctype"),
    @NamedQuery(name = "EmpEvent.findByDocnumber", query = "SELECT e FROM EmpEvent e WHERE e.docnumber = :docnumber"),
    @NamedQuery(name = "EmpEvent.findByDocdate", query = "SELECT e FROM EmpEvent e WHERE e.docdate = :docdate")})
public class EmpEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 6)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private int salary;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Size(max = 45)
    @Column(name = "doctype")
    private String doctype;
    @Size(max = 45)
    @Column(name = "docnumber")
    private String docnumber;
    @Column(name = "docdate")
    @Temporal(TemporalType.DATE)
    private Date docdate;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Position positionId;

    public EmpEvent() {
    }

    public EmpEvent(Integer id) {
        this.id = id;
    }

    public EmpEvent(Integer id, int salary) {
        this.id = id;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
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

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
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
        if (!(object instanceof EmpEvent)) {
            return false;
        }
        EmpEvent other = (EmpEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpEvent[ id=" + id + " ]";
    }
    
}

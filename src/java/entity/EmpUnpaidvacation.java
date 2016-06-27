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
@Table(name = "emp_unpaidvacation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpUnpaidvacation.findAll", query = "SELECT e FROM EmpUnpaidvacation e"),
    @NamedQuery(name = "EmpUnpaidvacation.findByStartdate", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "EmpUnpaidvacation.findByEnddate", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.enddate = :enddate"),
    @NamedQuery(name = "EmpUnpaidvacation.findByReason", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.reason = :reason"),
    @NamedQuery(name = "EmpUnpaidvacation.findByDoctype", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.doctype = :doctype"),
    @NamedQuery(name = "EmpUnpaidvacation.findByDocnumber", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.docnumber = :docnumber"),
    @NamedQuery(name = "EmpUnpaidvacation.findByDocdate", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.docdate = :docdate"),
    @NamedQuery(name = "EmpUnpaidvacation.findByFolding", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.folding = :folding"),
    @NamedQuery(name = "EmpUnpaidvacation.findById", query = "SELECT e FROM EmpUnpaidvacation e WHERE e.id = :id")})
public class EmpUnpaidvacation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @Size(max = 45)
    @Column(name = "doctype")
    private String doctype;
    @Size(max = 45)
    @Column(name = "docnumber")
    private String docnumber;
    @Column(name = "docdate")
    @Temporal(TemporalType.DATE)
    private Date docdate;
    @Column(name = "folding")
    private Boolean folding;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "typeunpaidvacation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Typeunpaidvacation typeunpaidvacationId;

    public EmpUnpaidvacation() {
    }

    public EmpUnpaidvacation(Integer id) {
        this.id = id;
    }

    public EmpUnpaidvacation(Integer id, Date startdate, Date enddate) {
        this.id = id;
        this.startdate = startdate;
        this.enddate = enddate;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Boolean getFolding() {
        return folding;
    }

    public void setFolding(Boolean folding) {
        this.folding = folding;
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

    public Typeunpaidvacation getTypeunpaidvacationId() {
        return typeunpaidvacationId;
    }

    public void setTypeunpaidvacationId(Typeunpaidvacation typeunpaidvacationId) {
        this.typeunpaidvacationId = typeunpaidvacationId;
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
        if (!(object instanceof EmpUnpaidvacation)) {
            return false;
        }
        EmpUnpaidvacation other = (EmpUnpaidvacation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpUnpaidvacation[ id=" + id + " ]";
    }
    
}

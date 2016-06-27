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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUHAMA
 */
@Entity
@Table(name = "emp_punishment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpPunishment.findAll", query = "SELECT e FROM EmpPunishment e"),
    @NamedQuery(name = "EmpPunishment.findByReason", query = "SELECT e FROM EmpPunishment e WHERE e.reason = :reason"),
    @NamedQuery(name = "EmpPunishment.findByDate", query = "SELECT e FROM EmpPunishment e WHERE e.date = :date"),
    @NamedQuery(name = "EmpPunishment.findByDoctype", query = "SELECT e FROM EmpPunishment e WHERE e.doctype = :doctype"),
    @NamedQuery(name = "EmpPunishment.findByDocnumber", query = "SELECT e FROM EmpPunishment e WHERE e.docnumber = :docnumber"),
    @NamedQuery(name = "EmpPunishment.findByDocdate", query = "SELECT e FROM EmpPunishment e WHERE e.docdate = :docdate"),
    @NamedQuery(name = "EmpPunishment.findById", query = "SELECT e FROM EmpPunishment e WHERE e.id = :id")})
public class EmpPunishment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "reason")
    private String reason;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
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
    @JoinColumn(name = "typepunishment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Typepunishment typepunishmentId;

    public EmpPunishment() {
    }

    public EmpPunishment(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Typepunishment getTypepunishmentId() {
        return typepunishmentId;
    }

    public void setTypepunishmentId(Typepunishment typepunishmentId) {
        this.typepunishmentId = typepunishmentId;
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
        if (!(object instanceof EmpPunishment)) {
            return false;
        }
        EmpPunishment other = (EmpPunishment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpPunishment[ id=" + id + " ]";
    }
    
}

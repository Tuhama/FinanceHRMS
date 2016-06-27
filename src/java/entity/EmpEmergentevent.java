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
@Table(name = "emp_emergentevent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpEmergentevent.findAll", query = "SELECT e FROM EmpEmergentevent e"),
    @NamedQuery(name = "EmpEmergentevent.findByDate", query = "SELECT e FROM EmpEmergentevent e WHERE e.date = :date"),
    @NamedQuery(name = "EmpEmergentevent.findByReason", query = "SELECT e FROM EmpEmergentevent e WHERE e.reason = :reason"),
    @NamedQuery(name = "EmpEmergentevent.findByDoctype", query = "SELECT e FROM EmpEmergentevent e WHERE e.doctype = :doctype"),
    @NamedQuery(name = "EmpEmergentevent.findByDocnumber", query = "SELECT e FROM EmpEmergentevent e WHERE e.docnumber = :docnumber"),
    @NamedQuery(name = "EmpEmergentevent.findByDocdate", query = "SELECT e FROM EmpEmergentevent e WHERE e.docdate = :docdate"),
    @NamedQuery(name = "EmpEmergentevent.findById", query = "SELECT e FROM EmpEmergentevent e WHERE e.id = :id")})
public class EmpEmergentevent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public EmpEmergentevent() {
    }

    public EmpEmergentevent(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof EmpEmergentevent)) {
            return false;
        }
        EmpEmergentevent other = (EmpEmergentevent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpEmergentevent[ id=" + id + " ]";
    }
    
}

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
@Table(name = "emp_reward")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpReward.findAll", query = "SELECT e FROM EmpReward e"),
    @NamedQuery(name = "EmpReward.findByAmount", query = "SELECT e FROM EmpReward e WHERE e.amount = :amount"),
    @NamedQuery(name = "EmpReward.findByKind", query = "SELECT e FROM EmpReward e WHERE e.kind = :kind"),
    @NamedQuery(name = "EmpReward.findByDoctype", query = "SELECT e FROM EmpReward e WHERE e.doctype = :doctype"),
    @NamedQuery(name = "EmpReward.findByDocnumber", query = "SELECT e FROM EmpReward e WHERE e.docnumber = :docnumber"),
    @NamedQuery(name = "EmpReward.findByDocdate", query = "SELECT e FROM EmpReward e WHERE e.docdate = :docdate"),
    @NamedQuery(name = "EmpReward.findById", query = "SELECT e FROM EmpReward e WHERE e.id = :id")})
public class EmpReward implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "amount")
    private Integer amount;
    @Size(max = 45)
    @Column(name = "kind")
    private String kind;
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

    public EmpReward() {
    }

    public EmpReward(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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
        if (!(object instanceof EmpReward)) {
            return false;
        }
        EmpReward other = (EmpReward) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpReward[ id=" + id + " ]";
    }
    
}

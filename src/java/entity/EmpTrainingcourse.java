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
@Table(name = "emp_trainingcourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpTrainingcourse.findAll", query = "SELECT e FROM EmpTrainingcourse e"),
    @NamedQuery(name = "EmpTrainingcourse.findByDuration", query = "SELECT e FROM EmpTrainingcourse e WHERE e.duration = :duration"),
    @NamedQuery(name = "EmpTrainingcourse.findByKind", query = "SELECT e FROM EmpTrainingcourse e WHERE e.kind = :kind"),
    @NamedQuery(name = "EmpTrainingcourse.findByPlace", query = "SELECT e FROM EmpTrainingcourse e WHERE e.place = :place"),
    @NamedQuery(name = "EmpTrainingcourse.findByStartdate", query = "SELECT e FROM EmpTrainingcourse e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "EmpTrainingcourse.findByEnddate", query = "SELECT e FROM EmpTrainingcourse e WHERE e.enddate = :enddate"),
    @NamedQuery(name = "EmpTrainingcourse.findById", query = "SELECT e FROM EmpTrainingcourse e WHERE e.id = :id")})
public class EmpTrainingcourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "duration")
    private Integer duration;
    @Size(max = 45)
    @Column(name = "kind")
    private String kind;
    @Size(max = 45)
    @Column(name = "place")
    private String place;
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

    public EmpTrainingcourse() {
    }

    public EmpTrainingcourse(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpTrainingcourse)) {
            return false;
        }
        EmpTrainingcourse other = (EmpTrainingcourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmpTrainingcourse[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUHAMA
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employee.findByFathername", query = "SELECT e FROM Employee e WHERE e.fathername = :fathername"),
    @NamedQuery(name = "Employee.findByMothername", query = "SELECT e FROM Employee e WHERE e.mothername = :mothername"),
    @NamedQuery(name = "Employee.findByDateOfBirth", query = "SELECT e FROM Employee e WHERE e.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Employee.findByNationalnumber", query = "SELECT e FROM Employee e WHERE e.nationalnumber = :nationalnumber"),
    @NamedQuery(name = "Employee.findByPlaceOfBirth", query = "SELECT e FROM Employee e WHERE e.placeOfBirth = :placeOfBirth"),
    @NamedQuery(name = "Employee.findByRegisteinfo", query = "SELECT e FROM Employee e WHERE e.registeinfo = :registeinfo"),
    @NamedQuery(name = "Employee.findByMobilePhone", query = "SELECT e FROM Employee e WHERE e.mobilePhone = :mobilePhone"),
    @NamedQuery(name = "Employee.findByHomePhone", query = "SELECT e FROM Employee e WHERE e.homePhone = :homePhone"),
    @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address"),
    @NamedQuery(name = "Employee.findByPassportnumber", query = "SELECT e FROM Employee e WHERE e.passportnumber = :passportnumber"),
    @NamedQuery(name = "Employee.findByBasesalary", query = "SELECT e FROM Employee e WHERE e.basesalary = :basesalary"),
    @NamedQuery(name = "Employee.findByNotes", query = "SELECT e FROM Employee e WHERE e.notes = :notes"),
    @NamedQuery(name = "Employee.findBySocialsecuritynumber", query = "SELECT e FROM Employee e WHERE e.socialsecuritynumber = :socialsecuritynumber"),
    @NamedQuery(name = "Employee.findByPersonalnumber", query = "SELECT e FROM Employee e WHERE e.personalnumber = :personalnumber"),
    @NamedQuery(name = "Employee.findByFirstworkdate", query = "SELECT e FROM Employee e WHERE e.firstworkdate = :firstworkdate"),
    @NamedQuery(name = "Employee.findByWorkdocnumber", query = "SELECT e FROM Employee e WHERE e.workdocnumber = :workdocnumber"),
    @NamedQuery(name = "Employee.findByWorkdocdate", query = "SELECT e FROM Employee e WHERE e.workdocdate = :workdocdate"),
    @NamedQuery(name = "Employee.findByModworkdocnumber", query = "SELECT e FROM Employee e WHERE e.modworkdocnumber = :modworkdocnumber"),
    @NamedQuery(name = "Employee.findByModworkdocdate", query = "SELECT e FROM Employee e WHERE e.modworkdocdate = :modworkdocdate"),
    @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "fathername")
    private String fathername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mothername")
    private String mothername;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nationalnumber")
    private String nationalnumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "placeOfBirth")
    private String placeOfBirth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "registeinfo")
    private String registeinfo;
    @Size(max = 20)
    @Column(name = "mobilePhone")
    private String mobilePhone;
    @Size(max = 20)
    @Column(name = "homePhone")
    private String homePhone;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @Size(max = 25)
    @Column(name = "passportnumber")
    private String passportnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "basesalary")
    private int basesalary;
    @Size(max = 150)
    @Column(name = "notes")
    private String notes;
    @Size(max = 10)
    @Column(name = "socialsecuritynumber")
    private String socialsecuritynumber;
    @Size(max = 10)
    @Column(name = "personalnumber")
    private String personalnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "firstworkdate")
    @Temporal(TemporalType.DATE)
    private Date firstworkdate;
    @Size(max = 15)
    @Column(name = "workdocnumber")
    private String workdocnumber;
    @Column(name = "workdocdate")
    @Temporal(TemporalType.DATE)
    private Date workdocdate;
    @Size(max = 15)
    @Column(name = "modworkdocnumber")
    private String modworkdocnumber;
    @Column(name = "modworkdocdate")
    @Temporal(TemporalType.DATE)
    private Date modworkdocdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "gender")
    private String gender;
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Branch branchId;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category categoryId;
    @JoinColumn(name = "certificate_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Certificate certificateId;
    @JoinColumn(name = "devision_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Devision devisionId;
    @JoinColumn(name = "familystatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Familystatus familystatusId;
    @JoinColumn(name = "foreignlanguage_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Foreignlanguage foreignlanguageId;
    @JoinColumn(name = "martialstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Martialstatus martialstatusId;
    @JoinColumn(name = "natianality_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Natianality natianalityId;
    @JoinColumn(name = "workstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Workstatus workstatusId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmpUnpaidvacation> empUnpaidvacationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmpTrainingcourse> empTrainingcourseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmpReward> empRewardCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmpEvent> empEventCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmpPunishment> empPunishmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmpHealthleav> empHealthleavCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmpServicejoin> empServicejoinCollection;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, String firstname, String lastname, String fathername, String mothername, Date dateOfBirth, String nationalnumber, String placeOfBirth, String registeinfo, int basesalary, Date firstworkdate, String gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.dateOfBirth = dateOfBirth;
        this.nationalnumber = nationalnumber;
        this.placeOfBirth = placeOfBirth;
        this.registeinfo = registeinfo;
        this.basesalary = basesalary;
        this.firstworkdate = firstworkdate;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalnumber() {
        return nationalnumber;
    }

    public void setNationalnumber(String nationalnumber) {
        this.nationalnumber = nationalnumber;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getRegisteinfo() {
        return registeinfo;
    }

    public void setRegisteinfo(String registeinfo) {
        this.registeinfo = registeinfo;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassportnumber() {
        return passportnumber;
    }

    public void setPassportnumber(String passportnumber) {
        this.passportnumber = passportnumber;
    }

    public int getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(int basesalary) {
        this.basesalary = basesalary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSocialsecuritynumber() {
        return socialsecuritynumber;
    }

    public void setSocialsecuritynumber(String socialsecuritynumber) {
        this.socialsecuritynumber = socialsecuritynumber;
    }

    public String getPersonalnumber() {
        return personalnumber;
    }

    public void setPersonalnumber(String personalnumber) {
        this.personalnumber = personalnumber;
    }

    public Date getFirstworkdate() {
        return firstworkdate;
    }

    public void setFirstworkdate(Date firstworkdate) {
        this.firstworkdate = firstworkdate;
    }

    public String getWorkdocnumber() {
        return workdocnumber;
    }

    public void setWorkdocnumber(String workdocnumber) {
        this.workdocnumber = workdocnumber;
    }

    public Date getWorkdocdate() {
        return workdocdate;
    }

    public void setWorkdocdate(Date workdocdate) {
        this.workdocdate = workdocdate;
    }

    public String getModworkdocnumber() {
        return modworkdocnumber;
    }

    public void setModworkdocnumber(String modworkdocnumber) {
        this.modworkdocnumber = modworkdocnumber;
    }

    public Date getModworkdocdate() {
        return modworkdocdate;
    }

    public void setModworkdocdate(Date modworkdocdate) {
        this.modworkdocdate = modworkdocdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Certificate getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Certificate certificateId) {
        this.certificateId = certificateId;
    }

    public Devision getDevisionId() {
        return devisionId;
    }

    public void setDevisionId(Devision devisionId) {
        this.devisionId = devisionId;
    }

    public Familystatus getFamilystatusId() {
        return familystatusId;
    }

    public void setFamilystatusId(Familystatus familystatusId) {
        this.familystatusId = familystatusId;
    }

    public Foreignlanguage getForeignlanguageId() {
        return foreignlanguageId;
    }

    public void setForeignlanguageId(Foreignlanguage foreignlanguageId) {
        this.foreignlanguageId = foreignlanguageId;
    }

    public Martialstatus getMartialstatusId() {
        return martialstatusId;
    }

    public void setMartialstatusId(Martialstatus martialstatusId) {
        this.martialstatusId = martialstatusId;
    }

    public Natianality getNatianalityId() {
        return natianalityId;
    }

    public void setNatianalityId(Natianality natianalityId) {
        this.natianalityId = natianalityId;
    }

    public Workstatus getWorkstatusId() {
        return workstatusId;
    }

    public void setWorkstatusId(Workstatus workstatusId) {
        this.workstatusId = workstatusId;
    }

    @XmlTransient
    public Collection<EmpUnpaidvacation> getEmpUnpaidvacationCollection() {
        return empUnpaidvacationCollection;
    }

    public void setEmpUnpaidvacationCollection(Collection<EmpUnpaidvacation> empUnpaidvacationCollection) {
        this.empUnpaidvacationCollection = empUnpaidvacationCollection;
    }

    @XmlTransient
    public Collection<EmpTrainingcourse> getEmpTrainingcourseCollection() {
        return empTrainingcourseCollection;
    }

    public void setEmpTrainingcourseCollection(Collection<EmpTrainingcourse> empTrainingcourseCollection) {
        this.empTrainingcourseCollection = empTrainingcourseCollection;
    }

    @XmlTransient
    public Collection<EmpReward> getEmpRewardCollection() {
        return empRewardCollection;
    }

    public void setEmpRewardCollection(Collection<EmpReward> empRewardCollection) {
        this.empRewardCollection = empRewardCollection;
    }

    @XmlTransient
    public Collection<EmpEvent> getEmpEventCollection() {
        return empEventCollection;
    }

    public void setEmpEventCollection(Collection<EmpEvent> empEventCollection) {
        this.empEventCollection = empEventCollection;
    }

    @XmlTransient
    public Collection<EmpPunishment> getEmpPunishmentCollection() {
        return empPunishmentCollection;
    }

    public void setEmpPunishmentCollection(Collection<EmpPunishment> empPunishmentCollection) {
        this.empPunishmentCollection = empPunishmentCollection;
    }

    @XmlTransient
    public Collection<EmpHealthleav> getEmpHealthleavCollection() {
        return empHealthleavCollection;
    }

    public void setEmpHealthleavCollection(Collection<EmpHealthleav> empHealthleavCollection) {
        this.empHealthleavCollection = empHealthleavCollection;
    }

    @XmlTransient
    public Collection<EmpServicejoin> getEmpServicejoinCollection() {
        return empServicejoinCollection;
    }

    public void setEmpServicejoinCollection(Collection<EmpServicejoin> empServicejoinCollection) {
        this.empServicejoinCollection = empServicejoinCollection;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Employee[ id=" + id + " ]";
    }
    
}

package entity;

import entity.Branch;
import entity.Category;
import entity.Certificate;
import entity.Devision;
import entity.EmpEvent;
import entity.EmpHealthleav;
import entity.EmpPunishment;
import entity.EmpReward;
import entity.EmpServicejoin;
import entity.EmpTrainingcourse;
import entity.EmpUnpaidvacation;
import entity.Familystatus;
import entity.Foreignlanguage;
import entity.Martialstatus;
import entity.Natianality;
import entity.Workstatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-02T11:27:35")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> firstname;
    public static volatile SingularAttribute<Employee, String> notes;
    public static volatile SingularAttribute<Employee, String> gender;
    public static volatile SingularAttribute<Employee, Devision> devisionId;
    public static volatile SingularAttribute<Employee, Certificate> certificateId;
    public static volatile SingularAttribute<Employee, String> registeinfo;
    public static volatile SingularAttribute<Employee, Date> workdocdate;
    public static volatile SingularAttribute<Employee, Martialstatus> martialstatusId;
    public static volatile SingularAttribute<Employee, String> personalnumber;
    public static volatile SingularAttribute<Employee, Integer> basesalary;
    public static volatile SingularAttribute<Employee, String> modworkdocnumber;
    public static volatile SingularAttribute<Employee, Integer> id;
    public static volatile SingularAttribute<Employee, String> mothername;
    public static volatile SingularAttribute<Employee, Workstatus> workstatusId;
    public static volatile CollectionAttribute<Employee, EmpPunishment> empPunishmentCollection;
    public static volatile SingularAttribute<Employee, Branch> branchId;
    public static volatile SingularAttribute<Employee, String> placeOfBirth;
    public static volatile SingularAttribute<Employee, String> address;
    public static volatile SingularAttribute<Employee, String> homePhone;
    public static volatile SingularAttribute<Employee, String> workdocnumber;
    public static volatile SingularAttribute<Employee, Date> dateOfBirth;
    public static volatile CollectionAttribute<Employee, EmpHealthleav> empHealthleavCollection;
    public static volatile SingularAttribute<Employee, Date> firstworkdate;
    public static volatile SingularAttribute<Employee, String> passportnumber;
    public static volatile SingularAttribute<Employee, String> lastname;
    public static volatile SingularAttribute<Employee, Date> modworkdocdate;
    public static volatile CollectionAttribute<Employee, EmpEvent> empEventCollection;
    public static volatile SingularAttribute<Employee, String> nationalnumber;
    public static volatile SingularAttribute<Employee, String> socialsecuritynumber;
    public static volatile SingularAttribute<Employee, String> fathername;
    public static volatile SingularAttribute<Employee, Familystatus> familystatusId;
    public static volatile SingularAttribute<Employee, String> mobilePhone;
    public static volatile CollectionAttribute<Employee, EmpServicejoin> empServicejoinCollection;
    public static volatile CollectionAttribute<Employee, EmpTrainingcourse> empTrainingcourseCollection;
    public static volatile SingularAttribute<Employee, Natianality> natianalityId;
    public static volatile SingularAttribute<Employee, Foreignlanguage> foreignlanguageId;
    public static volatile CollectionAttribute<Employee, EmpReward> empRewardCollection;
    public static volatile SingularAttribute<Employee, Category> categoryId;
    public static volatile CollectionAttribute<Employee, EmpUnpaidvacation> empUnpaidvacationCollection;

}
package system.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Employee extends Person{
	
	 
	 @ManyToOne
	 @JoinColumn(name = "department_id")
	 protected Department department;
	 
	// @ManyToOne
	// @JoinColumn(name = "role_id")
	// private Role role;
	 
	 @Column(name = "email")
	 private String email;
		
	 @Column(name = "phone_number")
	 private String phoneNumber;
	 
	 @Column(name = "hire_date")
		private Date hireDate;

}

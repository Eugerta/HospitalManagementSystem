package system.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends Person{
		
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="regDate")
	private Date regDate;
	
	@Column(name="birthday")
	private Date birthday;
	//private int pacient_InOut;
	
	@Column(name="diagnose")
	private String diagnose;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;

	
//	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//	private List<OutDoorVisitSchedule> visitList = new ArrayList();

//	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//	private List<PatientTest> patientTests = new ArrayList();
	
		
}

package system.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "indoor_patient")
public class InDoorPatient extends Person{
	
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
	
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "nurse_id")
	private Nurse nurse;
	
	@OneToMany(mappedBy = "inDoorPatient", cascade = CascadeType.ALL)
	private List<PatientMedication> patientMed = new ArrayList();
	
	@OneToMany(mappedBy = "inDoorPatient", cascade = CascadeType.ALL)
	private List<PatientTest> patientTest = new ArrayList();

}

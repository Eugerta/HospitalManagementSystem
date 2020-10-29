package system.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "patient_test")
public class PatientTest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="reg_date")
	private Date regDate;
	
	@Column(name="result_date")
	private Date resultDate;
	
	@Column(name="result")
	private String result;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private InDoorPatient inDoorPatient;
	
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;
	
}

package system.model;

import java.sql.Date;
import java.util.List;

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
@Table(name = "patient_medication")
public class PatientMedication extends BaseEntity {
	
	@Column(name="dose")
	private int quantity;
	
	@Column(name="doseDay")
	private int doseDay;
	
	@Column(name="startDate")
	private Date startDate;

	@Column(name="endDate")
	private Date endDate;
	
	@Column(name="available_indoor")
	private boolean availableInDoor;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private InDoorPatient inDoorPatient;
	
	@ManyToOne
	@JoinColumn(name = "medication_id")
	private Medication medication;
	

}

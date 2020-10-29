package system.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "doctor")
public class Doctor extends Employee{
	
	@ManyToOne
	@JoinColumn(name = "specialisation_id")
	private Specialisation specialisation;
	
	@Column(name="username")
	private String username;

	@Column(name = "password")
	private String password;
	
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Patient> pacients = new ArrayList();

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
	private List<OutDoorVisitSchedule> visit = new ArrayList();
	
	
}

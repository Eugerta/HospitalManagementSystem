package system.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "medication")
public class Medication extends BaseEntity{

	@Column(name="code")
	private String code;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="description")
    private String description;
	
	
	@OneToMany(mappedBy = "medication", cascade = CascadeType.PERSIST)
	private List<PatientMedication> medicationPacients = new ArrayList();
	
}

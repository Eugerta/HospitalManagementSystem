package system.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "nurse")
public class Nurse extends Employee{

	@OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL)
	private List<InDoorPatient> pacients = new ArrayList();
	
	
}

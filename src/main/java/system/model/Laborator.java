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
@Table(name = "laborator")
public class Laborator extends BaseEntity{

	@Column(name="description")
	private String description;
	

	@OneToMany(mappedBy="laborator",cascade=CascadeType.ALL)
	private List<Laborant> laborant = new ArrayList();
	

	
}

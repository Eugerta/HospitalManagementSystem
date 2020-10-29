package system.model;

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

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test")
public class Test extends BaseEntity {
	
	@Column(name="description")
    private String description;
		
	@ManyToOne
	@JoinColumn(name = "laborant_id")
	private Laborant laborant;
	
	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
	private List<PatientTest> pacientsTest = new ArrayList();


}

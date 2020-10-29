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
@Table(name = "department")
public class Department extends BaseEntity{

    @Column(name="description")
	public String description;
	
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL)
	private List<Doctor> doctors=new ArrayList();
	
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL)
	private List<Nurse> nurses = new ArrayList();
	
	
}

package system.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	
	//@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	//private List<Employee> employee = new ArrayList();
	
	


}

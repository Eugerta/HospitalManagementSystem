package system.model;


import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "recepsionist")
public class Recepsionist extends Employee {

	@Column(name="username")
	private String username;

	@Column(name = "password")
	private String password;
	
}

package system.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import system.config.StringPrefixedSequenceIdGenerator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
	
	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
	
	@ManyToOne
    @JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name="username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "active", columnDefinition = "BOOLEAN")
	private Boolean active;

}

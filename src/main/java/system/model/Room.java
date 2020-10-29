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
@Table(name = "room")
public class Room {

	@Id
	@Column(name="room_number")
	private int room_number;
	
	@Column(name="floor")
	private int floor;
	
	@Column(name="capacity")
	private int  capacity;
	
	@Column(name="available_bed")
	private int available_bed;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<InDoorPatient> patients = new ArrayList();

	

}

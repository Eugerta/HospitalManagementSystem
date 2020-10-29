package system.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system.model.Patient;
import system.model.Room;

public interface RoomRepository extends  JpaRepository<Room,Integer>{
	
	@Query(value="select sum(available_bed) from room where available_bed IS NOT NULL and available_bed>0  and department_id=?1 ",nativeQuery = true)
	int getAvailableBed(int departamentId);
	
	//@Query(value="update room set availableBed =?2 where room_number=?1 ",nativeQuery = true)
	//void updateAvailableBed(int roomNumber, int availableBed);

}

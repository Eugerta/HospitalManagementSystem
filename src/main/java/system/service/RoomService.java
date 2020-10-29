package system.service;

import java.util.List;
import system.model.Room;
public interface RoomService {
	
	List<Room> findAll();
	
	Room findById(int roomId);
	
	void deleteById(int roomId);
	
	Room save(Room room);
	

}

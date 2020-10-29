package system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.Room;
import system.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomRepository roomRepository;

	@Override
	public List<Room> findAll() {
		
		return roomRepository.findAll();
	}

	@Override
	public Room findById(int roomId) {
		
		return roomRepository.findById(roomId).get();
	}

	@Override
	public void deleteById(int roomId) {
		roomRepository.deleteById(roomId);
		
	}

	@Override
	public Room save(Room room) {
		
		return roomRepository.save(room);
	}

}

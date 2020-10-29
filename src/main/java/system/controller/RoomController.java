package system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Room;
import system.service.RoomService;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping(value = "/getAllRooms")
	private String getAllRooms(Model model) {
		List<Room> rooms = roomService.findAll();
		model.addAttribute("rooms", rooms);
		return "rooms.html";
	}
	
	@GetMapping(value = "/getAllRoomsRec")
	private String getAllRoomsRec(Model model) {
		List<Room> rooms = roomService.findAll();
		model.addAttribute("rooms", rooms);
		return "roomsRec.html";
	}

	@RequestMapping(value = "/deleteRoom/{id}")
	private String deleteRoom(@PathVariable int id) {
		roomService.deleteById(id);
		return "redirect:/getAllRooms?delete=true";
	}

	@RequestMapping(value = "/goToAddRoomForm")
	private String goToAddRoomForm( Model model) {
		model.addAttribute("room",new Room());
		return "addRoom.html";
	}

	@PostMapping(value = "/addRoomForm")
	private String addRoom(@Valid Room room, BindingResult result, Model model) {
		System.out.println(room.getRoom_number());
		roomService.save(room);
		return "redirect:/getAllRooms?success=true";
	}
}

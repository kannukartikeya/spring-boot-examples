package guru.springframework.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;

import guru.springframework.domain.Room;
import guru.springframework.domain.Device;
import guru.springframework.mqtt.MqttPublishSubscribeUtility;
import guru.springframework.repositories.RoomRepository;
import guru.springframework.repositories.DeviceRepostitory;

@Controller
public class HomeAutomationController {

	@Autowired
	RoomRepository repository;

	@Autowired
	DeviceRepostitory deviceRepository;

	@RequestMapping("/room/{id}")
	public String room(@PathVariable Long id, Model model) {
		model.addAttribute("room", repository.findOne(id));
		model.addAttribute("devices", deviceRepository.findAll());
		return "room";
	}
	
	@RequestMapping("/device/{id}")
	public String device(@PathVariable Long id, Model model) {
		//model.addAttribute("developer", repository.findOne(id));
		model.addAttribute("device", deviceRepository.findOne(id));
		return "device";
	}
	

	@RequestMapping(value="/rooms",method=RequestMethod.GET)
	public String roomList(Model model) {
		model.addAttribute("rooms", repository.findAll());
		return "rooms";
	}

	@RequestMapping(value="/rooms",method=RequestMethod.POST)
	public String roomAdd(@RequestParam String roomName, @RequestParam String roomType,@RequestParam String roomSize, Model model) {
		Room room = new Room();
		room.setRoomName(roomName);
		room.setRoomType(roomType);
		room.setRoomSize(roomSize);
		repository.save(room);


		
		model.addAttribute("room", room);
		model.addAttribute("devices", deviceRepository.findAll());
		return "redirect:/room/" + room.getId();
	}

	@RequestMapping(value="/room/{id}/devices", method=RequestMethod.POST)
	public String roomAddDevice(@PathVariable Long id,@RequestParam String label, @RequestParam String description,@RequestParam String status,Model model) {
		//Skill skill = skillRepository.findOne(skillId);
		Room room = repository.findOne(id);
		Device device = new Device();
		device.setLabel(label);
		device.setDescription(description);
		device.setStatus(status);
		
		deviceRepository.save(device);
		


		List<Device> deviceList = deviceRepository.findByLabel(label);
		
		if (room != null) {
			room.getDevices().addAll(deviceList);
			
			repository.save(room);
			model.addAttribute("room", repository.findOne(id));
			model.addAttribute("devices", deviceRepository.findAll());
			return "redirect:/room/" + room.getId();
		}

		model.addAttribute("rooms", repository.findAll());
		return "redirect:/rooms";
	}
	
	
	@RequestMapping(value="/device/{id}/update", method=RequestMethod.POST)
	public String updateDevice(@PathVariable Long id,@RequestParam String status,Model model) {
		//Skill skill = skillRepository.findOne(skillId);
		Device device = deviceRepository.findOne(id);
		/*skill.setLabel(label);
		skill.setDescription(description);*/
		device.setStatus(status);
		
		deviceRepository.save(device);
		
		JsonObject payload = new JsonObject();
		payload.addProperty("deviceLabel", device.getLabel());
        payload.addProperty("deviceDescription", device.getDescription());
        payload.addProperty("deviceStatus", status);
        /*payload.addProperty("dateTimeStamp",  ledModel.getDateTimeStamp());
        payload.addProperty("blinkCounter", ledModel.getBlinkCounter());*/
		
        MqttPublishSubscribeUtility mqttPublishSubscribeUtility = new MqttPublishSubscribeUtility();
        mqttPublishSubscribeUtility.mqttConnectNPublishNSubscribe(payload);
		
		device = deviceRepository.findOne(id);
		
		model.addAttribute("device", device);
		return "redirect:/device/" + device.getId();
		
	}
	
/*	
	@RequestMapping(value="/developer/{id}/skills", method=RequestMethod.POST)
	public String developersAddSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
		Skill skill = skillRepository.findOne(skillId);
		Developer developer = repository.findOne(id);

		if (developer != null) {
			if (!developer.hasSkill(skill)) {
				developer.getSkills().add(skill);
			}
			repository.save(developer);
			model.addAttribute("developer", repository.findOne(id));
			model.addAttribute("skills", skillRepository.findAll());
			return "redirect:/developer/" + developer.getId();
		}

		model.addAttribute("developers", repository.findAll());
		return "redirect:/developers";
	}*/

}

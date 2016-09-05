package guru.springframework.domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;


	private String roomName;
	private String roomType;
	private String roomSize;
	@ManyToMany
	private List<Device> devices;

	public Room() {
		super();
	}

	public Room(String roomName, String roomType, String roomSize,
			List<Device> deviceList) {
		super();
		this.roomName = roomName;
		this.roomType = roomType;
		this.roomSize = roomSize;
		this.devices = deviceList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> deviceList) {
		this.devices = deviceList;
	}


	public boolean hasDevice(Device device) {
		for (Device containedDevice: getDevices()) {
			if (containedDevice.getId() == device.getId()) {
				return true;
			}
		}
		return false;
	}

}
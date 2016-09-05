package guru.springframework.bootstrap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Room;
import guru.springframework.domain.Device;
import guru.springframework.repositories.RoomRepository;
import guru.springframework.repositories.DeviceRepostitory;

@Component
public class RoomDeviceLoader implements ApplicationListener<ContextRefreshedEvent> {

	
	@Autowired
	RoomRepository roomRepository;

    @Autowired
    DeviceRepostitory deviceRepositpry;

    private Logger log = Logger.getLogger(RoomDeviceLoader.class);

   /* @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
	    }
	*/
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    	Device device1 = new Device("device01", "fan","OFF");
    	Device device2 = new Device("device02", "light","OFF");
    	Device device3 = new Device("device03", "thermostat");
    	Device device4 = new Device("device04", "air conditioner");

    	deviceRepositpry.save(device1);
    	deviceRepositpry.save(device2);
    	deviceRepositpry.save(device3);
    	deviceRepositpry.save(device4);

    	List<Room> developers = new LinkedList<Room>();
    	developers.add(new Room("room01", "masterbedroom", "big", 
    			Arrays.asList(new Device[] { device1, device2 })));
    	developers.add(new Room("room02", "kidsroom", "medium", 
    			Arrays.asList(new Device[] { device3, device4 })));
    	roomRepository.save(developers);
}
}

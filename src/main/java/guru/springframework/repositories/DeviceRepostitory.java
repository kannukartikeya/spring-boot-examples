package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Device;

public interface DeviceRepostitory extends CrudRepository<Device, Long> {
	public List<Device> findByLabel(String label);
}

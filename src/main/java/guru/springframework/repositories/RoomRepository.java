package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

}

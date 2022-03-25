package uz.pdp.cinemarestervice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestervice.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}

package uz.pdp.cinemarestervice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestervice.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}

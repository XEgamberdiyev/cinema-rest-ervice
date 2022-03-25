package uz.pdp.cinemarestervice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestervice.model.PriceCategory;


public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Integer> {

}

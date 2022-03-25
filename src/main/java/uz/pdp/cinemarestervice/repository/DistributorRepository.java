package uz.pdp.cinemarestervice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestervice.model.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Integer> {
    boolean existsByName(String name);
}

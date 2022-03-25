package uz.pdp.cinemarestervice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestervice.model.Row;
import uz.pdp.cinemarestervice.projection.RowProjection;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Integer> {

    @Query(value = "select r.id, r.number, h.name as Name from hall_rows r join halls h on h.id = r.hall_id where h.id= :hallId",nativeQuery = true)
    List<RowProjection> ketmon(Integer hallId);
}

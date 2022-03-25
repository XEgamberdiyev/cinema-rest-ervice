package uz.pdp.cinemarestervice.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinemarestervice.model.Hall;
import uz.pdp.cinemarestervice.model.Row;

@Projection(types = {Row.class, Hall.class})
public interface RowProjection {

    Integer getId();

    Integer getNumber();

    String getName();
}

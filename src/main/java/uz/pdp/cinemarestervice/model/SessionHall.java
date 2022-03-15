package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "session_halls")
public class SessionHall extends AbsEntity {

    @ManyToOne
    private MovieSession movieSession;

    @ManyToOne
    private Hall hall;
}

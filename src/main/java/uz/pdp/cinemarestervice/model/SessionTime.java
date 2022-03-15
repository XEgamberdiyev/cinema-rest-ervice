package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "session_times")
public class SessionTime extends AbsEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private Timestamp time;

    private Integer session_date_id;

    @ManyToOne
    private SessionDate sessionDate;
}

package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tickets")
public class Ticket extends AbsEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer movie_session_id;
    private Integer seat_id;
    @OneToOne
    private Attachment qr_code;
    private double price;
    private Integer cart_id;
}

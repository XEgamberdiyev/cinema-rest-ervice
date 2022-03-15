package uz.pdp.cinemarestervice.model;

import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "purchase_histories")
public class PurchaseHistory extends AbsEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer user_id;

    private Integer movie_id;

    private Date date;

    private Integer pay_type_id;
}

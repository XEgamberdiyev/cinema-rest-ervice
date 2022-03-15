package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "refund_charge_fees")
public class RefundChargeFee extends AbsEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private Time interval_in_minutes;

    private double percentage;
}

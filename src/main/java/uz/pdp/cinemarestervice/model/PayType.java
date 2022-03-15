package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "pay_types")
public class PayType extends AbsEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private double commission_fee_in_percent;
}

package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachment_contents")
public class AttachmentContent extends AbsEntity {

    private byte[] data;

    @OneToOne
    private Attachment attachment;

}

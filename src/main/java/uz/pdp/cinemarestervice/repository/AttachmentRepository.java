package uz.pdp.cinemarestervice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestervice.model.Attachment;

public interface AttachmentRepository extends JpaRepository <Attachment,Integer> {

}

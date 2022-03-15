package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "movie_photos")
public class MoviePhoto {
    @Id
    private Integer id;
    private Integer movie_id;
    private Integer attachment_id;
}
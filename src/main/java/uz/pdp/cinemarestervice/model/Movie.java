package uz.pdp.cinemarestervice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestervice.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity (name = "movies")
public class Movie extends AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private int durationInMinutes;
    @OneToOne
    private Attachment coverImage;

    @OneToOne
    private Attachment trailerVideo;

    @ManyToMany
    private List<Director> directors;

    @ManyToMany
    private List<Genre> genres;

    private double minPrice;

    @OneToOne
    private Distributor distributor;

    private double distributorShareInPercent;
    @ManyToMany
    private List<Actor>actors;
}
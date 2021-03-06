package uz.pdp.cinemarestervice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestervice.model.Genre;
import uz.pdp.cinemarestervice.payload.ApiResponse;
import uz.pdp.cinemarestervice.service.GenreService;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public HttpEntity<?> getAllGenres(){
        ApiResponse apiResponse = genreService.getAllGenres();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getGenre(@PathVariable Integer id){
        ApiResponse apiResponse = genreService.getGenre(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addGenre(@RequestBody Genre genre){
        ApiResponse apiResponse = genreService.addGenre(genre);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editGenre(@PathVariable Integer id,@RequestBody Genre genre){
        ApiResponse apiResponse = genreService.editGenre(id,genre);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGenre(@PathVariable Integer id){
        ApiResponse apiResponse = genreService.deleteGenre(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }

}

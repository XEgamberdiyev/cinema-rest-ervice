package uz.pdp.cinemarestervice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestervice.model.Hall;
import uz.pdp.cinemarestervice.payload.ApiResponse;
import uz.pdp.cinemarestervice.service.HallService;


@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @GetMapping
    public HttpEntity<?> getAllHalls(){
        ApiResponse apiResponse = hallService.getAllHalls();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getHall(@PathVariable Integer id){
        ApiResponse apiResponse = hallService.getHallById(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addHall(@RequestBody Hall hall){
        ApiResponse apiResponse = hallService.addHall(hall);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editHall(@PathVariable Integer id,@RequestBody Hall hall){
        ApiResponse apiResponse = hallService.editHall(id,hall);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteHall(@PathVariable Integer id){
        ApiResponse apiResponse = hallService.deleteHall(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }
}

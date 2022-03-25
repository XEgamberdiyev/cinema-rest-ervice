package uz.pdp.cinemarestervice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestervice.model.Actor;
import uz.pdp.cinemarestervice.payload.ApiResponse;
import uz.pdp.cinemarestervice.service.ActorService;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public HttpEntity<?> getAllActors(){
        ApiResponse allActors = actorService.getAllActors();
        return ResponseEntity.status(allActors.isStatus()?200:204).body(allActors);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getActor(@PathVariable Integer id){
        ApiResponse apiResponse = actorService.getActor(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addActor(@RequestPart("file") MultipartFile file, @RequestPart("json-actor") Actor actor){
        ApiResponse apiResponse = actorService.addActor(file, actor);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editActor(@PathVariable Integer id,@RequestPart("file") MultipartFile file, @RequestPart("json-actor") Actor actor){
        ApiResponse apiResponse = actorService.editActor(id, file, actor);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> addActor(@PathVariable Integer id){
        ApiResponse apiResponse = actorService.deleteActor(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

}

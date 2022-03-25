package uz.pdp.cinemarestervice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarestervice.payload.ApiResponse;
import uz.pdp.cinemarestervice.service.RowService;

@RestController
@RequestMapping("/api/row")
@RequiredArgsConstructor
public class RowController {

    private final RowService rowService;

    @GetMapping("/getRowByHallId/{hallId}")
    public HttpEntity<?> getRowsByHallId(@PathVariable Integer hallId){
        ApiResponse apiResponse = rowService.getRowsByHallId(hallId);
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllRows(){
        ApiResponse apiResponse = rowService.getAllRows();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }
}

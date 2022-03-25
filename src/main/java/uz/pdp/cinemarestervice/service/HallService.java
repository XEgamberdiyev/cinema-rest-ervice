package uz.pdp.cinemarestervice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestervice.model.Hall;
import uz.pdp.cinemarestervice.payload.ApiResponse;
import uz.pdp.cinemarestervice.repository.HallRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HallService {

    private final HallRepository hallRepository;

    public ApiResponse getAllHalls() {
        List<Hall> hallList = hallRepository.findAll();
        if (hallList.size() == 0) {
            return new ApiResponse("List empty!", false, hallList);
        }
        return new ApiResponse("Success", true, hallList);
    }


    public ApiResponse getHallById(Integer id) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found!!", false);
        }
        return new ApiResponse("Success", true, optionalHall.get());
    }


    public ApiResponse addHall(Hall hall) {
        try {
            Hall saveHall = hallRepository.save(hall);
            return new ApiResponse("Successfully added!", true, saveHall);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe hall already exists!", false);
        }
    }

    public ApiResponse editHall(Integer id, Hall hall) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found!!", false);
        }
        Hall editingHall = optionalHall.get();
        editingHall.setName(hall.getName());
        editingHall.setVip_additional_fee_in_percent(hall.getVip_additional_fee_in_percent());
        Hall saveHall = hallRepository.save(editingHall);
        return new ApiResponse("Successfully edited!", true, saveHall);
    }

    public ApiResponse deleteHall(Integer id){
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found!!", false);
        }
        hallRepository.deleteById(optionalHall.get().getId());
        return new ApiResponse("Successfully deleted!", true);
    }


}

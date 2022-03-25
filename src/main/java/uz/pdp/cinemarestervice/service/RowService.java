package uz.pdp.cinemarestervice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestervice.model.Hall;
import uz.pdp.cinemarestervice.model.Row;
import uz.pdp.cinemarestervice.payload.ApiResponse;
import uz.pdp.cinemarestervice.projection.RowProjection;
import uz.pdp.cinemarestervice.repository.HallRepository;
import uz.pdp.cinemarestervice.repository.RowRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RowService {

    private final RowRepository rowRepository;
    private HallRepository hallRepository;

    public ApiResponse getRowsByHallId(Integer id) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found!!", false);
        }
        List<RowProjection> rowsByHallId = rowRepository.ketmon(id);
        if (rowsByHallId.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, rowsByHallId);
    }

    public ApiResponse getAllRows() {
        List<Row> rowList = rowRepository.findAll();
        if (rowList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, rowList);
    }

    public ApiResponse getRowById(Integer id) {
        Optional<Row> optionalRow = rowRepository.findById(id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row not found!!", false);
        }
        return new ApiResponse("Success", true, optionalRow.get());
    }

    public ApiResponse addRow(Integer id, Row row) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found!!", false);
        }
        try {
            Row saveRow = rowRepository.save(new Row(row.getNumber(), optionalHall.get()));
            return new ApiResponse("Successfully added!", true, saveRow);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe row already exists!!", false);
        }
    }


    public ApiResponse editRow(Integer id, Row row) {
        Optional<Row> optionalRow = rowRepository.findById(id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row not found!!", false);
        }
        try {
            Row editingRow = optionalRow.get();
            editingRow.setNumber(row.getNumber());
            Row saveRow = rowRepository.save(editingRow);
            return new ApiResponse("Successfully edited!", true, saveRow);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe row already exists!!", false);
        }

    }

    public ApiResponse deleteRow(Integer id){
        Optional<Row> optionalRow = rowRepository.findById(id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row not found!!", false);
        }
        hallRepository.deleteById(optionalRow.get().getId());
        return new ApiResponse("Successfully deleted!", true);
    }


}

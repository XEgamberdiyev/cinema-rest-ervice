package uz.pdp.cinemarestervice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestervice.model.Attachment;
import uz.pdp.cinemarestervice.model.AttachmentContent;
import uz.pdp.cinemarestervice.model.Director;
import uz.pdp.cinemarestervice.payload.ApiResponse;
import uz.pdp.cinemarestervice.repository.AttachmentContentRepository;
import uz.pdp.cinemarestervice.repository.AttachmentRepository;
import uz.pdp.cinemarestervice.repository.DirectorRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public ApiResponse getAllDirectors() {
        List<Director> directorList = directorRepository.findAll();
        if (directorList.size() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, directorList);
    }

    public ApiResponse getDirector(Integer id) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (!optionalDirector.isPresent()) {
            return new ApiResponse("Distributor not found!", false);
        }
        return new ApiResponse("Success!", true, optionalDirector.get());
    }

    public ApiResponse addDirector(MultipartFile file, Director director) {
        try {
            Attachment attachment = attachmentRepository.save(new Attachment(file.getContentType(), file.getSize(), file.getOriginalFilename()));
            AttachmentContent attachmentContent = attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachment));
            Director newDirector = new Director(director.getFullName(), director.getBio(), attachment);
            Director savedDirector = directorRepository.save(newDirector);
            return new ApiResponse("Successfully added!", true, savedDirector);
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse("Error!!", false);
        }
    }

    public ApiResponse editDirector(Integer id, MultipartFile file, Director director) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (!optionalDirector.isPresent()) {
            return new ApiResponse("Director not found!!", false);
        }
        try {
            Director editingDirector = optionalDirector.get();
            editingDirector.setBio(director.getBio());
            editingDirector.setFullName(director.getFullName());
            if (file.isEmpty()) {
                Director saveDirector = directorRepository.save(editingDirector);
                return new ApiResponse("Successfully edited!", true, saveDirector);
            }
            Attachment attachment = editingDirector.getAttachment();
            attachment.setContentType(file.getContentType());
            attachment.setOriginalName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            Attachment saveAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(saveAttachment.getId());
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(saveAttachment);
            attachmentContentRepository.save(attachmentContent);
            director.setAttachment(saveAttachment);
            Director saveDirector = directorRepository.save(editingDirector);
            return new ApiResponse("Successfully edited!", true, saveDirector);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }
    }

    public ApiResponse deleteDirector(Integer id) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (!optionalDirector.isPresent()) {
            return new ApiResponse("Director not found!!", false);
        }
        try {
            Director director = optionalDirector.get();
            Attachment attachment = director.getAttachment();
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            attachmentContentRepository.deleteById(attachmentContent.getId());
            attachmentRepository.deleteById(attachment.getId());
            directorRepository.deleteById(director.getId());
            return new ApiResponse("Successfully deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }
    }

}

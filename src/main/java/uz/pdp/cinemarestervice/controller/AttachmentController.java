package uz.pdp.cinemarestervice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.cinemarestervice.model.Attachment;
import uz.pdp.cinemarestervice.model.AttachmentContent;
import uz.pdp.cinemarestervice.repository.AttachmentContentRepository;
import uz.pdp.cinemarestervice.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @PostMapping("/upload")
    public String uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();
            try {
                attachmentContent.setData(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return "Save attachment where id: " + savedAttachment.getId();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Error";
    }
}

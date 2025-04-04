package com.boarding.app.Controller;

import com.boarding.app.Entity.Module1;
import com.boarding.app.Service.Module1Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/module1")
public class Module1Controller {

    @Autowired
    private Module1Service module1Service;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/submit", consumes = "multipart/form-data")
    public ResponseEntity<?> submitForm(
            @RequestPart("form") String formJson,
            @RequestPart(value = "photoVesselBoat", required = false) MultipartFile photo,
            @RequestPart(value = "photoVideoVesselBoat", required = false) MultipartFile video
    ) throws IOException {

        // Convert JSON string to Module1 object
        Module1 form = objectMapper.readValue(formJson, Module1.class);

        if (photo != null && !photo.isEmpty()) {
            form.setPhotoVesselBoat(photo.getBytes());
        }

        if (video != null && !video.isEmpty()) {
            form.setPhotoVideoVesselBoat(video.getBytes());
        }

        Module1 saved = module1Service.saveForm(form);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getForm(@PathVariable Long id) {
        return module1Service.getFormById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable Long id) {
        module1Service.deleteForm(id);
        return ResponseEntity.ok("Form deleted.");
    }
}

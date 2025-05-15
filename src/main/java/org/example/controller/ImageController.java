package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.business.ImageService;
import org.example.model.ImageRequest;
import org.example.model.ImageResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/verify")
    public ResponseEntity<String> verifyRunning() {
        return ResponseEntity.ok("The system is running.");
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@ModelAttribute ImageRequest imageRequest) {
        try {
            ImageResponse saved = imageService.save(imageRequest);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return imageService.findById(id)
                .map(img -> ResponseEntity.ok()
                        .contentType(MediaType.valueOf(img.getType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + img.getName() + "\"")
                        .body(new ByteArrayResource(img.getData())))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<ImageResponse>> getByBoard(@PathVariable Long boardId) {
        return imageService.findByBoardId(boardId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ImageResponse>> getByUser(@PathVariable Long userId) {
        return imageService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        imageService.deleteByImageId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<Void> deleteByBoard(@PathVariable Long boardId) {
        imageService.deleteByBoardId(boardId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteByUser(@PathVariable Long userId) {
        imageService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}

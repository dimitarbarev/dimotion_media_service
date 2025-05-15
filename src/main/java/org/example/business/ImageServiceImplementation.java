package org.example.business;

import lombok.AllArgsConstructor;
import org.example.model.ImageRequest;
import org.example.model.ImageResponse;
import org.example.repository.ImageEntity;
import org.example.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImplementation implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ImageResponse save(ImageRequest request) throws IOException {
        MultipartFile file = request.getFile();

        ImageEntity image = ImageEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(file.getBytes())
                .boardId(request.getBoard_id())
                .userId(request.getUser_id())
                .build();

        ImageEntity saved = imageRepository.save(image);
        return toResponse(saved);
    }

    @Override
    public Optional<ImageResponse> findById(Long id) {
        return imageRepository.findById(id)
                .map(this::toResponse);
    }

    @Override
    public Optional<List<ImageResponse>> findByBoardId(Long boardId) {
        List<ImageEntity> images = imageRepository.findByBoardId(boardId);
        return Optional.of(images.stream().map(this::toResponse).toList());
    }

    @Override
    public Optional<List<ImageResponse>> findByUserId(Long userId) {
        List<ImageEntity> images = imageRepository.findByUserId(userId);
        return Optional.of(images.stream().map(this::toResponse).toList());
    }

    @Override
    public void deleteByImageId(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public void deleteByBoardId(Long boardId) {
        imageRepository.deleteByBoardId(boardId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        imageRepository.deleteByUserId(userId);
    }

    // Helper to map entity → response
    private ImageResponse toResponse(ImageEntity entity) {
        return ImageResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .data(entity.getData())
                .board_id(entity.getBoardId())
                .user_id(entity.getUserId())
                .build();
    }
}

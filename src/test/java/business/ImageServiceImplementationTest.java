package business;

import org.example.business.ImageServiceImplementation;
import org.example.model.ImageRequest;
import org.example.model.ImageResponse;
import org.example.repository.ImageEntity;
import org.example.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageServiceImplementationTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImplementation imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldReturnImageResponse() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("test.jpg");
        when(file.getContentType()).thenReturn("image/jpeg");
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});

        ImageRequest request = ImageRequest.builder()
                .file(file)
                .board_id(1L)
                .user_id(2L)
                .build();

        ImageEntity savedEntity = ImageEntity.builder()
                .id(1L)
                .name("test.jpg")
                .type("image/jpeg")
                .data(new byte[]{1, 2, 3})
                .boardId(1L)
                .userId(2L)
                .build();

        when(imageRepository.save(any(ImageEntity.class))).thenReturn(savedEntity);

        ImageResponse response = imageService.save(request);

        assertNotNull(response);
        assertEquals("test.jpg", response.getName());
    }

    @Test
    void findById_shouldReturnImageResponseIfFound() {
        ImageEntity entity = ImageEntity.builder().id(1L).name("test").type("image/png")
                .data(new byte[]{1, 2}).boardId(1L).userId(1L).build();

        when(imageRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<ImageResponse> response = imageService.findById(1L);
        assertTrue(response.isPresent());
        assertEquals("test", response.get().getName());
    }

    @Test
    void findById_shouldReturnEmptyIfNotFound() {
        when(imageRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ImageResponse> response = imageService.findById(99L);
        assertTrue(response.isEmpty());
    }

    @Test
    void findByBoardId_shouldReturnListOfImages() {
        List<ImageEntity> entities = List.of(
                ImageEntity.builder().id(1L).name("a").type("image/png").data(new byte[]{})
                        .boardId(1L).userId(1L).build()
        );
        when(imageRepository.findByBoardId(1L)).thenReturn(entities);

        Optional<List<ImageResponse>> responses = imageService.findByBoardId(1L);
        assertTrue(responses.isPresent());
        assertEquals(1, responses.get().size());
    }

    @Test
    void findByUserId_shouldReturnListOfImages() {
        List<ImageEntity> entities = List.of(
                ImageEntity.builder().id(2L).name("b").type("image/png").data(new byte[]{})
                        .boardId(1L).userId(2L).build()
        );
        when(imageRepository.findByUserId(2L)).thenReturn(entities);

        Optional<List<ImageResponse>> responses = imageService.findByUserId(2L);
        assertTrue(responses.isPresent());
        assertEquals(1, responses.get().size());
    }

    @Test
    void deleteByImageId_shouldCallRepository() {
        imageService.deleteByImageId(1L);
        verify(imageRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteByBoardId_shouldDeleteAllImages() {
        List<ImageEntity> images = List.of(
                ImageEntity.builder().id(1L).boardId(1L).build(),
                ImageEntity.builder().id(2L).boardId(1L).build()
        );
        when(imageRepository.findByBoardId(1L)).thenReturn(images);

        imageService.deleteByBoardId(1L);

        verify(imageRepository).deleteAll(images);
    }

    @Test
    void deleteByUserId_shouldCallRepository() {
        imageService.deleteByUserId(2L);
        verify(imageRepository).findByUserId(2L);
    }
}

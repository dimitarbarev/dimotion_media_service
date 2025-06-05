//package controller;
//
//import org.example.business.ImageService;
//import org.example.controller.ImageController;
//import org.example.model.ImageRequest;
//import org.example.model.ImageResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ImageControllerTest {
//
//    @Mock
//    private ImageService imageService;
//
//    @InjectMocks
//    private ImageController imageController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void upload_shouldReturnSavedImage() throws IOException {
//        MockMultipartFile file = new MockMultipartFile("file", "filename.jpg", "image/jpeg", new byte[]{1});
//        ImageRequest request = ImageRequest.builder().file(file).board_id(1L).user_id(2L).build();
//        ImageResponse saved = ImageResponse.builder().id(1L).name("filename.jpg").type("image/jpeg")
//                .data(new byte[]{1}).board_id(1L).user_id(2L).build();
//
//        when(imageService.save(any())).thenReturn(saved);
//
//        ResponseEntity<?> response = imageController.upload(request);
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    void upload_shouldReturnErrorIfIOException() throws IOException {
//        MockMultipartFile file = new MockMultipartFile("file", "badfile.jpg", "image/jpeg", new byte[]{});
//        ImageRequest request = ImageRequest.builder().file(file).board_id(1L).user_id(2L).build();
//
//        when(imageService.save(any())).thenThrow(new IOException("I/O error"));
//
//        ResponseEntity<?> response = imageController.upload(request);
//        assertEquals(500, response.getStatusCodeValue());
//    }
//
//    @Test
//    void get_shouldReturnImage() {
//        ImageResponse image = ImageResponse.builder().id(1L).name("x.png").type("image/png").data(new byte[]{1}).build();
//        when(imageService.findById(1L)).thenReturn(Optional.of(image));
//
//        ResponseEntity<?> response = imageController.get(1L);
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    void get_shouldReturnNotFound() {
//        when(imageService.findById(100L)).thenReturn(Optional.empty());
//        ResponseEntity<?> response = imageController.get(100L);
//        assertEquals(404, response.getStatusCodeValue());
//    }
//
//    @Test
//    void getByBoard_shouldReturnImages() {
//        List<ImageResponse> images = List.of(ImageResponse.builder().id(1L).build());
//        when(imageService.findByBoardId(1L)).thenReturn(Optional.of(images));
//
//        ResponseEntity<List<ImageResponse>> response = imageController.getByBoard(1L);
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    void getByBoard_shouldReturnNotFound() {
//        when(imageService.findByBoardId(1L)).thenReturn(Optional.empty());
//        ResponseEntity<List<ImageResponse>> response = imageController.getByBoard(1L);
//        assertEquals(404, response.getStatusCodeValue());
//    }
//
//    @Test
//    void getByUser_shouldReturnImages() {
//        List<ImageResponse> images = List.of(ImageResponse.builder().id(2L).build());
//        when(imageService.findByUserId(2L)).thenReturn(Optional.of(images));
//
//        ResponseEntity<List<ImageResponse>> response = imageController.getByUser(2L);
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    void getByUser_shouldReturnNotFound() {
//        when(imageService.findByUserId(2L)).thenReturn(Optional.empty());
//        ResponseEntity<List<ImageResponse>> response = imageController.getByUser(2L);
//        assertEquals(404, response.getStatusCodeValue());
//    }
//
//    @Test
//    void deleteById_shouldReturnNoContent() {
//        ResponseEntity<Void> response = imageController.deleteById(1L);
//        assertEquals(204, response.getStatusCodeValue());
//    }
//
//    @Test
//    void deleteByBoard_shouldReturnNoContent() {
//        ResponseEntity<Void> response = imageController.deleteByBoard(1L);
//        assertEquals(204, response.getStatusCodeValue());
//    }
//
//    @Test
//    void deleteByUser_shouldReturnNoContent() {
//        ResponseEntity<Void> response = imageController.deleteByUser(2L);
//        assertEquals(204, response.getStatusCodeValue());
//        verify(imageService).deleteByUserId(2L);
//    }
//}
//

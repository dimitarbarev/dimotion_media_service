package org.example.messaging;

import org.example.business.ImageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BoardDeletionListener {

    private final ImageService imageService;

    public BoardDeletionListener(ImageService imageService) {
        this.imageService = imageService;
    }

    @RabbitListener(queues = "board.delete")
    public void onBoardDeleted(String boardIdString) {
        System.out.println("🔔 Raw message received: " + boardIdString);
        Long boardId = Long.valueOf(boardIdString);  // Convert String → Long
        imageService.deleteByBoardId(boardId);
    }

}

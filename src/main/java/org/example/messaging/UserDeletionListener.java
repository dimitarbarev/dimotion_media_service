package org.example.messaging;

import org.example.business.ImageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserDeletionListener {

    private final ImageService imageService;

    public UserDeletionListener(ImageService imageService) {
        this.imageService = imageService;
    }

    @RabbitListener(queues = "user.delete")
    public void onUserDeleted(String userIdString) {
        System.out.println("🔔 User deletion event received: " + userIdString);
        Long userId = Long.valueOf(userIdString);
        imageService.deleteByUserId(userId);
    }
}


package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByBoardId(Long boardId);
    List<ImageEntity> findByUserId(Long userId);
    void deleteByBoardId(Long boardId);
    void deleteByUserId(Long userId);
}

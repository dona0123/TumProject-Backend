package com.example.TumProject.service;

import com.example.TumProject.model.ShopEntity;
import com.example.TumProject.repository.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ShopService {

    @Autowired
    private ShopRepository repository;

    // Shop 아이템 생성
    public List<ShopEntity> create(final ShopEntity entity) {
        validate(entity);

        repository.save(entity);

        log.info("Entity id: {} is saved.", entity.getUserId());

        // 해당 유저가 작성한 모든 Shop 아이템 반환
        return repository.findByUserId(entity.getUserId());

    }

    // 매개변수로 받은 id의 유저가 작성한 Shop 반환
    public List<ShopEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }


    // 매개변수로 받은 title 반환
    public List<ShopEntity> findByTitle(final String title) {
        return repository.findByTitle(title);
    }



    // entity 를 받아오고, 그 정보로 갱신하기 (업데이트)
    public List<ShopEntity> update(final ShopEntity entity) {
        validate(entity);

        final Optional<ShopEntity> original = repository.findById(entity.getId());

        original.ifPresent(shop -> {
            // 엔티티 정보 업데이트
            shop.setTitle(entity.getTitle());

            repository.save(shop);
        });

        // 해당 유저가 작성한 모든 Shop 아이템 반환
        return retrieve(entity.getUserId());
    }

    // entity 삭제
    public List<ShopEntity> delete(final ShopEntity entity) {
        validate(entity);

        try {
            // 엔티티 삭제
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity ", entity.getUserId(), e);
            throw new RuntimeException("error deleting entity " + entity.getUserId());
        }
        // 해당 유저가 작성한 모든 Shop 아이템 반환
        return retrieve(entity.getUserId());
    }

    // entity 검사
    private void validate(final ShopEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}

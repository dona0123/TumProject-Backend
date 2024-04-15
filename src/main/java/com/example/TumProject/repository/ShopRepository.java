package com.example.TumProject.repository;

import com.example.TumProject.model.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Repository 클래스임을 명시, 자바 빈으로 인식
public interface ShopRepository extends JpaRepository<ShopEntity, String> {
    List<ShopEntity> findByUserId(String userId); // findBy + 속성이름
    List<ShopEntity> findByTitle(String title);
}

package com.example.TumProject.controller;

import com.example.TumProject.dto.ResponseDTO;
import com.example.TumProject.dto.ShopDTO;
import com.example.TumProject.model.ShopEntity;
import com.example.TumProject.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService service;

    ShopEntity entity;

    @PostMapping // shop 아이템을 요청할 떄는 post 로
    public ResponseEntity<?> createShop(@RequestBody ShopDTO dto) {
        try {

            entity = ShopDTO.toEntity(dto);

            String userId = entity.getUserId();

            entity.setId(null);

            entity.setUserId(userId);

            List<ShopEntity> entities = service.create(entity);

            List<ShopDTO> dtos = entities.stream().map(ShopDTO::new).collect(Collectors.toList());

            ResponseDTO<ShopDTO> response = ResponseDTO.<ShopDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<ShopDTO> response = ResponseDTO.<ShopDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);

        }

    }

    // 모든 제품 반환
//    @Autowired
//    private ShopService shopService;
//
//    @GetMapping
//    public List<ShopEntity> getAllShops() {
//        return shopService.retrieveAll();
//    }

    // userId 검색 반환
    @GetMapping
    public ResponseEntity<?> retrieveShopList() {
        String userId = entity.getUserId();

        List<ShopEntity> entities = service.retrieve(userId);

        List<ShopDTO> dtos = entities.stream().map(ShopDTO::new).collect(Collectors.toList());

        ResponseDTO<ShopDTO> response = ResponseDTO.<ShopDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    // title 검색 반환
    @PostMapping("/title")
    public ResponseEntity<?> searchByTitle(@RequestBody Map<String, String> requestBody) {
        String title = requestBody.get("title");

        if (title == null || title.isEmpty()) {
            return ResponseEntity.badRequest().body("Title cannot be empty");
        }

        String userId = entity.getUserId();

        List<ShopEntity> entities = service.findByTitle(title);

        List<ShopDTO> dtos = entities.stream().map(ShopDTO::new).collect(Collectors.toList());

        ResponseDTO<ShopDTO> response = ResponseDTO.<ShopDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateShop(@RequestBody ShopDTO dto) {
        String userId = entity.getUserId();

        ShopEntity entity = ShopDTO.toEntity(dto);

        entity.setUserId(userId);

        List<ShopEntity> entities = service.update(entity);

        List<ShopDTO> dtos = entities.stream().map(ShopDTO::new).collect(Collectors.toList());

        ResponseDTO<ShopDTO> response = ResponseDTO.<ShopDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteShop(@RequestBody ShopDTO dto) {
        try {
            String userId = entity.getUserId();

            ShopEntity entity = ShopDTO.toEntity(dto);

            entity.setUserId(userId);

            List<ShopEntity> entities = service.delete(entity);

            List<ShopDTO> dtos = entities.stream().map(ShopDTO::new).collect(Collectors.toList());

            ResponseDTO<ShopDTO> response = ResponseDTO.<ShopDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);


        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<ShopDTO> response = ResponseDTO.<ShopDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

}

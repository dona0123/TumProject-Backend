package com.example.TumProject.dto;

import com.example.TumProject.model.ShopEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopDTO {
    private String id;
    private String userId;
    private String ingredient;
    private String title;
    private boolean crisp;

    // 사용자에게 갈 정보 
    public ShopDTO(final ShopEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.ingredient = entity.getIngredient();
        this.title = entity.getTitle();
        this.crisp = entity.isCrisp();
    }


    // DTO -> Entity
    public static ShopEntity toEntity(final ShopDTO dto) {
        return ShopEntity.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .ingredient(dto.getIngredient())
                .title(dto.getTitle())
                .crisp(dto.isCrisp())
                .build();
    }
}


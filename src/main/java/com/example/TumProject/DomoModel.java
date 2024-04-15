package com.example.TumProject;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor // DomoModel의 생성자 추가 
public class DomoModel {

    @NonNull // id 가 Null이 아니어야 하게 함 
    private String id; 
}

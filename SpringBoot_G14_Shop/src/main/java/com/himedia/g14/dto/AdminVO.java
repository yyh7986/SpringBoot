package com.himedia.g14.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminVO {

    @NotEmpty
    private String adminid;
    @NotEmpty
    private String pwd;
    private String name;
    private String phone;
}
package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bistu.Enum.Gender;
import com.bistu.Enum.Identity;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Integer state;
    private String city;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String bankCardNumber;
    private Identity identity;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

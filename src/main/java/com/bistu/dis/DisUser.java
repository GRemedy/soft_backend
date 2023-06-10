package com.bistu.dis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bistu.Enum.Gender;
import com.bistu.Enum.Identity;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisUser {
    private Integer id;
    private String name;
    private String username;
    private String city;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String bankCardNumber;
    private Identity identity;
    private String image;
    private Integer state;
}

package com.dsicari.springbatchoverview.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder(toBuilder = true)
public record Client(
        @NotBlank(message = "Invalid first name, must be not empty or null")
        String firstName,
        @NotBlank(message = "Invalid last name, must be not empty or null")
        String lastName,
        @Min(value = 0, message = "Invalid age, should not be less than 0")
        int age,
        @NotBlank(message = "Invalid address, must be not empty or null")
        String address,
        @Email(message = "Invalid email format")
        String email,
        @Pattern(regexp = "\\+[0-9]+", message = "Invalid phone number format, must start with + followed by numbers")
        String phoneNumber)
{}

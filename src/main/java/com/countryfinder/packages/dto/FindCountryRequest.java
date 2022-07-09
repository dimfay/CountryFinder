package com.countryfinder.packages.dto;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCountryRequest {
    @NotBlank
    @Pattern(regexp = "^\\+[0-9]{6,14}[0-9]$", message = "wrong phone number format")
    @Length(min = 7, max = 16, message = "length must be between 7 and 16")
    private String number;
}

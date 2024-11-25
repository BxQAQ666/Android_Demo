package org.example.androiddemo.pojo.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UserDto {

    private Integer id;
    private String username;
    private String password;
    private String email;

}

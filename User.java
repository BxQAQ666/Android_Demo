package org.example.androiddemo.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table(name = "tb_user")
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "ID不能为空") // 改为 @NotNull
    @Column(name = "user_id")
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能超过 50 个字符") // 字符串最大长度为 50
    @Column(name = "user_name")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 100, message = "密码长度在 6 到 100 之间") // 密码长度在 6 到 100 之间
    @Column(name = "password")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确") // 确保是有效的电子邮件格式
    @Column(name = "email", unique = true)
    private String email;
}

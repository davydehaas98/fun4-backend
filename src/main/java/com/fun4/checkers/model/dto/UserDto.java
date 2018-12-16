package com.fun4.checkers.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private String password;

  public UserDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}

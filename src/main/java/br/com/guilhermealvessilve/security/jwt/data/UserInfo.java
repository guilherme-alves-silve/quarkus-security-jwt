package br.com.guilhermealvessilve.security.jwt.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String name;

    private String username;

    private String date;

}

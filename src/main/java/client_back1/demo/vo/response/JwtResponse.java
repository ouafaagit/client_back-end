package client_back1.demo.vo.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String account; //email
    private Long id;
    private String role;

    public JwtResponse(String token, String account, long id, String role) {
        this.account = account;
        this.id = id;
        this.token = token;
        this.role = role;
    }
}

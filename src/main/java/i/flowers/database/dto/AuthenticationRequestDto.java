//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.dto;

public class AuthenticationRequestDto {
    private String username;
    private String password;

    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequestDto() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}

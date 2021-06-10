package cat.itb.projectespring.model.entitat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "USUARIS")
public class Usuari {

    @NotNull
    @NotEmpty
    @Id
    @Column (name = "USERNAME")
    private String username;

    @NotNull
    @NotEmpty
    @Column (name = "PASSWORD")
    private String password;

    @NotNull
    @NotEmpty
    @Column (name = "MATCHING_PASSWORD")
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @Column (name = "ROLE")
    private String rol;

    public Usuari(String name, String password, String matchingPassword) {
        this.username = name;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.rol = "USER";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}

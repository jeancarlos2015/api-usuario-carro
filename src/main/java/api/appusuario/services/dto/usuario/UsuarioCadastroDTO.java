package api.appusuario.services.dto.usuario;

import javax.validation.constraints.NotNull;



public class UsuarioCadastroDTO {
    @NotNull
    private String login;
   
    @NotNull
    private String senha;
    @NotNull
    private String email;
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}

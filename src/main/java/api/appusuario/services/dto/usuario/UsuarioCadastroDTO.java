package api.appusuario.services.dto.usuario;

import javax.validation.constraints.NotNull;



public class UsuarioCadastroDTO {
    @NotNull
    private String nome;
    @NotNull
    private String senha;
    @NotNull
    private String email;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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

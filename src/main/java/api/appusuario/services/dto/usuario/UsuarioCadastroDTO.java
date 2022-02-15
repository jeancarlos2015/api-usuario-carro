package api.appusuario.services.dto.usuario;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioCadastroDTO {
    @NotNull
    private String nome;
    @NotNull
    private String senha;
    @NotNull
    private String email;
}

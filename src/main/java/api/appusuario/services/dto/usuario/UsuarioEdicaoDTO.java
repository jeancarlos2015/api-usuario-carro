package api.appusuario.services.dto.usuario;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioEdicaoDTO {
   
    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String senha;
    @NotNull
    private String email;

}

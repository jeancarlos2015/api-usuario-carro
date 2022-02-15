package api.appusuario.services.dto.usuario;

import lombok.Data;

@Data
public class UsuarioBuscaDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;

}

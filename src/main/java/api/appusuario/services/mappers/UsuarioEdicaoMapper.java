package api.appusuario.services.mappers;

import org.mapstruct.Mapper;

import api.appusuario.models.Usuario;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
@Mapper(componentModel = "spring")
public interface UsuarioEdicaoMapper extends EntityMapper<UsuarioEdicaoDTO, Usuario> {

}
    


package api.appusuario.services.mappers.usuario;

import org.mapstruct.Mapper;

import api.appusuario.models.Usuario;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.mappers.EntityMapper;
@Mapper(componentModel = "spring")
public interface UsuarioEdicaoMapper extends EntityMapper<UsuarioEdicaoDTO, Usuario> {

}
    


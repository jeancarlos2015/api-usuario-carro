package api.appusuario.services.mappers;

import org.mapstruct.Mapper;

import api.appusuario.models.Usuario;
import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;

@Mapper(componentModel = "spring")
public interface UsuarioBuscaMapper extends EntityMapper<UsuarioBuscaDTO, Usuario> {

}
    

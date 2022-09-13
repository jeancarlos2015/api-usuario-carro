package api.appusuario.services.mappers.usuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import api.appusuario.models.Usuario;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;
import api.appusuario.services.mappers.EntityMapper;

@Mapper(componentModel = "spring")
public interface UsuarioCadastroMapper extends EntityMapper<UsuarioCadastroDTO, Usuario> {
	@Mappings({
        @Mapping(target = "admin", source = "admin"),
    })
    Usuario toEntity(UsuarioLoginRetornoDTO dto);
}
    

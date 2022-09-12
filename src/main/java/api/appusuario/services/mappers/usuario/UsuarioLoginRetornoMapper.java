package api.appusuario.services.mappers.usuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import api.appusuario.models.Usuario;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;
import api.appusuario.services.mappers.EntityMapper;

@Mapper(componentModel = "spring")
public interface UsuarioLoginRetornoMapper extends EntityMapper<UsuarioLoginRetornoDTO,Usuario>{
    @Mappings({
        @Mapping(target = "id", ignore = true),
    })
    Usuario toEntity(UsuarioLoginRetornoDTO dto);
}

package api.appusuario.services.mappers.carro;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import api.appusuario.models.Carro;
import api.appusuario.services.dto.carro.CarroEdicaoDTO;
import api.appusuario.services.mappers.EntityMapper;

@Mapper(componentModel = "spring")
public interface CarroEdicaoMapper extends EntityMapper<CarroEdicaoDTO, Carro> {
	
}
    
package api.appusuario.services.mappers.carro;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import api.appusuario.models.Carro;
import api.appusuario.services.dto.carro.CarroBuscaDTO;
import api.appusuario.services.mappers.EntityMapper;


@Mapper(componentModel = "spring")
public interface CarroBuscaMapper extends EntityMapper<CarroBuscaDTO, Carro> {
	 @Mappings({
	        @Mapping(source="id", target = "id"),
	    })
	CarroBuscaDTO toDto(Carro entity);
	
}
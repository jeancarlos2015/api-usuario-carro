package api.appusuario.services.mappers.carro;

import org.mapstruct.Mapper;

import api.appusuario.models.Carro;
import api.appusuario.services.dto.carro.CarroBuscaDTO;
import api.appusuario.services.mappers.EntityMapper;

@Mapper(componentModel = "spring")
public interface CarroBuscaMapper extends EntityMapper<CarroBuscaDTO, Carro> {

}
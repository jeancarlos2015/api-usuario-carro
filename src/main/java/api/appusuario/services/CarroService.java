package api.appusuario.services;

import java.util.List;

import api.appusuario.services.dto.carro.CarroBuscaDTO;
import api.appusuario.services.dto.carro.CarroCadastroDTO;
import api.appusuario.services.dto.carro.CarroEdicaoDTO;


public interface CarroService {
    public CarroBuscaDTO cadastrar(CarroCadastroDTO CarroDTO);
    public CarroBuscaDTO buscarPorId(Long id);
    public CarroBuscaDTO atualizar(CarroEdicaoDTO CarroDTO);
    public void excluir(Long id);
    public List<CarroBuscaDTO> buscarTodos();

}

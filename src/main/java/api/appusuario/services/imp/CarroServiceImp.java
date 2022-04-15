package api.appusuario.services.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import api.appusuario.models.Carro;
import api.appusuario.repositorys.CarroRepository;
import api.appusuario.services.CarroService;
import api.appusuario.services.dto.carro.CarroBuscaDTO;
import api.appusuario.services.dto.carro.CarroCadastroDTO;
import api.appusuario.services.dto.carro.CarroEdicaoDTO;
import api.appusuario.services.mappers.carro.CarroBuscaMapper;
import api.appusuario.services.mappers.carro.CarroCadastroMapper;
import api.appusuario.services.mappers.carro.CarroEdicaoMapper;

@Transactional
@Service
public class CarroServiceImp implements CarroService {

    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private CarroCadastroMapper carroCadastroMapper;
    @Autowired
    private CarroBuscaMapper carroBuscaMapper;
    @Autowired
    private CarroEdicaoMapper carroEdicaoMapper;

    @Override
    public CarroBuscaDTO cadastrar(CarroCadastroDTO carroDTO) {
        Carro carroCadastro = carroCadastroMapper.toEntity(carroDTO);
        Carro carro = carroRepository.save(carroCadastro);
        return carroBuscaMapper.toDto(carro);
    }

    @Override
    public CarroBuscaDTO buscarPorId(Long id) {
        try {
            Carro usuario = carroRepository.findById(id).get();
            return carroBuscaMapper.toDto(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public CarroBuscaDTO atualizar(CarroEdicaoDTO carroEdicaoDTO) {
        try {
            Carro carroEdicao = carroEdicaoMapper.toEntity(carroEdicaoDTO);
            Carro carroResult = carroRepository.save(carroEdicao);
            return carroBuscaMapper.toDto(carroResult);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void excluir(Long id) {
        try {
            carroRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       
    }

    @Override
    public List<CarroBuscaDTO> buscarTodos() {
        List<Carro> carros = carroRepository.findAll();
        return carroBuscaMapper.toDto(carros);
    }

}

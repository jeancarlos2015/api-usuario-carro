package api.appusuario;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.appusuario.models.Carro;
import api.appusuario.repositorys.CarroRepository;
import api.appusuario.services.CarroService;
import api.appusuario.services.dto.carro.CarroBuscaDTO;
import api.appusuario.services.dto.carro.CarroCadastroDTO;
import api.appusuario.services.dto.carro.CarroEdicaoDTO;
import api.appusuario.services.mappers.carro.CarroEdicaoMapper;

@SpringBootTest
@Transactional(rollbackOn = Exception.class)
public class CarroTest {
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private CarroEdicaoMapper carroEdicaoMapper;
    @Autowired
    private CarroService carroService;

    @Test
    void testarCadastro() {
        CarroCadastroDTO carroDTO = new CarroCadastroDTO();
        carroDTO.setNome("New Fiesta");
        carroDTO.setMarca("Ford");
        carroDTO.setModelo("Fiesta");
        carroDTO.setChassi("123456789");
        CarroBuscaDTO carroBuscaDTO = carroService.cadastrar(carroDTO);
        assert carroBuscaDTO.getId() != null;
    }

    @Test
    void testarBusca() {
        CarroCadastroDTO carroDTO = new CarroCadastroDTO();
        carroDTO.setNome("New Fiesta");
        carroDTO.setMarca("Ford");
        carroDTO.setModelo("Fiesta");
        carroDTO.setChassi("123456789");
        CarroBuscaDTO carroBuscaDTO = carroService.cadastrar(carroDTO);
        CarroBuscaDTO usuarioBuscaDTO2 = carroService.buscarPorId(carroBuscaDTO.getId());
        assert usuarioBuscaDTO2.getNome().equals(carroBuscaDTO.getNome());

    }

    @Test
    void testarEdicao() {
        CarroCadastroDTO carroDTO = new CarroCadastroDTO();
        carroDTO.setNome("New Fiesta");
        carroDTO.setMarca("Ford");
        carroDTO.setModelo("Fiesta");
        carroDTO.setChassi("123456789");
        CarroBuscaDTO carroBuscaDTO = carroService.cadastrar(carroDTO);
        Carro carro = carroRepository.findById(carroBuscaDTO.getId()).get();
        CarroEdicaoDTO carroEdicaoDTO = carroEdicaoMapper.toDto(carro);
        carroEdicaoDTO.setNome("Cobalt");
        carroEdicaoDTO.setMarca("Chevrolet");
        carroEdicaoDTO.setModelo("Elite");
        carroEdicaoDTO.setChassi("123456789");
        Carro carroEdicao = carroEdicaoMapper.toEntity(carroEdicaoDTO);
        Carro carroResult = carroRepository.save(carroEdicao);
        CarroEdicaoDTO CarroEdicaoDTO2 = carroEdicaoMapper.toDto(carroResult);
        assert CarroEdicaoDTO2.getNome().equals(carroEdicaoDTO.getNome());
    }

    @Test
    void testarExclusao() {
        CarroCadastroDTO carroDTO = new CarroCadastroDTO();
        carroDTO.setNome("New Fiesta");
        carroDTO.setMarca("Ford");
        carroDTO.setModelo("Fiesta");
        carroDTO.setChassi("123456789");
        CarroBuscaDTO carroBuscaDTO = carroService.cadastrar(carroDTO);
        Carro carro = carroRepository.findById(carroBuscaDTO.getId()).get();
        carroRepository.delete(carro);
       
        assert  carroRepository.findById(carroBuscaDTO.getId()).isEmpty();
    }
}

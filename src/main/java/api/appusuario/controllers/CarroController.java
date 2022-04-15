package api.appusuario.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.appusuario.services.CarroService;
import api.appusuario.services.dto.carro.CarroBuscaDTO;
import api.appusuario.services.dto.carro.CarroCadastroDTO;
import api.appusuario.services.dto.carro.CarroEdicaoDTO;

@RestController
@RequestMapping("/carro")
@CrossOrigin
public class CarroController {
    @Autowired
    private CarroService carroService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public CarroBuscaDTO cadastrar(@RequestBody @Valid CarroCadastroDTO usuarioDTO) {
        return carroService.cadastrar(usuarioDTO);
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public CarroBuscaDTO buscarPorId(@PathVariable Long  id) {
        return carroService.buscarPorId(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping
    public CarroBuscaDTO atualizar(@RequestBody @Valid CarroEdicaoDTO usuarioDTO) {
        return carroService.atualizar(usuarioDTO);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        carroService.excluir(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<CarroBuscaDTO> buscarTodos() {
        return carroService.buscarTodos();
    }

}

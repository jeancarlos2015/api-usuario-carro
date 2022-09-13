package api.appusuario.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.appusuario.config.security.UsuarioServiceSecurityImp;
import api.appusuario.services.UsuarioService;
import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;

@RestController
@RequestMapping("/api/usuario")

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioServiceSecurityImp usuarioServiceSecurityImp;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public UsuarioLoginRetornoDTO cadastrar(@RequestBody @Valid UsuarioCadastroDTO usuarioDTO) {
        return usuarioServiceSecurityImp.salvar(usuarioDTO);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public UsuarioBuscaDTO buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping
    public UsuarioBuscaDTO atualizar(@RequestBody @Valid UsuarioEdicaoDTO usuarioDTO) {
        return usuarioService.atualizar(usuarioDTO);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UsuarioBuscaDTO> buscarTodos() {
    	
        return usuarioService.buscarTodos();
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/auth")
    public UsuarioLoginRetornoDTO autenticar(@RequestBody UsuarioLoginDTO credenciais) {
    	return	usuarioServiceSecurityImp.autenticar(credenciais);
	
    }

}

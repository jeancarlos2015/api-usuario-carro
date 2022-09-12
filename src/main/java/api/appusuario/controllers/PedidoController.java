package api.appusuario.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import api.appusuario.config.security.exception.SenhaInvalidaException;
import api.appusuario.models.Usuario;
import api.appusuario.services.UsuarioService;
import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;

@RestController
@RequestMapping("/api/pedido")

public class PedidoController {
  
   

  
 

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
       
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UsuarioBuscaDTO> buscarTodos() {
        return new ArrayList<>();
    }
    
    

}

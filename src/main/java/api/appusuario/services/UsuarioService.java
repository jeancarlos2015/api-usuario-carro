package api.appusuario.services;

import java.util.List;

import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;

public interface UsuarioService {
    public UsuarioBuscaDTO cadastrar(UsuarioCadastroDTO usuarioDTO);
    public UsuarioLoginRetornoDTO cadastrarLogin(UsuarioCadastroDTO usuarioDTO);
    public UsuarioBuscaDTO buscarPorId(Long id);
    public UsuarioBuscaDTO buscarPorLogin(String login);
    public UsuarioBuscaDTO atualizar(UsuarioEdicaoDTO usuarioDTO);
    public void excluir(Long id);
    public List<UsuarioBuscaDTO> buscarTodos();

}

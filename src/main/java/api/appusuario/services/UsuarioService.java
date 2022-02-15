package api.appusuario.services;

import java.util.List;

import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;

public interface UsuarioService {
    public UsuarioBuscaDTO cadastrar(UsuarioCadastroDTO usuarioDTO);
    public UsuarioBuscaDTO buscarPorId(Long id);
    public UsuarioBuscaDTO atualizar(UsuarioEdicaoDTO usuarioDTO);
    public void excluir(Long id);
    public List<UsuarioBuscaDTO> buscarTodos();

}

package api.appusuario.services.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import api.appusuario.models.Usuario;
import api.appusuario.repositorys.UsuarioRepository;
import api.appusuario.services.UsuarioService;
import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.mappers.usuario.UsuarioBuscaMapper;
import api.appusuario.services.mappers.usuario.UsuarioCadastroMapper;
import api.appusuario.services.mappers.usuario.UsuarioEdicaoMapper;

@Transactional
@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioCadastroMapper usuarioCadastroMapper;
    @Autowired
    private UsuarioBuscaMapper usuarioBuscaMapper;
    @Autowired
    private UsuarioEdicaoMapper usuarioEdicaoMapper;

    @Override
    public UsuarioBuscaDTO cadastrar(UsuarioCadastroDTO usuarioDTO) {
        Usuario usuarioCadastro = usuarioCadastroMapper.toEntity(usuarioDTO);
        Usuario usuario = usuarioRepository.save(usuarioCadastro);
        return usuarioBuscaMapper.toDto(usuario);
    }

    @Override
    public UsuarioBuscaDTO buscarPorId(Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).get();
            return usuarioBuscaMapper.toDto(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UsuarioBuscaDTO atualizar(UsuarioEdicaoDTO usuarioEdicaoDTO) {
        try {
            Usuario usuarioEdicao = usuarioEdicaoMapper.toEntity(usuarioEdicaoDTO);
            Usuario usuarioResult = usuarioRepository.save(usuarioEdicao);
            return usuarioBuscaMapper.toDto(usuarioResult);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void excluir(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       
    }

    @Override
    public List<UsuarioBuscaDTO> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioBuscaMapper.toDto(usuarios);
    }

}

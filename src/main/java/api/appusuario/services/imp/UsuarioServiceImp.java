package api.appusuario.services.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import api.appusuario.models.Usuario;
import api.appusuario.repositorys.UsuarioRepository;
import api.appusuario.services.UsuarioService;
import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;
import api.appusuario.services.mappers.usuario.UsuarioBuscaMapper;
import api.appusuario.services.mappers.usuario.UsuarioCadastroMapper;
import api.appusuario.services.mappers.usuario.UsuarioEdicaoMapper;
import api.appusuario.services.mappers.usuario.UsuarioLoginRetornoMapper;

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
    @Autowired
    private UsuarioLoginRetornoMapper usuarioLoginRetornoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioBuscaDTO cadastrar(UsuarioCadastroDTO usuarioDTO) {
        
        Usuario usuarioCadastro = usuarioCadastroMapper.toEntity(usuarioDTO);
        usuarioCadastro.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
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

    @Override
    public UsuarioBuscaDTO buscarPorLogin(String login) {

        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));
        return usuarioBuscaMapper.toDto(usuario);

    }

    @Override
    public UsuarioLoginRetornoDTO cadastrarLogin(UsuarioCadastroDTO usuarioDTO) {
        Usuario usuarioCadastro = usuarioCadastroMapper.toEntity(usuarioDTO);
        usuarioCadastro.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioRepository.save(usuarioCadastro);
        return usuarioLoginRetornoMapper.toDto(usuario);
    }

}

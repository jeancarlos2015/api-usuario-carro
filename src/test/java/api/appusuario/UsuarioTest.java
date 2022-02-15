package api.appusuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import api.appusuario.models.Usuario;
import api.appusuario.repositorys.UsuarioRepository;
import api.appusuario.services.UsuarioService;
import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.mappers.UsuarioBuscaMapper;
import api.appusuario.services.mappers.UsuarioCadastroMapper;
import api.appusuario.services.mappers.UsuarioEdicaoMapper;

@SpringBootTest
public class UsuarioTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioCadastroMapper usuarioCadastroMapper;

    @Autowired
    private UsuarioBuscaMapper usuarioBuscaMapper;

    @Autowired
    private UsuarioEdicaoMapper usuarioEdicaoMapper;
    @Autowired
    private UsuarioService usuarioService;

    @Test
    @Rollback(false)
    void testarCadastro() {
        UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
        usuarioDTO.setNome("Usuario Teste");
        usuarioDTO.setEmail("teste@gmail.com");
        usuarioDTO.setSenha("senha123");
        UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
        assert usuarioBuscaDTO.getId() != null;
    }

    @Test
    @Rollback(false)
    void testarBusca() {
        UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
        usuarioDTO.setNome("Usuario Teste");
        usuarioDTO.setEmail("teste@gmail.com");
        usuarioDTO.setSenha("senha123");
        UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
        UsuarioBuscaDTO usuarioBuscaDTO2 = usuarioService.buscarPorId(usuarioBuscaDTO.getId());
        assert usuarioBuscaDTO2.getNome().equals(usuarioBuscaDTO.getNome());

    }

    @Test
    @Rollback(false)
    void testarEdicao() {
        UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
        usuarioDTO.setNome("Usuario Teste");
        usuarioDTO.setEmail("teste@gmail.com");
        usuarioDTO.setSenha("senha123");
        UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
        Usuario usuario = usuarioRepository.findById(usuarioBuscaDTO.getId()).get();
        UsuarioEdicaoDTO usuarioEdicaoDTO = usuarioEdicaoMapper.toDto(usuario);
        usuarioEdicaoDTO.setNome("Usuario Teste Editado");
        usuarioEdicaoDTO.setEmail("testeedicado@gmail.com");
        usuarioEdicaoDTO.setSenha("senha123editado");
        Usuario usuarioEdicao = usuarioEdicaoMapper.toEntity(usuarioEdicaoDTO);
        Usuario usuarioResult = usuarioRepository.save(usuarioEdicao);
        UsuarioEdicaoDTO usuarioEdicaoDTO2 = usuarioEdicaoMapper.toDto(usuarioResult);
        assert usuarioEdicaoDTO2.getNome().equals(usuarioEdicaoDTO.getNome());
    }
}

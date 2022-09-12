package api.appusuario;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import api.appusuario.config.security.JwtServiceImp;
import api.appusuario.config.security.UsuarioServiceSecurityImp;
import api.appusuario.models.Usuario;
import api.appusuario.repositorys.UsuarioRepository;
import api.appusuario.services.UsuarioService;
import api.appusuario.services.dto.usuario.UsuarioBuscaDTO;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioEdicaoDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;
import api.appusuario.services.mappers.usuario.UsuarioEdicaoMapper;

@SpringBootTest
@Transactional(rollbackOn = Exception.class)
public class UsuarioTest {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioEdicaoMapper usuarioEdicaoMapper;
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	void testarCadastro() {
		UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
		usuarioDTO.setLogin("Usuario Teste");
		usuarioDTO.setEmail("teste@gmail.com");
		usuarioDTO.setSenha("senha123");
		UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
		assert usuarioBuscaDTO.getId() != null;
	}

	@Test
	void testarCadastroLoginRetorno() {
		UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
		usuarioDTO.setLogin("Usuario Teste");
		usuarioDTO.setEmail("teste@gmail.com");
		usuarioDTO.setSenha("senha123");
		UsuarioLoginRetornoDTO usuarioBuscaDTO = usuarioService.cadastrarLogin(usuarioDTO);
		assert usuarioBuscaDTO.getId() != null;
	}

	@Test
	void testarBusca() {
		UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
		usuarioDTO.setLogin("Usuario Teste");
		usuarioDTO.setEmail("teste@gmail.com");
		usuarioDTO.setSenha("senha123");
		UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
		UsuarioBuscaDTO usuarioBuscaDTO2 = usuarioService.buscarPorId(usuarioBuscaDTO.getId());
		assert usuarioBuscaDTO2.getLogin().equals(usuarioBuscaDTO.getLogin());

	}

	@Test
	void testarBuscaPorLogin() {
		UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
		usuarioDTO.setLogin("Usuario Teste");
		usuarioDTO.setEmail("teste@gmail.com");
		usuarioDTO.setSenha("senha123");
		UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
		UsuarioBuscaDTO usuarioBuscaDTO2 = usuarioService.buscarPorLogin(usuarioBuscaDTO.getLogin());
		assert usuarioBuscaDTO2.getLogin().equals(usuarioBuscaDTO.getLogin());

	}

	@Test
	void testarEdicao() {
		UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
		usuarioDTO.setLogin("Usuario Teste");
		usuarioDTO.setEmail("teste@gmail.com");
		usuarioDTO.setSenha("senha123");
		UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
		Usuario usuario = usuarioRepository.findById(usuarioBuscaDTO.getId()).get();
		UsuarioEdicaoDTO usuarioEdicaoDTO = usuarioEdicaoMapper.toDto(usuario);
		usuarioEdicaoDTO.setLogin("Usuario Teste Editado");
		usuarioEdicaoDTO.setEmail("testeedicado@gmail.com");
		usuarioEdicaoDTO.setSenha("senha123editado");
		Usuario usuarioEdicao = usuarioEdicaoMapper.toEntity(usuarioEdicaoDTO);
		Usuario usuarioResult = usuarioRepository.save(usuarioEdicao);
		UsuarioEdicaoDTO usuarioEdicaoDTO2 = usuarioEdicaoMapper.toDto(usuarioResult);
		assert usuarioEdicaoDTO2.getLogin().equals(usuarioEdicaoDTO.getLogin());
	}

	@Test
	void testarExclusao() {
		UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO();
		usuarioDTO.setLogin("Usuario Teste");
		usuarioDTO.setEmail("teste@gmail.com");
		usuarioDTO.setSenha("senha123");
		UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioDTO);
		Usuario usuario = usuarioRepository.findById(usuarioBuscaDTO.getId()).get();
		usuarioRepository.delete(usuario);
		assert usuarioRepository.findById(usuarioBuscaDTO.getId()).isEmpty();
	}
//
//	@Test
//	void testarGeracaoToken() {
//
//		ConfigurableApplicationContext contexto = SpringApplication.run(AppUsuarioApplication.class);
//		JwtServiceImp jwtServiceImp = contexto.getBean(JwtServiceImp.class);
//		UsuarioServiceSecurityImp usuarioServiceSecurityImp = contexto.getBean(UsuarioServiceSecurityImp.class);
//		UsuarioCadastroDTO usuarioCadastroDTO = new UsuarioCadastroDTO();
//		usuarioCadastroDTO.setLogin("UsuarioTeste");
//		usuarioCadastroDTO.setEmail("teste@gmail.com");
//		usuarioCadastroDTO.setSenha("senha123");
//		UsuarioBuscaDTO usuarioBuscaDTO = usuarioService.cadastrar(usuarioCadastroDTO);
//		Usuario usuario = usuarioRepository.findById(usuarioBuscaDTO.getId()).get();
//		String token = jwtServiceImp.gerarToken(usuario);
//		Boolean eValido = jwtServiceImp.tokenValido(token);
//		UsuarioLoginDTO usuarioDTO = new UsuarioLoginDTO();
//		usuarioDTO.setUsuario("UsuarioTeste");
//		usuarioDTO.setPassword("senha123");
//		UsuarioLoginRetornoDTO usuarioLoginRetornoDTO = usuarioServiceSecurityImp.autenticar(usuarioDTO);
//		Boolean eValidoTokenAutenticacao = jwtServiceImp.tokenValido(usuarioLoginRetornoDTO.getToken());
//		assert token != null && eValido == true && eValidoTokenAutenticacao == true;
//	}

	

}

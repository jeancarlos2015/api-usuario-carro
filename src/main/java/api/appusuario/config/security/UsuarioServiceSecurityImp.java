package api.appusuario.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import api.appusuario.config.security.exception.SenhaInvalidaException;
import api.appusuario.models.Usuario;
import api.appusuario.repositorys.UsuarioRepository;
import api.appusuario.services.dto.usuario.UsuarioCadastroDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginDTO;
import api.appusuario.services.dto.usuario.UsuarioLoginRetornoDTO;
import api.appusuario.services.mappers.usuario.UsuarioCadastroMapper;
import api.appusuario.services.mappers.usuario.UsuarioLoginRetornoMapper;

@Service
public class UsuarioServiceSecurityImp implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioCadastroMapper usuarioCadastroMapper;

	@Autowired
	private UsuarioLoginRetornoMapper usuarioLoginRetornoMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtServiceImp jwtServiceImp;
	
	@Transactional
	public UsuarioLoginRetornoDTO salvar(UsuarioCadastroDTO usuarioDTO) {
		Usuario usuario = usuarioCadastroMapper.toEntity(usuarioDTO);
		usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		usuario = usuarioRepository.save(usuario);
		return usuarioLoginRetornoMapper.toDto(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByLogin(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

		String[] roles = usuario.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };
		return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).roles(roles).build();
	}

	private UserDetails autenticarAux(UsuarioLoginDTO usuario) {
		UserDetails user = loadUserByUsername(usuario.getUsuario());
		boolean senhaValida = passwordEncoder.matches(usuario.getPassword(), user.getPassword());
		if(senhaValida) {
			return user;
		}
		throw new SenhaInvalidaException();
	}
	
	public UsuarioLoginRetornoDTO autenticar(UsuarioLoginDTO usuarioDTO) {
		try {
			loadUserByUsername(usuarioDTO.getUsuario());
			autenticarAux(usuarioDTO);
			Usuario usuario = usuarioRepository.findByLogin(usuarioDTO.getUsuario()).get();
			String token = jwtServiceImp.gerarToken(usuario);
			UsuarioLoginRetornoDTO usuarioLoginRetorno = usuarioLoginRetornoMapper.toDto(usuario);
			usuarioLoginRetorno.setToken(token);
			return usuarioLoginRetorno;
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,  e.getMessage());
		}
	
	}

}

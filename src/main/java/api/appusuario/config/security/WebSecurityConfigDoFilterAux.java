package api.appusuario.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class WebSecurityConfigDoFilterAux {

	@Autowired
	private JwtServiceImp jwtServiceImp;

	@Autowired
	private UsuarioServiceSecurityImp usuarioServiceSecurityImp;

	
}

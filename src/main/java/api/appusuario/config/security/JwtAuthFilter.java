package api.appusuario.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtServiceImp jwtServiceImp;
	private UsuarioServiceSecurityImp usuarioServiceSecurityImp;

	public JwtAuthFilter(JwtServiceImp jwtServiceImp, UsuarioServiceSecurityImp usuarioServiceSecurityImp) {
		this.jwtServiceImp = jwtServiceImp;
		this.usuarioServiceSecurityImp = usuarioServiceSecurityImp;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];
			boolean isValid = jwtServiceImp.tokenValido(token);
			if (isValid) {
				String loginUsuario = jwtServiceImp.obterLoginUsuario(token);
				UserDetails usuario = usuarioServiceSecurityImp.loadUserByUsername(loginUsuario);
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null,
						usuario.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
			filterChain.doFilter(request, response);

		}
	
	}
	
//	@Override
//    protected boolean shouldNotFilterAsyncDispatch() {
//        return true;
//    }
//
//    @Override
//    protected boolean shouldNotFilterErrorDispatch() {
//        return true;
//    }
//    
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return true;
//    }

}

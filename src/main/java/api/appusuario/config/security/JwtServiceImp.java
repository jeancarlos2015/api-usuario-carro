package api.appusuario.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import api.appusuario.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImp {
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;

	public String gerarToken(Usuario usuario) {
		long exp = Long.valueOf(expiracao);
		LocalDateTime dataHoraExp = LocalDateTime.now().plusMinutes(exp);
		Instant instant = dataHoraExp.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);

		HashMap<String, Object> claims = new HashMap<>();
		claims.put("usuario", usuario.getLogin());
		claims.put("email", usuario.getEmail());
		claims.put("id", usuario.getId());

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(usuario.getLogin())
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura).compact();
	}

	private Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(chaveAssinatura).parseClaimsJws(token).getBody();
	}

	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao
					.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDateTime();
			return LocalDateTime.now().isBefore(data);
		} catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) {
		return (String) obterClaims(token).getSubject();
	}
}

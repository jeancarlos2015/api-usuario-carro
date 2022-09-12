package api.appusuario.services.dto.usuario;

public class UsuarioLoginRetornoDTO {
    private Long id;
    private String login;
    private boolean admin;
    private String email;
    private String token;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
  

}

package br.com.mitz.system.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import br.com.mitz.system.model.Usuario;
import br.com.mitz.system.security.Security;
import br.com.mitz.system.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3329130791356773620L;

	private String username;
	private String password;

	private boolean loggedIn;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	private List<Usuario> usuarios;

	@Inject
	private UsuarioService usuarioService;

	/**
	 * Login operation.
	 * 
	 * @return
	 */
	public String doLogin() {
		try {
			usuarios = usuarioService.findUsuarioByLoginSenha(username, Security.encryptString(password));
			if (!usuarios.isEmpty() && usuarios.size() > 0) {
				loggedIn = true;
				return navigationBean.redirectToWelcome();
			}

			// Set login ERROR
			FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// To to login page
		return navigationBean.toLogin();

	}

	/**
	 * Logout operation.
	 * 
	 * @return
	 */
	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		loggedIn = false;

		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return navigationBean.toLogin();
	}

	// ------------------------------
	// Getters & Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

}
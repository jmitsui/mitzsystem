package br.com.mitz.system.model;

import javax.inject.Inject;
import javax.persistence.*;
import br.com.mitz.system.security.*;

public class UsuarioListener {
	@Inject
	private Security encryptor;

	/**
	 * Decrypt password before persisting
	 */
	@PrePersist
	@PreUpdate
	public void encryptPassword(Object pc) {
		if (!(pc instanceof Usuario)) {
			return;
		}

		Usuario user = (Usuario) pc;
		user.setSenha(encryptor.encryptString(user.getSenha()));
	}
}
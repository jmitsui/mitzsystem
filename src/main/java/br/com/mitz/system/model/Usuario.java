package br.com.mitz.system.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Classe de modelo que representa um usuário. O usuário é um objeto persistido,
 * por isso utilizamos o nome entidade.
 * 
 * <p>
 * As funcionalidades desse sistema demonstração são concentradas no cadastro
 * (CRUD) de usuários.
 * </p>
 * 
 * <p>
 * Essa entidade é mapeada com anotações da <code>JPA</code>, o mecanismo padrão
 * do Java para mapeamento <code>ORM</code>.
 * </p>
 * 
 * @author Joel Mitsui
 */
@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1, initialValue = 0)
@EntityListeners(UsuarioListener.class)
@NamedQueries({ 
	@NamedQuery(name = "findUsuarioByLoginSenha", 
				query = "SELECT u " + 
						"FROM Usuario u " + 
						"WHERE u.login = :login AND " + 
						"      u.senha = :senha") 
})
public class Usuario implements AbstractEntity {

	private static final long serialVersionUID = -2943224636519930581L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	private Long id;

	@Column
	private String login;

	@Column
	@Lob
	private String senha;

	@Column
	private String nome;

	@Column
	private String email;

	@Column
	private boolean active;

	// Getters and setters
	// ==================================================
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

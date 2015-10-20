package br.com.mitz.system.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe de modelo que representa um Localmonitor. A mercadoria é um objeto
 * persistido, por isso utilizamos o nome entidade.
 * 
 * <p>
 * As funcionalidades desse sistema demonstração são concentradas no cadastro
 * (CRUD) de Localmonitors.
 * </p>
 * 
 * <p>
 * Essa entidade é mapeada com anotações da <code>JPA</code>, o mecanismo padrão
 * do Java para mapeamento <code>ORM</code>.
 * </p>
 */
@Entity
@Table(name = "local")
@SequenceGenerator(name = "local_seq", sequenceName = "local_seq", allocationSize = 1, initialValue = 0)
public class Localmonitor implements AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String nome;

	@Column
	private String endereço;

	@Column
	private String complemento;

	@Column
	private String bairro;

	@Column
	private String cep;

	@Column
    @OneToOne(cascade=CascadeType.ALL)
	private Cidade cidade;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


}

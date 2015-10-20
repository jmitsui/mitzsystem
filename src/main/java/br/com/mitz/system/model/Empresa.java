package br.com.mitz.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de modelo que representa uma empresa. A empresa é um objeto
 * persistido, por isso utilizamos o nome entidade.
 * 
 * <p>
 * As funcionalidades desse sistema demonstração são concentradas no cadastro
 * (CRUD) de empresas.
 * </p>
 * 
 * <p>
 * Essa entidade é mapeada com anotações da <code>JPA</code>, o mecanismo padrão
 * do Java para mapeamento <code>ORM</code>.
 * </p>
 */
@Entity
@Table(name = "empresa")
public class Empresa implements AbstractEntity {

	private static final long serialVersionUID = 6369431683573659414L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String razaosocial;

	@Column
	private String nomefantasia;

	@Column
	private String cpfcnpj;

	@Column
	private String inscricaoestadual;

	@Column
	private String endereco;

	@Column
	private String bairro;

	@Column
	private String cep;

	@Column
	private String telefone;

	@Column
	private String fax;

	@Column
	private String email;

	@Column
	private String homepage;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public String getNomefantasia() {
		return nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getInscricaoestadual() {
		return inscricaoestadual;
	}

	public void setInscricaoestadual(String inscricaoestadual) {
		this.inscricaoestadual = inscricaoestadual;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

}

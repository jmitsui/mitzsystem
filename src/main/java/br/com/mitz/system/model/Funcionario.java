package br.com.mitz.system.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Classe de modelo que representa um funcionário. O funcionário é um objeto persistido, por isso utilizamos o nome entidade.
 * 
 * <p>As funcionalidades desse sistema demonstração são concentradas no cadastro (CRUD) de funcionários.</p>
 * 
 * <p>Essa entidade é mapeada com anotações da <code>JPA</code>, o mecanismo padrão do Java para mapeamento <code>ORM</code>.</p>
 * 
 * @author Joel Mitsui
 */
@Entity
@Table(name="funcionario")
@SequenceGenerator(name="funcionario_seq", sequenceName="funcionario_seq", allocationSize=1, initialValue=0)
public class Funcionario implements AbstractEntity {

	private static final long serialVersionUID = 1480875652621983906L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="funcionario_seq")
	private Long id;

	@Column
	private String nome;
	
	@Column
	private String cpf_cnpj;
	
	@Column
	private Integer pis;
	
	@Column
	private String cargo;
	
	@Column
	private Date admissao;
	
	@Column
	private Date nascimento;
	
	@Column
	private Double salario;
	
	@Column
	private String endereco;
	
	@Column
	private String bairro;
	
	@Column
	private String cep;
	
	@Column
	private String telefone;
	
	@Column
	private String celular;
	
	// Getters and setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public Integer getPis() {
		return pis;
	}

	public void setPis(Integer pis) {
		this.pis = pis;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	//==================================================
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

}

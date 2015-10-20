package br.com.mitz.system.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Classe de modelo que representa um registroponto. O registroponto é um objeto persistido, por isso utilizamos o nome entidade.
 * 
 * <p>As funcionalidades desse sistema demonstração são concentradas no cadastro (CRUD) de registropontos.</p>
 * 
 * <p>Essa entidade é mapeada com anotações da <code>JPA</code>, o mecanismo padrão do Java para mapeamento <code>ORM</code>.</p>
 * 
 * @author Joel Mitsui
 */
@Entity
@Table(name="registroponto")
@SequenceGenerator(name = "registroponto_seq", sequenceName = "registroponto_seq", allocationSize = 1, initialValue = 0)
public class Registroponto implements AbstractEntity {

	private static final long serialVersionUID = -388874159074202564L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registroponto_seq")
	private Long id;

	@Column
	private Date dataentrada;
	
	@Column
	private Date datasaida;

	@Column
	private String ocorrencia;

    @ManyToOne(cascade=CascadeType.ALL)
    private Funcionario funcionario; 
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(Date dataentrada) {
		this.dataentrada = dataentrada;
	}

	public Date getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(Date datasaida) {
		this.datasaida = datasaida;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	//==================================================
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

}

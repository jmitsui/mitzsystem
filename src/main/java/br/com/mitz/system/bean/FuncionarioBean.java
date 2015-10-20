package br.com.mitz.system.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mitz.system.model.Funcionario;
import br.com.mitz.system.service.FuncionarioService;

/**
 * Componente responsável por integrar o front-end (páginas JSF) c/ camada de serviço (EJB), para resolver o cadastro de <code>Funcionario</code>.
 * 
 * <p>Trata-se de um <code>Managed Bean</code>, ou seja, as instâncias dessa classe são controladas pelo <code>JSF</code>. 
 * Objetos de <code>FuncionarioMB</code> são criados e gerenciados pelo <code>CDI</code>, no escopo de <code>Request<code>.</p>
 * 
 * <p>Esse componente atua com um papel parecido com o <code>Controller</code> de outros frameworks <code>MVC</code>, ele resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 */
@Named
@RequestScoped
public class FuncionarioBean implements Serializable {
	
	private static final long serialVersionUID = -4935426359205758441L;

	/**
	 * Referência para o componente EJB, injetado pelo container.
	 */
	@Inject
	private FuncionarioService service;
	
	/**
	 * Referência para o funcionario utiliza na inclusão (nova) ou edição.
	 */
	@Inject
	private Funcionario funcionario;
	
	private Long idSelecionado;
	
	private List<Funcionario> funcionarios;
	
	public FuncionarioBean() {
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		funcionario = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Funcionario> getFuncionarios() {
		System.out.println("service: "+service);
		if (funcionarios == null) {
			funcionarios = service.findAll();
		}
		return funcionarios;
	}

	
	public String salvar() {
		try {
			System.out.println("service: "+service);
			service.save(funcionario);
		} catch(Exception ex) {
			System.out.println("Erro ao salvar funcionario:" + ex.getMessage());
			//log.error("Erro ao salvar funcionario.", ex);
			//addMessage(getMessageFromI18N("msg.erro.salvar.funcionario"), ex.getMessage());
			return "";
		}
		//log.debug("Salvou funcionario "+funcionario.getId());
		return "listaFuncionarios";
	}
	
	public String remover() {
		try {
			service.remove(funcionario);
		} catch(Exception ex) {
			System.out.println("Erro ao remover funcionario:" + ex.getMessage());
			//log.error("Erro ao remover funcionario.", ex);
			//addMessage(getMessageFromI18N("msg.erro.remover.funcionario"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu funcionario "+funcionario.getId());
		return "listaFuncionarios";
	}
	
	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}
	
	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}
	
}

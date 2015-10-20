package br.com.mitz.system.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.mitz.system.model.Empresa;
import br.com.mitz.system.service.EmpresaService;

/**
 * Componente responsável por integrar o front-end (páginas JSF) c/ camada de serviço (EJB), para resolver o cadastro de <code>Empresa</code>.
 * 
 * <p>Trata-se de um <code>Managed Bean</code>, ou seja, as instâncias dessa classe são controladas pelo <code>JSF</code>. 
 * Objetos de <code>EmpresaBean</code> são criados e gerenciados pelo <code>CDI</code>, no escopo de <code>Request<code>.</p>
 * 
 * <p>Esse componente atua com um papel parecido com o <code>Controller</code> de outros frameworks <code>MVC</code>, ele resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 */
@Named
@RequestScoped
public class EmpresaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Referência para o componente EJB, injetado pelo container.
	 */
	@Inject
	private EmpresaService service;
	
	/**
	 * Referência para a Mercadoria utiliza na inclusão (nova) ou edição.
	 */
	@Inject
	private Empresa empresa;
	
	private Long idSelecionado;
	
	private List<Empresa> empresas;
	
	public EmpresaBean() {
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		empresa = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Empresa> getEmpresas() {
		System.out.println("service: "+service);
		if (empresas == null) {
			empresas = service.findAll();
		}
		return empresas;
	}

	
	public String salvar() {
		try {
			System.out.println("service: "+service);
			service.save(empresa);
		} catch(Exception ex) {
			//log.error("Erro ao salvar empresa.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.empresa"), ex.getMessage());
			return "";
		}
		//log.debug("Salvou empresa "+mercadoria.getId());
		return "listaEmpresas";
	}
	
	public String remover() {
		try {
			service.remove(empresa);
		} catch(Exception ex) {
			//log.error("Erro ao remover empresa.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.empresa"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu empresa "+empresa.getId());
		return "listaEmpresas";
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

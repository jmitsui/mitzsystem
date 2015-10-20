package br.com.mitz.system.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.mitz.system.model.Registroponto;
import br.com.mitz.system.service.RegistropontoService;

/**
 * Componente responsável por integrar o front-end (páginas JSF) c/ camada de serviço (EJB), para resolver o cadastro de <code>Registroponto</code>.
 * 
 * <p>Trata-se de um <code>Managed Bean</code>, ou seja, as instâncias dessa classe são controladas pelo <code>JSF</code>. 
 * Objetos de <code>RegistropontoMB</code> são criados e gerenciados pelo <code>CDI</code>, no escopo de <code>Request<code>.</p>
 * 
 * <p>Esse componente atua com um papel parecido com o <code>Controller</code> de outros frameworks <code>MVC</code>, ele resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 */
@Named
@RequestScoped
public class RegistropontoBean implements Serializable {
	
	private static final long serialVersionUID = -1949405948297624897L;

	/**
	 * Referência para o componente EJB, injetado pelo container.
	 */
	@Inject
	private RegistropontoService service;
	
	/**
	 * Referência para o registroponto utiliza na inclusão (nova) ou edição.
	 */
	@Inject
	private Registroponto registroponto;
	
	private Long idSelecionado;
	
	private List<Registroponto> registropontos;
	
	public RegistropontoBean() {
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Registroponto getRegistroponto() {
		return registroponto;
	}
	
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		registroponto = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}

	public List<Registroponto> getRegistropontos() {
		System.out.println("service: "+service);
		if (registropontos == null) {
			registropontos = service.findAll();
		}
		return registropontos;
	}

	
	public String salvar() {
		try {
			System.out.println("service: "+service);
			service.save(registroponto);
		} catch(Exception ex) {
			//log.error("Erro ao salvar funcionario.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.registroponto"), ex.getMessage());
			return "";
		}
		//log.debug("Salvou registroponto "+registroponto.getId());
		return "listaRegistropontos";
	}
	
	public String remover() {
		try {
			service.remove(registroponto);
		} catch(Exception ex) {
			//log.error("Erro ao remover registroponto.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.registroponto"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu registroponto "+registroponto.getId());
		return "listaRegistropontos";
	}

	public Long getLastId() {
		Long registropontoId = Long.parseLong(service.maxByField("id"));
		if (registropontoId > 0) {
			registroponto = service.find(registropontoId);
			if (registroponto.getDataentrada() == null || registroponto.getDatasaida() == null) {
				registropontoId--;
			}
		}
		return registropontoId;
	}
	
	/*
    @WebMethod(operationName="methodName")
    public void salvarWebMethod(Long idSelecionado){
		registroponto = service.find(idSelecionado);
		String s = salvar();
        System.out.println(s);
    }
    */
    
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

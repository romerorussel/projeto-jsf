package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostLoad;

import org.primefaces.util.LangUtils;

import com.algaworks.erp.enums.TipoEmpresa;
import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.repository.RamoAtividades;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;
    
	@Inject
    private Empresas empresas;
	
	@Inject
	private RamoAtividades ramoAtividades;
	
	private Converter ramoAtividadeConverter;

	private List<Empresa> listaEmpresas;
    
    private List<Empresa> filteredEmpresa;
    
    private Empresa empresa;
    

	private final String PAGE_TITLE = "Cadastro de Empresas";
    
	@PostConstruct
    public void init() {
		empresa = new Empresa();
		
    	if(listaEmpresas == null) {
            listaEmpresas = this.empresas.getAll();
    	}
    }
	
	
	public List<RamoAtividade> autocompleteRamoAtividade(String param){
		
		List<RamoAtividade> listaRamoAtividade = ramoAtividades.getFilteredList(param);
		
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividade);
		
		return listaRamoAtividade;
	}
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }

        Empresa empresa = (Empresa) value;
        return empresa.getNomeFantasia().toLowerCase().contains(filterText)
                || empresa.getRazaoSocial().toLowerCase().contains(filterText)
                || empresa.getTipoEmpresa().getDescricao().toLowerCase().contains(filterText)
                || empresa.getRamoAtividade().getDescricao().toLowerCase().contains(filterText);
    }
     
    public void salvar() {
    	empresas.save(empresa);	
    }
    
    public boolean isSelected() {
    	return empresa != null && empresa.getId() != null;
    }
    
    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }

	public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }
    
    public List<Empresa> getFilteredEmpresa() {
        return listaEmpresas;
    }
    
    public void setFilteredEmpresa(List<Empresa> filteredEmpresa) {
    	this.filteredEmpresa = filteredEmpresa;
    }
    
    public TipoEmpresa[] getTipoEmpresa() {
    	return TipoEmpresa.values();
    }
    
    public String getPAGE_TITLE() {
		return PAGE_TITLE;
	}
    
    public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
    
    

}
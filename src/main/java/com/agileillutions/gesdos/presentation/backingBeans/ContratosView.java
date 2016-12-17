package com.agileillutions.gesdos.presentation.backingBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Contratos;
import com.agileillutions.gesdos.modelo.ContratosId;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.dto.ContratosDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ContratosView implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputText txtConDosi;
	private InputText txtConDura;
	private String txtConEst;
	private InputText txtConNroFac;
	private InputTextarea txtConObser;
	private InputText txtConTipo;
	private InputText txtConUsu;
	private InputText txtConVal;
	private InputText txtDetConCod;
	private InputText txtSocCod;
	private InputText txtConNro;
	private InputText txtEmpCod;
	private Calendar txtConFec;
	private Calendar txtConFecFac;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<ContratosDTO> data;
	private ContratosDTO selectedContratos;
	private Contratos entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private InputText txtEmpCodTabla;
	private InputText txtRazonSocialTabla;
	private List<Empresas> empresasTabla;
	private Empresas selectEmpresaTabla;
	
	private SelectOneMenu cbOpcionTipo;
	private SelectOneMenu cbOpcionEstado;

	private boolean botonGuardar;
	private boolean botonNuevo;
	private boolean botonModificar;
	private boolean botonBorrar;
	private boolean botonVisualizar;
	private boolean botonDosimetros;
	private boolean botonTrabajadores;
	private boolean disableBotonesBusqueda;
	
	private String txtTipoFac;
	
	private String txtDescCodAsociado;
	private Calendar txtConFecVen;
	
	private String codEmpresa;
	private String codContrato;

	public ContratosView() {
		super();
	}
	
	@PostConstruct
	public void getParametros() {
		try {

			inicializaCampos();

			botonGuardar = true;
			botonModificar = true;
			botonBorrar = true;
			botonVisualizar = true;
			botonDosimetros = true;
			botonTrabajadores = true;
			disableBotonesBusqueda = false;

			Map<String, String> requestParameterMap = FacesContext
					.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();

			String codEmpresa = requestParameterMap.get("codEmpresa");
			String codContrato = requestParameterMap.get("codContrato");
			String tipo = requestParameterMap.get("tipo");
			
			this.codEmpresa = codEmpresa;
			this.codContrato = codContrato;

			if (null == tipo || "".equals(tipo)) {
				txtConNro = new InputText();
				txtEmpCod = new InputText();
				txtEmpCodTabla = new InputText();

				txtConNro.setValue(codContrato);
				txtEmpCod.setValue(codEmpresa);
				txtEmpCodTabla.setValue(codEmpresa);

				if (null != codEmpresa && !codEmpresa.isEmpty()) {
					List<ContratosDTO> contratosDTOs = businessDelegatorView.getDataContratosPorEmpresa(Long.valueOf(codEmpresa));
					data = contratosDTOs;
				} 
				
				ejecutarControlesBusquedas();
			}
			// Tipo Visualizar
			else if ("2".equals(tipo)) {
				if (null != codContrato
						&& null != codEmpresa) {
					txtConNro = new InputText();
					txtEmpCod = new InputText();
					txtEmpCodTabla = new InputText();

					txtConNro.setValue(codContrato);
					txtEmpCod.setValue(codEmpresa);
					txtEmpCodTabla.setValue(codEmpresa);
					listener_txtId();
					modoVisualizar();
					ejecutarControlesBusquedas();
				}
			}
			// Tipo Modificar
			else if ("1".equals(tipo)) {
				if (null != codContrato
						&& null != codEmpresa) {
					txtConNro = new InputText();
					txtEmpCod = new InputText();
					txtEmpCodTabla = new InputText();

					txtConNro.setValue(codContrato);
					txtEmpCod.setValue(codEmpresa);
					txtEmpCodTabla.setValue(codEmpresa);
					listener_txtId();
					modoModificar();
					ejecutarControlesBusquedas();
					
					botonGuardar = false;
					botonModificar = true;
					botonBorrar = false;
					botonVisualizar = false;
					botonDosimetros = false;
					botonTrabajadores = false;
					disableBotonesBusqueda = false;
				}
			}
			// Tipo Crear
			else if ("0".equals(tipo)) {
				if(txtConNro.getValue() == null){
					txtConNro.setValue(this.codContrato);
				}
				
				if(txtEmpCod.getValue() == null){
					txtEmpCod.setValue(this.codEmpresa);
				}
				
				if(txtEmpCodTabla.getValue() == null){
					txtEmpCodTabla.setValue(this.codEmpresa);
				}
				
				botonGuardar = true;
				botonModificar = false;
				botonBorrar = false;
				botonVisualizar = false;
				botonDosimetros = false;
				botonTrabajadores = false;
				disableBotonesBusqueda = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String action_new() {
		action_clear();
		selectedContratos = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedContratos = null;

		if (txtConDosi != null) {
			txtConDosi.setValue(null);
			txtConDosi.setDisabled(true);
		}

		if (txtConDura != null) {
			txtConDura.setValue(null);
			txtConDura.setDisabled(true);
		}

		if (cbOpcionEstado != null) {
			cbOpcionEstado.setValue(null);
			cbOpcionEstado.setDisabled(true);
		}

		if (txtConNroFac != null) {
			txtConNroFac.setValue(null);
			txtConNroFac.setDisabled(true);
		}

		if (txtConObser != null) {
			txtConObser.setValue(null);
			txtConObser.setDisabled(true);
		}

		if (txtConTipo != null) {
			txtConTipo.setValue(null);
			txtConTipo.setDisabled(true);
		}

		if (txtConUsu != null) {
			txtConUsu.setValue(null);
			txtConUsu.setDisabled(true);
		}

		if (txtConVal != null) {
			txtConVal.setValue(null);
			txtConVal.setDisabled(true);
		}

		if (txtDetConCod != null) {
			txtDetConCod.setValue(null);
			txtDetConCod.setDisabled(true);
		}

		if (txtSocCod != null) {
			txtSocCod.setValue(null);
			txtSocCod.setDisabled(true);
		}

		if (txtConFec != null) {
			txtConFec.setValue(null);
			txtConFec.setDisabled(true);
		}

		if (txtConFecFac != null) {
			txtConFecFac.setValue(null);
			txtConFecFac.setDisabled(true);
		}

		if (txtConNro != null) {
			txtConNro.setValue(null);
			txtConNro.setDisabled(false);
		}

		if (txtEmpCod != null) {
			txtEmpCod.setValue(null);
			txtEmpCod.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtConFec() {
		Date inputDate = (Date) txtConFec.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public void listener_txtConFecFac() {
		Date inputDate = (Date) txtConFecFac.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			ContratosId id = new ContratosId();
			id.setConNro((((txtConNro.getValue()) == null) || (txtConNro.getValue()).equals("")) ? null
					: FacesUtils.checkLong(txtConNro));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod.getValue()).equals("")) ? null
					: FacesUtils.checkLong(txtEmpCod));
			entity = (id != null) ? businessDelegatorView.getContratos(id) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtConDosi.setDisabled(false);
			txtConDura.setDisabled(false);
			cbOpcionTipo.setDisabled(false);
			cbOpcionEstado.setDisabled(false);
			txtConNroFac.setDisabled(false);
			txtConObser.setDisabled(false);
			txtConTipo.setDisabled(false);
			txtConUsu.setDisabled(false);
			txtConVal.setDisabled(false);
			txtDetConCod.setDisabled(false);
			txtSocCod.setDisabled(false);
			txtConFec.setDisabled(false);
			txtConFecFac.setDisabled(false);
			txtConNro.setDisabled(false);
			txtEmpCod.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtConDosi.setValue(entity.getConDosi());
			txtConDura.setValue(entity.getConDura());
			cbOpcionEstado.setValue(entity.getConEst());
			txtConFec.setValue(entity.getConFec());
			txtConFecFac.setValue(entity.getConFecFac());
			txtConNroFac.setValue(entity.getConNroFac());
			txtConObser.setValue(entity.getConObser());
			txtConTipo.setValue(entity.getConTipo());
			txtConUsu.setValue(entity.getConUsu());
			txtConVal.setValue(entity.getConVal());
			txtDetConCod.setValue(entity.getDetConCod());
			txtSocCod.setValue(entity.getSocCod());
			txtConNro.setValue(entity.getId().getConNro());
			txtEmpCod.setValue(entity.getId().getEmpCod());
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedContratos = (ContratosDTO) (evt.getComponent().getAttributes().get("selectedContratos"));
		txtConDosi.setValue(selectedContratos.getConDosi());
		txtConDosi.setDisabled(false);
		txtConDura.setValue(selectedContratos.getConDura());
		txtConDura.setDisabled(false);
		cbOpcionEstado.setValue(selectedContratos.getConEst());
		cbOpcionEstado.setDisabled(false);
		txtConFec.setValue(selectedContratos.getConFec());
		txtConFec.setDisabled(false);
		txtConFecFac.setValue(selectedContratos.getConFecFac());
		txtConFecFac.setDisabled(false);
		txtConNroFac.setValue(selectedContratos.getConNroFac());
		txtConNroFac.setDisabled(false);
		txtConObser.setValue(selectedContratos.getConObser());
		txtConObser.setDisabled(false);
		txtConTipo.setValue(selectedContratos.getConTipo());
		txtConTipo.setDisabled(false);
		txtConUsu.setValue(selectedContratos.getConUsu());
		txtConUsu.setDisabled(false);
		txtConVal.setValue(selectedContratos.getConVal());
		txtConVal.setDisabled(false);
		txtDetConCod.setValue(selectedContratos.getDetConCod());
		txtDetConCod.setDisabled(false);
		txtSocCod.setValue(selectedContratos.getSocCod());
		txtSocCod.setDisabled(false);
		txtConNro.setValue(selectedContratos.getConNro());
		txtConNro.setDisabled(true);
		txtEmpCod.setValue(selectedContratos.getEmpCod());
		txtEmpCod.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedContratos == null) && (entity == null)) {
				action_create();
			} else {
				action_modify();
			}

			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_create() {
		try {
			entity = new Contratos();

			ContratosId id = new ContratosId();
			id.setConNro((((txtConNro.getValue()) == null) || (txtConNro.getValue()).equals("")) ? null
					: FacesUtils.checkLong(txtConNro));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod.getValue()).equals("")) ? null
					: FacesUtils.checkLong(txtEmpCod));

			entity.setConDosi(FacesUtils.checkLong(txtConDosi));
			entity.setConDura(FacesUtils.checkLong(txtConDura));
			entity.setConEst(FacesUtils.checkString(txtConEst));
			entity.setConFec(FacesUtils.checkDate(txtConFec));
			entity.setConFecFac(FacesUtils.checkDate(txtConFecFac));
			entity.setConNroFac(FacesUtils.checkLong(txtConNroFac));
			entity.setConObser(FacesUtils.checkString(txtConObser));
			entity.setConTipo(FacesUtils.checkString(txtConTipo));
			entity.setConUsu(FacesUtils.checkLong(txtConUsu));
			entity.setConVal(FacesUtils.checkLong(txtConVal));
			entity.setDetConCod(FacesUtils.checkLong(txtDetConCod));
			entity.setId(id);
			entity.setSocCod(FacesUtils.checkString(txtSocCod));
			businessDelegatorView.saveContratos(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				ContratosId id = new ContratosId();
				id.setConNro(selectedContratos.getConNro());
				id.setEmpCod(selectedContratos.getEmpCod());
				entity = businessDelegatorView.getContratos(id);
			}

			entity.setConDosi(FacesUtils.checkLong(txtConDosi));
			entity.setConDura(FacesUtils.checkLong(txtConDura));
			entity.setConEst(FacesUtils.checkString(txtConEst));
			entity.setConFec(FacesUtils.checkDate(txtConFec));
			entity.setConFecFac(FacesUtils.checkDate(txtConFecFac));
			entity.setConNroFac(FacesUtils.checkLong(txtConNroFac));
			entity.setConObser(FacesUtils.checkString(txtConObser));
			entity.setConTipo(FacesUtils.checkString(txtConTipo));
			entity.setConUsu(FacesUtils.checkLong(txtConUsu));
			entity.setConVal(FacesUtils.checkLong(txtConVal));
			entity.setDetConCod(FacesUtils.checkLong(txtDetConCod));
			entity.setSocCod(FacesUtils.checkString(txtSocCod));
			businessDelegatorView.updateContratos(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedContratos = (ContratosDTO) (evt.getComponent().getAttributes().get("selectedContratos"));

			ContratosId id = new ContratosId();
			id.setConNro(selectedContratos.getConNro());
			id.setEmpCod(selectedContratos.getEmpCod());
			entity = businessDelegatorView.getContratos(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			ContratosId id = new ContratosId();
			id.setConNro((((txtConNro.getValue()) == null) || (txtConNro.getValue()).equals("")) ? null
					: FacesUtils.checkLong(txtConNro));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod.getValue()).equals("")) ? null
					: FacesUtils.checkLong(txtEmpCod));
			entity = businessDelegatorView.getContratos(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteContratos(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedContratos = (ContratosDTO) (evt.getComponent().getAttributes().get("selectedContratos"));

			ContratosId id = new ContratosId();
			id.setConNro(selectedContratos.getConNro());
			id.setEmpCod(selectedContratos.getEmpCod());
			entity = businessDelegatorView.getContratos(id);
			businessDelegatorView.deleteContratos(entity);
			data.remove(selectedContratos);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long conNro, Long empCod, Long conDosi, Long conDura, String conEst, Date conFec,
			Date conFecFac, Long conNroFac, String conObser, String conTipo, Long conUsu, Long conVal, Long detConCod,
			String socCod) throws Exception {
		try {
			entity.setConDosi(FacesUtils.checkLong(conDosi));
			entity.setConDura(FacesUtils.checkLong(conDura));
			entity.setConEst(FacesUtils.checkString(conEst));
			entity.setConFec(FacesUtils.checkDate(conFec));
			entity.setConFecFac(FacesUtils.checkDate(conFecFac));
			entity.setConNroFac(FacesUtils.checkLong(conNroFac));
			entity.setConObser(FacesUtils.checkString(conObser));
			entity.setConTipo(FacesUtils.checkString(conTipo));
			entity.setConUsu(FacesUtils.checkLong(conUsu));
			entity.setConVal(FacesUtils.checkLong(conVal));
			entity.setDetConCod(FacesUtils.checkLong(detConCod));
			entity.setSocCod(FacesUtils.checkString(socCod));
			businessDelegatorView.updateContratos(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("ContratosView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @description
	 * @param id
	 * @param campoDeClase
	 * @param boleana
	 * @return
	 */
	public Object[] searchByCriteria(String id, String campoDeClase, boolean boleana) {
		Object object[] = new Object[4];
		object[0] = campoDeClase;
		object[1] = boleana;
		object[2] = id;
		object[3] = "=";
		return object;
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @description
	 */
	public void consultarEmpresasTabla() {
		try {
			if (null != txtEmpCodTabla.getValue()) {
				Object[] variablesEmpresa = searchByCriteria(txtEmpCodTabla.getValue().toString(), "empCod", true);
				List<Empresas> empresas = businessDelegatorView.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCodTabla.setValue(emp.getEmpCod());
						txtRazonSocialTabla.setValue(emp.getEmpRazSoc());
						txtRazonSocialTabla.setDisabled(true);

						RequestContext context = RequestContext.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmContrato:txtEmpCodTabla");
						s.add("frmContrato:txtRazonSocialTabla");
						context.update(s);
					}
				} else {
					empresasTabla = businessDelegatorView.getEmpresas();
				}
			} else {
				empresasTabla = businessDelegatorView.getEmpresas();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @description
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void onRowSelectEmpresaTabla() throws NumberFormatException, Exception {

		if (selectEmpresaTabla != null) {
			Empresas empresasDTO = selectEmpresaTabla;
			if (empresasDTO != null && empresasDTO.getEmpCod() != null) {

				txtEmpCodTabla.setValue(empresasDTO.getEmpCod());
				txtRazonSocialTabla.setValue(empresasDTO.getEmpRazSoc());
				txtRazonSocialTabla.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("frmContrato:txtEmpCodTabla");
				s.add("frmContrato:txtRazonSocialTabla");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresasTabla() {
		txtEmpCodTabla.resetValue();
		txtRazonSocialTabla.resetValue();
		txtRazonSocialTabla.setDisabled(true);
		data = null;
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("frmContrato:txtEmpCodTabla");
		s.add("frmContrato:txtRazonSocialTabla");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @description
	 */
	public void actionCerrarBusqueda() {

	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016
	 * @description 
	 * @param bandera
	 */
	public void cargarContratosDialog(String bandera) {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("contentWidth", "'100%'");
		options.put("contentheight", 550);
		options.put("width", "'100%'");
		options.put("height", 550);
		options.put("position", "top");
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("closeOnEscape", true);
		options.put("dynamic", false);

		String codEmpresa = "";
		String codContrato = "";
		if (null != selectedContratos) {
			codEmpresa = selectedContratos.getEmpCod().toString();
			codContrato = selectedContratos.getConNro().toString();
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);
		
		List<String> lstParamContrato = new ArrayList<String>();
		lstParamContrato.add(codContrato);

		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("codContrato", lstParamContrato);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/contratos.xhtml", options, params);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016
	 * @description 
	 * @param bandera
	 */
	public void cargarFacturasDialog(String bandera) {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("contentWidth", "'100%'");
		options.put("contentheight", 550);
		options.put("width", "'100%'");
		options.put("height", 550);
		options.put("position", "top");
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("closeOnEscape", true);
		options.put("dynamic", false);

		String codEmpresa = "";
		String codContrato = "";
		if (null != selectedContratos) {
			codEmpresa = selectedContratos.getEmpCod().toString();
			codContrato = selectedContratos.getConNro().toString();
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);
		
		List<String> lstParamContrato = new ArrayList<String>();
		lstParamContrato.add(codContrato);

		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("codContrato", lstParamContrato);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/facturaListDataTable.xhtml", options, params);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016
	 * @description
	 */
	public void inicializaCampos(){
		
		if (null == cbOpcionEstado) {
			cbOpcionEstado = new SelectOneMenu();
		}

		if (null == cbOpcionTipo) {
			cbOpcionTipo = new SelectOneMenu();
		}
		
		if (txtConDosi == null) {
			txtConDosi = new InputText();
		}

		if (txtConDura == null) {
			txtConDura = new InputText();
		}

		if (txtConNroFac == null) {
			txtConNroFac = new InputText();
		}

		if (txtConObser == null) {
			txtConObser = new InputTextarea();
		}

		if (txtConTipo == null) {
			txtConTipo = new InputText();
		}

		if (txtConUsu == null) {
			txtConUsu = new InputText();
		}

		if (txtConVal == null) {
			txtConVal = new InputText();
		}

		if (txtDetConCod == null) {
			txtDetConCod = new InputText();
		}

		if (txtSocCod == null) {
			txtSocCod = new InputText();
		}

		if (txtConNro == null) {
			txtConNro = new InputText();
		}

		if (txtEmpCod == null) {
			txtEmpCod = new InputText();
		}

		if (txtConFec == null) {
			txtConFec = new Calendar();
		}

		if (txtConFecFac == null) {
			txtConFecFac = new Calendar();
		}
		
		if (txtConFecVen == null) {
			txtConFecVen = new Calendar();
		}
		
		if (txtEmpCodTabla == null) {
			txtEmpCodTabla = new InputText();
		}
		
		if (txtRazonSocialTabla == null) {
			txtRazonSocialTabla = new InputText();
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016
	 * @description
	 */
	private void ejecutarControlesBusquedas() {
		consultarEmpresasTabla();
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016
	 * @description
	 */
	public void modoVisualizar() {

		botonGuardar = false;
		botonModificar = false;
		botonBorrar = false;
		botonVisualizar = false;
		botonDosimetros = false;
		botonTrabajadores = false;
		disableBotonesBusqueda = true;
		
		txtRazonSocialTabla.setDisabled(true);
		txtEmpCodTabla.setDisabled(true);
		txtConNro.setDisabled(true);
		txtConFecVen.setDisabled(true);
		
		txtEmpCod.setDisabled(false);
		txtConDosi.setDisabled(false);
		txtConDura.setDisabled(false);
		cbOpcionTipo.setDisabled(false);
		cbOpcionEstado.setDisabled(false);
		txtConNroFac.setDisabled(false);
		txtConObser.setDisabled(false);
		txtConTipo.setDisabled(false);
		txtConUsu.setDisabled(false);
		txtConVal.setDisabled(false);
		txtDetConCod.setDisabled(false);
		txtSocCod.setDisabled(false);
		txtConFec.setDisabled(false);
		txtConFecFac.setDisabled(false);

		RequestContext.getCurrentInstance().update("formDialogContrato");
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016
	 * @description
	 */
	public void modoModificar() {

		botonGuardar = false;
		botonModificar = true;
		botonBorrar = false;
		botonVisualizar = false;
		botonDosimetros = false;
		botonTrabajadores = false;
		disableBotonesBusqueda = false;

		txtRazonSocialTabla.setDisabled(true);
		txtEmpCodTabla.setDisabled(true);
		txtConNro.setDisabled(true);
		txtConFecVen.setDisabled(true);
		
		txtEmpCod.setDisabled(false);
		txtConDosi.setDisabled(false);
		txtConDura.setDisabled(false);
		cbOpcionTipo.setDisabled(false);
		cbOpcionEstado.setDisabled(false);
		txtConNroFac.setDisabled(false);
		txtConObser.setDisabled(false);
		txtConTipo.setDisabled(false);
		txtConUsu.setDisabled(false);
		txtConVal.setDisabled(false);
		txtDetConCod.setDisabled(false);
		txtSocCod.setDisabled(false);
		txtConFec.setDisabled(false);
		txtConFecFac.setDisabled(false);

		RequestContext.getCurrentInstance().update("formDialogContrato");
	}
	
	public InputText getTxtConDosi() {
		return txtConDosi;
	}

	public void setTxtConDosi(InputText txtConDosi) {
		this.txtConDosi = txtConDosi;
	}

	public InputText getTxtConDura() {
		return txtConDura;
	}

	public void setTxtConDura(InputText txtConDura) {
		this.txtConDura = txtConDura;
	}

	public String getTxtConEst() {
		return txtConEst;
	}

	public void setTxtConEst(String txtConEst) {
		this.txtConEst = txtConEst;
	}

	public InputText getTxtConNroFac() {
		return txtConNroFac;
	}

	public void setTxtConNroFac(InputText txtConNroFac) {
		this.txtConNroFac = txtConNroFac;
	}

	public InputTextarea getTxtConObser() {
		return txtConObser;
	}

	public void setTxtConObser(InputTextarea txtConObser) {
		this.txtConObser = txtConObser;
	}

	public InputText getTxtConTipo() {
		return txtConTipo;
	}

	public void setTxtConTipo(InputText txtConTipo) {
		this.txtConTipo = txtConTipo;
	}

	public InputText getTxtConUsu() {
		return txtConUsu;
	}

	public void setTxtConUsu(InputText txtConUsu) {
		this.txtConUsu = txtConUsu;
	}

	public InputText getTxtConVal() {
		return txtConVal;
	}

	public void setTxtConVal(InputText txtConVal) {
		this.txtConVal = txtConVal;
	}

	public InputText getTxtDetConCod() {
		return txtDetConCod;
	}

	public void setTxtDetConCod(InputText txtDetConCod) {
		this.txtDetConCod = txtDetConCod;
	}

	public InputText getTxtSocCod() {
		return txtSocCod;
	}

	public void setTxtSocCod(InputText txtSocCod) {
		this.txtSocCod = txtSocCod;
	}

	public Calendar getTxtConFec() {
		return txtConFec;
	}

	public void setTxtConFec(Calendar txtConFec) {
		this.txtConFec = txtConFec;
	}

	public Calendar getTxtConFecFac() {
		return txtConFecFac;
	}

	public void setTxtConFecFac(Calendar txtConFecFac) {
		this.txtConFecFac = txtConFecFac;
	}

	public InputText getTxtConNro() {
		return txtConNro;
	}

	public void setTxtConNro(InputText txtConNro) {
		this.txtConNro = txtConNro;
	}

	public InputText getTxtEmpCod() {
		return txtEmpCod;
	}

	public void setTxtEmpCod(InputText txtEmpCod) {
		this.txtEmpCod = txtEmpCod;
	}

	public List<ContratosDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataContratos();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<ContratosDTO> contratosDTO) {
		this.data = contratosDTO;
	}

	public ContratosDTO getSelectedContratos() {
		return selectedContratos;
	}

	public void setSelectedContratos(ContratosDTO contratos) {
		this.selectedContratos = contratos;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @return the txtEmpCodTabla
	 */
	public InputText getTxtEmpCodTabla() {
		return txtEmpCodTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @param txtEmpCodTabla
	 *            the txtEmpCodTabla to set
	 */
	public void setTxtEmpCodTabla(InputText txtEmpCodTabla) {
		this.txtEmpCodTabla = txtEmpCodTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @return the txtRazonSocialTabla
	 */
	public InputText getTxtRazonSocialTabla() {
		return txtRazonSocialTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @param txtRazonSocialTabla
	 *            the txtRazonSocialTabla to set
	 */
	public void setTxtRazonSocialTabla(InputText txtRazonSocialTabla) {
		this.txtRazonSocialTabla = txtRazonSocialTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @return the empresasTabla
	 */
	public List<Empresas> getEmpresasTabla() {
		return empresasTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @param empresasTabla
	 *            the empresasTabla to set
	 */
	public void setEmpresasTabla(List<Empresas> empresasTabla) {
		this.empresasTabla = empresasTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @return the selectEmpresaTabla
	 */
	public Empresas getSelectEmpresaTabla() {
		return selectEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
	 * @date 14/12/2016
	 * @param selectEmpresaTabla
	 *            the selectEmpresaTabla to set
	 */
	public void setSelectEmpresaTabla(Empresas selectEmpresaTabla) {
		this.selectEmpresaTabla = selectEmpresaTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the cbOpcionEstado 
	 */
	public SelectOneMenu getCbOpcionEstado() {
		return cbOpcionEstado;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param cbOpcionEstado the cbOpcionEstado to set 
	 */
	public void setCbOpcionEstado(SelectOneMenu cbOpcionEstado) {
		this.cbOpcionEstado = cbOpcionEstado;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the botonGuardar 
	 */
	public boolean isBotonGuardar() {
		return botonGuardar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param botonGuardar the botonGuardar to set 
	 */
	public void setBotonGuardar(boolean botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the botonNuevo 
	 */
	public boolean isBotonNuevo() {
		return botonNuevo;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param botonNuevo the botonNuevo to set 
	 */
	public void setBotonNuevo(boolean botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the botonModificar 
	 */
	public boolean isBotonModificar() {
		return botonModificar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param botonModificar the botonModificar to set 
	 */
	public void setBotonModificar(boolean botonModificar) {
		this.botonModificar = botonModificar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the botonBorrar 
	 */
	public boolean isBotonBorrar() {
		return botonBorrar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param botonBorrar the botonBorrar to set 
	 */
	public void setBotonBorrar(boolean botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the botonVisualizar 
	 */
	public boolean isBotonVisualizar() {
		return botonVisualizar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param botonVisualizar the botonVisualizar to set 
	 */
	public void setBotonVisualizar(boolean botonVisualizar) {
		this.botonVisualizar = botonVisualizar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the botonDosimetros 
	 */
	public boolean isBotonDosimetros() {
		return botonDosimetros;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param botonDosimetros the botonDosimetros to set 
	 */
	public void setBotonDosimetros(boolean botonDosimetros) {
		this.botonDosimetros = botonDosimetros;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the botonTrabajadores 
	 */
	public boolean isBotonTrabajadores() {
		return botonTrabajadores;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param botonTrabajadores the botonTrabajadores to set 
	 */
	public void setBotonTrabajadores(boolean botonTrabajadores) {
		this.botonTrabajadores = botonTrabajadores;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the disableBotonesBusqueda 
	 */
	public boolean isDisableBotonesBusqueda() {
		return disableBotonesBusqueda;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param disableBotonesBusqueda the disableBotonesBusqueda to set 
	 */
	public void setDisableBotonesBusqueda(boolean disableBotonesBusqueda) {
		this.disableBotonesBusqueda = disableBotonesBusqueda;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the cbOpcionTipo 
	 */
	public SelectOneMenu getCbOpcionTipo() {
		return cbOpcionTipo;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param cbOpcionTipo the cbOpcionTipo to set 
	 */
	public void setCbOpcionTipo(SelectOneMenu cbOpcionTipo) {
		this.cbOpcionTipo = cbOpcionTipo;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the txtTipoFac 
	 */
	public String getTxtTipoFac() {
		return txtTipoFac;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param txtTipoFac the txtTipoFac to set 
	 */
	public void setTxtTipoFac(String txtTipoFac) {
		this.txtTipoFac = txtTipoFac;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the txtDescCodAsociado 
	 */
	public String getTxtDescCodAsociado() {
		return txtDescCodAsociado;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param txtDescCodAsociado the txtDescCodAsociado to set 
	 */
	public void setTxtDescCodAsociado(String txtDescCodAsociado) {
		this.txtDescCodAsociado = txtDescCodAsociado;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @return the txtConFecVen 
	 */
	public Calendar getTxtConFecVen() {
		return txtConFecVen;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/12/2016 
	 * @param txtConFecVen the txtConFecVen to set 
	 */
	public void setTxtConFecVen(Calendar txtConFecVen) {
		this.txtConFecVen = txtConFecVen;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the codEmpresa 
	 */
	public String getCodEmpresa() {
		return codEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param codEmpresa the codEmpresa to set 
	 */
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the codContrato 
	 */
	public String getCodContrato() {
		return codContrato;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param codContrato the codContrato to set 
	 */
	public void setCodContrato(String codContrato) {
		this.codContrato = codContrato;
	}
}

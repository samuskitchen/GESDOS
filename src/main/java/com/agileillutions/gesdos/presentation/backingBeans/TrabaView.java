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
import org.primefaces.event.RowEditEvent;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.dto.EmpresasDTO;
import com.agileillutions.gesdos.modelo.dto.TrabaDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class TrabaView implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputText txtTraApe1;
	private InputText txtTraApe2;
	private String txtTraEst;
	private InputText txtTraNom;
	private InputTextarea txtTraObs;
	private String txtTraSex;
	private InputText txtTraCed;
	private Calendar txtTraFecIni;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<TrabaDTO> data;
	private TrabaDTO selectedTraba;
	private Traba entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private SelectOneMenu cbOpcionEstado;
	private SelectOneMenu cbOpcionSexo;

	private boolean botonGuardar;
	private boolean botonNuevo;
	private boolean botonModificar;
	private boolean botonBorrar;
	private boolean botonVisualizar;
	private boolean botonDosimetros;
	private boolean botonTrabajadores;
	private boolean disableBotonesBusqueda;
	
	private String codEmpresa;
	private InputText razonSocialEmpresa;
	private List<Empresas> empresas;
	private EmpresasDTO selectEmpresa;
	private boolean renderedEmpresa;
	

	public TrabaView() {
		super();
	}

	@PostConstruct
	public void getParametrosEmpresa() {
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

			codEmpresa = requestParameterMap.get("codEmpresa");
			String cedTraba = requestParameterMap.get("cedTraba");
			String tipo = requestParameterMap.get("tipo");

			if(null != codEmpresa){
				renderedEmpresa = true;
				consultarEmpresas();
			}else{
				renderedEmpresa = false;
			}
			
			if ("2".equals(tipo)) {
				if (null != cedTraba) {
					txtTraCed = new InputText();
					txtTraCed.setValue(cedTraba);
					listener_txtId();
					modoVisualizar();
				}
			} else if ("1".equals(tipo)) {
				if (null != cedTraba) {
					txtTraCed = new InputText();
					txtTraCed.setValue(cedTraba);
					listener_txtId();
					modoModificar();
				}
			} else if("0".equals(tipo)){
				botonGuardar = true;
				botonModificar = false;
				botonBorrar = false;
				botonVisualizar = false;
				botonDosimetros = false;
				botonTrabajadores = false;
				disableBotonesBusqueda = false;
				//RequestContext.getCurrentInstance().update("formDialogTraba");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			TrabaDTO trabaDTO = (TrabaDTO) e.getObject();

			if (txtTraApe1 == null) {
				txtTraApe1 = new InputText();
			}

			txtTraApe1.setValue(trabaDTO.getTraApe1());

			if (txtTraApe2 == null) {
				txtTraApe2 = new InputText();
			}

			txtTraApe2.setValue(trabaDTO.getTraApe2());

			if (txtTraNom == null) {
				txtTraNom = new InputText();
			}

			txtTraNom.setValue(trabaDTO.getTraNom());

			if (txtTraObs == null) {
				txtTraObs = new InputTextarea();
			}

			txtTraObs.setValue(trabaDTO.getTraObs());

			if (txtTraCed == null) {
				txtTraCed = new InputText();
			}

			txtTraCed.setValue(trabaDTO.getTraCed());

			if (txtTraFecIni == null) {
				txtTraFecIni = new Calendar();
			}

			txtTraFecIni.setValue(trabaDTO.getTraFecIni());

			Long traCed = FacesUtils.checkLong(txtTraCed);
			entity = businessDelegatorView.getTraba(traCed);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedTraba = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		//selectedTraba = null;

		if (txtTraApe1 != null) {
			txtTraApe1.setValue(null);
		}

		if (txtTraApe2 != null) {
			txtTraApe2.setValue(null);
		}

		if (txtTraNom != null) {
			txtTraNom.setValue(null);
		}

		if (txtTraObs != null) {
			txtTraObs.setValue(null);
		}

		if (txtTraFecIni != null) {
			txtTraFecIni.setValue(null);
		}

		if (txtTraCed != null) {
			txtTraCed.setValue(null);
		}

		if (null != cbOpcionEstado) {
			cbOpcionEstado.resetValue();
		}

		if (null != cbOpcionSexo) {
			cbOpcionSexo.resetValue();
		}
		
		return "";
	}

	public void listener_txtTraFecIni() {
		Date inputDate = (Date) txtTraFecIni.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Selected Date "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Long traCed = FacesUtils.checkLong(txtTraCed);
			entity = (traCed != null) ? businessDelegatorView.getTraba(traCed)
					: null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtTraApe1.setDisabled(false);
			txtTraApe2.setDisabled(false);
			txtTraNom.setDisabled(false);
			txtTraObs.setDisabled(false);
			txtTraFecIni.setDisabled(false);
			txtTraCed.setDisabled(false);
			btnSave.setDisabled(false);
			cbOpcionSexo.setDisabled(false);
			cbOpcionEstado.setDisabled(false);
		} else {
			txtTraApe1.setValue(entity.getTraApe1());
			txtTraApe2.setValue(entity.getTraApe2());
			cbOpcionEstado.setValue(entity.getTraEst());
			txtTraFecIni.setValue(entity.getTraFecIni());
			txtTraNom.setValue(entity.getTraNom());
			txtTraObs.setValue(entity.getTraObs());
			cbOpcionSexo.setValue(entity.getTraSex());
			txtTraCed.setValue(entity.getTraCed());
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedTraba = (TrabaDTO) (evt.getComponent().getAttributes()
				.get("selectedTraba"));
		txtTraApe1.setValue(selectedTraba.getTraApe1());
		txtTraApe1.setDisabled(false);
		txtTraApe2.setValue(selectedTraba.getTraApe2());
		txtTraApe2.setDisabled(false);
		cbOpcionEstado.setValue(selectedTraba.getTraEst());
		cbOpcionEstado.setDisabled(false);
		txtTraFecIni.setValue(selectedTraba.getTraFecIni());
		txtTraFecIni.setDisabled(false);
		txtTraNom.setValue(selectedTraba.getTraNom());
		txtTraNom.setDisabled(false);
		txtTraObs.setValue(selectedTraba.getTraObs());
		txtTraObs.setDisabled(false);
		cbOpcionSexo.setValue(selectedTraba.getTraSex());
		cbOpcionSexo.setDisabled(false);
		txtTraCed.setValue(selectedTraba.getTraCed());
		txtTraCed.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedTraba == null) && (entity == null)) {
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
			entity = new Traba();

			Long traCed = FacesUtils.checkLong(txtTraCed);

			if (null != traCed) {
				entity.setTraApe1(FacesUtils.checkString(txtTraApe1));
				entity.setTraApe2(FacesUtils.checkString(txtTraApe2));
				entity.setTraCed(traCed);
				entity.setTraEst(txtTraEst);
				entity.setTraFecIni(FacesUtils.checkDate(txtTraFecIni));
				entity.setTraNom(FacesUtils.checkString(txtTraNom));
				entity.setTraObs(FacesUtils.checkString(txtTraObs));
				entity.setTraSex(txtTraSex);
				businessDelegatorView.saveTraba(entity);
				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
				action_clear();
			}
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Long traCed = FacesUtils.checkLong(txtTraCed);
				entity = businessDelegatorView.getTraba(traCed);
			}

			entity.setTraApe1(FacesUtils.checkString(txtTraApe1));
			entity.setTraApe2(FacesUtils.checkString(txtTraApe2));
			entity.setTraEst(txtTraEst);
			entity.setTraFecIni(FacesUtils.checkDate(txtTraFecIni));
			entity.setTraNom(FacesUtils.checkString(txtTraNom));
			entity.setTraObs(FacesUtils.checkString(txtTraObs));
			entity.setTraSex(txtTraSex);
			businessDelegatorView.updateTraba(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable() {
		try {
			Long traCed = new Long(selectedTraba.getTraCed());
			entity = businessDelegatorView.getTraba(traCed);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long traCed = FacesUtils.checkLong(txtTraCed);
			entity = businessDelegatorView.getTraba(traCed);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteTraba(entity);
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
			selectedTraba = (TrabaDTO) (evt.getComponent().getAttributes()
					.get("selectedTraba"));

			Long traCed = new Long(selectedTraba.getTraCed());
			entity = businessDelegatorView.getTraba(traCed);
			businessDelegatorView.deleteTraba(entity);
			data.remove(selectedTraba);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String traApe1, String traApe2,
			Long traCed, String traEst, Date traFecIni, String traNom,
			String traObs, String traSex) throws Exception {
		try {
			entity.setTraApe1(FacesUtils.checkString(traApe1));
			entity.setTraApe2(FacesUtils.checkString(traApe2));
			entity.setTraEst(FacesUtils.checkString(traEst));
			entity.setTraFecIni(FacesUtils.checkDate(traFecIni));
			entity.setTraNom(FacesUtils.checkString(traNom));
			entity.setTraObs(FacesUtils.checkString(traObs));
			entity.setTraSex(FacesUtils.checkString(traSex));
			businessDelegatorView.updateTraba(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("TrabaView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description 
	 * @param id
	 * @param campoDeClase
	 * @param boleana
	 * @return
	 */
	public Object[] searchByCriteria(String id, String campoDeClase,
			boolean boleana) {
		Object object[] = new Object[4];
		object[0] = campoDeClase;
		object[1] = boleana;
		object[2] = id;
		object[3] = "like";
		return object;
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description
	 */
	public void actionCerrarBusqueda() {
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarEmpresas() {
		try {
			
			if (null != razonSocialEmpresa && null != razonSocialEmpresa.getValue()) {
				String valorDepartamento = razonSocialEmpresa.getValue().toString();
				Object[] variablesDepartamento = searchByCriteria(
						valorDepartamento, "EmpRazSoc", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesDepartamento, null,
								null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						razonSocialEmpresa.setValue(emp.getEmpRazSoc());

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmEmpleado:razonSocialEmpresa");
						context.update(s);
					}
				}
			}else if(null != codEmpresa && !codEmpresa.isEmpty()){
				Object[] variablesDepartamento = searchByCriteria(
						codEmpresa, "empCod", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesDepartamento, null,
								null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						razonSocialEmpresa.setValue(emp.getEmpRazSoc());

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmEmpleado:razonSocialEmpresa");
						context.update(s);
					}
				}
			} 
			
			else {
				empresas = businessDelegatorView.getEmpresas();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectEmpresa() {

		if (selectEmpresa != null) {

			EmpresasDTO empresas = selectEmpresa;

			if (empresas != null && empresas.getEmpCod() != null) {
				razonSocialEmpresa.setValue(empresas.getEmpRazSoc());

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("frmEmpleado:razonSocialEmpresa");
				context.update(s);
			}
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresa() {
		razonSocialEmpresa.resetValue();

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("frmEmpleado:razonSocialEmpresa");
		context.update(s);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description 
	 * @param bandera
	 */
	public void cargarTrabaDialog(String bandera) {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("contentWidth", "'100%'");
		options.put("contentheight", 700);
		options.put("width", "'100%'");
		options.put("height", 700);
		options.put("position", "top");
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("closeOnEscape", true);
		options.put("dynamic", false);

		String cedTraba = "";
		if(null != selectedTraba){
			cedTraba = selectedTraba.getTraCed().toString();			
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(cedTraba);
		
		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("cedTraba", lstParamEmpresa);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/traba.xhtml",
				options, params);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description
	 */
	public void inicializaCampos(){
		
		if(btnSave == null){
			btnSave = new CommandButton();
		}
		
		if(btnModify == null){
			btnModify = new CommandButton();
		}
		
		if(btnDelete == null){
			btnDelete = new CommandButton();
		}
		
		if(btnClear == null){
			btnClear = new CommandButton();
		}
		
		if (null == cbOpcionEstado) {
			cbOpcionEstado = new SelectOneMenu();
		}
		
		if (null == cbOpcionSexo) {
			cbOpcionSexo = new SelectOneMenu();
		}
		
		if(null == razonSocialEmpresa){
			razonSocialEmpresa =  new InputText();
		}
		
		if (txtTraApe1 == null) {
			txtTraApe1 = new InputText();
		}

		if (txtTraApe2 == null) {
			txtTraApe2 = new InputText();
		}

		if (txtTraNom == null) {
			txtTraNom = new InputText();
		}

		if (txtTraObs == null) {
			txtTraObs = new InputTextarea();
		}

		if (txtTraCed == null) {
			txtTraCed = new InputText();
		}

		if (txtTraFecIni == null) {
			txtTraFecIni = new Calendar();
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description
	 */
	public void modoVisualizar(){
		
		botonGuardar = false;
		botonModificar = false;
		botonBorrar = false;
		botonVisualizar = false;
		botonDosimetros = false;
		botonTrabajadores = false;
		disableBotonesBusqueda = true;
		
		txtTraApe1.setDisabled(true);
		txtTraApe2.setDisabled(true);
		txtTraNom.setDisabled(true);
		txtTraObs.setDisabled(true);
		txtTraFecIni.setDisabled(true);
		txtTraCed.setDisabled(true);
		btnSave.setDisabled(true);
		cbOpcionSexo.setDisabled(true);
		cbOpcionEstado.setDisabled(true);
		
		RequestContext.getCurrentInstance().update("formDialogTraba");
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description
	 */
	public void modoModificar(){
		
		botonGuardar = false;
		botonModificar = true;
		botonBorrar = false;
		botonVisualizar = false;
		botonDosimetros = false;
		botonTrabajadores = false;
		disableBotonesBusqueda = false;
		
		txtTraApe1.setDisabled(false);
		txtTraApe2.setDisabled(false);
		txtTraNom.setDisabled(false);
		txtTraObs.setDisabled(false);
		txtTraFecIni.setDisabled(false);
		txtTraCed.setDisabled(false);
		btnSave.setDisabled(false);
		cbOpcionSexo.setDisabled(false);
		cbOpcionEstado.setDisabled(false);
		
		RequestContext.getCurrentInstance().update("formDialogTraba");
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description 
	 * @throws Exception
	 */
	public void closeDialog() throws Exception {
		data = businessDelegatorView.getDataTraba();
		RequestContext.getCurrentInstance().update("frmEmpleado");
    }
	
	public InputText getTxtTraApe1() {
		return txtTraApe1;
	}

	public void setTxtTraApe1(InputText txtTraApe1) {
		this.txtTraApe1 = txtTraApe1;
	}

	public InputText getTxtTraApe2() {
		return txtTraApe2;
	}

	public void setTxtTraApe2(InputText txtTraApe2) {
		this.txtTraApe2 = txtTraApe2;
	}

	public String getTxtTraEst() {
		return txtTraEst;
	}

	public void setTxtTraEst(String txtTraEst) {
		this.txtTraEst = txtTraEst;
	}

	public InputText getTxtTraNom() {
		return txtTraNom;
	}

	public void setTxtTraNom(InputText txtTraNom) {
		this.txtTraNom = txtTraNom;
	}

	public InputTextarea getTxtTraObs() {
		return txtTraObs;
	}

	public void setTxtTraObs(InputTextarea txtTraObs) {
		this.txtTraObs = txtTraObs;
	}

	public String getTxtTraSex() {
		return txtTraSex;
	}

	public void setTxtTraSex(String txtTraSex) {
		this.txtTraSex = txtTraSex;
	}

	public Calendar getTxtTraFecIni() {
		return txtTraFecIni;
	}

	public void setTxtTraFecIni(Calendar txtTraFecIni) {
		this.txtTraFecIni = txtTraFecIni;
	}

	public InputText getTxtTraCed() {
		return txtTraCed;
	}

	public void setTxtTraCed(InputText txtTraCed) {
		this.txtTraCed = txtTraCed;
	}

	public List<TrabaDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataTraba();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<TrabaDTO> trabaDTO) {
		this.data = trabaDTO;
	}

	public TrabaDTO getSelectedTraba() {
		return selectedTraba;
	}

	public void setSelectedTraba(TrabaDTO traba) {
		this.selectedTraba = traba;
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

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the cbOpcionEstado
	 */
	public SelectOneMenu getCbOpcionEstado() {
		return cbOpcionEstado;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param cbOpcionEstado
	 *            the cbOpcionEstado to set
	 */
	public void setCbOpcionEstado(SelectOneMenu cbOpcionEstado) {
		this.cbOpcionEstado = cbOpcionEstado;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the cbOpcionSexo
	 */
	public SelectOneMenu getCbOpcionSexo() {
		return cbOpcionSexo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param cbOpcionSexo
	 *            the cbOpcionSexo to set
	 */
	public void setCbOpcionSexo(SelectOneMenu cbOpcionSexo) {
		this.cbOpcionSexo = cbOpcionSexo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the botonGuardar
	 */
	public boolean isBotonGuardar() {
		return botonGuardar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param botonGuardar
	 *            the botonGuardar to set
	 */
	public void setBotonGuardar(boolean botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the botonModificar
	 */
	public boolean isBotonModificar() {
		return botonModificar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param botonModificar
	 *            the botonModificar to set
	 */
	public void setBotonModificar(boolean botonModificar) {
		this.botonModificar = botonModificar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the botonBorrar
	 */
	public boolean isBotonBorrar() {
		return botonBorrar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param botonBorrar
	 *            the botonBorrar to set
	 */
	public void setBotonBorrar(boolean botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the botonVisualizar
	 */
	public boolean isBotonVisualizar() {
		return botonVisualizar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param botonVisualizar
	 *            the botonVisualizar to set
	 */
	public void setBotonVisualizar(boolean botonVisualizar) {
		this.botonVisualizar = botonVisualizar;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the botonDosimetros
	 */
	public boolean isBotonDosimetros() {
		return botonDosimetros;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param botonDosimetros
	 *            the botonDosimetros to set
	 */
	public void setBotonDosimetros(boolean botonDosimetros) {
		this.botonDosimetros = botonDosimetros;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the botonTrabajadores
	 */
	public boolean isBotonTrabajadores() {
		return botonTrabajadores;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param botonTrabajadores
	 *            the botonTrabajadores to set
	 */
	public void setBotonTrabajadores(boolean botonTrabajadores) {
		this.botonTrabajadores = botonTrabajadores;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the disableBotonesBusqueda
	 */
	public boolean isDisableBotonesBusqueda() {
		return disableBotonesBusqueda;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param disableBotonesBusqueda
	 *            the disableBotonesBusqueda to set
	 */
	public void setDisableBotonesBusqueda(boolean disableBotonesBusqueda) {
		this.disableBotonesBusqueda = disableBotonesBusqueda;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the codEmpresa 
	 */
	public String getCodEmpresa() {
		return codEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param codEmpresa the codEmpresa to set 
	 */
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the razonSocialEmpresa 
	 */
	public InputText getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param razonSocialEmpresa the razonSocialEmpresa to set 
	 */
	public void setRazonSocialEmpresa(InputText razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the empresas 
	 */
	public List<Empresas> getEmpresas() {
		return empresas;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param empresas the empresas to set 
	 */
	public void setEmpresas(List<Empresas> empresas) {
		this.empresas = empresas;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the selectEmpresa 
	 */
	public EmpresasDTO getSelectEmpresa() {
		return selectEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param selectEmpresa the selectEmpresa to set 
	 */
	public void setSelectEmpresa(EmpresasDTO selectEmpresa) {
		this.selectEmpresa = selectEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the renderedEmpresa 
	 */
	public boolean isRenderedEmpresa() {
		return renderedEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param renderedEmpresa the renderedEmpresa to set 
	 */
	public void setRenderedEmpresa(boolean renderedEmpresa) {
		this.renderedEmpresa = renderedEmpresa;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the botonNuevo 
	 */
	public boolean isBotonNuevo() {
		return botonNuevo;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param botonNuevo the botonNuevo to set 
	 */
	public void setBotonNuevo(boolean botonNuevo) {
		this.botonNuevo = botonNuevo;
	}
}

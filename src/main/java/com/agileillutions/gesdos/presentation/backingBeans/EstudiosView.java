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
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.Estudios;
import com.agileillutions.gesdos.modelo.EstudiosId;
import com.agileillutions.gesdos.modelo.Observa;
import com.agileillutions.gesdos.modelo.Revelado;
import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.dto.EstudiosDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean(name = "estudiosView")
@ViewScoped
public class EstudiosView implements Serializable {
	private static final long serialVersionUID = 1L;
	private Spinner txtEstDos;
	private InputText txtEstMesAct;
	private String txtEstRec;
	private InputText txtObsCod;
	private InputText txtRevNro;
	private InputText txtTraCed;
	private InputText txtEmpCod;
	private InputText txtDosCod;
	private InputText txtEstAni;
	private InputText txtEstMes;
	private Calendar txtEstFecRec;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<EstudiosDTO> data;
	private EstudiosDTO selectedEstudios;
	private Estudios entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private InputText txtEmpCodTabla;
	private String codEmpresa;
	private InputText razonSocialEmpresa;
	private List<Empresas> empresas;
	private Empresas selectEmpresa;
	private boolean renderedEmpresa;

	private String traCed;
	private InputText traNombre;
	private List<Traba> trabajadores;
	private Traba selectedTrabajador;

	private boolean botonGuardar;
	private boolean botonNuevo;
	private boolean botonModificar;
	private boolean botonBorrar;
	private boolean botonVisualizar;
	private boolean botonDosimetros;
	private boolean botonTrabajadores;
	private boolean disableBotonesBusqueda;

	private Calendar fechaNumRevelado;
	private List<Revelado> revelados;
	private Revelado selectedRevelado;

	private InputText obsDescripcion;
	private List<Observa> observaciones;
	private Observa selectedObservacion;
	
	private SelectOneMenu cbOpcionEstado;
	
	private String codDosimetro;
	private InputText txtTraCedTabla;

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
			String codTrabajador = requestParameterMap.get("codTrabajador");
			String codDosimetro = requestParameterMap.get("codDosimetro");
			String estAni = requestParameterMap.get("estAni");
			String estMes = requestParameterMap.get("estMes");
			String tipo = requestParameterMap.get("tipo");
			
			this.codEmpresa = codEmpresa;
			this.traCed = codTrabajador;
			this.codDosimetro = codDosimetro;

			if (null == tipo || "".equals(tipo)) {
				txtTraCed = new InputText();
				txtEmpCod = new InputText();

				txtTraCedTabla.setValue(codTrabajador);
				txtTraCed.setValue(codTrabajador);
				txtEmpCod.setValue(codEmpresa);
				txtEmpCodTabla.setValue(codEmpresa);

				if ((null != codEmpresa && !codEmpresa.isEmpty())
						&& (null != codTrabajador && !codTrabajador.isEmpty())) {
					List<EstudiosDTO> estudiosDTOs = businessDelegatorView
							.getDataEstudiosParam(Long.valueOf(codEmpresa),
									Long.valueOf(codTrabajador));
					data = estudiosDTOs;
				}
				ejecutarControlesBusquedas();
			}
			// Tipo Visualizar
			else if ("2".equals(tipo)) {
				if (null != codTrabajador && null != codDosimetro
						&& null != codEmpresa) {
					txtTraCed = new InputText();
					txtEmpCod = new InputText();
					txtDosCod = new InputText();
					txtEstAni = new InputText();
					txtEstMes = new InputText();

					txtTraCed.setValue(codTrabajador);
					txtEmpCod.setValue(codEmpresa);
					txtDosCod.setValue(codDosimetro);
					txtEstAni.setValue(estAni);
					txtEstMes.setValue(estMes);

					listener_txtId();
					modoVisualizar();
					ejecutarControlesBusquedas();
				}
			}
			// Tipo Modificar
			else if ("1".equals(tipo)) {
				if (null != codTrabajador && null != codDosimetro
						&& null != codEmpresa) {
					txtTraCed = new InputText();
					txtEmpCod = new InputText();
					txtDosCod = new InputText();
					txtEstAni = new InputText();
					txtEstMes = new InputText();

					txtTraCed.setValue(codTrabajador);
					txtEmpCod.setValue(codEmpresa);
					txtDosCod.setValue(codDosimetro);
					txtEstAni.setValue(estAni);
					txtEstMes.setValue(estMes);
					listener_txtId();
					modoModificar();
					ejecutarControlesBusquedas();
				}
			}
			// Tipo Crear
			else if ("0".equals(tipo)) {
				
				if(txtTraCed.getValue() == null){
					txtTraCed.setValue(this.traCed);
				}
				
				if(txtEmpCod.getValue() == null){
					txtEmpCod.setValue(this.codEmpresa);
				}
				
				if(txtDosCod.getValue() == null){
					txtDosCod.setValue(this.codDosimetro);
				}

				botonGuardar = true;
				botonModificar = false;
				botonBorrar = false;
				botonVisualizar = false;
				botonDosimetros = false;
				botonTrabajadores = false;
				disableBotonesBusqueda = false;
				txtTraCed.setDisabled(true);
				txtDosCod.setDisabled(true);
				
				ejecutarControlesBusquedas();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EstudiosView() {
		super();
	}

	public String action_new() {
		action_clear();
		selectedEstudios = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedEstudios = null;

		if (txtEstDos != null) {
			txtEstDos.setValue(null);
			txtEstDos.setDisabled(true);
		}

		if (txtEstMesAct != null) {
			txtEstMesAct.setValue(null);
			txtEstMesAct.setDisabled(true);
		}

		if (txtObsCod != null) {
			txtObsCod.setValue(null);
			txtObsCod.setDisabled(true);
		}

		if (txtRevNro != null) {
			txtRevNro.setValue(null);
			txtRevNro.setDisabled(true);
		}

		if (txtEstFecRec != null) {
			txtEstFecRec.setValue(null);
			txtEstFecRec.setDisabled(true);
		}

		if (txtTraCed != null) {
			txtTraCed.setValue(null);
			txtTraCed.setDisabled(false);
		}

		if (txtEmpCod != null) {
			txtEmpCod.setValue(null);
			txtEmpCod.setDisabled(false);
		}

		if (txtDosCod != null) {
			txtDosCod.setValue(null);
			txtDosCod.setDisabled(false);
		}

		if (txtEstAni != null) {
			txtEstAni.setValue(null);
			txtEstAni.setDisabled(false);
		}

		if (txtEstMes != null) {
			txtEstMes.setValue(null);
			txtEstMes.setDisabled(false);
		}

		if (btnSave != null) {
			btnSave.setDisabled(true);
		}

		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}

		return "";
	}

	public void listener_txtEstFecRec() {
		Date inputDate = (Date) txtEstFecRec.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Selected Date "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			EstudiosId id = new EstudiosId();
			id.setTraCed((((txtTraCed.getValue()) == null) || (txtTraCed
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtTraCed));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEmpCod));
			id.setDosCod((((txtDosCod.getValue()) == null) || (txtDosCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosCod));
			id.setEstAni((((txtEstAni.getValue()) == null) || (txtEstAni
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEstAni));
			id.setEstMes((((txtEstMes.getValue()) == null) || (txtEstMes
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEstMes));
			entity = (id != null) ? businessDelegatorView.getEstudios(id)
					: null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtEstDos.setDisabled(false);
			txtEstMesAct.setDisabled(false);
			cbOpcionEstado.setDisabled(false);
			txtObsCod.setDisabled(false);
			txtRevNro.setDisabled(false);
			txtEstFecRec.setDisabled(false);
			txtTraCed.setDisabled(false);
			txtEmpCod.setDisabled(false);
			txtDosCod.setDisabled(false);
			txtEstAni.setDisabled(false);
			txtEstMes.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtEstDos.setValue(entity.getEstDos());
			txtEstFecRec.setValue(entity.getEstFecRec());
			txtEstMesAct.setValue(entity.getEstMesAct());
			txtEstRec = entity.getEstRec();
			cbOpcionEstado.setValue(entity.getEstRec());
			txtObsCod.setValue(entity.getObsCod());
			txtRevNro.setValue(entity.getRevNro());
			txtTraCed.setValue(entity.getId().getTraCed());
			txtEmpCod.setValue(entity.getId().getEmpCod());
			txtDosCod.setValue(entity.getId().getDosCod());
			txtEstAni.setValue(entity.getId().getEstAni());
			txtEstMes.setValue(entity.getId().getEstMes());
			ejecutarControlesBusquedas();
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedEstudios = (EstudiosDTO) (evt.getComponent().getAttributes()
				.get("selectedEstudios"));
		txtEstDos.setValue(selectedEstudios.getEstDos());
		txtEstDos.setDisabled(false);
		txtEstFecRec.setValue(selectedEstudios.getEstFecRec());
		txtEstFecRec.setDisabled(false);
		txtEstMesAct.setValue(selectedEstudios.getEstMesAct());
		txtEstMesAct.setDisabled(false);
		setTxtEstRec(selectedEstudios.getEstRec().toString());
		txtObsCod.setValue(selectedEstudios.getObsCod());
		txtObsCod.setDisabled(false);
		txtRevNro.setValue(selectedEstudios.getRevNro());
		txtRevNro.setDisabled(false);
		txtTraCed.setValue(selectedEstudios.getTraCed());
		txtTraCed.setDisabled(true);
		txtEmpCod.setValue(selectedEstudios.getEmpCod());
		txtEmpCod.setDisabled(true);
		txtDosCod.setValue(selectedEstudios.getDosCod());
		txtDosCod.setDisabled(true);
		txtEstAni.setValue(selectedEstudios.getEstAni());
		txtEstAni.setDisabled(true);
		txtEstMes.setValue(selectedEstudios.getEstMes());
		txtEstMes.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedEstudios == null) && (entity == null)) {
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
			entity = new Estudios();

			EstudiosId id = new EstudiosId();
			id.setTraCed((((txtTraCed.getValue()) == null) || (txtTraCed
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtTraCed));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEmpCod));
			id.setDosCod((((txtDosCod.getValue()) == null) || (txtDosCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosCod));
			id.setEstAni((((txtEstAni.getValue()) == null) || (txtEstAni
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEstAni));
			id.setEstMes((((txtEstMes.getValue()) == null) || (txtEstMes
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEstMes));

			txtEstFecRec.setValue(new Date());
			entity.setEstDos(FacesUtils.checkDouble(txtEstDos));
			entity.setEstFecRec(FacesUtils.checkDate(txtEstFecRec));
			entity.setEstMesAct(FacesUtils.checkLong(txtEstMesAct));
			entity.setEstRec(txtEstRec);
			entity.setId(id);
			entity.setObsCod(FacesUtils.checkString(txtObsCod));
			entity.setRevNro(FacesUtils.checkLong(txtRevNro));
			businessDelegatorView.saveEstudios(entity);
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
				EstudiosId id = new EstudiosId();
				id.setTraCed(selectedEstudios.getTraCed());
				id.setEmpCod(selectedEstudios.getEmpCod());
				id.setDosCod(selectedEstudios.getDosCod());
				id.setEstAni(selectedEstudios.getEstAni());
				id.setEstMes(selectedEstudios.getEstMes());
				entity = businessDelegatorView.getEstudios(id);
			}

			entity.setEstDos(FacesUtils.checkDouble(txtEstDos));
			entity.setEstFecRec(FacesUtils.checkDate(txtEstFecRec));
			entity.setEstMesAct(FacesUtils.checkLong(txtEstMesAct));
			entity.setEstRec(txtEstRec);
			entity.setObsCod(FacesUtils.checkString(txtObsCod));
			entity.setRevNro(FacesUtils.checkLong(txtRevNro));
			businessDelegatorView.updateEstudios(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable() {
		try {
			EstudiosId id = new EstudiosId();
			id.setTraCed(selectedEstudios.getTraCed());
			id.setEmpCod(selectedEstudios.getEmpCod());
			id.setDosCod(selectedEstudios.getDosCod());
			id.setEstAni(selectedEstudios.getEstAni());
			id.setEstMes(selectedEstudios.getEstMes());
			entity = businessDelegatorView.getEstudios(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			EstudiosId id = new EstudiosId();
			id.setTraCed((((txtTraCed.getValue()) == null) || (txtTraCed
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtTraCed));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEmpCod));
			id.setDosCod((((txtDosCod.getValue()) == null) || (txtDosCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosCod));
			id.setEstAni((((txtEstAni.getValue()) == null) || (txtEstAni
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEstAni));
			id.setEstMes((((txtEstMes.getValue()) == null) || (txtEstMes
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEstMes));
			entity = businessDelegatorView.getEstudios(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteEstudios(entity);
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
			selectedEstudios = (EstudiosDTO) (evt.getComponent()
					.getAttributes().get("selectedEstudios"));

			EstudiosId id = new EstudiosId();
			id.setTraCed(selectedEstudios.getTraCed());
			id.setEmpCod(selectedEstudios.getEmpCod());
			id.setDosCod(selectedEstudios.getDosCod());
			id.setEstAni(selectedEstudios.getEstAni());
			id.setEstMes(selectedEstudios.getEstMes());
			entity = businessDelegatorView.getEstudios(id);
			businessDelegatorView.deleteEstudios(entity);
			data.remove(selectedEstudios);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long traCed, Long empCod, Long dosCod,
			Long estAni, Long estMes, Double estDos, Date estFecRec,
			Long estMesAct, String estRec, String obsCod, Long revNro)
			throws Exception {
		try {
			entity.setEstDos(FacesUtils.checkDouble(estDos));
			entity.setEstFecRec(FacesUtils.checkDate(estFecRec));
			entity.setEstMesAct(FacesUtils.checkLong(estMesAct));
			entity.setEstRec(FacesUtils.checkString(estRec));
			entity.setObsCod(FacesUtils.checkString(obsCod));
			entity.setRevNro(FacesUtils.checkLong(revNro));
			businessDelegatorView.updateEstudios(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("EstudiosView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void consultarEmpresas() {
		try {

			if (null != razonSocialEmpresa
					&& null != razonSocialEmpresa.getValue()
					&& !razonSocialEmpresa.getValue().toString().isEmpty()) {
				String valorDepartamento = razonSocialEmpresa.getValue()
						.toString();
				Object[] variablesEmpresa = searchByCriteriaRazonSocial("%"
						+ valorDepartamento + "%", "empRazSoc", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCod.setValue(emp.getEmpCod());
						txtEmpCod.setDisabled(true);
						razonSocialEmpresa.setValue(emp.getEmpRazSoc());
						razonSocialEmpresa.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogEstudio:txtEmpCod");
						s.add("formDialogEstudio:txtRazonSocialEmpresa");
						context.update(s);
					}
				}
			} else if (null != txtEmpCod
					&& null != txtEmpCod.getValue()
					&& !txtEmpCod.getValue().toString().isEmpty()) {
				Object[] variablesEmpresa = searchByCriteria(txtEmpCod
						.getValue().toString(), "empCod", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCod.setValue(emp.getEmpCod());
						txtEmpCod.setDisabled(true);
						razonSocialEmpresa.setValue(emp.getEmpRazSoc());
						razonSocialEmpresa.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogEstudio:txtEmpCod");
						s.add("formDialogEstudio:txtRazonSocialEmpresa");
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
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void consultarEmpresasTabla() {
		try {
			if (null != txtEmpCodTabla && null != txtEmpCodTabla.getValue()
					&& !txtEmpCodTabla.getValue().toString().isEmpty()) {
				Object[] variablesEmpresa = searchByCriteria(txtEmpCodTabla
						.getValue().toString(), "empCod", true);
				List<Empresas> empresasList = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresasList && empresasList.size() > 0) {
					Empresas emp = empresasList.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCodTabla.setValue(emp.getEmpCod());
						razonSocialEmpresa.setValue(emp.getEmpRazSoc());
						razonSocialEmpresa.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmEstudio:txtEmpCodTabla");
						s.add("frmEstudio:txtRazonSocialEmpresa");
						context.update(s);
					}
				} else {
					empresas = businessDelegatorView.getEmpresas();
				}
			} else {
				empresas = businessDelegatorView.getEmpresas();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void consultarTrabajador() {
		try {
			if (null != txtTraCed.getValue()) {
				Object[] variablesTrabaja = searchByCriteria(txtTraCed
						.getValue().toString(), "traCed", true);
				List<Traba> trabas = businessDelegatorView
						.findByCriteriaInTraba(variablesTrabaja, null, null);

				if (null != trabas && trabas.size() > 0) {
					Traba traba = trabas.get(0);
					if (traba != null && traba.getTraCed() != null) {
						String SegundoApellido = traba.getTraApe2() != null ? traba
								.getTraApe2() : "";

						txtTraCed.setValue(traba.getTraCed());
						txtTraCed.setDisabled(true);

						traNombre.setValue(traba.getTraNom().toString() + " "
								+ traba.getTraApe1().toString() + " "
								+ SegundoApellido);
						traNombre.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtTraCed");
						s.add("formDialogDosimetro:txtNombreTrabajador");
						context.update(s);
					}
				} else {
					trabajadores = businessDelegatorView.getTraba();
				}
			} else {
				trabajadores = businessDelegatorView.getTraba();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void consultarTrabajadorTabla() {
		try {
			if (null != txtTraCedTabla.getValue()) {
				Object[] variablesTrabaja = searchByCriteria(txtTraCedTabla
						.getValue().toString(), "traCed", true);
				List<Traba> trabas = businessDelegatorView
						.findByCriteriaInTraba(variablesTrabaja, null, null);

				if (null != trabas && trabas.size() > 0) {
					Traba traba = trabas.get(0);
					if (traba != null && traba.getTraCed() != null) {
						String SegundoApellido = traba.getTraApe2() != null ? traba
								.getTraApe2() : "";

						txtTraCedTabla.setValue(traba.getTraCed());
						txtTraCedTabla.setDisabled(false);
						
						traNombre.setValue(traba.getTraNom().toString() + " "
								+ traba.getTraApe1().toString() + " "
								+ SegundoApellido);
						traNombre.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtTraCedTabla");
						s.add("formDialogDosimetro:txtNombreTrabajador");
						context.update(s);
					}
				} else {
					trabajadores = businessDelegatorView.getTraba();
				}
			} else {
				trabajadores = businessDelegatorView.getTraba();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void consultarRevelado() {
		try {
			if (null != txtRevNro.getValue()) {
				Object[] variablesRevelado = searchByCriteria(txtRevNro
						.getValue().toString(), "revNro", true);
				List<Revelado> reveladosList = businessDelegatorView
						.findByCriteriaInRevelado(variablesRevelado, null, null);

				if (null != reveladosList && reveladosList.size() > 0) {
					Revelado revelado = reveladosList.get(0);
					if (revelado != null && revelado.getRevNro() != null) {

						txtRevNro.setValue(revelado.getRevNro());
						fechaNumRevelado.setValue(revelado.getRevFec());
						fechaNumRevelado.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogEstudio:txtRevNro");
						s.add("formDialogEstudio:fechaNumRevelado");
						context.update(s);
					}
				} else {
					revelados = businessDelegatorView.getRevelado();
				}
			} else {
				revelados = businessDelegatorView.getRevelado();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void consultarObservacion() {
		try {
			if (null != txtObsCod.getValue()) {
				Object[] variablesObserva = searchByCriteria(txtObsCod
						.getValue().toString(), "obsCod", true);
				List<Observa> observasList = businessDelegatorView
						.findByCriteriaInObserva(variablesObserva, null, null);

				if (null != observasList && observasList.size() > 0) {
					Observa observa = observasList.get(0);
					if (observa != null && observa.getObsCod() != null) {

						txtObsCod.setValue(observa.getObsCod());
						obsDescripcion.setValue(observa.getObsDes());
						obsDescripcion.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogEstudio:txtObsCod");
						s.add("formDialogEstudio:txtObsDescripcion");
						context.update(s);
					}
				} else {
					observaciones = businessDelegatorView.getObserva();
				}
			} else {
				observaciones = businessDelegatorView.getObserva();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void onRowSelectEmpresa() {

		if (selectEmpresa != null) {

			Empresas empresas = selectEmpresa;

			if (empresas != null && empresas.getEmpCod() != null) {
				txtEmpCod.setValue(empresas.getEmpCod());
				razonSocialEmpresa.setValue(empresas.getEmpRazSoc());
				razonSocialEmpresa.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogEstudio:txtEmpCod");
				s.add("formDialogEstudio:txtRazonSocialEmpresa");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 27/11/2016
	 * @description
	 * @throws Exception
	 */
	public void onRowSelectEmpresaTabla() throws Exception {

		if (selectEmpresa != null) {

			Empresas empresas = selectEmpresa;

			if (empresas != null && empresas.getEmpCod() != null) {
				txtEmpCodTabla.setValue(empresas.getEmpCod());
				razonSocialEmpresa.setValue(empresas.getEmpRazSoc());

				List<EstudiosDTO> estudiosDTOs = businessDelegatorView
						.getDataEstudiosParam(empresas.getEmpCod(), null);
				data = estudiosDTOs;

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("frmEstudio:txtEmpCodTabla");
				s.add("frmEstudio:txtRazonSocialEmpresa");
				s.add("frmEstudio:idDataTableEstudio");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 27/11/2016
	 * @description
	 * @throws Exception
	 */
	public void onRowSelectTrajabadorTabla() throws Exception {

		if (selectedTrabajador != null) {

			Traba traba = selectedTrabajador;

			if (traba != null && traba.getTraCed() != null) {

				String nombre = traba.getTraNom() != null ? traba.getTraNom()
						: "";
				String primerApellido = traba.getTraApe1() != null ? traba
						.getTraApe1() : "";
				String segundoApellido = traba.getTraApe2() != null ? traba
						.getTraApe2() : "";

				txtTraCedTabla.setValue(traba.getTraCed());
				traNombre.setValue(nombre + " " + primerApellido + " "
						+ segundoApellido);
				traNombre.setDisabled(true);

				List<EstudiosDTO> estudiosDTOs = businessDelegatorView
						.getDataEstudiosParam(null, traba.getTraCed());
				data = estudiosDTOs;

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("frmEstudio:txtTraCedTabla");
				s.add("frmEstudio:txtTraNombre");
				s.add("frmEstudio:idDataTableEstudio");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void onRowSelectTrajabador() {

		if (selectedTrabajador != null) {

			Traba traba = selectedTrabajador;

			if (traba != null && traba.getTraCed() != null) {

				String nombre = traba.getTraNom() != null ? traba.getTraNom()
						: "";
				String primerApellido = traba.getTraApe1() != null ? traba
						.getTraApe1() : "";
				String segundoApellido = traba.getTraApe2() != null ? traba
						.getTraApe2() : "";

				txtTraCed.setValue(traba.getTraCed());
				traNombre.setValue(nombre + " " + primerApellido + " "
						+ segundoApellido);
				traNombre.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogEstudio:txtTraCed");
				s.add("formDialogEstudio:txtTraNombre");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void onRowSelectRevelados() {

		if (selectedRevelado != null) {
			Revelado revelado = selectedRevelado;

			if (revelado != null && revelado.getRevNro() != null) {
				txtRevNro.setValue(revelado.getRevNro());
				fechaNumRevelado.setValue(revelado.getRevFec());
				fechaNumRevelado.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogEstudio:txtRevNro");
				s.add("formDialogEstudio:fechaNumRevelado");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void onRowSelectObservacion() {

		if (selectedObservacion != null) {
			Observa observa = selectedObservacion;

			if (observa != null && observa.getObsCod() != null) {
				txtObsCod.setValue(observa.getObsCod());
				obsDescripcion.setValue(observa.getObsDes());
				obsDescripcion.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogEstudio:txtObsCod");
				s.add("formDialogEstudio:txtObsDescripcion");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresa() {
		txtEmpCod.resetValue();
		razonSocialEmpresa.resetValue();

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogEstudio:txtEmpCod");
		s.add("formDialogEstudio:txtRazonSocialEmpresa");
		context.update(s);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresaTabla() {
		txtEmpCodTabla.resetValue();
		razonSocialEmpresa.resetValue();

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("frmEstudio:txtEmpCodTabla");
		s.add("frmEstudio:txtRazonSocialEmpresa");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaTrabajador() {
		txtTraCed.resetValue();
		traNombre.resetValue();

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogEstudio:txtTraCed");
		s.add("formDialogEstudio:txtTraNombre");
		context.update(s);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaTrabajadorTabla() {
		txtTraCedTabla.resetValue();
		traNombre.resetValue();

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("frmEstudio:txtTraCed");
		s.add("frmEstudio:txtTraNombre");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaRevelado() {
		txtRevNro.resetValue();
		fechaNumRevelado.resetValue();

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogEstudio:txtRevNro");
		s.add("formDialogEstudio:fechaNumRevelado");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaObservacion() {
		txtObsCod.resetValue();
		obsDescripcion.resetValue();

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogEstudio:txtObsCod");
		s.add("formDialogEstudio:txtObsDescripcion");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 * @param id
	 * @param campoDeClase
	 * @param boleana
	 * @return
	 */
	public Object[] searchByCriteriaRazonSocial(String id, String campoDeClase,
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
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
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
		object[3] = "=";
		return object;
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void actionCerrarBusqueda() {
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 * @param bandera
	 */
	public void cargarEstudioDialog(String bandera) {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("contentWidth", "'100%'");
		options.put("contentheight", 600);
		options.put("width", "'100%'");
		options.put("height", 600);
		options.put("position", "top");
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("closeOnEscape", true);
		options.put("dynamic", false);

		String codEmpresa = "";
		String codTrabajador = "";
		String codDosimetro = "";
		String estAni = "";
		String estMes = "";

		if (null != selectedEstudios) {
			
			if(null != this.codEmpresa){
				codEmpresa = this.codEmpresa;
			}else{
				codEmpresa = selectedEstudios.getEmpCod().toString();
			}
			
			if(null != this.traCed){
				codTrabajador = this.traCed;
			}else{
				codTrabajador = selectedEstudios.getTraCed().toString();
			}
			
			if(null != this.codDosimetro){
				codDosimetro = this.codDosimetro;
			}else{
				codDosimetro = selectedEstudios.getDosCod().toString();
			}
			
			estAni = selectedEstudios.getEstAni().toString();
			estMes = selectedEstudios.getEstMes().toString();
		}else{
			codEmpresa = this.codEmpresa;
			codTrabajador = this.traCed;
			codDosimetro = this.codDosimetro;
			
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);

		List<String> lstParamTrabajador = new ArrayList<String>();
		lstParamTrabajador.add(codTrabajador);

		List<String> lstParamDosimetro = new ArrayList<String>();
		lstParamDosimetro.add(codDosimetro);

		List<String> lstParamEstAni = new ArrayList<String>();
		lstParamEstAni.add(estAni);

		List<String> lstParamEstMes = new ArrayList<String>();
		lstParamEstMes.add(estMes);

		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("codTrabajador", lstParamTrabajador);
		params.put("codDosimetro", lstParamDosimetro);
		params.put("estAni", lstParamEstAni);
		params.put("estMes", lstParamEstMes);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/estudios.xhtml",
				options, params);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void inicializaCampos() {

		if (btnSave == null) {
			btnSave = new CommandButton();
		}

		if (btnDelete == null) {
			btnDelete = new CommandButton();
		}

		if (btnModify == null) {
			btnModify = new CommandButton();
		}

		if (btnClear == null) {
			btnClear = new CommandButton();
		}

		if (txtEstDos == null) {
			txtEstDos = new Spinner();
		}

		if (txtEstMesAct == null) {
			txtEstMesAct = new InputText();
		}

		if (txtObsCod == null) {
			txtObsCod = new InputText();
		}

		if (txtRevNro == null) {
			txtRevNro = new InputText();
		}

		if (txtTraCed == null) {
			txtTraCed = new InputText();
		}

		if (txtEmpCod == null) {
			txtEmpCod = new InputText();
		}

		if (txtDosCod == null) {
			txtDosCod = new InputText();
		}

		if (txtEstAni == null) {
			txtEstAni = new InputText();
		}

		if (txtEstMes == null) {
			txtEstMes = new InputText();
		}

		if (txtEstFecRec == null) {
			txtEstFecRec = new Calendar();
		}

		if (fechaNumRevelado == null) {
			fechaNumRevelado = new Calendar();
		}

		if (obsDescripcion == null) {
			obsDescripcion = new InputText();
		}
		
		if(traNombre == null){
			traNombre = new InputText();
		}
		
		if(razonSocialEmpresa == null){
			razonSocialEmpresa = new InputText();
		}
		
		if(txtEmpCodTabla == null){
			txtEmpCodTabla = new InputText();
		}
		
		if (null == cbOpcionEstado) {
			cbOpcionEstado = new SelectOneMenu();
		}
		
		if(txtTraCedTabla == null){
			txtTraCedTabla = new InputText();
		}
		
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	private void ejecutarControlesBusquedas() {
		consultarEmpresas();
		consultarEmpresasTabla();
		consultarTrabajador();
		consultarTrabajadorTabla();
		consultarRevelado();
		consultarObservacion();
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
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

		txtTraCed.setDisabled(true);
		txtEmpCod.setDisabled(true);
		txtDosCod.setDisabled(true);
		txtEstFecRec.setDisabled(true);
		fechaNumRevelado.setDisabled(true);
		obsDescripcion.setDisabled(true);
		txtEstDos.setDisabled(true);
		txtEstMesAct.setDisabled(true);
		cbOpcionEstado.setDisabled(true);
		txtObsCod.setDisabled(true);
		txtRevNro.setDisabled(true);
		txtEstAni.setDisabled(true);
		txtEstMes.setDisabled(true);

		RequestContext.getCurrentInstance().update("formDialogEstudio");
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
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

		txtTraCed.setDisabled(true);
		txtEmpCod.setDisabled(true);
		txtDosCod.setDisabled(true);
		txtEstFecRec.setDisabled(true);
		fechaNumRevelado.setDisabled(true);
		obsDescripcion.setDisabled(true);
		razonSocialEmpresa.setDisabled(true);
		txtEstAni.setDisabled(true);
		txtEstMes.setDisabled(true);
		txtEstDos.setDisabled(false);
		txtEstMesAct.setDisabled(false);
		txtRevNro.setDisabled(false);
		txtObsCod.setDisabled(false);
		cbOpcionEstado.setDisabled(false);
		
		RequestContext.getCurrentInstance().update("formDialogEstudio");
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @description
	 */
	public void actionCrearRevelados(){
		RequestContext.getCurrentInstance().execute("PF('widgetVarDialogRevelado').show();");
	}

	public Spinner getTxtEstDos() {
		return txtEstDos;
	}

	public void setTxtEstDos(Spinner txtEstDos) {
		this.txtEstDos = txtEstDos;
	}

	public InputText getTxtEstMesAct() {
		return txtEstMesAct;
	}

	public void setTxtEstMesAct(InputText txtEstMesAct) {
		this.txtEstMesAct = txtEstMesAct;
	}

	public String getTxtEstRec() {
		return txtEstRec;
	}

	public void setTxtEstRec(String txtEstRec) {
		this.txtEstRec = txtEstRec;
	}

	public InputText getTxtObsCod() {
		return txtObsCod;
	}

	public void setTxtObsCod(InputText txtObsCod) {
		this.txtObsCod = txtObsCod;
	}

	public InputText getTxtRevNro() {
		return txtRevNro;
	}

	public void setTxtRevNro(InputText txtRevNro) {
		this.txtRevNro = txtRevNro;
	}

	public Calendar getTxtEstFecRec() {
		return txtEstFecRec;
	}

	public void setTxtEstFecRec(Calendar txtEstFecRec) {
		this.txtEstFecRec = txtEstFecRec;
	}

	public InputText getTxtTraCed() {
		return txtTraCed;
	}

	public void setTxtTraCed(InputText txtTraCed) {
		this.txtTraCed = txtTraCed;
	}

	public InputText getTxtEmpCod() {
		return txtEmpCod;
	}

	public void setTxtEmpCod(InputText txtEmpCod) {
		this.txtEmpCod = txtEmpCod;
	}

	public InputText getTxtDosCod() {
		return txtDosCod;
	}

	public void setTxtDosCod(InputText txtDosCod) {
		this.txtDosCod = txtDosCod;
	}

	public InputText getTxtEstAni() {
		return txtEstAni;
	}

	public void setTxtEstAni(InputText txtEstAni) {
		this.txtEstAni = txtEstAni;
	}

	public InputText getTxtEstMes() {
		return txtEstMes;
	}

	public void setTxtEstMes(InputText txtEstMes) {
		this.txtEstMes = txtEstMes;
	}

	public List<EstudiosDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataEstudios();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<EstudiosDTO> estudiosDTO) {
		this.data = estudiosDTO;
	}

	public EstudiosDTO getSelectedEstudios() {
		return selectedEstudios;
	}

	public void setSelectedEstudios(EstudiosDTO estudios) {
		this.selectedEstudios = estudios;
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
	 * @date 27/11/2016
	 * @return the codEmpresa
	 */
	public String getCodEmpresa() {
		return codEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param codEmpresa
	 *            the codEmpresa to set
	 */
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @return the razonSocialEmpresa
	 */
	public InputText getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param razonSocialEmpresa
	 *            the razonSocialEmpresa to set
	 */
	public void setRazonSocialEmpresa(InputText razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @return the empresas
	 */
	public List<Empresas> getEmpresas() {
		return empresas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param empresas
	 *            the empresas to set
	 */
	public void setEmpresas(List<Empresas> empresas) {
		this.empresas = empresas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @return the selectEmpresa
	 */
	public Empresas getSelectEmpresa() {
		return selectEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param selectEmpresa
	 *            the selectEmpresa to set
	 */
	public void setSelectEmpresa(Empresas selectEmpresa) {
		this.selectEmpresa = selectEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @return the renderedEmpresa
	 */
	public boolean isRenderedEmpresa() {
		return renderedEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 27/11/2016
	 * @param renderedEmpresa
	 *            the renderedEmpresa to set
	 */
	public void setRenderedEmpresa(boolean renderedEmpresa) {
		this.renderedEmpresa = renderedEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the traCed
	 */
	public String getTraCed() {
		return traCed;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param traCed
	 *            the traCed to set
	 */
	public void setTraCed(String traCed) {
		this.traCed = traCed;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the traNombre
	 */
	public InputText getTraNombre() {
		return traNombre;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param traNombre
	 *            the traNombre to set
	 */
	public void setTraNombre(InputText traNombre) {
		this.traNombre = traNombre;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the trabajadores
	 */
	public List<Traba> getTrabajadores() {
		return trabajadores;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param trabajadores
	 *            the trabajadores to set
	 */
	public void setTrabajadores(List<Traba> trabajadores) {
		this.trabajadores = trabajadores;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the selectedTrabajador
	 */
	public Traba getSelectedTrabajador() {
		return selectedTrabajador;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param selectedTrabajador
	 *            the selectedTrabajador to set
	 */
	public void setSelectedTrabajador(Traba selectedTrabajador) {
		this.selectedTrabajador = selectedTrabajador;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the botonGuardar
	 */
	public boolean isBotonGuardar() {
		return botonGuardar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param botonGuardar
	 *            the botonGuardar to set
	 */
	public void setBotonGuardar(boolean botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the botonNuevo
	 */
	public boolean isBotonNuevo() {
		return botonNuevo;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param botonNuevo
	 *            the botonNuevo to set
	 */
	public void setBotonNuevo(boolean botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the botonModificar
	 */
	public boolean isBotonModificar() {
		return botonModificar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param botonModificar
	 *            the botonModificar to set
	 */
	public void setBotonModificar(boolean botonModificar) {
		this.botonModificar = botonModificar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the botonBorrar
	 */
	public boolean isBotonBorrar() {
		return botonBorrar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param botonBorrar
	 *            the botonBorrar to set
	 */
	public void setBotonBorrar(boolean botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the botonVisualizar
	 */
	public boolean isBotonVisualizar() {
		return botonVisualizar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param botonVisualizar
	 *            the botonVisualizar to set
	 */
	public void setBotonVisualizar(boolean botonVisualizar) {
		this.botonVisualizar = botonVisualizar;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the botonDosimetros
	 */
	public boolean isBotonDosimetros() {
		return botonDosimetros;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param botonDosimetros
	 *            the botonDosimetros to set
	 */
	public void setBotonDosimetros(boolean botonDosimetros) {
		this.botonDosimetros = botonDosimetros;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the botonTrabajadores
	 */
	public boolean isBotonTrabajadores() {
		return botonTrabajadores;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param botonTrabajadores
	 *            the botonTrabajadores to set
	 */
	public void setBotonTrabajadores(boolean botonTrabajadores) {
		this.botonTrabajadores = botonTrabajadores;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the disableBotonesBusqueda
	 */
	public boolean isDisableBotonesBusqueda() {
		return disableBotonesBusqueda;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param disableBotonesBusqueda
	 *            the disableBotonesBusqueda to set
	 */
	public void setDisableBotonesBusqueda(boolean disableBotonesBusqueda) {
		this.disableBotonesBusqueda = disableBotonesBusqueda;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the fechaNumRevelado
	 */
	public Calendar getFechaNumRevelado() {
		return fechaNumRevelado;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param fechaNumRevelado
	 *            the fechaNumRevelado to set
	 */
	public void setFechaNumRevelado(Calendar fechaNumRevelado) {
		this.fechaNumRevelado = fechaNumRevelado;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the revelados
	 */
	public List<Revelado> getRevelados() {
		return revelados;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param revelados
	 *            the revelados to set
	 */
	public void setRevelados(List<Revelado> revelados) {
		this.revelados = revelados;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the selectedRevelado
	 */
	public Revelado getSelectedRevelado() {
		return selectedRevelado;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param selectedRevelado
	 *            the selectedRevelado to set
	 */
	public void setSelectedRevelado(Revelado selectedRevelado) {
		this.selectedRevelado = selectedRevelado;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the obsDescripcion
	 */
	public InputText getObsDescripcion() {
		return obsDescripcion;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param obsDescripcion
	 *            the obsDescripcion to set
	 */
	public void setObsDescripcion(InputText obsDescripcion) {
		this.obsDescripcion = obsDescripcion;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the observaciones
	 */
	public List<Observa> getObservaciones() {
		return observaciones;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(List<Observa> observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @return the selectedObservacion
	 */
	public Observa getSelectedObservacion() {
		return selectedObservacion;
	}

	/**
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a>
	 * @date 9/12/2016
	 * @param selectedObservacion
	 *            the selectedObservacion to set
	 */
	public void setSelectedObservacion(Observa selectedObservacion) {
		this.selectedObservacion = selectedObservacion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 9/12/2016
	 * @return the txtEmpCodTabla
	 */
	public InputText getTxtEmpCodTabla() {
		return txtEmpCodTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 9/12/2016
	 * @param txtEmpCodTabla
	 *            the txtEmpCodTabla to set
	 */
	public void setTxtEmpCodTabla(InputText txtEmpCodTabla) {
		this.txtEmpCodTabla = txtEmpCodTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 10/12/2016 
	 * @return the cbOpcionEstado 
	 */
	public SelectOneMenu getCbOpcionEstado() {
		return cbOpcionEstado;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 10/12/2016 
	 * @param cbOpcionEstado the cbOpcionEstado to set 
	 */
	public void setCbOpcionEstado(SelectOneMenu cbOpcionEstado) {
		this.cbOpcionEstado = cbOpcionEstado;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 10/12/2016 
	 * @return the codDosimetro 
	 */
	public String getCodDosimetro() {
		return codDosimetro;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 10/12/2016 
	 * @param codDosimetro the codDosimetro to set 
	 */
	public void setCodDosimetro(String codDosimetro) {
		this.codDosimetro = codDosimetro;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 10/12/2016 
	 * @return the txtTraCedTabla 
	 */
	public InputText getTxtTraCedTabla() {
		return txtTraCedTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 10/12/2016 
	 * @param txtTraCedTabla the txtTraCedTabla to set 
	 */
	public void setTxtTraCedTabla(InputText txtTraCedTabla) {
		this.txtTraCedTabla = txtTraCedTabla;
	}
}

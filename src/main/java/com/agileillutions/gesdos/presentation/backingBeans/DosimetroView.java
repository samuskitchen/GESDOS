package com.agileillutions.gesdos.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Cargo;
import com.agileillutions.gesdos.modelo.Contratos;
import com.agileillutions.gesdos.modelo.Dosimetro;
import com.agileillutions.gesdos.modelo.DosimetroId;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.Geominas;
import com.agileillutions.gesdos.modelo.Practica;
import com.agileillutions.gesdos.modelo.Radiacion;
import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.Ubicacion;
import com.agileillutions.gesdos.modelo.dto.DosimetroDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean(name = "dosimetroView")
@ViewScoped
public class DosimetroView implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputText txtCarCod;
	private InputText txtDosConNro;
	private String txtDosEst;
	private InputText txtDosPerRec;
	private String txtDosTipo;
	private InputText txtGeoCod;
	private InputText txtPraCod;
	private InputText txtRadCod;
	private InputText txtUbiCod;
	private InputText txtTraCed;
	private InputText txtDosCod;
	private InputText txtEmpCod;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<DosimetroDTO> data;
	private DosimetroDTO selectedDosimetro;
	private Dosimetro entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

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

	// Datos de Empresa Tabla
	private InputText txtEmpCodTabla;
	private InputText txtRazonSocialTabla;
	private List<Empresas> empresasTabla;
	private Empresas selectEmpresaTabla;
	private boolean habilitarBusquedaEmpresaTabla;

	// Datos de Empresa
	private InputText txtRazonSocial;
	private List<Empresas> empresas;
	private Empresas selectEmpresa;
	private boolean habilitarBusquedaEmpresa;

	// Datos de Ttrabajador
	private InputText txtNombreTrabajador;
	private List<Traba> trabajoderes;
	private Traba selectTrabador;

	// Datos de Ubicacion
	private InputText txtNombreUbicion;
	private List<Ubicacion> ubicaciones;
	private Ubicacion selectUbicacion;

	// Datos de Cargo
	private InputText txtNombreCargo;
	private List<Cargo> cargos;
	private Cargo selectCargo;

	// Datos de Practica
	private InputText txtNombrePractica;
	private List<Practica> practicas;
	private Practica selectPractica;

	// Datos de Radicacion
	private InputText txtNombreRadicacion;
	private List<Radiacion> radicaciones;
	private Radiacion selectRadiacion;

	// Datos de Ingeminas
	private InputText txtNombreIngeomina;
	private List<Geominas> ingeominas;
	private Geominas selectIngeomina;

	// Datos de Contrato
	private InputText txtNombreContrato;
	private List<Contratos> contratos;
	private Contratos selectContrato;
	private Calendar fechaIncio;
	private Calendar fechaVence;

	private DosimetroDTO selectedDosimetros;

	public DosimetroView() {
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

			String codDosimetro = requestParameterMap.get("codDosimetro");
			String codEmpresa = requestParameterMap.get("codEmpresa");
			String codTrabajador = requestParameterMap.get("codTrabajador");
			String tipo = requestParameterMap.get("tipo");

			if (null != codEmpresa && !codEmpresa.isEmpty()) {
				habilitarBusquedaEmpresaTabla = false;
				txtEmpCodTabla = new InputText();
				txtEmpCodTabla.setValue(Long.valueOf(codEmpresa));
				consultarEmpresasTabla();
			} else {
				habilitarBusquedaEmpresaTabla = true;
			}

			if (null == tipo || "".equals(tipo)) {
				txtTraCed = new InputText();
				txtEmpCod = new InputText();

				txtTraCed.setValue(codTrabajador);
				txtEmpCod.setValue(codEmpresa);

				if ((null != codEmpresa && !codEmpresa.isEmpty())
						&& (null != codTrabajador && !codTrabajador.isEmpty())
						&& (null != codDosimetro && !codDosimetro.isEmpty())) {
					List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
							.getDataDosimetroEmpTraba(Long.valueOf(codEmpresa), Long.valueOf(codTrabajador), Long.valueOf(codDosimetro));
					data = dosimetroDTOs;
				} else if (null != codEmpresa && !codEmpresa.isEmpty()) {
					List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
							.getDataDosimetroTipo("empresa",
									Long.valueOf(codEmpresa));
					data = dosimetroDTOs;
				} else if (null != codTrabajador && !codTrabajador.isEmpty()) {
					List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
							.getDataDosimetroTipo("traba",
									Long.valueOf(codTrabajador));
					data = dosimetroDTOs;
				}
				ejecutarControlesBusquedas();
			}
			// Tipo Visualizar
			else if ("2".equals(tipo)) {
				if (null != codTrabajador && null != codDosimetro
						&& null != codEmpresa) {
					txtTraCed = new InputText();
					txtDosCod = new InputText();
					txtEmpCod = new InputText();

					txtDosCod.setValue(codDosimetro);
					txtTraCed.setValue(codTrabajador);
					txtEmpCod.setValue(codEmpresa);
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
					txtDosCod = new InputText();
					txtEmpCod = new InputText();

					txtDosCod.setValue(codDosimetro);
					txtTraCed.setValue(codTrabajador);
					txtEmpCod.setValue(codEmpresa);
					listener_txtId();
					modoModificar();
					ejecutarControlesBusquedas();
				}
			}
			// Tipo Crear
			else if ("0".equals(tipo)) {
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

	public void rowEventListener(RowEditEvent e) {
		try {
			DosimetroDTO dosimetroDTO = (DosimetroDTO) e.getObject();

			if (txtCarCod == null) {
				txtCarCod = new InputText();
			}

			txtCarCod.setValue(dosimetroDTO.getCarCod());

			if (txtDosConNro == null) {
				txtDosConNro = new InputText();
			}

			txtDosConNro.setValue(dosimetroDTO.getDosConNro());

			if (txtDosPerRec == null) {
				txtDosPerRec = new InputText();
			}

			txtDosPerRec.setValue(dosimetroDTO.getDosPerRec());

			if (txtGeoCod == null) {
				txtGeoCod = new InputText();
			}

			txtGeoCod.setValue(dosimetroDTO.getGeoCod());

			if (txtPraCod == null) {
				txtPraCod = new InputText();
			}

			txtPraCod.setValue(dosimetroDTO.getPraCod());

			if (txtRadCod == null) {
				txtRadCod = new InputText();
			}

			txtRadCod.setValue(dosimetroDTO.getRadCod());

			if (txtUbiCod == null) {
				txtUbiCod = new InputText();
			}

			txtUbiCod.setValue(dosimetroDTO.getUbiCod());

			if (txtTraCed == null) {
				txtTraCed = new InputText();
			}

			txtTraCed.setValue(dosimetroDTO.getTraCed());

			if (txtDosCod == null) {
				txtDosCod = new InputText();
			}

			txtDosCod.setValue(dosimetroDTO.getDosCod());

			if (txtEmpCod == null) {
				txtEmpCod = new InputText();
			}

			txtEmpCod.setValue(dosimetroDTO.getEmpCod());

			DosimetroId id = new DosimetroId();
			id.setTraCed((((txtTraCed.getValue()) == null) || (txtTraCed
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtTraCed));
			id.setDosCod((((txtDosCod.getValue()) == null) || (txtDosCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosCod));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEmpCod));
			entity = businessDelegatorView.getDosimetro(id);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedDosimetro = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedDosimetro = null;

		if (txtCarCod != null) {
			txtCarCod.setValue(null);
			txtCarCod.setDisabled(true);
		}

		if (txtDosConNro != null) {
			txtDosConNro.setValue(null);
			txtDosConNro.setDisabled(true);
		}

		if (txtDosPerRec != null) {
			txtDosPerRec.setValue(null);
			txtDosPerRec.setDisabled(true);
		}

		if (txtGeoCod != null) {
			txtGeoCod.setValue(null);
			txtGeoCod.setDisabled(true);
		}

		if (txtPraCod != null) {
			txtPraCod.setValue(null);
			txtPraCod.setDisabled(true);
		}

		if (txtRadCod != null) {
			txtRadCod.setValue(null);
			txtRadCod.setDisabled(true);
		}

		if (txtUbiCod != null) {
			txtUbiCod.setValue(null);
			txtUbiCod.setDisabled(true);
		}

		if (txtTraCed != null) {
			txtTraCed.setValue(null);
			txtTraCed.setDisabled(false);
		}

		if (txtDosCod != null) {
			txtDosCod.setValue(null);
			txtDosCod.setDisabled(false);
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

	public void listener_txtId() {
		try {
			DosimetroId id = new DosimetroId();
			id.setTraCed((((txtTraCed.getValue()) == null) || (txtTraCed
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtTraCed));
			id.setDosCod((((txtDosCod.getValue()) == null) || (txtDosCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosCod));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEmpCod));
			entity = (id != null) ? businessDelegatorView.getDosimetro(id)
					: null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtCarCod.setDisabled(false);
			txtDosConNro.setDisabled(false);
			cbOpcionEstado.setDisabled(false);
			txtDosPerRec.setDisabled(false);
			cbOpcionTipo.setDisabled(false);
			txtGeoCod.setDisabled(false);
			txtPraCod.setDisabled(false);
			txtRadCod.setDisabled(false);
			txtUbiCod.setDisabled(false);
			txtTraCed.setDisabled(false);
			txtDosCod.setDisabled(false);
			txtEmpCod.setDisabled(false);
		} else {
			txtCarCod.setValue(entity.getCarCod());
			txtDosConNro.setValue(entity.getDosConNro());
			cbOpcionEstado.setValue(entity.getDosEst());
			txtDosPerRec.setValue(entity.getDosPerRec());
			cbOpcionTipo.setValue(entity.getDosTipo());
			txtGeoCod.setValue(entity.getGeoCod());
			txtPraCod.setValue(entity.getPraCod());
			txtRadCod.setValue(entity.getRadCod());
			txtUbiCod.setValue(entity.getUbiCod());
			txtTraCed.setValue(entity.getId().getTraCed());
			txtDosCod.setValue(entity.getId().getDosCod());
			txtEmpCod.setValue(entity.getId().getEmpCod());
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedDosimetro = (DosimetroDTO) (evt.getComponent().getAttributes()
				.get("selectedDosimetro"));
		txtCarCod.setValue(selectedDosimetro.getCarCod());
		txtCarCod.setDisabled(false);
		txtDosConNro.setValue(selectedDosimetro.getDosConNro());
		txtDosConNro.setDisabled(false);
		cbOpcionEstado.setValue(selectedDosimetro.getDosEst());
		cbOpcionEstado.setDisabled(false);
		txtDosPerRec.setValue(selectedDosimetro.getDosPerRec());
		txtDosPerRec.setDisabled(false);
		cbOpcionTipo.setValue(selectedDosimetro.getDosTipo());
		cbOpcionTipo.setDisabled(false);
		txtGeoCod.setValue(selectedDosimetro.getGeoCod());
		txtGeoCod.setDisabled(false);
		txtPraCod.setValue(selectedDosimetro.getPraCod());
		txtPraCod.setDisabled(false);
		txtRadCod.setValue(selectedDosimetro.getRadCod());
		txtRadCod.setDisabled(false);
		txtUbiCod.setValue(selectedDosimetro.getUbiCod());
		txtUbiCod.setDisabled(false);
		txtTraCed.setValue(selectedDosimetro.getTraCed());
		txtTraCed.setDisabled(true);
		txtDosCod.setValue(selectedDosimetro.getDosCod());
		txtDosCod.setDisabled(true);
		txtEmpCod.setValue(selectedDosimetro.getEmpCod());
		txtEmpCod.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedDosimetro == null) && (entity == null)) {
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
			entity = new Dosimetro();

			DosimetroId id = new DosimetroId();
			id.setTraCed((((txtTraCed.getValue()) == null) || (txtTraCed
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtTraCed));
			id.setDosCod((((txtDosCod.getValue()) == null) || (txtDosCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosCod));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEmpCod));

			if (null != id) {
				entity.setCarCod(FacesUtils.checkString(txtCarCod));
				entity.setDosConNro((((txtDosConNro.getValue()) == null) || (txtDosConNro
						.getValue()).equals("")) ? null : FacesUtils
						.checkLong(txtDosConNro));
				entity.setDosEst(txtDosEst);
				entity.setDosPerRec((((txtDosPerRec.getValue()) == null) || (txtDosPerRec
						.getValue()).equals("")) ? null : FacesUtils
						.checkLong(txtDosPerRec));
				entity.setDosTipo(txtDosTipo);
				entity.setGeoCod(FacesUtils.checkString(txtGeoCod));
				entity.setId(id);
				entity.setPraCod(FacesUtils.checkString(txtPraCod));
				entity.setRadCod(FacesUtils.checkString(txtRadCod));
				entity.setUbiCod(FacesUtils.checkString(txtUbiCod));
				businessDelegatorView.saveDosimetro(entity);
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
				DosimetroId id = new DosimetroId();
				id.setTraCed(FacesUtils.checkLong(txtTraCed));
				id.setDosCod(FacesUtils.checkLong(txtDosCod));
				id.setEmpCod(FacesUtils.checkLong(txtEmpCod));
				entity = businessDelegatorView.getDosimetro(id);
			}

			entity.setCarCod(FacesUtils.checkString(txtCarCod));
			entity.setDosConNro((((txtDosConNro.getValue()) == null) || (txtDosConNro
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosConNro));
			entity.setDosEst(txtDosEst);
			entity.setDosPerRec((((txtDosPerRec.getValue()) == null) || (txtDosPerRec
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosPerRec));
			entity.setDosTipo(txtDosTipo);
			entity.setGeoCod(FacesUtils.checkString(txtGeoCod));
			entity.setPraCod(FacesUtils.checkString(txtPraCod));
			entity.setRadCod(FacesUtils.checkString(txtRadCod));
			entity.setUbiCod(FacesUtils.checkString(txtUbiCod));
			businessDelegatorView.updateDosimetro(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable() {
		try {
			DosimetroId id = new DosimetroId();
			id.setTraCed(selectedDosimetro.getTraCed());
			id.setDosCod(selectedDosimetro.getDosCod());
			id.setEmpCod(selectedDosimetro.getEmpCod());
			entity = businessDelegatorView.getDosimetro(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			DosimetroId id = new DosimetroId();
			id.setTraCed((((txtTraCed.getValue()) == null) || (txtTraCed
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtTraCed));
			id.setDosCod((((txtDosCod.getValue()) == null) || (txtDosCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtDosCod));
			id.setEmpCod((((txtEmpCod.getValue()) == null) || (txtEmpCod
					.getValue()).equals("")) ? null : FacesUtils
					.checkLong(txtEmpCod));
			entity = businessDelegatorView.getDosimetro(id);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteDosimetro(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			// action_clear();
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
			selectedDosimetro = (DosimetroDTO) (evt.getComponent()
					.getAttributes().get("selectedDosimetro"));

			DosimetroId id = new DosimetroId();
			id.setTraCed(selectedDosimetro.getTraCed());
			id.setDosCod(selectedDosimetro.getDosCod());
			id.setEmpCod(selectedDosimetro.getEmpCod());
			entity = businessDelegatorView.getDosimetro(id);
			businessDelegatorView.deleteDosimetro(entity);
			data.remove(selectedDosimetro);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long traCed, Long dosCod, Long empCod,
			String carCod, Long dosConNro, String dosEst, Long dosPerRec,
			String dosTipo, String geoCod, String praCod, String radCod,
			String ubiCod) throws Exception {
		try {
			entity.setCarCod(FacesUtils.checkString(carCod));
			entity.setDosConNro(FacesUtils.checkLong(dosConNro));
			entity.setDosEst(FacesUtils.checkString(dosEst));
			entity.setDosPerRec(FacesUtils.checkLong(dosPerRec));
			entity.setDosTipo(FacesUtils.checkString(dosTipo));
			entity.setGeoCod(FacesUtils.checkString(geoCod));
			entity.setPraCod(FacesUtils.checkString(praCod));
			entity.setRadCod(FacesUtils.checkString(radCod));
			entity.setUbiCod(FacesUtils.checkString(ubiCod));
			businessDelegatorView.updateDosimetro(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("DosimetroView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
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
		object[3] = "=";
		return object;
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void actionCerrarBusqueda() {
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarEmpresasTabla() {
		try {
			if (null != txtEmpCodTabla.getValue()) {
				Object[] variablesEmpresa = searchByCriteria(txtEmpCodTabla
						.getValue().toString(), "empCod", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCodTabla.setValue(emp.getEmpCod());
						txtRazonSocialTabla.setValue(emp.getEmpRazSoc());
						txtRazonSocialTabla.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmDomisetro:txtEmpCodTabla");
						s.add("frmDomisetro:txtRazonSocialTabla");
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
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarEmpresas() {
		try {
			if (null != txtEmpCod.getValue()
					&& !txtEmpCod.getValue().toString().isEmpty()) {
				Object[] variablesEmpresa = searchByCriteria(txtEmpCod
						.getValue().toString(), "empCod", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCod.setValue(emp.getEmpCod());
						txtRazonSocial.setValue(emp.getEmpRazSoc());
						txtRazonSocial.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtEmpCod");
						s.add("formDialogDosimetro:txtRazonSocial");
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
	 * @date 14/11/2016
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

						txtNombreTrabajador.setValue(traba.getTraNom()
								.toString()
								+ " "
								+ traba.getTraApe1().toString()
								+ " "
								+ SegundoApellido);
						txtNombreTrabajador.getValue().toString();
						txtNombreTrabajador.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtTraCed");
						s.add("formDialogDosimetro:txtNombreTrabajador");
						context.update(s);
					}
				} else {
					trabajoderes = businessDelegatorView.getTraba();
				}
			} else {
				trabajoderes = businessDelegatorView.getTraba();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarUbicacion() {
		try {
			if (null != txtUbiCod.getValue()) {
				Object[] variablesUbicacion = searchByCriteria(txtUbiCod
						.getValue().toString(), "ubiCod", true);
				List<Ubicacion> ubicacions = businessDelegatorView
						.findByCriteriaInUbicacion(variablesUbicacion, null,
								null);

				if (null != ubicacions && ubicacions.size() > 0) {
					Ubicacion ubicacion = ubicacions.get(0);
					if (ubicacion != null && ubicacion.getUbiCod() != null) {
						txtUbiCod.setValue(ubicacion.getUbiCod());
						txtNombreUbicion.setValue(ubicacion.getUbiDes());
						txtNombreUbicion.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtUbiCod");
						s.add("formDialogDosimetro:txtNombreUbicion");
						context.update(s);
					}
				} else {
					ubicaciones = businessDelegatorView.getUbicacion();
				}
			} else {
				ubicaciones = businessDelegatorView.getUbicacion();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarCargo() {
		try {
			if (null != txtCarCod.getValue()) {
				Object[] variablesCargo = searchByCriteria(txtCarCod.getValue()
						.toString(), "carCod", true);
				List<Cargo> listCargos = businessDelegatorView
						.findByCriteriaInCargo(variablesCargo, null, null);

				if (null != listCargos && listCargos.size() > 0) {
					Cargo cargo = listCargos.get(0);
					if (cargo != null && cargo.getCarCod() != null) {
						txtCarCod.setValue(cargo.getCarCod());
						txtNombreCargo.setValue(cargo.getCarDes());
						txtNombreCargo.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtCarCod");
						s.add("formDialogDosimetro:txtNombreCargo");
						context.update(s);
					}
				} else {
					cargos = businessDelegatorView.getCargo();
				}
			} else {
				cargos = businessDelegatorView.getCargo();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarPractica() {
		try {
			if (null != txtPraCod.getValue()) {
				Object[] variablesPractica = searchByCriteria(txtPraCod
						.getValue().toString(), "praCod", true);
				List<Practica> practicasList = businessDelegatorView
						.findByCriteriaInPractica(variablesPractica, null, null);

				if (null != practicasList && practicasList.size() > 0) {
					Practica practica = practicasList.get(0);
					if (practica != null && practica.getPraCod() != null) {
						txtPraCod.setValue(practica.getPraCod());
						txtNombrePractica.setValue(practica.getPraDes());
						txtNombrePractica.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtPraCod");
						s.add("formDialogDosimetro:txtNombrePractica");
						context.update(s);
					}
				} else {
					practicas = businessDelegatorView.getPractica();
				}
			} else {
				practicas = businessDelegatorView.getPractica();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarRadiacion() {
		try {
			if (null != txtRadCod.getValue()) {
				Object[] variablesRadiacion = searchByCriteria(txtRadCod
						.getValue().toString(), "radCod", true);
				List<Radiacion> radiacionList = businessDelegatorView
						.findByCriteriaInRadiacion(variablesRadiacion, null,
								null);

				if (null != radiacionList && radiacionList.size() > 0) {
					Radiacion radiacion = radiacionList.get(0);
					if (radiacion != null && radiacion.getRadCod() != null) {
						txtRadCod.setValue(radiacion.getRadCod());
						txtNombreRadicacion.setValue(radiacion.getRadDes());
						txtNombreRadicacion.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtRadCod");
						s.add("formDialogDosimetro:txtNombreRadicacion");
						context.update(s);
					}
				} else {
					radicaciones = businessDelegatorView.getRadiacion();
				}
			} else {
				radicaciones = businessDelegatorView.getRadiacion();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarGeominas() {
		try {
			if (null != txtGeoCod.getValue()) {
				Object[] variablesGeominas = searchByCriteria(txtGeoCod
						.getValue().toString(), "geoCod", true);
				List<Geominas> geominasList = businessDelegatorView
						.findByCriteriaInGeominas(variablesGeominas, null, null);

				if (null != geominasList && geominasList.size() > 0) {
					Geominas geominas = geominasList.get(0);
					if (geominas != null && geominas.getGeoCod() != null) {
						txtGeoCod.setValue(geominas.getGeoCod());
						txtNombreIngeomina.setValue(geominas.getGeoDes());
						txtNombreIngeomina.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialogDosimetro:txtGeoCod");
						s.add("formDialogDosimetro:txtNombreIngeomina");
						context.update(s);
					}
				} else {
					ingeominas = businessDelegatorView.getGeominas();
				}
			} else {
				ingeominas = businessDelegatorView.getGeominas();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarContratos() {
		try {

			String codEmpresa = txtEmpCod.getValue().toString();
			String codContrato = txtDosConNro.getValue() != null ? txtDosConNro
					.getValue().toString() : null;

			Object[] variables = null;
			if (null != codContrato && !codContrato.isEmpty()) {
				variables = searchByCriteriaContrato(codContrato, "id.conNro",
						true, codContrato, "id.empCod", false);

				List<Contratos> contratosList = businessDelegatorView
						.findByCriteriaInContratos(variables, null, null);

				if (null != contratosList && contratosList.size() > 0) {
					Contratos contratos = contratosList.get(0);
					if (contratos != null && contratos.getId() != null) {

						txtDosConNro.setValue(contratos.getId().getConNro());
						fechaIncio.setValue(contratos.getConFec());
						fechaVence.setValue(contratos.getConFecFac());
						fechaIncio.setDisabled(true);
						fechaVence.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialog:txtDosConNro");
						s.add("formDialogDosimetro:fechaIncio");
						s.add("formDialogDosimetro:fechaVence");
						context.update(s);
					}
				} else {
					variables = searchByCriteria(codEmpresa, "id.empCod", true);
					contratos = businessDelegatorView
							.findByCriteriaInContratos(variables, null, null);
				}
			} else {
				variables = searchByCriteria(codEmpresa, "id.empCod", true);
				contratos = businessDelegatorView.findByCriteriaInContratos(
						variables, null, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 * @param id
	 * @param campoDeClase
	 * @param boleana
	 * @param id2
	 * @param campoDeClase2
	 * @param boleana2
	 * @return
	 */
	public Object[] searchByCriteriaContrato(String id, String campoDeClase,
			boolean boleana, String id2, String campoDeClase2, boolean boleana2) {
		Object whereObject[] = new Object[8];
		whereObject[0] = campoDeClase;
		whereObject[1] = boleana;
		whereObject[2] = id;
		whereObject[3] = "=";
		whereObject[4] = campoDeClase2;
		whereObject[5] = boleana2;
		whereObject[6] = id2;
		whereObject[7] = "=";
		return whereObject;
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectEmpresa() {

		if (selectEmpresa != null) {
			Empresas empresasDTO = selectEmpresa;
			if (empresasDTO != null && empresasDTO.getEmpCod() != null) {

				txtEmpCod.setValue(empresasDTO.getEmpCod());
				txtRazonSocial.setValue(empresasDTO.getEmpRazSoc());
				txtRazonSocial.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtEmpCod");
				s.add("formDialogDosimetro:txtRazonSocial");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectEmpresaTabla() throws NumberFormatException, Exception {

		if (selectEmpresaTabla != null) {
			Empresas empresasDTO = selectEmpresaTabla;
			if (empresasDTO != null && empresasDTO.getEmpCod() != null) {

				txtEmpCodTabla.setValue(empresasDTO.getEmpCod());
				txtRazonSocialTabla.setValue(empresasDTO.getEmpRazSoc());
				txtRazonSocialTabla.setDisabled(true);
				
				List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
						.getDataDosimetroTipo("empresa",
								Long.valueOf(txtEmpCodTabla.getValue().toString()));
				data = dosimetroDTOs;
				

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("frmDomisetro:txtEmpCodTabla");
				s.add("frmDomisetro:txtRazonSocialTabla");
				s.add("frmDomisetro:idTableDosimetros");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelecTrabajador() {

		if (selectTrabador != null) {
			Traba trabaDTO = selectTrabador;
			if (trabaDTO != null && trabaDTO.getTraCed() != null) {

				String SegundoApellido = trabaDTO.getTraApe2() != null ? trabaDTO
						.getTraApe2().toString() : "";

				txtTraCed.setValue(trabaDTO.getTraCed());
				txtNombreTrabajador.setValue(trabaDTO.getTraNom().toString()
						+ " " + trabaDTO.getTraApe1().toString() + " "
						+ SegundoApellido);
				txtNombreTrabajador.getValue().toString();
				txtNombreTrabajador.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtTraCed");
				s.add("formDialogDosimetro:txtNombreTrabajador");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectUbicacion() {

		if (selectUbicacion != null) {
			Ubicacion ubicacionDTO = selectUbicacion;
			if (ubicacionDTO != null && ubicacionDTO.getUbiCod() != null) {
				txtUbiCod.setValue(ubicacionDTO.getUbiCod());
				txtNombreUbicion.setValue(ubicacionDTO.getUbiDes());
				txtNombreUbicion.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtUbiCod");
				s.add("formDialogDosimetro:txtNombreUbicion");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectCargo() {

		if (selectCargo != null) {
			Cargo cargoDTO = selectCargo;
			if (cargoDTO != null && cargoDTO.getCarCod() != null) {
				txtCarCod.setValue(cargoDTO.getCarCod());
				txtNombreCargo.setValue(cargoDTO.getCarDes());
				txtNombreCargo.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtCarCod");
				s.add("formDialogDosimetro:txtNombreCargo");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectPractica() {

		if (selectPractica != null) {
			Practica practicaDTO = selectPractica;
			if (practicaDTO != null && practicaDTO.getPraCod() != null) {
				txtPraCod.setValue(practicaDTO.getPraCod());
				txtNombrePractica.setValue(practicaDTO.getPraDes());
				txtNombrePractica.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtPraCod");
				s.add("formDialogDosimetro:txtNombrePractica");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectRadiacion() {

		if (selectRadiacion != null) {
			Radiacion radiacionDTO = selectRadiacion;
			if (radiacionDTO != null && radiacionDTO.getRadCod() != null) {
				txtRadCod.setValue(radiacionDTO.getRadCod());
				txtNombreRadicacion.setValue(radiacionDTO.getRadDes());
				txtNombreRadicacion.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtRadCod");
				s.add("formDialogDosimetro:txtNombreRadicacion");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectGeominas() {

		if (selectIngeomina != null) {
			Geominas geominasDTO = selectIngeomina;
			if (geominasDTO != null && geominasDTO.getGeoCod() != null) {
				txtGeoCod.setValue(geominasDTO.getGeoCod());
				txtNombreIngeomina.setValue(geominasDTO.getGeoDes());

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtGeoCod");
				s.add("formDialogDosimetro:txtNombreIngeomina");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectContratos() {
		if (selectContrato != null) {
			Contratos contratosDTO = selectContrato;
			if (contratosDTO != null
					&& contratosDTO.getId().getConNro() != null) {
				txtDosConNro.setValue(contratosDTO.getId().getConNro());
				fechaIncio.setValue(contratosDTO.getConFec());
				fechaVence.setValue(contratosDTO.getConFecFac());
				fechaIncio.setDisabled(true);
				fechaVence.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialogDosimetro:txtDosConNro");
				s.add("formDialogDosimetro:fechaIncio");
				s.add("formDialogDosimetro:fechaVence");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresasTabla() {
		txtEmpCodTabla.resetValue();
		txtRazonSocialTabla.resetValue();
		txtRazonSocialTabla.setDisabled(true);
		data = null;
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("frmDomisetro:txtEmpCodTabla");
		s.add("frmDomisetro:txtRazonSocialTabla");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresas() {
		txtEmpCod.resetValue();
		txtRazonSocial.resetValue();
		txtRazonSocial.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtEmpCod");
		s.add("formDialogDosimetro:txtRazonSocial");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaTrabajador() {
		txtTraCed.resetValue();
		txtNombreTrabajador.resetValue();
		txtNombreTrabajador.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtTraCed");
		s.add("formDialogDosimetro:txtNombreTrabajador");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaUbicacion() {
		txtUbiCod.resetValue();
		txtNombreUbicion.resetValue();
		txtNombreUbicion.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtUbiCod");
		s.add("formDialogDosimetro:txtNombreUbicion");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaCargo() {
		txtCarCod.resetValue();
		txtNombreCargo.resetValue();
		txtNombreCargo.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtCarCod");
		s.add("formDialogDosimetro:txtNombreCargo");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaPractica() {
		txtPraCod.resetValue();
		txtNombrePractica.resetValue();
		txtNombrePractica.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtPraCod");
		s.add("formDialogDosimetro:txtNombrePractica");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaRadiacion() {
		txtRadCod.resetValue();
		txtNombreRadicacion.resetValue();
		txtNombreRadicacion.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtRadCod");
		s.add("formDialogDosimetro:txtNombreRadicacion");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaGeominas() {
		txtGeoCod.resetValue();
		txtNombreIngeomina.resetValue();
		txtNombreIngeomina.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtGeoCod");
		s.add("formDialogDosimetro:txtNombreIngeomina");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaContratos() {
		txtDosConNro.resetValue();
		fechaIncio.resetValue();
		fechaVence.resetValue();
		fechaIncio.setDisabled(true);
		fechaVence.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialogDosimetro:txtDosConNro");
		s.add("formDialogDosimetro:fechaIncio");
		s.add("formDialogDosimetro:fechaVence");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 * @param bandera
	 */
	public void cargarDosimetroDialog(String bandera) {

		Map<String, Object> options = new HashMap<String, Object>();

		options.put("contentWidth", "'100%'");
		options.put("contentheight", 500);
		options.put("width", "'100%'");
		options.put("height", 500);
		options.put("position", "top");
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("closeOnEscape", true);
		options.put("dynamic", false);

		String codEmpresa = "";
		String codTrabajador = "";
		String codDosimetro = "";
		if (null != selectedDosimetro) {
			codEmpresa = selectedDosimetro.getEmpCod().toString();
			codTrabajador = selectedDosimetro.getTraCed().toString();
			codDosimetro = selectedDosimetro.getDosCod().toString();
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);

		List<String> lstParamTrabajador = new ArrayList<String>();
		lstParamTrabajador.add(codTrabajador);

		List<String> lstParamDosimetro = new ArrayList<String>();
		lstParamDosimetro.add(codDosimetro);

		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("codTrabajador", lstParamTrabajador);
		params.put("codDosimetro", lstParamDosimetro);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog(
				"/XHTML/dosimetro.xhtml", options, params);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void inicializaCampos() {

		if (null == cbOpcionEstado) {
			cbOpcionEstado = new SelectOneMenu();
		}

		if (null == cbOpcionTipo) {
			cbOpcionTipo = new SelectOneMenu();
		}

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

		if (txtCarCod == null) {
			txtCarCod = new InputText();
		}

		if (txtDosConNro == null) {
			txtDosConNro = new InputText();
		}

		if (txtDosPerRec == null) {
			txtDosPerRec = new InputText();
		}

		if (txtGeoCod == null) {
			txtGeoCod = new InputText();
		}

		if (txtPraCod == null) {
			txtPraCod = new InputText();
		}

		if (txtRadCod == null) {
			txtRadCod = new InputText();
		}

		if (txtUbiCod == null) {
			txtUbiCod = new InputText();
		}

		if (txtTraCed == null) {
			txtTraCed = new InputText();
		}

		if (txtDosCod == null) {
			txtDosCod = new InputText();
		}

		if (txtEmpCod == null) {
			txtEmpCod = new InputText();
		}

		if (txtEmpCodTabla == null) {
			txtEmpCodTabla = new InputText();
		}

		if (txtRazonSocial == null) {
			txtRazonSocial = new InputText();
		}

		if (txtRazonSocialTabla == null) {
			txtRazonSocialTabla = new InputText();
		}

		if (txtNombreTrabajador == null) {
			txtNombreTrabajador = new InputText();
		}

		if (txtNombreUbicion == null) {
			txtNombreUbicion = new InputText();
		}

		if (txtNombreCargo == null) {
			txtNombreCargo = new InputText();
		}

		if (txtNombrePractica == null) {
			txtNombrePractica = new InputText();
		}

		if (txtNombreRadicacion == null) {
			txtNombreRadicacion = new InputText();
		}

		if (txtNombreIngeomina == null) {
			txtNombreIngeomina = new InputText();
		}

		if (fechaIncio == null) {
			fechaIncio = new Calendar();
		}

		if (fechaVence == null) {
			fechaVence = new Calendar();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
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
		txtDosCod.setDisabled(true);
		txtDosPerRec.setDisabled(true);
		txtRazonSocial.setDisabled(true);
		txtRazonSocialTabla.setDisabled(true);
		txtNombreTrabajador.setDisabled(true);
		txtNombreUbicion.setDisabled(true);
		txtNombreCargo.setDisabled(true);
		txtNombrePractica.setDisabled(true);
		txtNombreRadicacion.setDisabled(true);
		txtNombreIngeomina.setDisabled(true);
		fechaIncio.setDisabled(true);
		fechaVence.setDisabled(true);

		RequestContext.getCurrentInstance().update("formDialogDosimetro");
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
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

		txtCarCod.setDisabled(false);
		txtDosConNro.setDisabled(false);
		cbOpcionEstado.setDisabled(false);
		txtDosPerRec.setDisabled(false);
		cbOpcionTipo.setDisabled(false);
		txtGeoCod.setDisabled(false);
		txtPraCod.setDisabled(false);
		txtRadCod.setDisabled(false);
		txtUbiCod.setDisabled(false);
		txtTraCed.setDisabled(true);
		txtDosCod.setDisabled(true);
		txtEmpCod.setDisabled(true);
		cbOpcionEstado.setDisabled(false);
		txtRazonSocial.setDisabled(true);
		txtRazonSocialTabla.setDisabled(true);
		txtNombreTrabajador.setDisabled(true);
		txtNombreUbicion.setDisabled(true);
		txtNombreCargo.setDisabled(true);
		txtNombrePractica.setDisabled(true);
		txtNombreRadicacion.setDisabled(true);
		txtNombreIngeomina.setDisabled(true);
		fechaIncio.setDisabled(true);
		fechaVence.setDisabled(true);

		RequestContext.getCurrentInstance().update("formDialogDosimetro");
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 * @throws Exception
	 */
	public void closeDialog() throws Exception {
		data = businessDelegatorView.getDataDosimetro();
		RequestContext.getCurrentInstance().update("frmDomisetro");
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 15/11/2016
	 * @description
	 */
	private void ejecutarControlesBusquedas() {
		consultarCargo();
		consultarContratos();
		consultarEmpresas();
		consultarGeominas();
		consultarPractica();
		consultarRadiacion();
		consultarTrabajador();
		consultarUbicacion();
	}

	public InputText getTxtCarCod() {
		return txtCarCod;
	}

	public void setTxtCarCod(InputText txtCarCod) {
		this.txtCarCod = txtCarCod;
	}

	public InputText getTxtDosConNro() {
		return txtDosConNro;
	}

	public void setTxtDosConNro(InputText txtDosConNro) {
		this.txtDosConNro = txtDosConNro;
	}

	public String getTxtDosEst() {
		return txtDosEst;
	}

	public void setTxtDosEst(String txtDosEst) {
		this.txtDosEst = txtDosEst;
	}

	public InputText getTxtDosPerRec() {
		return txtDosPerRec;
	}

	public void setTxtDosPerRec(InputText txtDosPerRec) {
		this.txtDosPerRec = txtDosPerRec;
	}

	public String getTxtDosTipo() {
		return txtDosTipo;
	}

	public void setTxtDosTipo(String txtDosTipo) {
		this.txtDosTipo = txtDosTipo;
	}

	public InputText getTxtGeoCod() {
		return txtGeoCod;
	}

	public void setTxtGeoCod(InputText txtGeoCod) {
		this.txtGeoCod = txtGeoCod;
	}

	public InputText getTxtPraCod() {
		return txtPraCod;
	}

	public void setTxtPraCod(InputText txtPraCod) {
		this.txtPraCod = txtPraCod;
	}

	public InputText getTxtRadCod() {
		return txtRadCod;
	}

	public void setTxtRadCod(InputText txtRadCod) {
		this.txtRadCod = txtRadCod;
	}

	public InputText getTxtUbiCod() {
		return txtUbiCod;
	}

	public void setTxtUbiCod(InputText txtUbiCod) {
		this.txtUbiCod = txtUbiCod;
	}

	public InputText getTxtTraCed() {
		return txtTraCed;
	}

	public void setTxtTraCed(InputText txtTraCed) {
		this.txtTraCed = txtTraCed;
	}

	public InputText getTxtDosCod() {
		return txtDosCod;
	}

	public void setTxtDosCod(InputText txtDosCod) {
		this.txtDosCod = txtDosCod;
	}

	public InputText getTxtEmpCod() {
		return txtEmpCod;
	}

	public void setTxtEmpCod(InputText txtEmpCod) {
		this.txtEmpCod = txtEmpCod;
	}

	public List<DosimetroDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataDosimetro();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<DosimetroDTO> dosimetroDTO) {
		this.data = dosimetroDTO;
	}

	public DosimetroDTO getSelectedDosimetro() {
		return selectedDosimetro;
	}

	public void setSelectedDosimetro(DosimetroDTO dosimetro) {
		this.selectedDosimetro = dosimetro;
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
	 * @return the cbOpcionTipo
	 */
	public SelectOneMenu getCbOpcionTipo() {
		return cbOpcionTipo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param cbOpcionTipo
	 *            the cbOpcionTipo to set
	 */
	public void setCbOpcionTipo(SelectOneMenu cbOpcionTipo) {
		this.cbOpcionTipo = cbOpcionTipo;
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
	 * @return the botonNuevo
	 */
	public boolean isBotonNuevo() {
		return botonNuevo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param botonNuevo
	 *            the botonNuevo to set
	 */
	public void setBotonNuevo(boolean botonNuevo) {
		this.botonNuevo = botonNuevo;
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
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtRazonSocial
	 */
	public InputText getTxtRazonSocial() {
		return txtRazonSocial;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtRazonSocial
	 *            the txtRazonSocial to set
	 */
	public void setTxtRazonSocial(InputText txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the empresas
	 */
	public List<Empresas> getEmpresas() {
		return empresas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param empresas
	 *            the empresas to set
	 */
	public void setEmpresas(List<Empresas> empresas) {
		this.empresas = empresas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectEmpresa
	 */
	public Empresas getSelectEmpresa() {
		return selectEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectEmpresa
	 *            the selectEmpresa to set
	 */
	public void setSelectEmpresa(Empresas selectEmpresa) {
		this.selectEmpresa = selectEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtNombreTrabajador
	 */
	public InputText getTxtNombreTrabajador() {
		return txtNombreTrabajador;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtNombreTrabajador
	 *            the txtNombreTrabajador to set
	 */
	public void setTxtNombreTrabajador(InputText txtNombreTrabajador) {
		this.txtNombreTrabajador = txtNombreTrabajador;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the trabajoderes
	 */
	public List<Traba> getTrabajoderes() {
		return trabajoderes;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param trabajoderes
	 *            the trabajoderes to set
	 */
	public void setTrabajoderes(List<Traba> trabajoderes) {
		this.trabajoderes = trabajoderes;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectTrabador
	 */
	public Traba getSelectTrabador() {
		return selectTrabador;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectTrabador
	 *            the selectTrabador to set
	 */
	public void setSelectTrabador(Traba selectTrabador) {
		this.selectTrabador = selectTrabador;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtNombreUbicion
	 */
	public InputText getTxtNombreUbicion() {
		return txtNombreUbicion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtNombreUbicion
	 *            the txtNombreUbicion to set
	 */
	public void setTxtNombreUbicion(InputText txtNombreUbicion) {
		this.txtNombreUbicion = txtNombreUbicion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the ubicaciones
	 */
	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param ubicaciones
	 *            the ubicaciones to set
	 */
	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectUbicacion
	 */
	public Ubicacion getSelectUbicacion() {
		return selectUbicacion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectUbicacion
	 *            the selectUbicacion to set
	 */
	public void setSelectUbicacion(Ubicacion selectUbicacion) {
		this.selectUbicacion = selectUbicacion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtNombreCargo
	 */
	public InputText getTxtNombreCargo() {
		return txtNombreCargo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtNombreCargo
	 *            the txtNombreCargo to set
	 */
	public void setTxtNombreCargo(InputText txtNombreCargo) {
		this.txtNombreCargo = txtNombreCargo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the cargos
	 */
	public List<Cargo> getCargos() {
		return cargos;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param cargos
	 *            the cargos to set
	 */
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectCargo
	 */
	public Cargo getSelectCargo() {
		return selectCargo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectCargo
	 *            the selectCargo to set
	 */
	public void setSelectCargo(Cargo selectCargo) {
		this.selectCargo = selectCargo;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtNombrePractica
	 */
	public InputText getTxtNombrePractica() {
		return txtNombrePractica;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtNombrePractica
	 *            the txtNombrePractica to set
	 */
	public void setTxtNombrePractica(InputText txtNombrePractica) {
		this.txtNombrePractica = txtNombrePractica;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the practicas
	 */
	public List<Practica> getPracticas() {
		return practicas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param practicas
	 *            the practicas to set
	 */
	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectPractica
	 */
	public Practica getSelectPractica() {
		return selectPractica;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectPractica
	 *            the selectPractica to set
	 */
	public void setSelectPractica(Practica selectPractica) {
		this.selectPractica = selectPractica;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtNombreRadicacion
	 */
	public InputText getTxtNombreRadicacion() {
		return txtNombreRadicacion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtNombreRadicacion
	 *            the txtNombreRadicacion to set
	 */
	public void setTxtNombreRadicacion(InputText txtNombreRadicacion) {
		this.txtNombreRadicacion = txtNombreRadicacion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the radicaciones
	 */
	public List<Radiacion> getRadicaciones() {
		return radicaciones;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param radicaciones
	 *            the radicaciones to set
	 */
	public void setRadicaciones(List<Radiacion> radicaciones) {
		this.radicaciones = radicaciones;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectRadiacion
	 */
	public Radiacion getSelectRadiacion() {
		return selectRadiacion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectRadiacion
	 *            the selectRadiacion to set
	 */
	public void setSelectRadiacion(Radiacion selectRadiacion) {
		this.selectRadiacion = selectRadiacion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtNombreIngeomina
	 */
	public InputText getTxtNombreIngeomina() {
		return txtNombreIngeomina;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtNombreIngeomina
	 *            the txtNombreIngeomina to set
	 */
	public void setTxtNombreIngeomina(InputText txtNombreIngeomina) {
		this.txtNombreIngeomina = txtNombreIngeomina;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the ingeominas
	 */
	public List<Geominas> getIngeominas() {
		return ingeominas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param ingeominas
	 *            the ingeominas to set
	 */
	public void setIngeominas(List<Geominas> ingeominas) {
		this.ingeominas = ingeominas;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectIngeomina
	 */
	public Geominas getSelectIngeomina() {
		return selectIngeomina;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectIngeomina
	 *            the selectIngeomina to set
	 */
	public void setSelectIngeomina(Geominas selectIngeomina) {
		this.selectIngeomina = selectIngeomina;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtNombreContrato
	 */
	public InputText getTxtNombreContrato() {
		return txtNombreContrato;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtNombreContrato
	 *            the txtNombreContrato to set
	 */
	public void setTxtNombreContrato(InputText txtNombreContrato) {
		this.txtNombreContrato = txtNombreContrato;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the contratos
	 */
	public List<Contratos> getContratos() {
		return contratos;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param contratos
	 *            the contratos to set
	 */
	public void setContratos(List<Contratos> contratos) {
		this.contratos = contratos;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectContrato
	 */
	public Contratos getSelectContrato() {
		return selectContrato;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectContrato
	 *            the selectContrato to set
	 */
	public void setSelectContrato(Contratos selectContrato) {
		this.selectContrato = selectContrato;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the habilitarBusquedaEmpresa
	 */
	public boolean isHabilitarBusquedaEmpresa() {
		return habilitarBusquedaEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param habilitarBusquedaEmpresa
	 *            the habilitarBusquedaEmpresa to set
	 */
	public void setHabilitarBusquedaEmpresa(boolean habilitarBusquedaEmpresa) {
		this.habilitarBusquedaEmpresa = habilitarBusquedaEmpresa;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the fechaIncio
	 */
	public Calendar getFechaIncio() {
		return fechaIncio;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param fechaIncio
	 *            the fechaIncio to set
	 */
	public void setFechaIncio(Calendar fechaIncio) {
		this.fechaIncio = fechaIncio;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the fechaVence
	 */
	public Calendar getFechaVence() {
		return fechaVence;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param fechaVence
	 *            the fechaVence to set
	 */
	public void setFechaVence(Calendar fechaVence) {
		this.fechaVence = fechaVence;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtRazonSocialTabla
	 */
	public InputText getTxtRazonSocialTabla() {
		return txtRazonSocialTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtRazonSocialTabla
	 *            the txtRazonSocialTabla to set
	 */
	public void setTxtRazonSocialTabla(InputText txtRazonSocialTabla) {
		this.txtRazonSocialTabla = txtRazonSocialTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the empresasTabla
	 */
	public List<Empresas> getEmpresasTabla() {
		return empresasTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param empresasTabla
	 *            the empresasTabla to set
	 */
	public void setEmpresasTabla(List<Empresas> empresasTabla) {
		this.empresasTabla = empresasTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the selectEmpresaTabla
	 */
	public Empresas getSelectEmpresaTabla() {
		return selectEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param selectEmpresaTabla
	 *            the selectEmpresaTabla to set
	 */
	public void setSelectEmpresaTabla(Empresas selectEmpresaTabla) {
		this.selectEmpresaTabla = selectEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the habilitarBusquedaEmpresaTabla
	 */
	public boolean isHabilitarBusquedaEmpresaTabla() {
		return habilitarBusquedaEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param habilitarBusquedaEmpresaTabla
	 *            the habilitarBusquedaEmpresaTabla to set
	 */
	public void setHabilitarBusquedaEmpresaTabla(
			boolean habilitarBusquedaEmpresaTabla) {
		this.habilitarBusquedaEmpresaTabla = habilitarBusquedaEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @return the txtEmpCodTabla
	 */
	public InputText getTxtEmpCodTabla() {
		return txtEmpCodTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @param txtEmpCodTabla
	 *            the txtEmpCodTabla to set
	 */
	public void setTxtEmpCodTabla(InputText txtEmpCodTabla) {
		this.txtEmpCodTabla = txtEmpCodTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 15/11/2016
	 * @return the selectedDosimetros
	 */
	public DosimetroDTO getSelectedDosimetros() {
		return selectedDosimetros;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 15/11/2016
	 * @param selectedDosimetros
	 *            the selectedDosimetros to set
	 */
	public void setSelectedDosimetros(DosimetroDTO selectedDosimetros) {
		this.selectedDosimetros = selectedDosimetros;
	}
}

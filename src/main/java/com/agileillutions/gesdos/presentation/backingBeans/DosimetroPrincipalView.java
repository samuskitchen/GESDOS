package com.agileillutions.gesdos.presentation.backingBeans;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;

import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.dto.DosimetroDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean(name = "dosimetroPrincipalView")
@ViewScoped
public class DosimetroPrincipalView implements Serializable {

	private static final String JASPERT_REPORT_BASE_PATH = "/WEB-INF/classes/reportes/";
	private static final String CONTENT_DISPOSITION_ATTACHMENT = "attachment";
	private static final String EXCEL_EXTENSION = "xls";
	private static final String FORMATO_ASPROMEDICA_DOSIMETRIA = "formatoAspromedicaDosimetria.jasper";

	private static final long serialVersionUID = 1L;
	private InputText txtDosConNro;
	private InputText txtDosConNroDiag;
	private InputText txtTraCed;

	private List<DosimetroDTO> data;
	private DosimetroDTO selectedDosimetro;
	private DosimetroDTO entity;
	private boolean showDialog;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	// Datos de Empresa Tabla
	private InputText txtEmpCodTabla;
	private InputText txtRazonSocialTabla;
	private List<Empresas> empresasTabla;
	private Empresas selectEmpresaTabla;
	private boolean habilitarBusquedaEmpresaTabla;

	private Calendar fechaRecepcion;
	private InputText txtAnioDosimetro;
	private InputText txtMesDosimetro;

	private InputText txtEmpCodTablaDiag;
	private InputText txtRazonSocialTablaDiag;
	private List<Empresas> empresasTablaDiag;
	private Empresas selectEmpresaTablaDiag;

	private List<DosimetroDTO> selectedDosExcel;

	private InputText txtAnioDosimetroDiag;
	private InputText txtMesDosimetroDiag;
	private InputText txtConCodTablaDiag;

	public DosimetroPrincipalView() {
		super();
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @description
	 */
	@PostConstruct
	public void getParametros() {
		try {

			txtDosConNro = new InputText();
			txtEmpCodTabla = new InputText();
			txtRazonSocialTabla = new InputText();

			txtEmpCodTablaDiag = new InputText();
			txtRazonSocialTablaDiag = new InputText();
			txtDosConNroDiag = new InputText();
			txtTraCed = new InputText();
			txtAnioDosimetroDiag = new InputText();
			txtMesDosimetroDiag = new InputText();
			txtConCodTablaDiag = new InputText();

			fechaRecepcion = new Calendar();
			txtAnioDosimetro = new InputText();
			txtMesDosimetro = new InputText();

			Map<String, String> requestParameterMap = FacesContext
					.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();

			String codDosimetro = requestParameterMap.get("codDosimetro");
			String codEmpresa = requestParameterMap.get("codEmpresa");
			String codTrabajador = requestParameterMap.get("codTrabajador");
			String anioDosimetro = requestParameterMap.get("anioDosimetro");
			String mesDosimetro = requestParameterMap.get("mesDosimetro");
			String codContrato = requestParameterMap.get("codContrato"); 
			String tipo = requestParameterMap.get("tipo");

			if (null != tipo && tipo.equals("0")) {
				txtEmpCodTablaDiag.setValue(codEmpresa);
				txtDosConNroDiag.setValue(codDosimetro);
				txtTraCed.setValue(codTrabajador);
				txtAnioDosimetroDiag.setValue(anioDosimetro);
				txtMesDosimetroDiag.setValue(mesDosimetro);
				txtConCodTablaDiag.setValue(codContrato);

				List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
						.getDataDosimetroPrincipal(Long.valueOf(codEmpresa), null);
				data = dosimetroDTOs;

				consultarEmpresasTablaDiag();
			}

			int anioActual = java.util.Calendar.getInstance().get(
					java.util.Calendar.YEAR);
			txtAnioDosimetro.setValue(anioActual);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @description
	 */
	public void listener_txtCodDosimetro() {
		try {
			Long codDosimetro = Long
					.valueOf(txtDosConNro.getValue().toString());

			List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
					.getDataDosimetroPrincipal(null, codDosimetro);
			data = dosimetroDTOs;
			entity = dosimetroDTOs.get(0);
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDosConNro.setDisabled(false);
			txtEmpCodTabla.setDisabled(false);
			txtRazonSocialTabla.setDisabled(false);
		} else {
			txtDosConNro.setValue(entity.getDosConNro());
			txtEmpCodTabla.setValue(entity.getEmpCod());
			txtRazonSocialTabla.setValue(entity.getRazonSocial());

			consultarEmpresasTabla();
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
	 * @date 17/12/2016
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
			if (null != txtRazonSocialTabla
					&& null != txtRazonSocialTabla.getValue()
					&& !txtRazonSocialTabla.getValue().toString().isEmpty()) {
				String valorDepartamento = txtRazonSocialTabla.getValue()
						.toString();
				Object[] variablesEmpresa = searchByCriteriaRazonSocial("%"
						+ valorDepartamento + "%", "empRazSoc", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCodTabla.setValue(emp.getEmpCod());
						txtRazonSocialTabla.setValue(emp.getEmpRazSoc());

						List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
								.getDataDosimetroPrincipal(Long
										.valueOf(txtEmpCodTabla.getValue()
												.toString()), null);
						data = dosimetroDTOs;
						if (!dosimetroDTOs.isEmpty()) {
							txtDosConNro.setValue(dosimetroDTOs.get(0)
									.getDosCod());
						}

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmDomisetroPrincipal:txtEmpCodTabla");
						s.add("frmDomisetroPrincipal:txtRazonSocialTabla");
						s.add("frmDomisetroPrincipal:txtDosConNro");
						s.add("frmDomisetroPrincipal:idTableDosimetros");
						context.update(s);
					}
				}
			} else if (null != txtEmpCodTabla
					&& null != txtEmpCodTabla.getValue()
					&& !txtEmpCodTabla.getValue().toString().isEmpty()) {
				String valorCodEmpresa = txtEmpCodTabla.getValue().toString();
				Object[] variablesEmpresa = searchByCriteria(valorCodEmpresa,
						"empCod", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCodTabla.setValue(emp.getEmpCod());
						txtRazonSocialTabla.setValue(emp.getEmpRazSoc());

						List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
								.getDataDosimetroPrincipal(Long
										.valueOf(txtEmpCodTabla.getValue()
												.toString()), null);
						data = dosimetroDTOs;
						if (!dosimetroDTOs.isEmpty()) {
							txtDosConNro.setValue(dosimetroDTOs.get(0)
									.getDosCod());
						}

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmDomisetroPrincipal:txtEmpCodTabla");
						s.add("frmDomisetroPrincipal:txtRazonSocialTabla");
						s.add("frmDomisetroPrincipal:txtDosConNro");
						s.add("frmDomisetroPrincipal:idTableDosimetros");
						context.update(s);
					}
				}
			}

			else {
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
	 * @throws Exception
	 * @throws NumberFormatException
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectEmpresaTabla() throws NumberFormatException,
			Exception {

		if (selectEmpresaTabla != null) {
			Empresas empresasDTO = selectEmpresaTabla;
			if (empresasDTO != null && empresasDTO.getEmpCod() != null) {

				txtEmpCodTabla.setValue(empresasDTO.getEmpCod());
				txtRazonSocialTabla.setValue(empresasDTO.getEmpRazSoc());

				List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
						.getDataDosimetroPrincipal(Long.valueOf(txtEmpCodTabla
								.getValue().toString()), null);
				data = dosimetroDTOs;
				if (!dosimetroDTOs.isEmpty()) {
					txtDosConNro.setValue(dosimetroDTOs.get(0).getDosCod());
				}

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("frmDomisetroPrincipal:txtEmpCodTabla");
				s.add("frmDomisetroPrincipal:txtRazonSocialTabla");
				s.add("frmDomisetroPrincipal:txtDosConNro");
				s.add("frmDomisetroPrincipal:idTableDosimetros");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @throws Exception
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresasTabla() throws Exception {
		txtEmpCodTabla.resetValue();
		txtRazonSocialTabla.resetValue();
		txtDosConNro.resetValue();
		data = businessDelegatorView.getDataDosimetroPrincipal(null, null);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("frmDomisetroPrincipal:txtEmpCodTabla");
		s.add("frmDomisetroPrincipal:txtRazonSocialTabla");
		s.add("frmDomisetroPrincipal:txtDosConNro");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 14/11/2016
	 * @description
	 */
	public void consultarEmpresasTablaDiag() {
		try {
			if (null != txtRazonSocialTablaDiag
					&& null != txtRazonSocialTablaDiag.getValue()
					&& !txtRazonSocialTablaDiag.getValue().toString().isEmpty()) {
				String valorDepartamento = txtRazonSocialTablaDiag.getValue()
						.toString();
				Object[] variablesEmpresa = searchByCriteriaRazonSocial("%"
						+ valorDepartamento + "%", "empRazSoc", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCodTablaDiag.setValue(emp.getEmpCod());
						txtRazonSocialTablaDiag.setValue(emp.getEmpRazSoc());

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmDiagDomisetroPrincipal:txtEmpCodTablaDiag");
						s.add("frmDiagDomisetroPrincipal:txtRazonSocialTablaDiag");
						context.update(s);
					}
				}
			} else if (null != txtEmpCodTablaDiag
					&& null != txtEmpCodTablaDiag.getValue()
					&& !txtEmpCodTablaDiag.getValue().toString().isEmpty()) {
				String valorCodEmpresa = txtEmpCodTablaDiag.getValue()
						.toString();
				Object[] variablesEmpresa = searchByCriteria(valorCodEmpresa,
						"empCod", true);
				List<Empresas> empresas = businessDelegatorView
						.findByCriteriaInEmpresas(variablesEmpresa, null, null);

				if (null != empresas && empresas.size() > 0) {
					Empresas emp = empresas.get(0);
					if (emp != null && emp.getEmpCod() != null) {
						txtEmpCodTablaDiag.setValue(emp.getEmpCod());
						txtRazonSocialTablaDiag.setValue(emp.getEmpRazSoc());

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("frmDiagDomisetroPrincipal:txtEmpCodTablaDiag");
						s.add("frmDiagDomisetroPrincipal:txtRazonSocialTablaDiag");
						context.update(s);
					}
				}
			}

			else {
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
	 * @throws Exception
	 * @throws NumberFormatException
	 * @date 14/11/2016
	 * @description
	 */
	public void onRowSelectEmpresaTablaDiag() throws NumberFormatException,
			Exception {

		if (selectEmpresaTablaDiag != null) {
			Empresas empresasDTO = selectEmpresaTablaDiag;
			if (empresasDTO != null && empresasDTO.getEmpCod() != null) {

				txtEmpCodTablaDiag.setValue(empresasDTO.getEmpCod());
				txtRazonSocialTablaDiag.setValue(empresasDTO.getEmpRazSoc());

				List<DosimetroDTO> dosimetroDTOs = businessDelegatorView
						.getDataDosimetroPrincipal(Long.valueOf(txtEmpCodTabla
								.getValue().toString()), null);
				data = dosimetroDTOs;

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("frmDiagDomisetroPrincipal:txtEmpCodTablaDiag");
				s.add("frmDiagDomisetroPrincipal:txtRazonSocialTablaDiag");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @throws Exception
	 * @date 14/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaEmpresasTablaDiag() throws Exception {
		txtEmpCodTablaDiag.resetValue();
		txtRazonSocialTablaDiag.resetValue();
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("frmDiagDomisetroPrincipal:txtEmpCodTablaDiag");
		s.add("frmDiagDomisetroPrincipal:txtRazonSocialTablaDiag");
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
	public void cargarDosimetroPrincipalDialog(String bandera) {

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

		String codEmpresa = "";
		String codTrabajador = "";
		String codDosimetro = "";
		String codContrato = "";
		String anioDosimetro = "";
		String mesDosimetro = "";

		if (null != selectedDosimetro) {
			codEmpresa = selectedDosimetro.getEmpCod().toString();
			codTrabajador = selectedDosimetro.getTraCed().toString();
			codDosimetro = selectedDosimetro.getDosCod().toString();
			codContrato = selectedDosimetro.getCodContrato().toString();
			
			if(null != selectedDosimetro.getAnioEstudio()){
				anioDosimetro = selectedDosimetro.getAnioEstudio().toString();
			}
			
			if(null != selectedDosimetro.getMesEstudio()){
				mesDosimetro = selectedDosimetro.getMesEstudio().toString();
			}
			
		} else {
			FacesUtils.addErrorMessage("Por favor seleccionar una empresa");
			return;
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);

		List<String> lstParamTrabajador = new ArrayList<String>();
		lstParamTrabajador.add(codTrabajador);

		List<String> lstParamDosimetro = new ArrayList<String>();
		lstParamDosimetro.add(codDosimetro);
		
		List<String> lstParamContrato = new ArrayList<String>();
		lstParamContrato.add(codContrato);

		List<String> lstParamAnio = new ArrayList<String>();
		lstParamAnio.add(anioDosimetro);
		
		List<String> lstParamMes = new ArrayList<String>();
		lstParamMes.add(mesDosimetro);

		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("codTrabajador", lstParamTrabajador);
		params.put("codDosimetro", lstParamDosimetro);
		params.put("codContrato", lstParamContrato);
		params.put("anioDosimetro", lstParamAnio);
		params.put("mesDosimetro", lstParamMes);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog(
				"/XHTML/dialogDosimetroListMenu.xhtml", options, params);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
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
		cedTraba = txtTraCed.getValue().toString();

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(cedTraba);

		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("cedTraba", lstParamEmpresa);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/diagTraba.xhtml",
				options, params);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @description
	 * @param bandera
	 */
	public void cargarDosimetroDialog(String bandera) {

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

		String codEmpresa = "";
		String codTrabajador = "";
		String codDosimetro = "";
		codEmpresa = txtEmpCodTablaDiag.getValue().toString();
		codTrabajador = txtTraCed.getValue().toString();
		codDosimetro = txtDosConNroDiag.getValue().toString();

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
				"/XHTML/diagDosimetro.xhtml", options, params);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @description
	 * @param bandera
	 */
	public void cargarEstudioDialog(String bandera) {

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

		String codEmpresa = "";
		String codTrabajador = "";
		String codDosimetro = "";
		String estAni = "";
		String estMes = "";

		codEmpresa = txtEmpCodTablaDiag.getValue().toString();
		codTrabajador = txtTraCed.getValue().toString();
		codDosimetro = txtDosConNroDiag.getValue().toString();
		estAni = txtAnioDosimetroDiag.getValue().toString();
		estMes = txtMesDosimetroDiag.getValue().toString();

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

		RequestContext.getCurrentInstance().openDialog("/XHTML/diagEstudios.xhtml",
				options, params);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @description
	 * @param bandera
	 */
	public void cargarContratosDialog(String bandera) {

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

		String codEmpresa = "";
		String codContrato = "";
		
		codEmpresa = txtEmpCodTablaDiag.getValue().toString();
		codContrato = txtConCodTablaDiag.getValue().toString();

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

		RequestContext.getCurrentInstance().openDialog("/XHTML/diagContratos.xhtml", options, params);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 10/12/2016
	 * @description
	 */
	public void actionGenerarExcel() {
		try {
			
			if(null == selectedDosExcel) {
				FacesUtils.addErrorMessage("Por favor seleccionar un dosimetro");
				return;
			}
			
			String rutaReporte = JASPERT_REPORT_BASE_PATH
					+ FORMATO_ASPROMEDICA_DOSIMETRIA;

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext extContext = facesContext.getExternalContext();

			InputStream input = extContext.getResourceAsStream(rutaReporte);
			
			Long anioDosimetro = null;
			if(null != txtAnioDosimetroDiag.getValue()){
				anioDosimetro = Long.valueOf(txtAnioDosimetroDiag.getValue().toString());
			}
			
			Long mesDosimetro = null;
			if(null != txtMesDosimetroDiag.getValue()){
				mesDosimetro = Long.valueOf(txtMesDosimetroDiag.getValue().toString());
			}

			ByteArrayOutputStream excel = businessDelegatorView
					.generarArchivoAspromedica(input, selectedDosExcel, anioDosimetro, mesDosimetro);

			extContext.responseReset();
			extContext.responseReset();
			String nombre = "Formato_ASPROMEDICA_Dosimetria";
			setDefaultResponseHeadersForExcel(extContext,
					CONTENT_DISPOSITION_ATTACHMENT, nombre);

			OutputStream out = extContext.getResponseOutputStream();
			excel.writeTo(out);

			out.flush();
			out.close();

			facesContext.responseComplete();
		} catch (Exception e) {
			FacesUtils
					.addErrorMessage("Error generando el Excel de ASPROMEDICA");
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 10/12/2016
	 * @description
	 * @param externalContext
	 * @param contentDisposition
	 * @param fileName
	 */
	private void setDefaultResponseHeadersForExcel(
			ExternalContext externalContext, String contentDisposition,
			String fileName) {

		externalContext.setResponseContentType("application/vnd.ms-excel");
		StringBuilder contentDispositionValue = new StringBuilder();
		contentDispositionValue.append(contentDisposition);
		contentDispositionValue.append("; filename = \"");
		contentDispositionValue.append(fileName);
		contentDispositionValue.append(".");
		contentDispositionValue.append(EXCEL_EXTENSION);
		contentDispositionValue.append("\"");
		externalContext.setResponseHeader("Content-Disposition",
				contentDispositionValue.toString());
		externalContext.setResponseHeader("Cache-Control", "no-cache");
		externalContext.setResponseHeader("Pragma", "no-cache");
		externalContext.setResponseHeader("Expires", "0");

	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtDosConNro
	 */
	public InputText getTxtDosConNro() {
		return txtDosConNro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtDosConNro
	 *            the txtDosConNro to set
	 */
	public void setTxtDosConNro(InputText txtDosConNro) {
		this.txtDosConNro = txtDosConNro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the data
	 */
	public List<DosimetroDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataDosimetroPrincipal(null,
						null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param data
	 *            the data to set
	 */
	public void setData(List<DosimetroDTO> data) {
		this.data = data;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the selectedDosimetro
	 */
	public DosimetroDTO getSelectedDosimetro() {
		return selectedDosimetro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param selectedDosimetro
	 *            the selectedDosimetro to set
	 */
	public void setSelectedDosimetro(DosimetroDTO selectedDosimetro) {
		this.selectedDosimetro = selectedDosimetro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the showDialog
	 */
	public boolean isShowDialog() {
		return showDialog;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param showDialog
	 *            the showDialog to set
	 */
	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the businessDelegatorView
	 */
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param businessDelegatorView
	 *            the businessDelegatorView to set
	 */
	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtEmpCodTabla
	 */
	public InputText getTxtEmpCodTabla() {
		return txtEmpCodTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtEmpCodTabla
	 *            the txtEmpCodTabla to set
	 */
	public void setTxtEmpCodTabla(InputText txtEmpCodTabla) {
		this.txtEmpCodTabla = txtEmpCodTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtRazonSocialTabla
	 */
	public InputText getTxtRazonSocialTabla() {
		return txtRazonSocialTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtRazonSocialTabla
	 *            the txtRazonSocialTabla to set
	 */
	public void setTxtRazonSocialTabla(InputText txtRazonSocialTabla) {
		this.txtRazonSocialTabla = txtRazonSocialTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the empresasTabla
	 */
	public List<Empresas> getEmpresasTabla() {
		return empresasTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param empresasTabla
	 *            the empresasTabla to set
	 */
	public void setEmpresasTabla(List<Empresas> empresasTabla) {
		this.empresasTabla = empresasTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the selectEmpresaTabla
	 */
	public Empresas getSelectEmpresaTabla() {
		return selectEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param selectEmpresaTabla
	 *            the selectEmpresaTabla to set
	 */
	public void setSelectEmpresaTabla(Empresas selectEmpresaTabla) {
		this.selectEmpresaTabla = selectEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the habilitarBusquedaEmpresaTabla
	 */
	public boolean isHabilitarBusquedaEmpresaTabla() {
		return habilitarBusquedaEmpresaTabla;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
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
	 * @date 17/12/2016
	 * @return the fechaRecepcion
	 */
	public Calendar getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param fechaRecepcion
	 *            the fechaRecepcion to set
	 */
	public void setFechaRecepcion(Calendar fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtAnioDosimetro
	 */
	public InputText getTxtAnioDosimetro() {
		return txtAnioDosimetro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtAnioDosimetro
	 *            the txtAnioDosimetro to set
	 */
	public void setTxtAnioDosimetro(InputText txtAnioDosimetro) {
		this.txtAnioDosimetro = txtAnioDosimetro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtMesDosimetro
	 */
	public InputText getTxtMesDosimetro() {
		return txtMesDosimetro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtMesDosimetro
	 *            the txtMesDosimetro to set
	 */
	public void setTxtMesDosimetro(InputText txtMesDosimetro) {
		this.txtMesDosimetro = txtMesDosimetro;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtEmpCodTablaDiag
	 */
	public InputText getTxtEmpCodTablaDiag() {
		return txtEmpCodTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtEmpCodTablaDiag
	 *            the txtEmpCodTablaDiag to set
	 */
	public void setTxtEmpCodTablaDiag(InputText txtEmpCodTablaDiag) {
		this.txtEmpCodTablaDiag = txtEmpCodTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtRazonSocialTablaDiag
	 */
	public InputText getTxtRazonSocialTablaDiag() {
		return txtRazonSocialTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtRazonSocialTablaDiag
	 *            the txtRazonSocialTablaDiag to set
	 */
	public void setTxtRazonSocialTablaDiag(InputText txtRazonSocialTablaDiag) {
		this.txtRazonSocialTablaDiag = txtRazonSocialTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the empresasTablaDiag
	 */
	public List<Empresas> getEmpresasTablaDiag() {
		return empresasTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param empresasTablaDiag
	 *            the empresasTablaDiag to set
	 */
	public void setEmpresasTablaDiag(List<Empresas> empresasTablaDiag) {
		this.empresasTablaDiag = empresasTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the selectEmpresaTablaDiag
	 */
	public Empresas getSelectEmpresaTablaDiag() {
		return selectEmpresaTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param selectEmpresaTablaDiag
	 *            the selectEmpresaTablaDiag to set
	 */
	public void setSelectEmpresaTablaDiag(Empresas selectEmpresaTablaDiag) {
		this.selectEmpresaTablaDiag = selectEmpresaTablaDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtTraCed
	 */
	public InputText getTxtTraCed() {
		return txtTraCed;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtTraCed
	 *            the txtTraCed to set
	 */
	public void setTxtTraCed(InputText txtTraCed) {
		this.txtTraCed = txtTraCed;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtDosConNroDiag
	 */
	public InputText getTxtDosConNroDiag() {
		return txtDosConNroDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtDosConNroDiag
	 *            the txtDosConNroDiag to set
	 */
	public void setTxtDosConNroDiag(InputText txtDosConNroDiag) {
		this.txtDosConNroDiag = txtDosConNroDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the selectedDosExcel
	 */
	public List<DosimetroDTO> getSelectedDosExcel() {
		return selectedDosExcel;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param selectedDosExcel
	 *            the selectedDosExcel to set
	 */
	public void setSelectedDosExcel(List<DosimetroDTO> selectedDosExcel) {
		this.selectedDosExcel = selectedDosExcel;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtAnioDosimetroDiag
	 */
	public InputText getTxtAnioDosimetroDiag() {
		return txtAnioDosimetroDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtAnioDosimetroDiag
	 *            the txtAnioDosimetroDiag to set
	 */
	public void setTxtAnioDosimetroDiag(InputText txtAnioDosimetroDiag) {
		this.txtAnioDosimetroDiag = txtAnioDosimetroDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @return the txtMesDosimetroDiag
	 */
	public InputText getTxtMesDosimetroDiag() {
		return txtMesDosimetroDiag;
	}

	/**
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 17/12/2016
	 * @param txtMesDosimetroDiag
	 *            the txtMesDosimetroDiag to set
	 */
	public void setTxtMesDosimetroDiag(InputText txtMesDosimetroDiag) {
		this.txtMesDosimetroDiag = txtMesDosimetroDiag;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 18/12/2016 
	 * @return the txtConCodTablaDiag 
	 */
	public InputText getTxtConCodTablaDiag() {
		return txtConCodTablaDiag;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 18/12/2016 
	 * @param txtConCodTablaDiag the txtConCodTablaDiag to set 
	 */
	public void setTxtConCodTablaDiag(InputText txtConCodTablaDiag) {
		this.txtConCodTablaDiag = txtConCodTablaDiag;
	}

}

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
import org.primefaces.context.RequestContext;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.Factura;
import com.agileillutions.gesdos.modelo.FacturaId;
import com.agileillutions.gesdos.modelo.dto.FacturaDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class FacturaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtFacVal;
    private InputText txtConNro;
    private InputText txtEmpCod;
    private InputText txtFacNro;
    private Calendar txtFacFech;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<FacturaDTO> data;
    private FacturaDTO selectedFactura;
    private Factura entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    
    private InputText txtEmpCodTabla;
	private InputText txtRazonSocialTabla;
	private List<Empresas> empresasTabla;
	private Empresas selectEmpresaTabla;
	
	private boolean botonGuardar;
	private boolean botonNuevo;
	private boolean botonModificar;
	private boolean botonBorrar;
	private boolean botonVisualizar;
	private boolean botonDosimetros;
	private boolean botonTrabajadores;
	private boolean disableBotonesBusqueda;
	
	private String codEmpresa;
	private String codContrato;
	private String numFactura;
	
	private Calendar txtConFec;
	private InputText txtSaldoFactura;

    public FacturaView() {
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
			String numFactura = requestParameterMap.get("numFactura"); 
			String tipo = requestParameterMap.get("tipo");
			
			this.codEmpresa = codEmpresa;
			this.codContrato = codContrato;
			this.numFactura = numFactura;

			if (null == tipo || "".equals(tipo)) {
				txtConNro = new InputText();
				txtEmpCod = new InputText();

				txtConNro.setValue(codContrato);
				txtEmpCod.setValue(codEmpresa);

				if (null != codEmpresa && !codEmpresa.isEmpty()) {
					List<FacturaDTO> facturaDTOs = businessDelegatorView.getDataFacturaEmpresaContrato(Long.valueOf(codEmpresa), Long.valueOf(codContrato));
					data = facturaDTOs;
				} 
				
				ejecutarControlesBusquedas();
			}
			// Tipo Visualizar
			else if ("2".equals(tipo)) {
				if (null != codContrato
						&& null != codEmpresa) {
					txtConNro = new InputText();
					txtEmpCod = new InputText();
					txtFacNro = new InputText();

					txtConNro.setValue(codContrato);
					txtEmpCod.setValue(codEmpresa);
					txtFacNro.setValue(numFactura);
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
					txtFacNro = new InputText();

					txtConNro.setValue(codContrato);
					txtEmpCod.setValue(codEmpresa);
					txtFacNro.setValue(numFactura);
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

				if(txtFacNro.getValue() == null){
					txtFacNro.setValue(this.numFactura);
				}
				
				if(txtConNro.getValue() == null){
					txtConNro.setValue(this.codContrato);
				}
				
				if(txtEmpCod.getValue() == null){
					txtEmpCod.setValue(this.codEmpresa);
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
        selectedFactura = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedFactura = null;

        if (txtFacVal != null) {
            txtFacVal.setValue(null);
            txtFacVal.setDisabled(true);
        }

        if (txtFacFech != null) {
            txtFacFech.setValue(null);
            txtFacFech.setDisabled(true);
        }

        if (txtConNro != null) {
            txtConNro.setValue(null);
            txtConNro.setDisabled(false);
        }

        if (txtEmpCod != null) {
            txtEmpCod.setValue(null);
            txtEmpCod.setDisabled(false);
        }

        if (txtFacNro != null) {
            txtFacNro.setValue(null);
            txtFacNro.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFacFech() {
        Date inputDate = (Date) txtFacFech.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            FacturaId id = new FacturaId();
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            id.setFacNro((((txtFacNro.getValue()) == null) ||
                (txtFacNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtFacNro));
            entity = (id != null) ? businessDelegatorView.getFactura(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtFacVal.setDisabled(false);
            txtFacFech.setDisabled(false);
            txtConNro.setDisabled(false);
            txtEmpCod.setDisabled(false);
            txtFacNro.setDisabled(false);
            txtConFec.setDisabled(false);
            txtSaldoFactura.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFacFech.setValue(entity.getFacFech());
            txtFacVal.setValue(entity.getFacVal());
            txtConNro.setValue(entity.getId().getConNro());
            txtEmpCod.setValue(entity.getId().getEmpCod());
            txtFacNro.setValue(entity.getId().getFacNro());
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedFactura = (FacturaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedFactura"));
        txtFacFech.setValue(selectedFactura.getFacFech());
        txtFacFech.setDisabled(false);
        txtFacVal.setValue(selectedFactura.getFacVal());
        txtFacVal.setDisabled(false);
        txtConNro.setValue(selectedFactura.getConNro());
        txtConNro.setDisabled(true);
        txtEmpCod.setValue(selectedFactura.getEmpCod());
        txtEmpCod.setDisabled(true);
        txtFacNro.setValue(selectedFactura.getFacNro());
        txtFacNro.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedFactura == null) && (entity == null)) {
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
            entity = new Factura();

            FacturaId id = new FacturaId();
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            id.setFacNro((((txtFacNro.getValue()) == null) ||
                (txtFacNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtFacNro));

            entity.setFacFech(FacesUtils.checkDate(txtFacFech));
            entity.setFacVal(FacesUtils.checkDouble(txtFacVal));
            entity.setId(id);
            businessDelegatorView.saveFactura(entity);
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
                FacturaId id = new FacturaId();
                id.setConNro(selectedFactura.getConNro());
                id.setEmpCod(selectedFactura.getEmpCod());
                id.setFacNro(selectedFactura.getFacNro());
                entity = businessDelegatorView.getFactura(id);
            }

            entity.setFacFech(FacesUtils.checkDate(txtFacFech));
            entity.setFacVal(FacesUtils.checkDouble(txtFacVal));
            businessDelegatorView.updateFactura(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedFactura = (FacturaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedFactura"));

            FacturaId id = new FacturaId();
            id.setConNro(selectedFactura.getConNro());
            id.setEmpCod(selectedFactura.getEmpCod());
            id.setFacNro(selectedFactura.getFacNro());
            entity = businessDelegatorView.getFactura(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            FacturaId id = new FacturaId();
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            id.setFacNro((((txtFacNro.getValue()) == null) ||
                (txtFacNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtFacNro));
            entity = businessDelegatorView.getFactura(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteFactura(entity);
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
            selectedFactura = (FacturaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedFactura"));

            FacturaId id = new FacturaId();
            id.setConNro(selectedFactura.getConNro());
            id.setEmpCod(selectedFactura.getEmpCod());
            id.setFacNro(selectedFactura.getFacNro());
            entity = businessDelegatorView.getFactura(id);
            businessDelegatorView.deleteFactura(entity);
            data.remove(selectedFactura);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long conNro, Long empCod, Long facNro,
        Date facFech, Double facVal) throws Exception {
        try {
            entity.setFacFech(FacesUtils.checkDate(facFech));
            entity.setFacVal(FacesUtils.checkDouble(facVal));
            businessDelegatorView.updateFactura(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("FacturaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }
    
    /**
     * 
     * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
     * @date 15/12/2016
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
	 * @date 15/12/2016
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
	 * @date 15/12/2016
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
	 * @date 15/12/2016
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
	 * @date 15/12/2016
	 * @description
	 */
	public void actionCerrarBusqueda() {

	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016
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
		String numFactura = "";
		if (null != selectedFactura) {
			codEmpresa = selectedFactura.getEmpCod().toString();
			codContrato = selectedFactura.getConNro().toString();
			numFactura = selectedFactura.getFacNro().toString();
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);
		
		List<String> lstParamContrato = new ArrayList<String>();
		lstParamContrato.add(codContrato);
		
		List<String> lstParamFactura = new ArrayList<String>();
		lstParamFactura.add(numFactura);

		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("codContrato", lstParamContrato);
		params.put("numFactura", lstParamFactura);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/factura.xhtml", options, params);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016
	 * @description
	 */
	public void inicializaCampos(){
		
		if (txtFacVal == null) {
            txtFacVal = new InputText();
        }

        if (txtConNro == null) {
            txtConNro = new InputText();
        }

        if (txtEmpCod == null) {
            txtEmpCod = new InputText();
        }

        if (txtFacNro == null) {
            txtFacNro = new InputText();
        }

        if (txtFacFech == null) {
            txtFacFech = new Calendar();
        }
		
		if (txtConFec == null) {
			txtConFec = new Calendar();
		}
		
		if (txtSaldoFactura == null) {
			txtSaldoFactura = new InputText();
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
		txtConFec.setDisabled(true);
		txtSaldoFactura.setDisabled(true);
		
		txtFacVal.setDisabled(false);
        txtFacFech.setDisabled(false);
        txtConNro.setDisabled(false);
        txtEmpCod.setDisabled(false);
        txtFacNro.setDisabled(false);
        

		RequestContext.getCurrentInstance().update("formDialogFacturas");
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
		txtConFec.setDisabled(true);
		txtSaldoFactura.setDisabled(true);
		txtFacVal.setDisabled(true);
        txtFacFech.setDisabled(true);
        txtConNro.setDisabled(true);
        txtEmpCod.setDisabled(true);
        txtFacNro.setDisabled(true);

		RequestContext.getCurrentInstance().update("formDialogFacturas");
	}

    public InputText getTxtFacVal() {
        return txtFacVal;
    }

    public void setTxtFacVal(InputText txtFacVal) {
        this.txtFacVal = txtFacVal;
    }

    public Calendar getTxtFacFech() {
        return txtFacFech;
    }

    public void setTxtFacFech(Calendar txtFacFech) {
        this.txtFacFech = txtFacFech;
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

    public InputText getTxtFacNro() {
        return txtFacNro;
    }

    public void setTxtFacNro(InputText txtFacNro) {
        this.txtFacNro = txtFacNro;
    }

    public List<FacturaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataFactura();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<FacturaDTO> facturaDTO) {
        this.data = facturaDTO;
    }

    public FacturaDTO getSelectedFactura() {
        return selectedFactura;
    }

    public void setSelectedFactura(FacturaDTO factura) {
        this.selectedFactura = factura;
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
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the txtEmpCodTabla 
	 */
	public InputText getTxtEmpCodTabla() {
		return txtEmpCodTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param txtEmpCodTabla the txtEmpCodTabla to set 
	 */
	public void setTxtEmpCodTabla(InputText txtEmpCodTabla) {
		this.txtEmpCodTabla = txtEmpCodTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the txtRazonSocialTabla 
	 */
	public InputText getTxtRazonSocialTabla() {
		return txtRazonSocialTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param txtRazonSocialTabla the txtRazonSocialTabla to set 
	 */
	public void setTxtRazonSocialTabla(InputText txtRazonSocialTabla) {
		this.txtRazonSocialTabla = txtRazonSocialTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the empresasTabla 
	 */
	public List<Empresas> getEmpresasTabla() {
		return empresasTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param empresasTabla the empresasTabla to set 
	 */
	public void setEmpresasTabla(List<Empresas> empresasTabla) {
		this.empresasTabla = empresasTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the selectEmpresaTabla 
	 */
	public Empresas getSelectEmpresaTabla() {
		return selectEmpresaTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param selectEmpresaTabla the selectEmpresaTabla to set 
	 */
	public void setSelectEmpresaTabla(Empresas selectEmpresaTabla) {
		this.selectEmpresaTabla = selectEmpresaTabla;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the botonGuardar 
	 */
	public boolean isBotonGuardar() {
		return botonGuardar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param botonGuardar the botonGuardar to set 
	 */
	public void setBotonGuardar(boolean botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the botonNuevo 
	 */
	public boolean isBotonNuevo() {
		return botonNuevo;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param botonNuevo the botonNuevo to set 
	 */
	public void setBotonNuevo(boolean botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the botonModificar 
	 */
	public boolean isBotonModificar() {
		return botonModificar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param botonModificar the botonModificar to set 
	 */
	public void setBotonModificar(boolean botonModificar) {
		this.botonModificar = botonModificar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the botonBorrar 
	 */
	public boolean isBotonBorrar() {
		return botonBorrar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param botonBorrar the botonBorrar to set 
	 */
	public void setBotonBorrar(boolean botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the botonVisualizar 
	 */
	public boolean isBotonVisualizar() {
		return botonVisualizar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param botonVisualizar the botonVisualizar to set 
	 */
	public void setBotonVisualizar(boolean botonVisualizar) {
		this.botonVisualizar = botonVisualizar;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the botonDosimetros 
	 */
	public boolean isBotonDosimetros() {
		return botonDosimetros;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param botonDosimetros the botonDosimetros to set 
	 */
	public void setBotonDosimetros(boolean botonDosimetros) {
		this.botonDosimetros = botonDosimetros;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the botonTrabajadores 
	 */
	public boolean isBotonTrabajadores() {
		return botonTrabajadores;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param botonTrabajadores the botonTrabajadores to set 
	 */
	public void setBotonTrabajadores(boolean botonTrabajadores) {
		this.botonTrabajadores = botonTrabajadores;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the disableBotonesBusqueda 
	 */
	public boolean isDisableBotonesBusqueda() {
		return disableBotonesBusqueda;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param disableBotonesBusqueda the disableBotonesBusqueda to set 
	 */
	public void setDisableBotonesBusqueda(boolean disableBotonesBusqueda) {
		this.disableBotonesBusqueda = disableBotonesBusqueda;
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

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the numFactura 
	 */
	public String getNumFactura() {
		return numFactura;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param numFactura the numFactura to set 
	 */
	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}


	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the txtConFec 
	 */
	public Calendar getTxtConFec() {
		return txtConFec;
	}


	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param txtConFec the txtConFec to set 
	 */
	public void setTxtConFec(Calendar txtConFec) {
		this.txtConFec = txtConFec;
	}


	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @return the txtSaldoFactura 
	 */
	public InputText getTxtSaldoFactura() {
		return txtSaldoFactura;
	}


	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/12/2016 
	 * @param txtSaldoFactura the txtSaldoFactura to set 
	 */
	public void setTaxtSaldoFactura(InputText txtSaldoFactura) {
		this.txtSaldoFactura = txtSaldoFactura;
	}
}

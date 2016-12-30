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
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Ciuda;
import com.agileillutions.gesdos.modelo.Depar;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.dto.EmpresasDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class EmpresasView implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputText txtCiuCod;
	private InputText txtDepCod;
	private InputText txtEmpApe1con;
	private InputText txtEmpApe1con2;
	private InputText txtEmpCarCon;
	private InputText txtEmpCarCon2;
	private InputText txtEmpCel;
	private InputText txtEmpCel1;
	private InputText txtEmpCel2;
	private InputText txtEmpConMail;
	private InputText txtEmpConMail2;
	private InputText txtEmpDir;
	private String txtEmpEst;
	private InputText txtEmpFax;
	private InputText txtEmpMail;
	private InputText txtEmpNit;
	private InputText txtEmpNomCom2;
	private InputText txtEmpNomCon;
	private InputText txtEmpObs;
	private InputText txtEmpRazSoc;
	private InputText txtEmpTel;
	private InputText txtEmpCod;
	private Calendar txtEmpFecVin;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<EmpresasDTO> data;
	private EmpresasDTO selectedEmpresas;
	private Empresas entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private List<Depar> departamentos;
	private Depar selectedDepartamento;
	private InputText txtDepNombre;
	private SelectOneMenu cbOpcionEstado;

	private List<Ciuda> ciudades;
	private Ciuda selectedCiuda;
	private InputText txtCiudaNombre;

	private EmpresasDTO selectedEmpresa;
	
	private boolean botonGuardar;
	private boolean botonModificar;
	private boolean botonBorrar;
	private boolean botonVisualizar;
	private boolean botonDosimetros;
	private boolean botonTrabajadores;
	private boolean disableBotonesBusqueda;
	
	

	public EmpresasView() {
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

			String codEmpresa = requestParameterMap.get("CodEmpresa");
			String tipo = requestParameterMap.get("tipo");
			
			if("2".equals(tipo)){
				if (null != codEmpresa) {
					txtEmpCod = new InputText();
					txtEmpCod.setValue(codEmpresa);
					listener_txtId();
					modoVisualizar();
				}
			}else if("1".equals(tipo)){
				if (null != codEmpresa) {
					txtEmpCod = new InputText();
					txtEmpCod.setValue(codEmpresa);
					listener_txtId();
					modoModificar();
				}
			}else{
				btnSave.setValue("Guardar");
				botonGuardar = true;
				botonModificar = false;
				botonBorrar = false;
				botonVisualizar = false;
				botonDosimetros = false;
				botonTrabajadores = false;
				disableBotonesBusqueda = false;
				RequestContext.getCurrentInstance().update("formDialog");
				//action_clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			EmpresasDTO empresasDTO = (EmpresasDTO) e.getObject();

			if (txtCiuCod == null) {
				txtCiuCod = new InputText();
			}

			txtCiuCod.setValue(empresasDTO.getCiuCod());

			if (txtDepCod == null) {
				txtDepCod = new InputText();
			}

			txtDepCod.setValue(empresasDTO.getDepCod());

			if (txtEmpApe1con == null) {
				txtEmpApe1con = new InputText();
			}

			txtEmpApe1con.setValue(empresasDTO.getEmpApe1con());

			if (txtEmpApe1con2 == null) {
				txtEmpApe1con2 = new InputText();
			}

			txtEmpApe1con2.setValue(empresasDTO.getEmpApe1con2());

			if (txtEmpCarCon == null) {
				txtEmpCarCon = new InputText();
			}

			txtEmpCarCon.setValue(empresasDTO.getEmpCarCon());

			if (txtEmpCarCon2 == null) {
				txtEmpCarCon2 = new InputText();
			}

			txtEmpCarCon2.setValue(empresasDTO.getEmpCarCon2());

			if (txtEmpCel == null) {
				txtEmpCel = new InputText();
			}

			txtEmpCel.setValue(empresasDTO.getEmpCel());

			if (txtEmpCel1 == null) {
				txtEmpCel1 = new InputText();
			}

			txtEmpCel1.setValue(empresasDTO.getEmpCel1());

			if (txtEmpCel2 == null) {
				txtEmpCel2 = new InputText();
			}

			txtEmpCel2.setValue(empresasDTO.getEmpCel2());

			if (txtEmpConMail == null) {
				txtEmpConMail = new InputText();
			}

			txtEmpConMail.setValue(empresasDTO.getEmpConMail());

			if (txtEmpConMail2 == null) {
				txtEmpConMail2 = new InputText();
			}

			txtEmpConMail2.setValue(empresasDTO.getEmpConMail2());

			if (txtEmpDir == null) {
				txtEmpDir = new InputText();
			}

			if (txtEmpFax == null) {
				txtEmpFax = new InputText();
			}

			txtEmpFax.setValue(empresasDTO.getEmpFax());

			if (txtEmpMail == null) {
				txtEmpMail = new InputText();
			}

			txtEmpMail.setValue(empresasDTO.getEmpMail());

			if (txtEmpNit == null) {
				txtEmpNit = new InputText();
			}

			txtEmpNit.setValue(empresasDTO.getEmpNit());

			if (txtEmpNomCom2 == null) {
				txtEmpNomCom2 = new InputText();
			}

			txtEmpNomCom2.setValue(empresasDTO.getEmpNomCom2());

			if (txtEmpNomCon == null) {
				txtEmpNomCon = new InputText();
			}

			txtEmpNomCon.setValue(empresasDTO.getEmpNomCon());

			if (txtEmpRazSoc == null) {
				txtEmpRazSoc = new InputText();
			}

			txtEmpRazSoc.setValue(empresasDTO.getEmpRazSoc());

			if (txtEmpTel == null) {
				txtEmpTel = new InputText();
			}

			txtEmpTel.setValue(empresasDTO.getEmpTel());

			if (txtEmpCod == null) {
				txtEmpCod = new InputText();
			}

			txtEmpCod.setValue(empresasDTO.getEmpCod());

			if (txtEmpFecVin == null) {
				txtEmpFecVin = new Calendar();
			}

			txtEmpFecVin.setValue(empresasDTO.getEmpFecVin());

			Long empCod = FacesUtils.checkLong(txtEmpCod);
			entity = businessDelegatorView.getEmpresas(empCod);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedEmpresas = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedEmpresa = null;
		
		if (txtCiuCod != null) {
            txtCiuCod.setValue(null);
            txtCiuCod.setDisabled(true);
        }

        if (txtDepCod != null) {
            txtDepCod.setValue(null);
            txtDepCod.setDisabled(true);
        }

        if (txtEmpApe1con != null) {
            txtEmpApe1con.setValue(null);
            txtEmpApe1con.setDisabled(true);
        }

        if (txtEmpApe1con2 != null) {
            txtEmpApe1con2.setValue(null);
            txtEmpApe1con2.setDisabled(true);
        }

        if (txtEmpCarCon != null) {
            txtEmpCarCon.setValue(null);
            txtEmpCarCon.setDisabled(true);
        }

        if (txtEmpCarCon2 != null) {
            txtEmpCarCon2.setValue(null);
            txtEmpCarCon2.setDisabled(true);
        }

        if (txtEmpCel != null) {
            txtEmpCel.setValue(null);
            txtEmpCel.setDisabled(true);
        }

        if (txtEmpCel1 != null) {
            txtEmpCel1.setValue(null);
            txtEmpCel1.setDisabled(true);
        }

        if (txtEmpCel2 != null) {
            txtEmpCel2.setValue(null);
            txtEmpCel2.setDisabled(true);
        }

        if (txtEmpConMail != null) {
            txtEmpConMail.setValue(null);
            txtEmpConMail.setDisabled(true);
        }

        if (txtEmpConMail2 != null) {
            txtEmpConMail2.setValue(null);
            txtEmpConMail2.setDisabled(true);
        }

        if (txtEmpDir != null) {
            txtEmpDir.setValue(null);
            txtEmpDir.setDisabled(true);
        }

        if (txtEmpFax != null) {
            txtEmpFax.setValue(null);
            txtEmpFax.setDisabled(true);
        }

        if (txtEmpMail != null) {
            txtEmpMail.setValue(null);
            txtEmpMail.setDisabled(true);
        }

        if (txtEmpNit != null) {
            txtEmpNit.setValue(null);
            txtEmpNit.setDisabled(true);
        }

        if (txtEmpNomCom2 != null) {
            txtEmpNomCom2.setValue(null);
            txtEmpNomCom2.setDisabled(true);
        }

        if (txtEmpNomCon != null) {
            txtEmpNomCon.setValue(null);
            txtEmpNomCon.setDisabled(true);
        }

        if (txtEmpObs != null) {
            txtEmpObs.setValue(null);
            txtEmpObs.setDisabled(true);
        }

        if (txtEmpRazSoc != null) {
            txtEmpRazSoc.setValue(null);
            txtEmpRazSoc.setDisabled(true);
        }

        if (txtEmpTel != null) {
            txtEmpTel.setValue(null);
            txtEmpTel.setDisabled(true);
        }

        if (txtEmpFecVin != null) {
            txtEmpFecVin.setValue(null);
            txtEmpFecVin.setDisabled(true);
        }

        if (txtEmpCod != null) {
            txtEmpCod.setValue(null);
            txtEmpCod.setDisabled(true);
        }
        
        if (null != cbOpcionEstado) {
			cbOpcionEstado.resetValue();
			cbOpcionEstado.setDisabled(true);
		}

		//FacesUtils.resetComponente(":frm:");

		return "";
	}

	public void listener_txtEmpFecVin() {
		Date inputDate = (Date) txtEmpFecVin.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Selected Date "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Long empCod = FacesUtils.checkLong(txtEmpCod);
			entity = (empCod != null) ? businessDelegatorView
					.getEmpresas(empCod) : null;
		} catch (Exception e) {
			entity = null;
		}
		
		if (entity == null) {
            txtCiuCod.setDisabled(false);
            txtDepCod.setDisabled(false);
            txtEmpApe1con.setDisabled(false);
            txtEmpApe1con2.setDisabled(false);
            txtEmpCarCon.setDisabled(false);
            txtEmpCarCon2.setDisabled(false);
            txtEmpCel.setDisabled(false);
            txtEmpCel1.setDisabled(false);
            txtEmpCel2.setDisabled(false);
            txtEmpConMail.setDisabled(false);
            txtEmpConMail2.setDisabled(false);
            txtEmpDir.setDisabled(false);
            txtEmpFax.setDisabled(false);
            txtEmpMail.setDisabled(false);
            txtEmpNit.setDisabled(false);
            txtEmpNomCom2.setDisabled(false);
            txtEmpNomCon.setDisabled(false);
            txtEmpRazSoc.setDisabled(false);
            txtEmpTel.setDisabled(false);
            txtEmpFecVin.setDisabled(false);
            txtEmpCod.setDisabled(false);
            cbOpcionEstado.setDisabled(false);
    		txtDepNombre.setDisabled(false);
    		txtCiudaNombre.setDisabled(false);
        } else {
        	txtCiuCod.setValue(entity.getCiuCod());
    		txtDepCod.setValue(entity.getDepCod());
    		txtEmpApe1con.setValue(entity.getEmpApe1con());
    		txtEmpApe1con2.setValue(entity.getEmpApe1con2());
    		txtEmpCarCon.setValue(entity.getEmpCarCon());
    		txtEmpCarCon2.setValue(entity.getEmpCarCon2());
    		txtEmpCel.setValue(entity.getEmpCel());
    		txtEmpCel1.setValue(entity.getEmpCel1());
    		txtEmpCel2.setValue(entity.getEmpCel2());
    		txtEmpConMail.setValue(entity.getEmpConMail());
    		txtEmpConMail2.setValue(entity.getEmpConMail2());
    		txtEmpDir.setValue(entity.getEmpDir());
    		cbOpcionEstado.setValue(entity.getEmpEst());
    		txtEmpFax.setValue(entity.getEmpFax());
    		txtEmpFecVin.setValue(entity.getEmpFecVin());
    		txtEmpMail.setValue(entity.getEmpMail());
    		txtEmpNit.setValue(entity.getEmpNit());
    		txtEmpNomCom2.setValue(entity.getEmpNomCom2());
    		txtEmpNomCon.setValue(entity.getEmpNomCon());
    		txtEmpRazSoc.setValue(entity.getEmpRazSoc());
    		txtEmpTel.setValue(entity.getEmpTel());
    		txtEmpCod.setValue(entity.getEmpCod());
    		consultarDepartamentos();
    		consultarCiudades();
        }
	}

	public String action_edit(ActionEvent evt) {
		selectedEmpresas = (EmpresasDTO) (evt.getComponent().getAttributes()
				.get("selectedEmpresas"));
		txtCiuCod.setValue(selectedEmpresas.getCiuCod());
		txtCiuCod.setDisabled(false);
		txtDepCod.setValue(selectedEmpresas.getDepCod());
		txtDepCod.setDisabled(false);
		txtEmpApe1con.setValue(selectedEmpresas.getEmpApe1con());
		txtEmpApe1con.setDisabled(false);
		txtEmpApe1con2.setValue(selectedEmpresas.getEmpApe1con2());
		txtEmpApe1con2.setDisabled(false);
		txtEmpCarCon.setValue(selectedEmpresas.getEmpCarCon());
		txtEmpCarCon.setDisabled(false);
		txtEmpCarCon2.setValue(selectedEmpresas.getEmpCarCon2());
		txtEmpCarCon2.setDisabled(false);
		txtEmpCel.setValue(selectedEmpresas.getEmpCel());
		txtEmpCel.setDisabled(false);
		txtEmpCel1.setValue(selectedEmpresas.getEmpCel1());
		txtEmpCel1.setDisabled(false);
		txtEmpCel2.setValue(selectedEmpresas.getEmpCel2());
		txtEmpCel2.setDisabled(false);
		txtEmpConMail.setValue(selectedEmpresas.getEmpConMail());
		txtEmpConMail.setDisabled(false);
		txtEmpConMail2.setValue(selectedEmpresas.getEmpConMail2());
		txtEmpConMail2.setDisabled(false);
		txtEmpDir.setValue(selectedEmpresas.getEmpDir());
		txtEmpDir.setDisabled(false);
		txtEmpFax.setValue(selectedEmpresas.getEmpFax());
		txtEmpFax.setDisabled(false);
		txtEmpFecVin.setValue(selectedEmpresas.getEmpFecVin());
		txtEmpFecVin.setDisabled(false);
		txtEmpMail.setValue(selectedEmpresas.getEmpMail());
		txtEmpMail.setDisabled(false);
		txtEmpNit.setValue(selectedEmpresas.getEmpNit());
		txtEmpNit.setDisabled(false);
		txtEmpNomCom2.setValue(selectedEmpresas.getEmpNomCom2());
		txtEmpNomCom2.setDisabled(false);
		txtEmpNomCon.setValue(selectedEmpresas.getEmpNomCon());
		txtEmpNomCon.setDisabled(false);
		txtEmpRazSoc.setValue(selectedEmpresas.getEmpRazSoc());
		txtEmpRazSoc.setDisabled(false);
		txtEmpTel.setValue(selectedEmpresas.getEmpTel());
		txtEmpTel.setDisabled(false);
		txtEmpCod.setValue(selectedEmpresas.getEmpCod());
		txtEmpCod.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedEmpresas == null) && (entity == null)) {
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
			entity = new Empresas();

			Long empCod = FacesUtils.checkLong(txtEmpCod);

			if(null != empCod){
				entity.setCiuCod(FacesUtils.checkLong(txtCiuCod));
				entity.setDepCod(FacesUtils.checkLong(txtDepCod));
				entity.setEmpApe1con(FacesUtils.checkString(txtEmpApe1con));
				entity.setEmpApe1con2(FacesUtils.checkString(txtEmpApe1con2));
				entity.setEmpCarCon(FacesUtils.checkString(txtEmpCarCon));
				entity.setEmpCarCon2(FacesUtils.checkString(txtEmpCarCon2));
				entity.setEmpCel(FacesUtils.checkString(txtEmpCel));
				entity.setEmpCel1(FacesUtils.checkString(txtEmpCel1));
				entity.setEmpCel2(FacesUtils.checkString(txtEmpCel2));
				entity.setEmpCod(empCod);
				entity.setEmpConMail(FacesUtils.checkString(txtEmpConMail));
				entity.setEmpConMail2(FacesUtils.checkString(txtEmpConMail2));
				entity.setEmpDir(FacesUtils.checkString(txtEmpDir));
				entity.setEmpEst(txtEmpEst);
				entity.setEmpFax(FacesUtils.checkString(txtEmpFax));
				entity.setEmpFecVin(FacesUtils.checkDate(txtEmpFecVin));
				entity.setEmpMail(FacesUtils.checkString(txtEmpMail));
				entity.setEmpNit(FacesUtils.checkLong(txtEmpNit));
				entity.setEmpNomCom2(FacesUtils.checkString(txtEmpNomCom2));
				entity.setEmpNomCon(FacesUtils.checkString(txtEmpNomCon));
				entity.setEmpRazSoc(FacesUtils.checkString(txtEmpRazSoc));
				entity.setEmpTel(FacesUtils.checkString(txtEmpTel));
				businessDelegatorView.saveEmpresas(entity);
				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
				action_clear();
				closeDialog();
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
				Long empCod = FacesUtils.checkLong(txtEmpCod);
				entity = businessDelegatorView.getEmpresas(empCod);
			}

			entity.setCiuCod(FacesUtils.checkLong(txtCiuCod));
			entity.setDepCod(FacesUtils.checkLong(txtDepCod));
			entity.setEmpApe1con(FacesUtils.checkString(txtEmpApe1con));
			entity.setEmpApe1con2(FacesUtils.checkString(txtEmpApe1con2));
			entity.setEmpCarCon(FacesUtils.checkString(txtEmpCarCon));
			entity.setEmpCarCon2(FacesUtils.checkString(txtEmpCarCon2));
			entity.setEmpCel(FacesUtils.checkString(txtEmpCel));
			entity.setEmpCel1(FacesUtils.checkString(txtEmpCel1));
			entity.setEmpCel2(FacesUtils.checkString(txtEmpCel2));
			entity.setEmpConMail(FacesUtils.checkString(txtEmpConMail));
			entity.setEmpConMail2(FacesUtils.checkString(txtEmpConMail2));
			entity.setEmpDir(FacesUtils.checkString(txtEmpDir));
			entity.setEmpEst(txtEmpEst);
			entity.setEmpFax(FacesUtils.checkString(txtEmpFax));
			entity.setEmpFecVin(FacesUtils.checkDate(txtEmpFecVin));
			entity.setEmpMail(FacesUtils.checkString(txtEmpMail));
			entity.setEmpNit(FacesUtils.checkLong(txtEmpNit));
			entity.setEmpNomCom2(FacesUtils.checkString(txtEmpNomCom2));
			entity.setEmpNomCon(FacesUtils.checkString(txtEmpNomCon));
			entity.setEmpRazSoc(FacesUtils.checkString(txtEmpRazSoc));
			entity.setEmpTel(FacesUtils.checkString(txtEmpTel));
			businessDelegatorView.updateEmpresas(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable() {
		try {
			Long empCod = new Long(selectedEmpresa.getEmpCod());
			txtEmpCod.setValue(empCod);
			entity = businessDelegatorView.getEmpresas(empCod);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long empCod = FacesUtils.checkLong(txtEmpCod);
			entity = businessDelegatorView.getEmpresas(empCod);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteEmpresas(entity);
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
			selectedEmpresas = (EmpresasDTO) (evt.getComponent()
					.getAttributes().get("selectedEmpresas"));

			Long empCod = new Long(selectedEmpresas.getEmpCod());
			entity = businessDelegatorView.getEmpresas(empCod);
			businessDelegatorView.deleteEmpresas(entity);
			data.remove(selectedEmpresas);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(Long ciuCod, Long depCod,
			String empApe1con, String empApe1con2, String empCarCon,
			String empCarCon2, String empCel, String empCel1, String empCel2,
			Long empCod, String empConMail, String empConMail2, String empDir,
			String empEst, String empFax, Date empFecVin, String empMail,
			Long empNit, String empNomCom2, String empNomCon, String empObs,
			String empRazSoc, String empTel) throws Exception {
		try {
			entity.setCiuCod(FacesUtils.checkLong(ciuCod));
			entity.setDepCod(FacesUtils.checkLong(depCod));
			entity.setEmpApe1con(FacesUtils.checkString(empApe1con));
			entity.setEmpApe1con2(FacesUtils.checkString(empApe1con2));
			entity.setEmpCarCon(FacesUtils.checkString(empCarCon));
			entity.setEmpCarCon2(FacesUtils.checkString(empCarCon2));
			entity.setEmpCel(FacesUtils.checkString(empCel));
			entity.setEmpCel1(FacesUtils.checkString(empCel1));
			entity.setEmpCel2(FacesUtils.checkString(empCel2));
			entity.setEmpConMail(FacesUtils.checkString(empConMail));
			entity.setEmpConMail2(FacesUtils.checkString(empConMail2));
			entity.setEmpDir(FacesUtils.checkString(empDir));
			entity.setEmpEst(FacesUtils.checkString(empEst));
			entity.setEmpFax(FacesUtils.checkString(empFax));
			entity.setEmpFecVin(FacesUtils.checkDate(empFecVin));
			entity.setEmpMail(FacesUtils.checkString(empMail));
			entity.setEmpNit(FacesUtils.checkLong(empNit));
			entity.setEmpNomCom2(FacesUtils.checkString(empNomCom2));
			entity.setEmpNomCon(FacesUtils.checkString(empNomCon));
			entity.setEmpObs(FacesUtils.checkString(empObs));
			entity.setEmpRazSoc(FacesUtils.checkString(empRazSoc));
			entity.setEmpTel(FacesUtils.checkString(empTel));
			businessDelegatorView.updateEmpresas(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("EmpresasView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 13/11/2016
	 * @description
	 * @param id
	 * @param campoDeClase
	 * @param boleana
	 * @return
	 */
	public Object[] searchByCriteriaDepar(String id, String campoDeClase,
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
	 * @date 13/11/2016
	 * @description
	 */
	public void consultarDepartamentos() {
		try {
			if (null != txtDepCod.getValue()) {
				Object[] variablesDepartamento = searchByCriteriaDepar(
						txtDepCod.getValue().toString(), "depCod", true);
				List<Depar> depars = businessDelegatorView
						.findByCriteriaInDepar(variablesDepartamento, null,
								null);

				if (null != depars && depars.size() > 0) {
					Depar depar = depars.get(0);
					if (depar != null && depar.getDepCod() != null) {
						txtCiuCod.setDisabled(false);
						txtDepCod.setValue(depar.getDepCod());
						txtDepNombre.setValue(depar.getDepNom());
						txtDepNombre.setDisabled(true);

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialog:txtDepCod");
						s.add("formDialog:txtDepNombre");
						s.add("formDialog:txtCiuCod");
						context.update(s);
					}
				}else {
					departamentos = businessDelegatorView.getDepar();
				}
			} else {
				departamentos = businessDelegatorView.getDepar();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 13/11/2016
	 * @description
	 */
	public void actionCerrarBusqueda() {

	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 13/11/2016
	 * @description
	 */
	public void onRowSelectDepartamento() {

		if (selectedDepartamento != null) {

			Depar depar = selectedDepartamento;

			if (depar != null && depar.getDepCod() != null) {

				txtCiuCod.setDisabled(false);
				txtDepCod.setValue(depar.getDepCod());
				txtDepNombre.setValue(depar.getDepNom());
				txtDepNombre.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialog:txtDepCod");
				s.add("formDialog:txtDepNombre");
				s.add("formDialog:txtCiuCod");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 13/11/2016
	 * @description
	 * @param id
	 * @param campoDeClase
	 * @param boleana
	 * @param id2
	 * @param campoDeClase2
	 * @param boleana2
	 * @return
	 */
	public Object[] searchByCriteriaCiuda(String id, String campoDeClase,
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
	 * @date 13/11/2016
	 * @description
	 */
	public void consultarCiudades() {
		try {

			String codDepar = txtDepCod.getValue().toString();
			String codCiuda = txtCiuCod.getValue() != null ? txtCiuCod
					.getValue().toString() : null;

			Object[] variables = null;
			if (null != codCiuda && !codCiuda.isEmpty()) {
				variables = searchByCriteriaCiuda(codCiuda, "id.ciuCod", true,
						codDepar, "id.depCod", false);

				List<Ciuda> ciudas = businessDelegatorView
						.findByCriteriaInCiuda(variables, null, null);

				if (null != ciudas && ciudas.size() > 0) {
					Ciuda ciuda = ciudas.get(0);
					if (ciuda != null && ciuda.getId() != null) {

						txtCiuCod.setValue(ciuda.getId().getCiuCod());
						txtCiudaNombre.setValue(ciuda.getCiuNom());
						txtCiudaNombre.isDisabled();

						RequestContext context = RequestContext
								.getCurrentInstance();
						List<String> s = new ArrayList<String>();
						s.add("formDialog:txtCiuCod");
						s.add("formDialog:txtCiudaNombre");
						context.update(s);
					}
				}else {
					variables = searchByCriteriaDepar(codDepar, "id.depCod", true);
					ciudades = businessDelegatorView.findByCriteriaInCiuda(
							variables, null, null);
				}
			} else {
				variables = searchByCriteriaDepar(codDepar, "id.depCod", true);
				ciudades = businessDelegatorView.findByCriteriaInCiuda(
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
	 * @date 13/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaDepartamento() {
		txtCiuCod.setDisabled(true);
		txtDepCod.resetValue();
		txtDepNombre.resetValue();
		txtDepNombre.setDisabled(true);

		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialog:txtDepCod");
		s.add("formDialog:txtDepNombre");
		s.add("formDialog:txtCiuCod");
		context.update(s);
		limpiarControlBusquedaCiudad();
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 13/11/2016
	 * @description
	 */
	public void limpiarControlBusquedaCiudad() {
		txtCiuCod.resetValue();
		txtCiudaNombre.resetValue();
		txtCiudaNombre.setDisabled(true);
		RequestContext context = RequestContext.getCurrentInstance();
		List<String> s = new ArrayList<String>();
		s.add("formDialog:txtCiuCod");
		s.add("formDialog:txtCiudaNombre");
		context.update(s);
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 13/11/2016
	 * @description
	 */
	public void onRowSelectCiudad() {

		if (selectedCiuda != null) {
			Ciuda ciuda = selectedCiuda;
			if (ciuda != null && ciuda.getId() != null) {

				txtCiuCod.setValue(ciuda.getId().getCiuCod());
				txtCiudaNombre.setValue(ciuda.getCiuNom());
				txtCiudaNombre.setDisabled(true);

				RequestContext context = RequestContext.getCurrentInstance();
				List<String> s = new ArrayList<String>();
				s.add("formDialog:txtCiuCod");
				s.add("formDialog:txtCiudaNombre");
				context.update(s);
			}
		}
	}

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava
	 *         Suarez</a>
	 * @date 13/11/2016
	 * @description
	 */
	public void cargarEmpresaDialog(String bandera) {

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
		if(null != selectedEmpresa){
			codEmpresa = selectedEmpresa.getEmpCod().toString();			
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);
		
		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("CodEmpresa", lstParamEmpresa);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/empresas.xhtml",
				options, params);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 13/11/2016
	 * @description
	 */
	public void inicializaCampos(){
		
		if (null == cbOpcionEstado) {
			cbOpcionEstado = new SelectOneMenu();
		}
		
		if(btnSave == null){
			btnSave = new CommandButton();
		}
		
		if(btnDelete == null){
			btnDelete = new CommandButton();
		}
		
		if(btnModify == null){
			btnModify = new CommandButton();
		}
		
		if(btnClear == null){
			btnClear = new CommandButton();
		}
		
		if (txtCiuCod == null) {
			txtCiuCod = new InputText();
			txtCiuCod.setDisabled(true);
		}

		if (txtDepCod == null) {
			txtDepCod = new InputText();
		}

		if (txtEmpApe1con == null) {
			txtEmpApe1con = new InputText();
		}

		if (txtEmpApe1con2 == null) {
			txtEmpApe1con2 = new InputText();
		}

		if (txtEmpCarCon == null) {
			txtEmpCarCon = new InputText();
		}

		if (txtEmpCarCon2 == null) {
			txtEmpCarCon2 = new InputText();
		}
		if (txtEmpCel == null) {
			txtEmpCel = new InputText();
		}

		if (txtEmpCel1 == null) {
			txtEmpCel1 = new InputText();
		}

		if (txtEmpCel2 == null) {
			txtEmpCel2 = new InputText();
		}
		if (txtEmpConMail == null) {
			txtEmpConMail = new InputText();
		}

		if (txtEmpConMail2 == null) {
			txtEmpConMail2 = new InputText();
		}

		if (txtEmpDir == null) {
			txtEmpDir = new InputText();
		}

		if (txtEmpFax == null) {
			txtEmpFax = new InputText();
		}

		if (txtEmpMail == null) {
			txtEmpMail = new InputText();
		}

		if (txtEmpNit == null) {
			txtEmpNit = new InputText();
		}

		if (txtEmpNomCom2 == null) {
			txtEmpNomCom2 = new InputText();
		}

		if (txtEmpNomCon == null) {
			txtEmpNomCon = new InputText();
		}

		if (txtEmpRazSoc == null) {
			txtEmpRazSoc = new InputText();
		}

		if (txtEmpTel == null) {
			txtEmpTel = new InputText();
		}

		if (txtEmpCod == null) {
			txtEmpCod = new InputText();
		}

		if (txtEmpFecVin == null) {
			txtEmpFecVin = new Calendar();
		}
		
		if(txtDepNombre == null){
			txtDepNombre = new InputText();
			txtDepNombre.setDisabled(true);
		}
		
		if(txtCiudaNombre == null){
			txtCiudaNombre = new InputText();
			txtCiudaNombre.setDisabled(true);
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 13/11/2016
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
		txtCiuCod.setDisabled(true);
		
		RequestContext.getCurrentInstance().update("formDialog");
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

		txtCiuCod.setDisabled(false);
        txtDepCod.setDisabled(false);
        txtEmpApe1con.setDisabled(false);
        txtEmpApe1con2.setDisabled(false);
        txtEmpCarCon.setDisabled(false);
        txtEmpCarCon2.setDisabled(false);
        txtEmpCel.setDisabled(false);
        txtEmpCel1.setDisabled(false);
        txtEmpCel2.setDisabled(false);
        txtEmpConMail.setDisabled(false);
        txtEmpConMail2.setDisabled(false);
        txtEmpDir.setDisabled(false);
        txtEmpFax.setDisabled(false);
        txtEmpMail.setDisabled(false);
        txtEmpNit.setDisabled(false);
        txtEmpNomCom2.setDisabled(false);
        txtEmpNomCon.setDisabled(false);
        txtEmpRazSoc.setDisabled(false);
        txtEmpTel.setDisabled(false);
        txtEmpFecVin.setDisabled(false);
        txtEmpCod.setDisabled(false);
        cbOpcionEstado.setDisabled(false);
		txtDepNombre.setDisabled(false);
		txtCiudaNombre.setDisabled(false);
		
		RequestContext.getCurrentInstance().update("formDialog");
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description 
	 * @throws Exception
	 */
	public void closeDialog() throws Exception {
		data = businessDelegatorView.getDataEmpresas();
		RequestContext.getCurrentInstance().update("frm");
    }
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016
	 * @description 
	 * @param bandera
	 */
	public void cargarTrabajadoresDialog(String bandera) {

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
		if(null != selectedEmpresa){
			codEmpresa = selectedEmpresa.getEmpCod().toString();			
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);
		
		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/dialogTrabaListDataTable.xhtml",
				options, params);
	}
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 15/11/2016
	 * @description 
	 * @param bandera
	 */
	public void cargarDosimetrosDialog(String bandera) {

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
		if(null != selectedEmpresa){
			codEmpresa = selectedEmpresa.getEmpCod().toString();			
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);
		
		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/dosimetroListDataTable.xhtml",
				options, params);
	}
	
	public void cargarContratosDialog(String bandera) {

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
		if(null != selectedEmpresa){
			codEmpresa = selectedEmpresa.getEmpCod().toString();			
		}

		List<String> lstParamEmpresa = new ArrayList<String>();
		lstParamEmpresa.add(codEmpresa);
		
		List<String> lstParamTipo = new ArrayList<String>();
		lstParamTipo.add(bandera);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("codEmpresa", lstParamEmpresa);
		params.put("tipo", lstParamTipo);

		RequestContext.getCurrentInstance().openDialog("/XHTML/contratosListDataTable.xhtml",
				options, params);
	}

	public InputText getTxtCiuCod() {
		return txtCiuCod;
	}

	public void setTxtCiuCod(InputText txtCiuCod) {
		this.txtCiuCod = txtCiuCod;
	}

	public InputText getTxtDepCod() {
		return txtDepCod;
	}

	public void setTxtDepCod(InputText txtDepCod) {
		this.txtDepCod = txtDepCod;
	}

	public InputText getTxtEmpApe1con() {
		return txtEmpApe1con;
	}

	public void setTxtEmpApe1con(InputText txtEmpApe1con) {
		this.txtEmpApe1con = txtEmpApe1con;
	}

	public InputText getTxtEmpApe1con2() {
		return txtEmpApe1con2;
	}

	public void setTxtEmpApe1con2(InputText txtEmpApe1con2) {
		this.txtEmpApe1con2 = txtEmpApe1con2;
	}

	public InputText getTxtEmpCarCon() {
		return txtEmpCarCon;
	}

	public void setTxtEmpCarCon(InputText txtEmpCarCon) {
		this.txtEmpCarCon = txtEmpCarCon;
	}

	public InputText getTxtEmpCarCon2() {
		return txtEmpCarCon2;
	}

	public void setTxtEmpCarCon2(InputText txtEmpCarCon2) {
		this.txtEmpCarCon2 = txtEmpCarCon2;
	}

	public InputText getTxtEmpCel() {
		return txtEmpCel;
	}

	public void setTxtEmpCel(InputText txtEmpCel) {
		this.txtEmpCel = txtEmpCel;
	}

	public InputText getTxtEmpCel1() {
		return txtEmpCel1;
	}

	public void setTxtEmpCel1(InputText txtEmpCel1) {
		this.txtEmpCel1 = txtEmpCel1;
	}

	public InputText getTxtEmpCel2() {
		return txtEmpCel2;
	}

	public void setTxtEmpCel2(InputText txtEmpCel2) {
		this.txtEmpCel2 = txtEmpCel2;
	}

	public InputText getTxtEmpConMail() {
		return txtEmpConMail;
	}

	public void setTxtEmpConMail(InputText txtEmpConMail) {
		this.txtEmpConMail = txtEmpConMail;
	}

	public InputText getTxtEmpConMail2() {
		return txtEmpConMail2;
	}

	public void setTxtEmpConMail2(InputText txtEmpConMail2) {
		this.txtEmpConMail2 = txtEmpConMail2;
	}

	public InputText getTxtEmpDir() {
		return txtEmpDir;
	}

	public void setTxtEmpDir(InputText txtEmpDir) {
		this.txtEmpDir = txtEmpDir;
	}

	public String getTxtEmpEst() {
		return txtEmpEst;
	}

	public void setTxtEmpEst(String txtEmpEst) {
		this.txtEmpEst = txtEmpEst;
	}

	public InputText getTxtEmpFax() {
		return txtEmpFax;
	}

	public void setTxtEmpFax(InputText txtEmpFax) {
		this.txtEmpFax = txtEmpFax;
	}

	public InputText getTxtEmpMail() {
		return txtEmpMail;
	}

	public void setTxtEmpMail(InputText txtEmpMail) {
		this.txtEmpMail = txtEmpMail;
	}

	public InputText getTxtEmpNit() {
		return txtEmpNit;
	}

	public void setTxtEmpNit(InputText txtEmpNit) {
		this.txtEmpNit = txtEmpNit;
	}

	public InputText getTxtEmpNomCom2() {
		return txtEmpNomCom2;
	}

	public void setTxtEmpNomCom2(InputText txtEmpNomCom2) {
		this.txtEmpNomCom2 = txtEmpNomCom2;
	}

	public InputText getTxtEmpNomCon() {
		return txtEmpNomCon;
	}

	public void setTxtEmpNomCon(InputText txtEmpNomCon) {
		this.txtEmpNomCon = txtEmpNomCon;
	}

	public InputText getTxtEmpObs() {
		return txtEmpObs;
	}

	public void setTxtEmpObs(InputText txtEmpObs) {
		this.txtEmpObs = txtEmpObs;
	}

	public InputText getTxtEmpRazSoc() {
		return txtEmpRazSoc;
	}

	public void setTxtEmpRazSoc(InputText txtEmpRazSoc) {
		this.txtEmpRazSoc = txtEmpRazSoc;
	}

	public InputText getTxtEmpTel() {
		return txtEmpTel;
	}

	public void setTxtEmpTel(InputText txtEmpTel) {
		this.txtEmpTel = txtEmpTel;
	}

	public Calendar getTxtEmpFecVin() {
		return txtEmpFecVin;
	}

	public void setTxtEmpFecVin(Calendar txtEmpFecVin) {
		this.txtEmpFecVin = txtEmpFecVin;
	}

	public InputText getTxtEmpCod() {
		return txtEmpCod;
	}

	public void setTxtEmpCod(InputText txtEmpCod) {
		this.txtEmpCod = txtEmpCod;
	}

	public List<EmpresasDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataEmpresas();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<EmpresasDTO> empresasDTO) {
		this.data = empresasDTO;
	}

	public EmpresasDTO getSelectedEmpresas() {
		return selectedEmpresas;
	}

	public void setSelectedEmpresas(EmpresasDTO empresas) {
		this.selectedEmpresas = empresas;
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

	public List<Depar> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Depar> departamentos) {
		this.departamentos = departamentos;
	}

	public Depar getSelectedDepartamento() {
		return selectedDepartamento;
	}

	public void setSelectedDepartamento(Depar selectedDepartamento) {
		this.selectedDepartamento = selectedDepartamento;
	}

	public InputText getTxtDepNombre() {
		return txtDepNombre;
	}

	public void setTxtDepNombre(InputText txtDepNombre) {
		this.txtDepNombre = txtDepNombre;
	}

	public SelectOneMenu getCbOpcionEstado() {
		return cbOpcionEstado;
	}

	public void setCbOpcionEstado(SelectOneMenu cbOpcionEstado) {
		this.cbOpcionEstado = cbOpcionEstado;
	}

	public List<Ciuda> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciuda> ciudades) {
		this.ciudades = ciudades;
	}

	public Ciuda getSelectedCiuda() {
		return selectedCiuda;
	}

	public void setSelectedCiuda(Ciuda selectedCiuda) {
		this.selectedCiuda = selectedCiuda;
	}

	public InputText getTxtCiudaNombre() {
		return txtCiudaNombre;
	}

	public void setTxtCiudaNombre(InputText txtCiudaNombre) {
		this.txtCiudaNombre = txtCiudaNombre;
	}

	public EmpresasDTO getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public void setSelectedEmpresa(EmpresasDTO selectedEmpresa) {
		this.selectedEmpresa = selectedEmpresa;
	}

	public boolean isBotonDosimetros() {
		return botonDosimetros;
	}

	public void setBotonDosimetros(boolean botonDosimetros) {
		this.botonDosimetros = botonDosimetros;
	}

	public boolean isBotonTrabajadores() {
		return botonTrabajadores;
	}

	public void setBotonTrabajadores(boolean botonTrabajadores) {
		this.botonTrabajadores = botonTrabajadores;
	}

	public boolean isBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(boolean botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public boolean isBotonModificar() {
		return botonModificar;
	}

	public void setBotonModificar(boolean botonModificar) {
		this.botonModificar = botonModificar;
	}

	public boolean isBotonBorrar() {
		return botonBorrar;
	}

	public void setBotonBorrar(boolean botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	public boolean isBotonVisualizar() {
		return botonVisualizar;
	}

	public void setBotonVisualizar(boolean botonVisualizar) {
		this.botonVisualizar = botonVisualizar;
	}

	public boolean isDisableBotonesBusqueda() {
		return disableBotonesBusqueda;
	}

	public void setDisableBotonesBusqueda(boolean disableBotonesBusqueda) {
		this.disableBotonesBusqueda = disableBotonesBusqueda;
	}
}
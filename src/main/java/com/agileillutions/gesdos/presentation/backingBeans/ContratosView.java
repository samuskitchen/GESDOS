package com.agileillutions.gesdos.presentation.backingBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Contratos;
import com.agileillutions.gesdos.modelo.ContratosId;
import com.agileillutions.gesdos.modelo.dto.ContratosDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ContratosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtConDosi;
    private InputText txtConDura;
    private InputText txtConEst;
    private InputText txtConNroFac;
    private InputText txtConObser;
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

    public ContratosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ContratosDTO contratosDTO = (ContratosDTO) e.getObject();

            if (txtConDosi == null) {
                txtConDosi = new InputText();
            }

            txtConDosi.setValue(contratosDTO.getConDosi());

            if (txtConDura == null) {
                txtConDura = new InputText();
            }

            txtConDura.setValue(contratosDTO.getConDura());

            if (txtConEst == null) {
                txtConEst = new InputText();
            }

            txtConEst.setValue(contratosDTO.getConEst());

            if (txtConNroFac == null) {
                txtConNroFac = new InputText();
            }

            txtConNroFac.setValue(contratosDTO.getConNroFac());

            if (txtConObser == null) {
                txtConObser = new InputText();
            }

            txtConObser.setValue(contratosDTO.getConObser());

            if (txtConTipo == null) {
                txtConTipo = new InputText();
            }

            txtConTipo.setValue(contratosDTO.getConTipo());

            if (txtConUsu == null) {
                txtConUsu = new InputText();
            }

            txtConUsu.setValue(contratosDTO.getConUsu());

            if (txtConVal == null) {
                txtConVal = new InputText();
            }

            txtConVal.setValue(contratosDTO.getConVal());

            if (txtDetConCod == null) {
                txtDetConCod = new InputText();
            }

            txtDetConCod.setValue(contratosDTO.getDetConCod());

            if (txtSocCod == null) {
                txtSocCod = new InputText();
            }

            txtSocCod.setValue(contratosDTO.getSocCod());

            if (txtConNro == null) {
                txtConNro = new InputText();
            }

            txtConNro.setValue(contratosDTO.getConNro());

            if (txtEmpCod == null) {
                txtEmpCod = new InputText();
            }

            txtEmpCod.setValue(contratosDTO.getEmpCod());

            if (txtConFec == null) {
                txtConFec = new Calendar();
            }

            txtConFec.setValue(contratosDTO.getConFec());

            if (txtConFecFac == null) {
                txtConFecFac = new Calendar();
            }

            txtConFecFac.setValue(contratosDTO.getConFecFac());

            ContratosId id = new ContratosId();
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            entity = businessDelegatorView.getContratos(id);

            action_modify();
        } catch (Exception ex) {
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

        if (txtConEst != null) {
            txtConEst.setValue(null);
            txtConEst.setDisabled(true);
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
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtConFecFac() {
        Date inputDate = (Date) txtConFecFac.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            ContratosId id = new ContratosId();
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            entity = (id != null) ? businessDelegatorView.getContratos(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtConDosi.setDisabled(false);
            txtConDura.setDisabled(false);
            txtConEst.setDisabled(false);
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
            txtConDosi.setDisabled(false);
            txtConDura.setValue(entity.getConDura());
            txtConDura.setDisabled(false);
            txtConEst.setValue(entity.getConEst());
            txtConEst.setDisabled(false);
            txtConFec.setValue(entity.getConFec());
            txtConFec.setDisabled(false);
            txtConFecFac.setValue(entity.getConFecFac());
            txtConFecFac.setDisabled(false);
            txtConNroFac.setValue(entity.getConNroFac());
            txtConNroFac.setDisabled(false);
            txtConObser.setValue(entity.getConObser());
            txtConObser.setDisabled(false);
            txtConTipo.setValue(entity.getConTipo());
            txtConTipo.setDisabled(false);
            txtConUsu.setValue(entity.getConUsu());
            txtConUsu.setDisabled(false);
            txtConVal.setValue(entity.getConVal());
            txtConVal.setDisabled(false);
            txtDetConCod.setValue(entity.getDetConCod());
            txtDetConCod.setDisabled(false);
            txtSocCod.setValue(entity.getSocCod());
            txtSocCod.setDisabled(false);
            txtConNro.setValue(entity.getId().getConNro());
            txtConNro.setDisabled(true);
            txtEmpCod.setValue(entity.getId().getEmpCod());
            txtEmpCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedContratos = (ContratosDTO) (evt.getComponent().getAttributes()
                                               .get("selectedContratos"));
        txtConDosi.setValue(selectedContratos.getConDosi());
        txtConDosi.setDisabled(false);
        txtConDura.setValue(selectedContratos.getConDura());
        txtConDura.setDisabled(false);
        txtConEst.setValue(selectedContratos.getConEst());
        txtConEst.setDisabled(false);
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
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));

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
            selectedContratos = (ContratosDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedContratos"));

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
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
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
            selectedContratos = (ContratosDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedContratos"));

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

    public String action_modifyWitDTO(Long conNro, Long empCod, Long conDosi,
        Long conDura, String conEst, Date conFec, Date conFecFac,
        Long conNroFac, String conObser, String conTipo, Long conUsu,
        Long conVal, Long detConCod, String socCod) throws Exception {
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
            //renderManager.getOnDemandRenderer("ContratosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
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

    public InputText getTxtConEst() {
        return txtConEst;
    }

    public void setTxtConEst(InputText txtConEst) {
        this.txtConEst = txtConEst;
    }

    public InputText getTxtConNroFac() {
        return txtConNroFac;
    }

    public void setTxtConNroFac(InputText txtConNroFac) {
        this.txtConNroFac = txtConNroFac;
    }

    public InputText getTxtConObser() {
        return txtConObser;
    }

    public void setTxtConObser(InputText txtConObser) {
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
}

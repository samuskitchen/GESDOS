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
import com.agileillutions.gesdos.modelo.Facturapago;
import com.agileillutions.gesdos.modelo.FacturapagoId;
import com.agileillutions.gesdos.modelo.dto.FacturapagoDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class FacturapagoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtFacPagRet;
    private InputText txtFacPagVal;
    private InputText txtConNro;
    private InputText txtEmpCod;
    private InputText txtFacNro;
    private InputText txtFacPagNro;
    private Calendar txtFacPagFec;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<FacturapagoDTO> data;
    private FacturapagoDTO selectedFacturapago;
    private Facturapago entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public FacturapagoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            FacturapagoDTO facturapagoDTO = (FacturapagoDTO) e.getObject();

            if (txtFacPagRet == null) {
                txtFacPagRet = new InputText();
            }

            txtFacPagRet.setValue(facturapagoDTO.getFacPagRet());

            if (txtFacPagVal == null) {
                txtFacPagVal = new InputText();
            }

            txtFacPagVal.setValue(facturapagoDTO.getFacPagVal());

            if (txtConNro == null) {
                txtConNro = new InputText();
            }

            txtConNro.setValue(facturapagoDTO.getConNro());

            if (txtEmpCod == null) {
                txtEmpCod = new InputText();
            }

            txtEmpCod.setValue(facturapagoDTO.getEmpCod());

            if (txtFacNro == null) {
                txtFacNro = new InputText();
            }

            txtFacNro.setValue(facturapagoDTO.getFacNro());

            if (txtFacPagNro == null) {
                txtFacPagNro = new InputText();
            }

            txtFacPagNro.setValue(facturapagoDTO.getFacPagNro());

            if (txtFacPagFec == null) {
                txtFacPagFec = new Calendar();
            }

            txtFacPagFec.setValue(facturapagoDTO.getFacPagFec());

            FacturapagoId id = new FacturapagoId();
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
            id.setFacPagNro((((txtFacPagNro.getValue()) == null) ||
                (txtFacPagNro.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtFacPagNro));
            entity = businessDelegatorView.getFacturapago(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedFacturapago = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedFacturapago = null;

        if (txtFacPagRet != null) {
            txtFacPagRet.setValue(null);
            txtFacPagRet.setDisabled(true);
        }

        if (txtFacPagVal != null) {
            txtFacPagVal.setValue(null);
            txtFacPagVal.setDisabled(true);
        }

        if (txtFacPagFec != null) {
            txtFacPagFec.setValue(null);
            txtFacPagFec.setDisabled(true);
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

        if (txtFacPagNro != null) {
            txtFacPagNro.setValue(null);
            txtFacPagNro.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFacPagFec() {
        Date inputDate = (Date) txtFacPagFec.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            FacturapagoId id = new FacturapagoId();
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
            id.setFacPagNro((((txtFacPagNro.getValue()) == null) ||
                (txtFacPagNro.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtFacPagNro));
            entity = (id != null) ? businessDelegatorView.getFacturapago(id)
                                  : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtFacPagRet.setDisabled(false);
            txtFacPagVal.setDisabled(false);
            txtFacPagFec.setDisabled(false);
            txtConNro.setDisabled(false);
            txtEmpCod.setDisabled(false);
            txtFacNro.setDisabled(false);
            txtFacPagNro.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFacPagFec.setValue(entity.getFacPagFec());
            txtFacPagFec.setDisabled(false);
            txtFacPagRet.setValue(entity.getFacPagRet());
            txtFacPagRet.setDisabled(false);
            txtFacPagVal.setValue(entity.getFacPagVal());
            txtFacPagVal.setDisabled(false);
            txtConNro.setValue(entity.getId().getConNro());
            txtConNro.setDisabled(true);
            txtEmpCod.setValue(entity.getId().getEmpCod());
            txtEmpCod.setDisabled(true);
            txtFacNro.setValue(entity.getId().getFacNro());
            txtFacNro.setDisabled(true);
            txtFacPagNro.setValue(entity.getId().getFacPagNro());
            txtFacPagNro.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedFacturapago = (FacturapagoDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedFacturapago"));
        txtFacPagFec.setValue(selectedFacturapago.getFacPagFec());
        txtFacPagFec.setDisabled(false);
        txtFacPagRet.setValue(selectedFacturapago.getFacPagRet());
        txtFacPagRet.setDisabled(false);
        txtFacPagVal.setValue(selectedFacturapago.getFacPagVal());
        txtFacPagVal.setDisabled(false);
        txtConNro.setValue(selectedFacturapago.getConNro());
        txtConNro.setDisabled(true);
        txtEmpCod.setValue(selectedFacturapago.getEmpCod());
        txtEmpCod.setDisabled(true);
        txtFacNro.setValue(selectedFacturapago.getFacNro());
        txtFacNro.setDisabled(true);
        txtFacPagNro.setValue(selectedFacturapago.getFacPagNro());
        txtFacPagNro.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedFacturapago == null) && (entity == null)) {
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
            entity = new Facturapago();

            FacturapagoId id = new FacturapagoId();
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
            id.setFacPagNro((((txtFacPagNro.getValue()) == null) ||
                (txtFacPagNro.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtFacPagNro));

            entity.setFacPagFec(FacesUtils.checkDate(txtFacPagFec));
            entity.setFacPagRet(FacesUtils.checkDouble(txtFacPagRet));
            entity.setFacPagVal(FacesUtils.checkDouble(txtFacPagVal));
            entity.setId(id);
            businessDelegatorView.saveFacturapago(entity);
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
                FacturapagoId id = new FacturapagoId();
                id.setConNro(selectedFacturapago.getConNro());
                id.setEmpCod(selectedFacturapago.getEmpCod());
                id.setFacNro(selectedFacturapago.getFacNro());
                id.setFacPagNro(selectedFacturapago.getFacPagNro());
                entity = businessDelegatorView.getFacturapago(id);
            }

            entity.setFacPagFec(FacesUtils.checkDate(txtFacPagFec));
            entity.setFacPagRet(FacesUtils.checkDouble(txtFacPagRet));
            entity.setFacPagVal(FacesUtils.checkDouble(txtFacPagVal));
            businessDelegatorView.updateFacturapago(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedFacturapago = (FacturapagoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedFacturapago"));

            FacturapagoId id = new FacturapagoId();
            id.setConNro(selectedFacturapago.getConNro());
            id.setEmpCod(selectedFacturapago.getEmpCod());
            id.setFacNro(selectedFacturapago.getFacNro());
            id.setFacPagNro(selectedFacturapago.getFacPagNro());
            entity = businessDelegatorView.getFacturapago(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            FacturapagoId id = new FacturapagoId();
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
            id.setFacPagNro((((txtFacPagNro.getValue()) == null) ||
                (txtFacPagNro.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtFacPagNro));
            entity = businessDelegatorView.getFacturapago(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteFacturapago(entity);
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
            selectedFacturapago = (FacturapagoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedFacturapago"));

            FacturapagoId id = new FacturapagoId();
            id.setConNro(selectedFacturapago.getConNro());
            id.setEmpCod(selectedFacturapago.getEmpCod());
            id.setFacNro(selectedFacturapago.getFacNro());
            id.setFacPagNro(selectedFacturapago.getFacPagNro());
            entity = businessDelegatorView.getFacturapago(id);
            businessDelegatorView.deleteFacturapago(entity);
            data.remove(selectedFacturapago);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long conNro, Long empCod, Long facNro,
        Long facPagNro, Date facPagFec, Double facPagRet, Double facPagVal)
        throws Exception {
        try {
            entity.setFacPagFec(FacesUtils.checkDate(facPagFec));
            entity.setFacPagRet(FacesUtils.checkDouble(facPagRet));
            entity.setFacPagVal(FacesUtils.checkDouble(facPagVal));
            businessDelegatorView.updateFacturapago(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("FacturapagoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtFacPagRet() {
        return txtFacPagRet;
    }

    public void setTxtFacPagRet(InputText txtFacPagRet) {
        this.txtFacPagRet = txtFacPagRet;
    }

    public InputText getTxtFacPagVal() {
        return txtFacPagVal;
    }

    public void setTxtFacPagVal(InputText txtFacPagVal) {
        this.txtFacPagVal = txtFacPagVal;
    }

    public Calendar getTxtFacPagFec() {
        return txtFacPagFec;
    }

    public void setTxtFacPagFec(Calendar txtFacPagFec) {
        this.txtFacPagFec = txtFacPagFec;
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

    public InputText getTxtFacPagNro() {
        return txtFacPagNro;
    }

    public void setTxtFacPagNro(InputText txtFacPagNro) {
        this.txtFacPagNro = txtFacPagNro;
    }

    public List<FacturapagoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataFacturapago();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<FacturapagoDTO> facturapagoDTO) {
        this.data = facturapagoDTO;
    }

    public FacturapagoDTO getSelectedFacturapago() {
        return selectedFacturapago;
    }

    public void setSelectedFacturapago(FacturapagoDTO facturapago) {
        this.selectedFacturapago = facturapago;
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

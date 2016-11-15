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

    public FacturaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            FacturaDTO facturaDTO = (FacturaDTO) e.getObject();

            if (txtFacVal == null) {
                txtFacVal = new InputText();
            }

            txtFacVal.setValue(facturaDTO.getFacVal());

            if (txtConNro == null) {
                txtConNro = new InputText();
            }

            txtConNro.setValue(facturaDTO.getConNro());

            if (txtEmpCod == null) {
                txtEmpCod = new InputText();
            }

            txtEmpCod.setValue(facturaDTO.getEmpCod());

            if (txtFacNro == null) {
                txtFacNro = new InputText();
            }

            txtFacNro.setValue(facturaDTO.getFacNro());

            if (txtFacFech == null) {
                txtFacFech = new Calendar();
            }

            txtFacFech.setValue(facturaDTO.getFacFech());

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

            action_modify();
        } catch (Exception ex) {
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
            btnSave.setDisabled(false);
        } else {
            txtFacFech.setValue(entity.getFacFech());
            txtFacFech.setDisabled(false);
            txtFacVal.setValue(entity.getFacVal());
            txtFacVal.setDisabled(false);
            txtConNro.setValue(entity.getId().getConNro());
            txtConNro.setDisabled(true);
            txtEmpCod.setValue(entity.getId().getEmpCod());
            txtEmpCod.setDisabled(true);
            txtFacNro.setValue(entity.getId().getFacNro());
            txtFacNro.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
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
}

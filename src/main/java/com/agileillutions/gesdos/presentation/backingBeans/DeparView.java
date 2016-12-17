package com.agileillutions.gesdos.presentation.backingBeans;

import java.io.Serializable;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Depar;
import com.agileillutions.gesdos.modelo.dto.DeparDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class DeparView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDepNom;
    private InputText txtDepCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DeparDTO> data;
    private DeparDTO selectedDepar;
    private Depar entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DeparView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            DeparDTO deparDTO = (DeparDTO) e.getObject();

            if (txtDepNom == null) {
                txtDepNom = new InputText();
            }

            txtDepNom.setValue(deparDTO.getDepNom());

            if (txtDepCod == null) {
                txtDepCod = new InputText();
            }

            txtDepCod.setValue(deparDTO.getDepCod());

            Long depCod = FacesUtils.checkLong(txtDepCod);
            entity = businessDelegatorView.getDepar(depCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedDepar = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDepar = null;

        if (txtDepNom != null) {
            txtDepNom.setValue(null);
            txtDepNom.setDisabled(true);
        }

        if (txtDepCod != null) {
            txtDepCod.setValue(null);
            txtDepCod.setDisabled(false);
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
            Long depCod = FacesUtils.checkLong(txtDepCod);
            entity = (depCod != null) ? businessDelegatorView.getDepar(depCod)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDepNom.setDisabled(false);
            txtDepCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDepNom.setValue(entity.getDepNom());
            txtDepNom.setDisabled(false);
            txtDepCod.setValue(entity.getDepCod());
            txtDepCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDepar = (DeparDTO) (evt.getComponent().getAttributes()
                                       .get("selectedDepar"));
        txtDepNom.setValue(selectedDepar.getDepNom());
        txtDepNom.setDisabled(false);
        txtDepCod.setValue(selectedDepar.getDepCod());
        txtDepCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDepar == null) && (entity == null)) {
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
            entity = new Depar();

            Long depCod = FacesUtils.checkLong(txtDepCod);

            entity.setDepCod(depCod);
            entity.setDepNom(FacesUtils.checkString(txtDepNom));
            businessDelegatorView.saveDepar(entity);
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
                Long depCod = new Long(selectedDepar.getDepCod());
                entity = businessDelegatorView.getDepar(depCod);
            }

            entity.setDepNom(FacesUtils.checkString(txtDepNom));
            businessDelegatorView.updateDepar(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDepar = (DeparDTO) (evt.getComponent().getAttributes()
                                           .get("selectedDepar"));

            Long depCod = new Long(selectedDepar.getDepCod());
            entity = businessDelegatorView.getDepar(depCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long depCod = FacesUtils.checkLong(txtDepCod);
            entity = businessDelegatorView.getDepar(depCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDepar(entity);
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
            selectedDepar = (DeparDTO) (evt.getComponent().getAttributes()
                                           .get("selectedDepar"));

            Long depCod = new Long(selectedDepar.getDepCod());
            entity = businessDelegatorView.getDepar(depCod);
            businessDelegatorView.deleteDepar(entity);
            data.remove(selectedDepar);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long depCod, String depNom)
        throws Exception {
        try {
            entity.setDepNom(FacesUtils.checkString(depNom));
            businessDelegatorView.updateDepar(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DeparView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDepNom() {
        return txtDepNom;
    }

    public void setTxtDepNom(InputText txtDepNom) {
        this.txtDepNom = txtDepNom;
    }

    public InputText getTxtDepCod() {
        return txtDepCod;
    }

    public void setTxtDepCod(InputText txtDepCod) {
        this.txtDepCod = txtDepCod;
    }

    public List<DeparDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDepar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DeparDTO> deparDTO) {
        this.data = deparDTO;
    }

    public DeparDTO getSelectedDepar() {
        return selectedDepar;
    }

    public void setSelectedDepar(DeparDTO depar) {
        this.selectedDepar = depar;
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

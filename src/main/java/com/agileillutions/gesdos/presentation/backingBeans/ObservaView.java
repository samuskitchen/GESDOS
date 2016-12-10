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
import com.agileillutions.gesdos.modelo.Observa;
import com.agileillutions.gesdos.modelo.dto.ObservaDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ObservaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtObsDes;
    private InputText txtObsCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ObservaDTO> data;
    private ObservaDTO selectedObserva;
    private Observa entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ObservaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ObservaDTO observaDTO = (ObservaDTO) e.getObject();

            if (txtObsDes == null) {
                txtObsDes = new InputText();
            }

            txtObsDes.setValue(observaDTO.getObsDes());

            if (txtObsCod == null) {
                txtObsCod = new InputText();
            }

            txtObsCod.setValue(observaDTO.getObsCod());

            String obsCod = FacesUtils.checkString(txtObsCod);
            entity = businessDelegatorView.getObserva(obsCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedObserva = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedObserva = null;

        if (txtObsDes != null) {
            txtObsDes.setValue(null);
            txtObsDes.setDisabled(true);
        }

        if (txtObsCod != null) {
            txtObsCod.setValue(null);
            txtObsCod.setDisabled(false);
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
            String obsCod = FacesUtils.checkString(txtObsCod);
            entity = (obsCod != null)
                ? businessDelegatorView.getObserva(obsCod) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtObsDes.setDisabled(false);
            txtObsCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtObsDes.setValue(entity.getObsDes());
            txtObsDes.setDisabled(false);
            txtObsCod.setValue(entity.getObsCod());
            txtObsCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedObserva = (ObservaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedObserva"));
        txtObsDes.setValue(selectedObserva.getObsDes());
        txtObsDes.setDisabled(false);
        txtObsCod.setValue(selectedObserva.getObsCod());
        txtObsCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedObserva == null) && (entity == null)) {
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
            entity = new Observa();

            String obsCod = FacesUtils.checkString(txtObsCod);

            entity.setObsCod(obsCod);
            entity.setObsDes(FacesUtils.checkString(txtObsDes));
            businessDelegatorView.saveObserva(entity);
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
                String obsCod = new String(selectedObserva.getObsCod());
                entity = businessDelegatorView.getObserva(obsCod);
            }

            entity.setObsDes(FacesUtils.checkString(txtObsDes));
            businessDelegatorView.updateObserva(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedObserva = (ObservaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedObserva"));

            String obsCod = new String(selectedObserva.getObsCod());
            entity = businessDelegatorView.getObserva(obsCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String obsCod = FacesUtils.checkString(txtObsCod);
            entity = businessDelegatorView.getObserva(obsCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteObserva(entity);
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
            selectedObserva = (ObservaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedObserva"));

            String obsCod = new String(selectedObserva.getObsCod());
            entity = businessDelegatorView.getObserva(obsCod);
            businessDelegatorView.deleteObserva(entity);
            data.remove(selectedObserva);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String obsCod, String obsDes)
        throws Exception {
        try {
            entity.setObsDes(FacesUtils.checkString(obsDes));
            businessDelegatorView.updateObserva(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ObservaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtObsDes() {
        return txtObsDes;
    }

    public void setTxtObsDes(InputText txtObsDes) {
        this.txtObsDes = txtObsDes;
    }

    public InputText getTxtObsCod() {
        return txtObsCod;
    }

    public void setTxtObsCod(InputText txtObsCod) {
        this.txtObsCod = txtObsCod;
    }

    public List<ObservaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataObserva();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ObservaDTO> observaDTO) {
        this.data = observaDTO;
    }

    public ObservaDTO getSelectedObserva() {
        return selectedObserva;
    }

    public void setSelectedObserva(ObservaDTO observa) {
        this.selectedObserva = observa;
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

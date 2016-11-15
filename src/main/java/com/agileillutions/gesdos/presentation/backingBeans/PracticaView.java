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
import com.agileillutions.gesdos.modelo.Practica;
import com.agileillutions.gesdos.modelo.dto.PracticaDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PracticaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtPraDes;
    private InputText txtPraCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PracticaDTO> data;
    private PracticaDTO selectedPractica;
    private Practica entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PracticaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PracticaDTO practicaDTO = (PracticaDTO) e.getObject();

            if (txtPraDes == null) {
                txtPraDes = new InputText();
            }

            txtPraDes.setValue(practicaDTO.getPraDes());

            if (txtPraCod == null) {
                txtPraCod = new InputText();
            }

            txtPraCod.setValue(practicaDTO.getPraCod());

            String praCod = FacesUtils.checkString(txtPraCod);
            entity = businessDelegatorView.getPractica(praCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPractica = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPractica = null;

        if (txtPraDes != null) {
            txtPraDes.setValue(null);
            txtPraDes.setDisabled(true);
        }

        if (txtPraCod != null) {
            txtPraCod.setValue(null);
            txtPraCod.setDisabled(false);
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
            String praCod = FacesUtils.checkString(txtPraCod);
            entity = (praCod != null)
                ? businessDelegatorView.getPractica(praCod) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtPraDes.setDisabled(false);
            txtPraCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtPraDes.setValue(entity.getPraDes());
            txtPraDes.setDisabled(false);
            txtPraCod.setValue(entity.getPraCod());
            txtPraCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPractica = (PracticaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPractica"));
        txtPraDes.setValue(selectedPractica.getPraDes());
        txtPraDes.setDisabled(false);
        txtPraCod.setValue(selectedPractica.getPraCod());
        txtPraCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPractica == null) && (entity == null)) {
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
            entity = new Practica();

            String praCod = FacesUtils.checkString(txtPraCod);

            entity.setPraCod(praCod);
            entity.setPraDes(FacesUtils.checkString(txtPraDes));
            businessDelegatorView.savePractica(entity);
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
                String praCod = new String(selectedPractica.getPraCod());
                entity = businessDelegatorView.getPractica(praCod);
            }

            entity.setPraDes(FacesUtils.checkString(txtPraDes));
            businessDelegatorView.updatePractica(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPractica = (PracticaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPractica"));

            String praCod = new String(selectedPractica.getPraCod());
            entity = businessDelegatorView.getPractica(praCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String praCod = FacesUtils.checkString(txtPraCod);
            entity = businessDelegatorView.getPractica(praCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePractica(entity);
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
            selectedPractica = (PracticaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPractica"));

            String praCod = new String(selectedPractica.getPraCod());
            entity = businessDelegatorView.getPractica(praCod);
            businessDelegatorView.deletePractica(entity);
            data.remove(selectedPractica);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String praCod, String praDes)
        throws Exception {
        try {
            entity.setPraDes(FacesUtils.checkString(praDes));
            businessDelegatorView.updatePractica(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PracticaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtPraDes() {
        return txtPraDes;
    }

    public void setTxtPraDes(InputText txtPraDes) {
        this.txtPraDes = txtPraDes;
    }

    public InputText getTxtPraCod() {
        return txtPraCod;
    }

    public void setTxtPraCod(InputText txtPraCod) {
        this.txtPraCod = txtPraCod;
    }

    public List<PracticaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPractica();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PracticaDTO> practicaDTO) {
        this.data = practicaDTO;
    }

    public PracticaDTO getSelectedPractica() {
        return selectedPractica;
    }

    public void setSelectedPractica(PracticaDTO practica) {
        this.selectedPractica = practica;
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

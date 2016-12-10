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
import com.agileillutions.gesdos.modelo.Ubicacion;
import com.agileillutions.gesdos.modelo.dto.UbicacionDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UbicacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtUbiDes;
    private InputText txtUbiCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UbicacionDTO> data;
    private UbicacionDTO selectedUbicacion;
    private Ubicacion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UbicacionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UbicacionDTO ubicacionDTO = (UbicacionDTO) e.getObject();

            if (txtUbiDes == null) {
                txtUbiDes = new InputText();
            }

            txtUbiDes.setValue(ubicacionDTO.getUbiDes());

            if (txtUbiCod == null) {
                txtUbiCod = new InputText();
            }

            txtUbiCod.setValue(ubicacionDTO.getUbiCod());

            String ubiCod = FacesUtils.checkString(txtUbiCod);
            entity = businessDelegatorView.getUbicacion(ubiCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUbicacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUbicacion = null;

        if (txtUbiDes != null) {
            txtUbiDes.setValue(null);
            txtUbiDes.setDisabled(true);
        }

        if (txtUbiCod != null) {
            txtUbiCod.setValue(null);
            txtUbiCod.setDisabled(false);
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
            String ubiCod = FacesUtils.checkString(txtUbiCod);
            entity = (ubiCod != null)
                ? businessDelegatorView.getUbicacion(ubiCod) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtUbiDes.setDisabled(false);
            txtUbiCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtUbiDes.setValue(entity.getUbiDes());
            txtUbiDes.setDisabled(false);
            txtUbiCod.setValue(entity.getUbiCod());
            txtUbiCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUbicacion = (UbicacionDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUbicacion"));
        txtUbiDes.setValue(selectedUbicacion.getUbiDes());
        txtUbiDes.setDisabled(false);
        txtUbiCod.setValue(selectedUbicacion.getUbiCod());
        txtUbiCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUbicacion == null) && (entity == null)) {
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
            entity = new Ubicacion();

            String ubiCod = FacesUtils.checkString(txtUbiCod);

            entity.setUbiCod(ubiCod);
            entity.setUbiDes(FacesUtils.checkString(txtUbiDes));
            businessDelegatorView.saveUbicacion(entity);
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
                String ubiCod = new String(selectedUbicacion.getUbiCod());
                entity = businessDelegatorView.getUbicacion(ubiCod);
            }

            entity.setUbiDes(FacesUtils.checkString(txtUbiDes));
            businessDelegatorView.updateUbicacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUbicacion = (UbicacionDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedUbicacion"));

            String ubiCod = new String(selectedUbicacion.getUbiCod());
            entity = businessDelegatorView.getUbicacion(ubiCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String ubiCod = FacesUtils.checkString(txtUbiCod);
            entity = businessDelegatorView.getUbicacion(ubiCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUbicacion(entity);
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
            selectedUbicacion = (UbicacionDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedUbicacion"));

            String ubiCod = new String(selectedUbicacion.getUbiCod());
            entity = businessDelegatorView.getUbicacion(ubiCod);
            businessDelegatorView.deleteUbicacion(entity);
            data.remove(selectedUbicacion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String ubiCod, String ubiDes)
        throws Exception {
        try {
            entity.setUbiDes(FacesUtils.checkString(ubiDes));
            businessDelegatorView.updateUbicacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UbicacionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtUbiDes() {
        return txtUbiDes;
    }

    public void setTxtUbiDes(InputText txtUbiDes) {
        this.txtUbiDes = txtUbiDes;
    }

    public InputText getTxtUbiCod() {
        return txtUbiCod;
    }

    public void setTxtUbiCod(InputText txtUbiCod) {
        this.txtUbiCod = txtUbiCod;
    }

    public List<UbicacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUbicacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UbicacionDTO> ubicacionDTO) {
        this.data = ubicacionDTO;
    }

    public UbicacionDTO getSelectedUbicacion() {
        return selectedUbicacion;
    }

    public void setSelectedUbicacion(UbicacionDTO ubicacion) {
        this.selectedUbicacion = ubicacion;
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

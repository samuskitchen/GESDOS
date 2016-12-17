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
import com.agileillutions.gesdos.modelo.Radiacion;
import com.agileillutions.gesdos.modelo.dto.RadiacionDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class RadiacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtRadDes;
    private InputText txtRadCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RadiacionDTO> data;
    private RadiacionDTO selectedRadiacion;
    private Radiacion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RadiacionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RadiacionDTO radiacionDTO = (RadiacionDTO) e.getObject();

            if (txtRadDes == null) {
                txtRadDes = new InputText();
            }

            txtRadDes.setValue(radiacionDTO.getRadDes());

            if (txtRadCod == null) {
                txtRadCod = new InputText();
            }

            txtRadCod.setValue(radiacionDTO.getRadCod());

            String radCod = FacesUtils.checkString(txtRadCod);
            entity = businessDelegatorView.getRadiacion(radCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRadiacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRadiacion = null;

        if (txtRadDes != null) {
            txtRadDes.setValue(null);
            txtRadDes.setDisabled(true);
        }

        if (txtRadCod != null) {
            txtRadCod.setValue(null);
            txtRadCod.setDisabled(false);
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
            String radCod = FacesUtils.checkString(txtRadCod);
            entity = (radCod != null)
                ? businessDelegatorView.getRadiacion(radCod) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtRadDes.setDisabled(false);
            txtRadCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtRadDes.setValue(entity.getRadDes());
            txtRadDes.setDisabled(false);
            txtRadCod.setValue(entity.getRadCod());
            txtRadCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRadiacion = (RadiacionDTO) (evt.getComponent().getAttributes()
                                               .get("selectedRadiacion"));
        txtRadDes.setValue(selectedRadiacion.getRadDes());
        txtRadDes.setDisabled(false);
        txtRadCod.setValue(selectedRadiacion.getRadCod());
        txtRadCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRadiacion == null) && (entity == null)) {
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
            entity = new Radiacion();

            String radCod = FacesUtils.checkString(txtRadCod);

            entity.setRadCod(radCod);
            entity.setRadDes(FacesUtils.checkString(txtRadDes));
            businessDelegatorView.saveRadiacion(entity);
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
                String radCod = new String(selectedRadiacion.getRadCod());
                entity = businessDelegatorView.getRadiacion(radCod);
            }

            entity.setRadDes(FacesUtils.checkString(txtRadDes));
            businessDelegatorView.updateRadiacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRadiacion = (RadiacionDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedRadiacion"));

            String radCod = new String(selectedRadiacion.getRadCod());
            entity = businessDelegatorView.getRadiacion(radCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String radCod = FacesUtils.checkString(txtRadCod);
            entity = businessDelegatorView.getRadiacion(radCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRadiacion(entity);
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
            selectedRadiacion = (RadiacionDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedRadiacion"));

            String radCod = new String(selectedRadiacion.getRadCod());
            entity = businessDelegatorView.getRadiacion(radCod);
            businessDelegatorView.deleteRadiacion(entity);
            data.remove(selectedRadiacion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String radCod, String radDes)
        throws Exception {
        try {
            entity.setRadDes(FacesUtils.checkString(radDes));
            businessDelegatorView.updateRadiacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RadiacionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtRadDes() {
        return txtRadDes;
    }

    public void setTxtRadDes(InputText txtRadDes) {
        this.txtRadDes = txtRadDes;
    }

    public InputText getTxtRadCod() {
        return txtRadCod;
    }

    public void setTxtRadCod(InputText txtRadCod) {
        this.txtRadCod = txtRadCod;
    }

    public List<RadiacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRadiacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RadiacionDTO> radiacionDTO) {
        this.data = radiacionDTO;
    }

    public RadiacionDTO getSelectedRadiacion() {
        return selectedRadiacion;
    }

    public void setSelectedRadiacion(RadiacionDTO radiacion) {
        this.selectedRadiacion = radiacion;
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

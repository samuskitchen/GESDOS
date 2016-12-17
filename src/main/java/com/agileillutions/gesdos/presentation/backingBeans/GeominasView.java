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
import com.agileillutions.gesdos.modelo.Geominas;
import com.agileillutions.gesdos.modelo.dto.GeominasDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GeominasView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtGeoDes;
    private InputText txtGeoCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<GeominasDTO> data;
    private GeominasDTO selectedGeominas;
    private Geominas entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public GeominasView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            GeominasDTO geominasDTO = (GeominasDTO) e.getObject();

            if (txtGeoDes == null) {
                txtGeoDes = new InputText();
            }

            txtGeoDes.setValue(geominasDTO.getGeoDes());

            if (txtGeoCod == null) {
                txtGeoCod = new InputText();
            }

            txtGeoCod.setValue(geominasDTO.getGeoCod());

            String geoCod = FacesUtils.checkString(txtGeoCod);
            entity = businessDelegatorView.getGeominas(geoCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedGeominas = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedGeominas = null;

        if (txtGeoDes != null) {
            txtGeoDes.setValue(null);
            txtGeoDes.setDisabled(true);
        }

        if (txtGeoCod != null) {
            txtGeoCod.setValue(null);
            txtGeoCod.setDisabled(false);
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
            String geoCod = FacesUtils.checkString(txtGeoCod);
            entity = (geoCod != null)
                ? businessDelegatorView.getGeominas(geoCod) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtGeoDes.setDisabled(false);
            txtGeoCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtGeoDes.setValue(entity.getGeoDes());
            txtGeoDes.setDisabled(false);
            txtGeoCod.setValue(entity.getGeoCod());
            txtGeoCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedGeominas = (GeominasDTO) (evt.getComponent().getAttributes()
                                             .get("selectedGeominas"));
        txtGeoDes.setValue(selectedGeominas.getGeoDes());
        txtGeoDes.setDisabled(false);
        txtGeoCod.setValue(selectedGeominas.getGeoCod());
        txtGeoCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedGeominas == null) && (entity == null)) {
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
            entity = new Geominas();

            String geoCod = FacesUtils.checkString(txtGeoCod);

            entity.setGeoCod(geoCod);
            entity.setGeoDes(FacesUtils.checkString(txtGeoDes));
            businessDelegatorView.saveGeominas(entity);
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
                String geoCod = new String(selectedGeominas.getGeoCod());
                entity = businessDelegatorView.getGeominas(geoCod);
            }

            entity.setGeoDes(FacesUtils.checkString(txtGeoDes));
            businessDelegatorView.updateGeominas(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedGeominas = (GeominasDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedGeominas"));

            String geoCod = new String(selectedGeominas.getGeoCod());
            entity = businessDelegatorView.getGeominas(geoCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String geoCod = FacesUtils.checkString(txtGeoCod);
            entity = businessDelegatorView.getGeominas(geoCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteGeominas(entity);
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
            selectedGeominas = (GeominasDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedGeominas"));

            String geoCod = new String(selectedGeominas.getGeoCod());
            entity = businessDelegatorView.getGeominas(geoCod);
            businessDelegatorView.deleteGeominas(entity);
            data.remove(selectedGeominas);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String geoCod, String geoDes)
        throws Exception {
        try {
            entity.setGeoDes(FacesUtils.checkString(geoDes));
            businessDelegatorView.updateGeominas(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("GeominasView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtGeoDes() {
        return txtGeoDes;
    }

    public void setTxtGeoDes(InputText txtGeoDes) {
        this.txtGeoDes = txtGeoDes;
    }

    public InputText getTxtGeoCod() {
        return txtGeoCod;
    }

    public void setTxtGeoCod(InputText txtGeoCod) {
        this.txtGeoCod = txtGeoCod;
    }

    public List<GeominasDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataGeominas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<GeominasDTO> geominasDTO) {
        this.data = geominasDTO;
    }

    public GeominasDTO getSelectedGeominas() {
        return selectedGeominas;
    }

    public void setSelectedGeominas(GeominasDTO geominas) {
        this.selectedGeominas = geominas;
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

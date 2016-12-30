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
import com.agileillutions.gesdos.modelo.Permisos;
import com.agileillutions.gesdos.modelo.PermisosId;
import com.agileillutions.gesdos.modelo.dto.PermisosDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PermisosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtGrupCodigo;
    private InputText txtProgCodigo;
    private InputText txtEvenCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PermisosDTO> data;
    private PermisosDTO selectedPermisos;
    private Permisos entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PermisosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            PermisosDTO permisosDTO = (PermisosDTO) e.getObject();

            if (txtGrupCodigo == null) {
                txtGrupCodigo = new InputText();
            }

            txtGrupCodigo.setValue(permisosDTO.getGrupCodigo());

            if (txtProgCodigo == null) {
                txtProgCodigo = new InputText();
            }

            txtProgCodigo.setValue(permisosDTO.getProgCodigo());

            if (txtEvenCodigo == null) {
                txtEvenCodigo = new InputText();
            }

            txtEvenCodigo.setValue(permisosDTO.getEvenCodigo());

            PermisosId id = new PermisosId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));
            entity = businessDelegatorView.getPermisos(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPermisos = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPermisos = null;

        if (txtGrupCodigo != null) {
            txtGrupCodigo.setValue(null);
            txtGrupCodigo.setDisabled(false);
        }

        if (txtProgCodigo != null) {
            txtProgCodigo.setValue(null);
            txtProgCodigo.setDisabled(false);
        }

        if (txtEvenCodigo != null) {
            txtEvenCodigo.setValue(null);
            txtEvenCodigo.setDisabled(false);
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
            PermisosId id = new PermisosId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));
            entity = (id != null) ? businessDelegatorView.getPermisos(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtGrupCodigo.setDisabled(false);
            txtProgCodigo.setDisabled(false);
            txtEvenCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtGrupCodigo.setValue(entity.getId().getGrupCodigo());
            txtGrupCodigo.setDisabled(true);
            txtProgCodigo.setValue(entity.getId().getProgCodigo());
            txtProgCodigo.setDisabled(true);
            txtEvenCodigo.setValue(entity.getId().getEvenCodigo());
            txtEvenCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPermisos = (PermisosDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPermisos"));
        txtGrupCodigo.setValue(selectedPermisos.getGrupCodigo());
        txtGrupCodigo.setDisabled(true);
        txtProgCodigo.setValue(selectedPermisos.getProgCodigo());
        txtProgCodigo.setDisabled(true);
        txtEvenCodigo.setValue(selectedPermisos.getEvenCodigo());
        txtEvenCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPermisos == null) && (entity == null)) {
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
            entity = new Permisos();

            PermisosId id = new PermisosId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));

            entity.setId(id);
            businessDelegatorView.savePermisos(entity);
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
                PermisosId id = new PermisosId();
                id.setGrupCodigo(selectedPermisos.getGrupCodigo());
                id.setProgCodigo(selectedPermisos.getProgCodigo());
                id.setEvenCodigo(selectedPermisos.getEvenCodigo());
                entity = businessDelegatorView.getPermisos(id);
            }

            businessDelegatorView.updatePermisos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPermisos = (PermisosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPermisos"));

            PermisosId id = new PermisosId();
            id.setGrupCodigo(selectedPermisos.getGrupCodigo());
            id.setProgCodigo(selectedPermisos.getProgCodigo());
            id.setEvenCodigo(selectedPermisos.getEvenCodigo());
            entity = businessDelegatorView.getPermisos(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            PermisosId id = new PermisosId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));
            entity = businessDelegatorView.getPermisos(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePermisos(entity);
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
            selectedPermisos = (PermisosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPermisos"));

            PermisosId id = new PermisosId();
            id.setGrupCodigo(selectedPermisos.getGrupCodigo());
            id.setProgCodigo(selectedPermisos.getProgCodigo());
            id.setEvenCodigo(selectedPermisos.getEvenCodigo());
            entity = businessDelegatorView.getPermisos(id);
            businessDelegatorView.deletePermisos(entity);
            data.remove(selectedPermisos);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String grupCodigo, String progCodigo,
        String evenCodigo) throws Exception {
        try {
            businessDelegatorView.updatePermisos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PermisosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtGrupCodigo() {
        return txtGrupCodigo;
    }

    public void setTxtGrupCodigo(InputText txtGrupCodigo) {
        this.txtGrupCodigo = txtGrupCodigo;
    }

    public InputText getTxtProgCodigo() {
        return txtProgCodigo;
    }

    public void setTxtProgCodigo(InputText txtProgCodigo) {
        this.txtProgCodigo = txtProgCodigo;
    }

    public InputText getTxtEvenCodigo() {
        return txtEvenCodigo;
    }

    public void setTxtEvenCodigo(InputText txtEvenCodigo) {
        this.txtEvenCodigo = txtEvenCodigo;
    }

    public List<PermisosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPermisos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PermisosDTO> permisosDTO) {
        this.data = permisosDTO;
    }

    public PermisosDTO getSelectedPermisos() {
        return selectedPermisos;
    }

    public void setSelectedPermisos(PermisosDTO permisos) {
        this.selectedPermisos = permisos;
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

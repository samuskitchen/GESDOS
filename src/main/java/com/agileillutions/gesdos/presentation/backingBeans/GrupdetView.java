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
import com.agileillutions.gesdos.modelo.Grupdet;
import com.agileillutions.gesdos.modelo.GrupdetId;
import com.agileillutions.gesdos.modelo.dto.GrupdetDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GrupdetView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtGrupCodigo;
    private InputText txtUsuaCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<GrupdetDTO> data;
    private GrupdetDTO selectedGrupdet;
    private Grupdet entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public GrupdetView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            GrupdetDTO grupdetDTO = (GrupdetDTO) e.getObject();

            if (txtGrupCodigo == null) {
                txtGrupCodigo = new InputText();
            }

            txtGrupCodigo.setValue(grupdetDTO.getGrupCodigo());

            if (txtUsuaCodigo == null) {
                txtUsuaCodigo = new InputText();
            }

            txtUsuaCodigo.setValue(grupdetDTO.getUsuaCodigo());

            GrupdetId id = new GrupdetId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setUsuaCodigo((((txtUsuaCodigo.getValue()) == null) ||
                (txtUsuaCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtUsuaCodigo));
            entity = businessDelegatorView.getGrupdet(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedGrupdet = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedGrupdet = null;

        if (txtGrupCodigo != null) {
            txtGrupCodigo.setValue(null);
            txtGrupCodigo.setDisabled(false);
        }

        if (txtUsuaCodigo != null) {
            txtUsuaCodigo.setValue(null);
            txtUsuaCodigo.setDisabled(false);
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
            GrupdetId id = new GrupdetId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setUsuaCodigo((((txtUsuaCodigo.getValue()) == null) ||
                (txtUsuaCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtUsuaCodigo));
            entity = (id != null) ? businessDelegatorView.getGrupdet(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtGrupCodigo.setDisabled(false);
            txtUsuaCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtGrupCodigo.setValue(entity.getId().getGrupCodigo());
            txtGrupCodigo.setDisabled(true);
            txtUsuaCodigo.setValue(entity.getId().getUsuaCodigo());
            txtUsuaCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedGrupdet = (GrupdetDTO) (evt.getComponent().getAttributes()
                                           .get("selectedGrupdet"));
        txtGrupCodigo.setValue(selectedGrupdet.getGrupCodigo());
        txtGrupCodigo.setDisabled(true);
        txtUsuaCodigo.setValue(selectedGrupdet.getUsuaCodigo());
        txtUsuaCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedGrupdet == null) && (entity == null)) {
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
            entity = new Grupdet();

            GrupdetId id = new GrupdetId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setUsuaCodigo((((txtUsuaCodigo.getValue()) == null) ||
                (txtUsuaCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtUsuaCodigo));

            entity.setId(id);
            businessDelegatorView.saveGrupdet(entity);
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
                GrupdetId id = new GrupdetId();
                id.setGrupCodigo(selectedGrupdet.getGrupCodigo());
                id.setUsuaCodigo(selectedGrupdet.getUsuaCodigo());
                entity = businessDelegatorView.getGrupdet(id);
            }

            businessDelegatorView.updateGrupdet(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedGrupdet = (GrupdetDTO) (evt.getComponent().getAttributes()
                                               .get("selectedGrupdet"));

            GrupdetId id = new GrupdetId();
            id.setGrupCodigo(selectedGrupdet.getGrupCodigo());
            id.setUsuaCodigo(selectedGrupdet.getUsuaCodigo());
            entity = businessDelegatorView.getGrupdet(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            GrupdetId id = new GrupdetId();
            id.setGrupCodigo((((txtGrupCodigo.getValue()) == null) ||
                (txtGrupCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtGrupCodigo));
            id.setUsuaCodigo((((txtUsuaCodigo.getValue()) == null) ||
                (txtUsuaCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtUsuaCodigo));
            entity = businessDelegatorView.getGrupdet(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteGrupdet(entity);
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
            selectedGrupdet = (GrupdetDTO) (evt.getComponent().getAttributes()
                                               .get("selectedGrupdet"));

            GrupdetId id = new GrupdetId();
            id.setGrupCodigo(selectedGrupdet.getGrupCodigo());
            id.setUsuaCodigo(selectedGrupdet.getUsuaCodigo());
            entity = businessDelegatorView.getGrupdet(id);
            businessDelegatorView.deleteGrupdet(entity);
            data.remove(selectedGrupdet);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String grupCodigo, String usuaCodigo)
        throws Exception {
        try {
            businessDelegatorView.updateGrupdet(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("GrupdetView").requestRender();
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

    public InputText getTxtUsuaCodigo() {
        return txtUsuaCodigo;
    }

    public void setTxtUsuaCodigo(InputText txtUsuaCodigo) {
        this.txtUsuaCodigo = txtUsuaCodigo;
    }

    public List<GrupdetDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataGrupdet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<GrupdetDTO> grupdetDTO) {
        this.data = grupdetDTO;
    }

    public GrupdetDTO getSelectedGrupdet() {
        return selectedGrupdet;
    }

    public void setSelectedGrupdet(GrupdetDTO grupdet) {
        this.selectedGrupdet = grupdet;
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

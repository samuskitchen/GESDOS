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
import com.agileillutions.gesdos.modelo.Programa1;
import com.agileillutions.gesdos.modelo.Programa1Id;
import com.agileillutions.gesdos.modelo.dto.Programa1DTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class Programa1View implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtEvenDescri;
    private InputText txtProgCodigo;
    private InputText txtEvenCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<Programa1DTO> data;
    private Programa1DTO selectedPrograma1;
    private Programa1 entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public Programa1View() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            Programa1DTO programa1DTO = (Programa1DTO) e.getObject();

            if (txtEvenDescri == null) {
                txtEvenDescri = new InputText();
            }

            txtEvenDescri.setValue(programa1DTO.getEvenDescri());

            if (txtProgCodigo == null) {
                txtProgCodigo = new InputText();
            }

            txtProgCodigo.setValue(programa1DTO.getProgCodigo());

            if (txtEvenCodigo == null) {
                txtEvenCodigo = new InputText();
            }

            txtEvenCodigo.setValue(programa1DTO.getEvenCodigo());

            Programa1Id id = new Programa1Id();
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));
            entity = businessDelegatorView.getPrograma1(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedPrograma1 = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPrograma1 = null;

        if (txtEvenDescri != null) {
            txtEvenDescri.setValue(null);
            txtEvenDescri.setDisabled(true);
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
            Programa1Id id = new Programa1Id();
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));
            entity = (id != null) ? businessDelegatorView.getPrograma1(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEvenDescri.setDisabled(false);
            txtProgCodigo.setDisabled(false);
            txtEvenCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEvenDescri.setValue(entity.getEvenDescri());
            txtEvenDescri.setDisabled(false);
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
        selectedPrograma1 = (Programa1DTO) (evt.getComponent().getAttributes()
                                               .get("selectedPrograma1"));
        txtEvenDescri.setValue(selectedPrograma1.getEvenDescri());
        txtEvenDescri.setDisabled(false);
        txtProgCodigo.setValue(selectedPrograma1.getProgCodigo());
        txtProgCodigo.setDisabled(true);
        txtEvenCodigo.setValue(selectedPrograma1.getEvenCodigo());
        txtEvenCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPrograma1 == null) && (entity == null)) {
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
            entity = new Programa1();

            Programa1Id id = new Programa1Id();
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));

            entity.setEvenDescri(FacesUtils.checkString(txtEvenDescri));
            entity.setId(id);
            businessDelegatorView.savePrograma1(entity);
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
                Programa1Id id = new Programa1Id();
                id.setProgCodigo(selectedPrograma1.getProgCodigo());
                id.setEvenCodigo(selectedPrograma1.getEvenCodigo());
                entity = businessDelegatorView.getPrograma1(id);
            }

            entity.setEvenDescri(FacesUtils.checkString(txtEvenDescri));
            businessDelegatorView.updatePrograma1(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPrograma1 = (Programa1DTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPrograma1"));

            Programa1Id id = new Programa1Id();
            id.setProgCodigo(selectedPrograma1.getProgCodigo());
            id.setEvenCodigo(selectedPrograma1.getEvenCodigo());
            entity = businessDelegatorView.getPrograma1(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Programa1Id id = new Programa1Id();
            id.setProgCodigo((((txtProgCodigo.getValue()) == null) ||
                (txtProgCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtProgCodigo));
            id.setEvenCodigo((((txtEvenCodigo.getValue()) == null) ||
                (txtEvenCodigo.getValue()).equals("")) ? null
                                                       : FacesUtils.checkString(
                    txtEvenCodigo));
            entity = businessDelegatorView.getPrograma1(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePrograma1(entity);
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
            selectedPrograma1 = (Programa1DTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedPrograma1"));

            Programa1Id id = new Programa1Id();
            id.setProgCodigo(selectedPrograma1.getProgCodigo());
            id.setEvenCodigo(selectedPrograma1.getEvenCodigo());
            entity = businessDelegatorView.getPrograma1(id);
            businessDelegatorView.deletePrograma1(entity);
            data.remove(selectedPrograma1);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String progCodigo, String evenCodigo,
        String evenDescri) throws Exception {
        try {
            entity.setEvenDescri(FacesUtils.checkString(evenDescri));
            businessDelegatorView.updatePrograma1(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("Programa1View").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEvenDescri() {
        return txtEvenDescri;
    }

    public void setTxtEvenDescri(InputText txtEvenDescri) {
        this.txtEvenDescri = txtEvenDescri;
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

    public List<Programa1DTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPrograma1();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<Programa1DTO> programa1DTO) {
        this.data = programa1DTO;
    }

    public Programa1DTO getSelectedPrograma1() {
        return selectedPrograma1;
    }

    public void setSelectedPrograma1(Programa1DTO programa1) {
        this.selectedPrograma1 = programa1;
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

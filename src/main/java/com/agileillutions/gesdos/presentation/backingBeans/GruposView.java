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
import com.agileillutions.gesdos.modelo.Grupos;
import com.agileillutions.gesdos.modelo.dto.GruposDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GruposView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtGrupNombre;
    private InputText txtGrupCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<GruposDTO> data;
    private GruposDTO selectedGrupos;
    private Grupos entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public GruposView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            GruposDTO gruposDTO = (GruposDTO) e.getObject();

            if (txtGrupNombre == null) {
                txtGrupNombre = new InputText();
            }

            txtGrupNombre.setValue(gruposDTO.getGrupNombre());

            if (txtGrupCodigo == null) {
                txtGrupCodigo = new InputText();
            }

            txtGrupCodigo.setValue(gruposDTO.getGrupCodigo());

            String grupCodigo = FacesUtils.checkString(txtGrupCodigo);
            entity = businessDelegatorView.getGrupos(grupCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedGrupos = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedGrupos = null;

        if (txtGrupNombre != null) {
            txtGrupNombre.setValue(null);
            txtGrupNombre.setDisabled(true);
        }

        if (txtGrupCodigo != null) {
            txtGrupCodigo.setValue(null);
            txtGrupCodigo.setDisabled(false);
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
            String grupCodigo = FacesUtils.checkString(txtGrupCodigo);
            entity = (grupCodigo != null)
                ? businessDelegatorView.getGrupos(grupCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtGrupNombre.setDisabled(false);
            txtGrupCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtGrupNombre.setValue(entity.getGrupNombre());
            txtGrupNombre.setDisabled(false);
            txtGrupCodigo.setValue(entity.getGrupCodigo());
            txtGrupCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedGrupos = (GruposDTO) (evt.getComponent().getAttributes()
                                         .get("selectedGrupos"));
        txtGrupNombre.setValue(selectedGrupos.getGrupNombre());
        txtGrupNombre.setDisabled(false);
        txtGrupCodigo.setValue(selectedGrupos.getGrupCodigo());
        txtGrupCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedGrupos == null) && (entity == null)) {
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
            entity = new Grupos();

            String grupCodigo = FacesUtils.checkString(txtGrupCodigo);

            entity.setGrupCodigo(grupCodigo);
            entity.setGrupNombre(FacesUtils.checkString(txtGrupNombre));
            businessDelegatorView.saveGrupos(entity);
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
                String grupCodigo = new String(selectedGrupos.getGrupCodigo());
                entity = businessDelegatorView.getGrupos(grupCodigo);
            }

            entity.setGrupNombre(FacesUtils.checkString(txtGrupNombre));
            businessDelegatorView.updateGrupos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedGrupos = (GruposDTO) (evt.getComponent().getAttributes()
                                             .get("selectedGrupos"));

            String grupCodigo = new String(selectedGrupos.getGrupCodigo());
            entity = businessDelegatorView.getGrupos(grupCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String grupCodigo = FacesUtils.checkString(txtGrupCodigo);
            entity = businessDelegatorView.getGrupos(grupCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteGrupos(entity);
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
            selectedGrupos = (GruposDTO) (evt.getComponent().getAttributes()
                                             .get("selectedGrupos"));

            String grupCodigo = new String(selectedGrupos.getGrupCodigo());
            entity = businessDelegatorView.getGrupos(grupCodigo);
            businessDelegatorView.deleteGrupos(entity);
            data.remove(selectedGrupos);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String grupCodigo, String grupNombre)
        throws Exception {
        try {
            entity.setGrupNombre(FacesUtils.checkString(grupNombre));
            businessDelegatorView.updateGrupos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("GruposView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtGrupNombre() {
        return txtGrupNombre;
    }

    public void setTxtGrupNombre(InputText txtGrupNombre) {
        this.txtGrupNombre = txtGrupNombre;
    }

    public InputText getTxtGrupCodigo() {
        return txtGrupCodigo;
    }

    public void setTxtGrupCodigo(InputText txtGrupCodigo) {
        this.txtGrupCodigo = txtGrupCodigo;
    }

    public List<GruposDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataGrupos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<GruposDTO> gruposDTO) {
        this.data = gruposDTO;
    }

    public GruposDTO getSelectedGrupos() {
        return selectedGrupos;
    }

    public void setSelectedGrupos(GruposDTO grupos) {
        this.selectedGrupos = grupos;
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

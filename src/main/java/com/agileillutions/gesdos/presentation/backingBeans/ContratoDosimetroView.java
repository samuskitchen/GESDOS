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
import com.agileillutions.gesdos.modelo.ContratoDosimetro;
import com.agileillutions.gesdos.modelo.dto.ContratoDosimetroDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ContratoDosimetroView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtConNro;
    private InputText txtDosCod;
    private InputText txtConDosi;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ContratoDosimetroDTO> data;
    private ContratoDosimetroDTO selectedContratoDosimetro;
    private ContratoDosimetro entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ContratoDosimetroView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ContratoDosimetroDTO contratoDosimetroDTO = (ContratoDosimetroDTO) e.getObject();

            if (txtConNro == null) {
                txtConNro = new InputText();
            }

            txtConNro.setValue(contratoDosimetroDTO.getConNro());

            if (txtDosCod == null) {
                txtDosCod = new InputText();
            }

            txtDosCod.setValue(contratoDosimetroDTO.getDosCod());

            if (txtConDosi == null) {
                txtConDosi = new InputText();
            }

            txtConDosi.setValue(contratoDosimetroDTO.getConDosi());

            Long conDosi = FacesUtils.checkLong(txtConDosi);
            entity = businessDelegatorView.getContratoDosimetro(conDosi);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedContratoDosimetro = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedContratoDosimetro = null;

        if (txtConNro != null) {
            txtConNro.setValue(null);
            txtConNro.setDisabled(true);
        }

        if (txtDosCod != null) {
            txtDosCod.setValue(null);
            txtDosCod.setDisabled(true);
        }

        if (txtConDosi != null) {
            txtConDosi.setValue(null);
            txtConDosi.setDisabled(false);
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
            Long conDosi = FacesUtils.checkLong(txtConDosi);
            entity = (conDosi != null)
                ? businessDelegatorView.getContratoDosimetro(conDosi) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtConNro.setDisabled(false);
            txtDosCod.setDisabled(false);
            txtConDosi.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtConNro.setValue(entity.getConNro());
            txtConNro.setDisabled(false);
            txtDosCod.setValue(entity.getDosCod());
            txtDosCod.setDisabled(false);
            txtConDosi.setValue(entity.getConDosi());
            txtConDosi.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedContratoDosimetro = (ContratoDosimetroDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedContratoDosimetro"));
        txtConNro.setValue(selectedContratoDosimetro.getConNro());
        txtConNro.setDisabled(false);
        txtDosCod.setValue(selectedContratoDosimetro.getDosCod());
        txtDosCod.setDisabled(false);
        txtConDosi.setValue(selectedContratoDosimetro.getConDosi());
        txtConDosi.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedContratoDosimetro == null) && (entity == null)) {
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
            entity = new ContratoDosimetro();

            Long conDosi = FacesUtils.checkLong(txtConDosi);

            entity.setConDosi(conDosi);
            entity.setConNro(FacesUtils.checkLong(txtConNro));
            entity.setDosCod(FacesUtils.checkLong(txtDosCod));
            businessDelegatorView.saveContratoDosimetro(entity);
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
                Long conDosi = new Long(selectedContratoDosimetro.getConDosi());
                entity = businessDelegatorView.getContratoDosimetro(conDosi);
            }

            entity.setConNro(FacesUtils.checkLong(txtConNro));
            entity.setDosCod(FacesUtils.checkLong(txtDosCod));
            businessDelegatorView.updateContratoDosimetro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedContratoDosimetro = (ContratoDosimetroDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedContratoDosimetro"));

            Long conDosi = new Long(selectedContratoDosimetro.getConDosi());
            entity = businessDelegatorView.getContratoDosimetro(conDosi);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long conDosi = FacesUtils.checkLong(txtConDosi);
            entity = businessDelegatorView.getContratoDosimetro(conDosi);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteContratoDosimetro(entity);
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
            selectedContratoDosimetro = (ContratoDosimetroDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedContratoDosimetro"));

            Long conDosi = new Long(selectedContratoDosimetro.getConDosi());
            entity = businessDelegatorView.getContratoDosimetro(conDosi);
            businessDelegatorView.deleteContratoDosimetro(entity);
            data.remove(selectedContratoDosimetro);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long conDosi, Long conNro, Long dosCod)
        throws Exception {
        try {
            entity.setConNro(FacesUtils.checkLong(conNro));
            entity.setDosCod(FacesUtils.checkLong(dosCod));
            businessDelegatorView.updateContratoDosimetro(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ContratoDosimetroView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtConNro() {
        return txtConNro;
    }

    public void setTxtConNro(InputText txtConNro) {
        this.txtConNro = txtConNro;
    }

    public InputText getTxtDosCod() {
        return txtDosCod;
    }

    public void setTxtDosCod(InputText txtDosCod) {
        this.txtDosCod = txtDosCod;
    }

    public InputText getTxtConDosi() {
        return txtConDosi;
    }

    public void setTxtConDosi(InputText txtConDosi) {
        this.txtConDosi = txtConDosi;
    }

    public List<ContratoDosimetroDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataContratoDosimetro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ContratoDosimetroDTO> contratoDosimetroDTO) {
        this.data = contratoDosimetroDTO;
    }

    public ContratoDosimetroDTO getSelectedContratoDosimetro() {
        return selectedContratoDosimetro;
    }

    public void setSelectedContratoDosimetro(
        ContratoDosimetroDTO contratoDosimetro) {
        this.selectedContratoDosimetro = contratoDosimetro;
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

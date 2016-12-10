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
import com.agileillutions.gesdos.modelo.Numer;
import com.agileillutions.gesdos.modelo.dto.NumerDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class NumerView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtNumDes;
    private InputText txtNumUlt;
    private InputText txtNumCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<NumerDTO> data;
    private NumerDTO selectedNumer;
    private Numer entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public NumerView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            NumerDTO numerDTO = (NumerDTO) e.getObject();

            if (txtNumDes == null) {
                txtNumDes = new InputText();
            }

            txtNumDes.setValue(numerDTO.getNumDes());

            if (txtNumUlt == null) {
                txtNumUlt = new InputText();
            }

            txtNumUlt.setValue(numerDTO.getNumUlt());

            if (txtNumCod == null) {
                txtNumCod = new InputText();
            }

            txtNumCod.setValue(numerDTO.getNumCod());

            Long numCod = FacesUtils.checkLong(txtNumCod);
            entity = businessDelegatorView.getNumer(numCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedNumer = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedNumer = null;

        if (txtNumDes != null) {
            txtNumDes.setValue(null);
            txtNumDes.setDisabled(true);
        }

        if (txtNumUlt != null) {
            txtNumUlt.setValue(null);
            txtNumUlt.setDisabled(true);
        }

        if (txtNumCod != null) {
            txtNumCod.setValue(null);
            txtNumCod.setDisabled(false);
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
            Long numCod = FacesUtils.checkLong(txtNumCod);
            entity = (numCod != null) ? businessDelegatorView.getNumer(numCod)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtNumDes.setDisabled(false);
            txtNumUlt.setDisabled(false);
            txtNumCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtNumDes.setValue(entity.getNumDes());
            txtNumDes.setDisabled(false);
            txtNumUlt.setValue(entity.getNumUlt());
            txtNumUlt.setDisabled(false);
            txtNumCod.setValue(entity.getNumCod());
            txtNumCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedNumer = (NumerDTO) (evt.getComponent().getAttributes()
                                       .get("selectedNumer"));
        txtNumDes.setValue(selectedNumer.getNumDes());
        txtNumDes.setDisabled(false);
        txtNumUlt.setValue(selectedNumer.getNumUlt());
        txtNumUlt.setDisabled(false);
        txtNumCod.setValue(selectedNumer.getNumCod());
        txtNumCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedNumer == null) && (entity == null)) {
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
            entity = new Numer();

            Long numCod = FacesUtils.checkLong(txtNumCod);

            entity.setNumCod(numCod);
            entity.setNumDes(FacesUtils.checkString(txtNumDes));
            entity.setNumUlt(FacesUtils.checkLong(txtNumUlt));
            businessDelegatorView.saveNumer(entity);
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
                Long numCod = new Long(selectedNumer.getNumCod());
                entity = businessDelegatorView.getNumer(numCod);
            }

            entity.setNumDes(FacesUtils.checkString(txtNumDes));
            entity.setNumUlt(FacesUtils.checkLong(txtNumUlt));
            businessDelegatorView.updateNumer(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedNumer = (NumerDTO) (evt.getComponent().getAttributes()
                                           .get("selectedNumer"));

            Long numCod = new Long(selectedNumer.getNumCod());
            entity = businessDelegatorView.getNumer(numCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long numCod = FacesUtils.checkLong(txtNumCod);
            entity = businessDelegatorView.getNumer(numCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteNumer(entity);
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
            selectedNumer = (NumerDTO) (evt.getComponent().getAttributes()
                                           .get("selectedNumer"));

            Long numCod = new Long(selectedNumer.getNumCod());
            entity = businessDelegatorView.getNumer(numCod);
            businessDelegatorView.deleteNumer(entity);
            data.remove(selectedNumer);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long numCod, String numDes, Long numUlt)
        throws Exception {
        try {
            entity.setNumDes(FacesUtils.checkString(numDes));
            entity.setNumUlt(FacesUtils.checkLong(numUlt));
            businessDelegatorView.updateNumer(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("NumerView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtNumDes() {
        return txtNumDes;
    }

    public void setTxtNumDes(InputText txtNumDes) {
        this.txtNumDes = txtNumDes;
    }

    public InputText getTxtNumUlt() {
        return txtNumUlt;
    }

    public void setTxtNumUlt(InputText txtNumUlt) {
        this.txtNumUlt = txtNumUlt;
    }

    public InputText getTxtNumCod() {
        return txtNumCod;
    }

    public void setTxtNumCod(InputText txtNumCod) {
        this.txtNumCod = txtNumCod;
    }

    public List<NumerDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataNumer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<NumerDTO> numerDTO) {
        this.data = numerDTO;
    }

    public NumerDTO getSelectedNumer() {
        return selectedNumer;
    }

    public void setSelectedNumer(NumerDTO numer) {
        this.selectedNumer = numer;
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

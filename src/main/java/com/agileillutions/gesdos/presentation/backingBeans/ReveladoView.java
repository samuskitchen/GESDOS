package com.agileillutions.gesdos.presentation.backingBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Revelado;
import com.agileillutions.gesdos.modelo.dto.ReveladoDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ReveladoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtRevDes;
    private InputText txtRevNro;
    private Calendar txtRevFec;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ReveladoDTO> data;
    private ReveladoDTO selectedRevelado;
    private Revelado entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ReveladoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ReveladoDTO reveladoDTO = (ReveladoDTO) e.getObject();

            if (txtRevDes == null) {
                txtRevDes = new InputText();
            }

            txtRevDes.setValue(reveladoDTO.getRevDes());

            if (txtRevNro == null) {
                txtRevNro = new InputText();
            }

            txtRevNro.setValue(reveladoDTO.getRevNro());

            if (txtRevFec == null) {
                txtRevFec = new Calendar();
            }

            txtRevFec.setValue(reveladoDTO.getRevFec());

            Long revNro = FacesUtils.checkLong(txtRevNro);
            entity = businessDelegatorView.getRevelado(revNro);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRevelado = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRevelado = null;

        if (txtRevDes != null) {
            txtRevDes.setValue(null);
            txtRevDes.setDisabled(true);
        }

        if (txtRevFec != null) {
            txtRevFec.setValue(null);
            txtRevFec.setDisabled(true);
        }

        if (txtRevNro != null) {
            txtRevNro.setValue(null);
            txtRevNro.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtRevFec() {
        Date inputDate = (Date) txtRevFec.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long revNro = FacesUtils.checkLong(txtRevNro);
            entity = (revNro != null)
                ? businessDelegatorView.getRevelado(revNro) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtRevDes.setDisabled(false);
            txtRevFec.setDisabled(false);
            txtRevNro.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtRevDes.setValue(entity.getRevDes());
            txtRevDes.setDisabled(false);
            txtRevFec.setValue(entity.getRevFec());
            txtRevFec.setDisabled(false);
            txtRevNro.setValue(entity.getRevNro());
            txtRevNro.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRevelado = (ReveladoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedRevelado"));
        txtRevDes.setValue(selectedRevelado.getRevDes());
        txtRevDes.setDisabled(false);
        txtRevFec.setValue(selectedRevelado.getRevFec());
        txtRevFec.setDisabled(false);
        txtRevNro.setValue(selectedRevelado.getRevNro());
        txtRevNro.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRevelado == null) && (entity == null)) {
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
            entity = new Revelado();

            Long revNro = FacesUtils.checkLong(txtRevNro);

            entity.setRevDes(FacesUtils.checkString(txtRevDes));
            entity.setRevFec(FacesUtils.checkDate(txtRevFec));
            entity.setRevNro(revNro);
            businessDelegatorView.saveRevelado(entity);
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
                Long revNro = new Long(selectedRevelado.getRevNro());
                entity = businessDelegatorView.getRevelado(revNro);
            }

            entity.setRevDes(FacesUtils.checkString(txtRevDes));
            entity.setRevFec(FacesUtils.checkDate(txtRevFec));
            businessDelegatorView.updateRevelado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRevelado = (ReveladoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedRevelado"));

            Long revNro = new Long(selectedRevelado.getRevNro());
            entity = businessDelegatorView.getRevelado(revNro);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long revNro = FacesUtils.checkLong(txtRevNro);
            entity = businessDelegatorView.getRevelado(revNro);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRevelado(entity);
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
            selectedRevelado = (ReveladoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedRevelado"));

            Long revNro = new Long(selectedRevelado.getRevNro());
            entity = businessDelegatorView.getRevelado(revNro);
            businessDelegatorView.deleteRevelado(entity);
            data.remove(selectedRevelado);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String revDes, Date revFec, Long revNro)
        throws Exception {
        try {
            entity.setRevDes(FacesUtils.checkString(revDes));
            entity.setRevFec(FacesUtils.checkDate(revFec));
            businessDelegatorView.updateRevelado(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ReveladoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtRevDes() {
        return txtRevDes;
    }

    public void setTxtRevDes(InputText txtRevDes) {
        this.txtRevDes = txtRevDes;
    }

    public Calendar getTxtRevFec() {
        return txtRevFec;
    }

    public void setTxtRevFec(Calendar txtRevFec) {
        this.txtRevFec = txtRevFec;
    }

    public InputText getTxtRevNro() {
        return txtRevNro;
    }

    public void setTxtRevNro(InputText txtRevNro) {
        this.txtRevNro = txtRevNro;
    }

    public List<ReveladoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRevelado();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ReveladoDTO> reveladoDTO) {
        this.data = reveladoDTO;
    }

    public ReveladoDTO getSelectedRevelado() {
        return selectedRevelado;
    }

    public void setSelectedRevelado(ReveladoDTO revelado) {
        this.selectedRevelado = revelado;
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

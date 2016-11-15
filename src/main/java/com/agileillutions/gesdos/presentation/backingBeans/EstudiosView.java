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
import com.agileillutions.gesdos.modelo.Estudios;
import com.agileillutions.gesdos.modelo.EstudiosId;
import com.agileillutions.gesdos.modelo.dto.EstudiosDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class EstudiosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtEstDos;
    private InputText txtEstMesAct;
    private InputText txtEstRec;
    private InputText txtObsCod;
    private InputText txtRevNro;
    private InputText txtTraCed;
    private InputText txtEmpCod;
    private InputText txtDosCod;
    private InputText txtEstAni;
    private InputText txtEstMes;
    private Calendar txtEstFecRec;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EstudiosDTO> data;
    private EstudiosDTO selectedEstudios;
    private Estudios entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EstudiosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            EstudiosDTO estudiosDTO = (EstudiosDTO) e.getObject();

            if (txtEstDos == null) {
                txtEstDos = new InputText();
            }

            txtEstDos.setValue(estudiosDTO.getEstDos());

            if (txtEstMesAct == null) {
                txtEstMesAct = new InputText();
            }

            txtEstMesAct.setValue(estudiosDTO.getEstMesAct());

            if (txtEstRec == null) {
                txtEstRec = new InputText();
            }

            txtEstRec.setValue(estudiosDTO.getEstRec());

            if (txtObsCod == null) {
                txtObsCod = new InputText();
            }

            txtObsCod.setValue(estudiosDTO.getObsCod());

            if (txtRevNro == null) {
                txtRevNro = new InputText();
            }

            txtRevNro.setValue(estudiosDTO.getRevNro());

            if (txtTraCed == null) {
                txtTraCed = new InputText();
            }

            txtTraCed.setValue(estudiosDTO.getTraCed());

            if (txtEmpCod == null) {
                txtEmpCod = new InputText();
            }

            txtEmpCod.setValue(estudiosDTO.getEmpCod());

            if (txtDosCod == null) {
                txtDosCod = new InputText();
            }

            txtDosCod.setValue(estudiosDTO.getDosCod());

            if (txtEstAni == null) {
                txtEstAni = new InputText();
            }

            txtEstAni.setValue(estudiosDTO.getEstAni());

            if (txtEstMes == null) {
                txtEstMes = new InputText();
            }

            txtEstMes.setValue(estudiosDTO.getEstMes());

            if (txtEstFecRec == null) {
                txtEstFecRec = new Calendar();
            }

            txtEstFecRec.setValue(estudiosDTO.getEstFecRec());

            EstudiosId id = new EstudiosId();
            id.setTraCed((((txtTraCed.getValue()) == null) ||
                (txtTraCed.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtTraCed));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            id.setDosCod((((txtDosCod.getValue()) == null) ||
                (txtDosCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDosCod));
            id.setEstAni((((txtEstAni.getValue()) == null) ||
                (txtEstAni.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstAni));
            id.setEstMes((((txtEstMes.getValue()) == null) ||
                (txtEstMes.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstMes));
            entity = businessDelegatorView.getEstudios(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedEstudios = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEstudios = null;

        if (txtEstDos != null) {
            txtEstDos.setValue(null);
            txtEstDos.setDisabled(true);
        }

        if (txtEstMesAct != null) {
            txtEstMesAct.setValue(null);
            txtEstMesAct.setDisabled(true);
        }

        if (txtEstRec != null) {
            txtEstRec.setValue(null);
            txtEstRec.setDisabled(true);
        }

        if (txtObsCod != null) {
            txtObsCod.setValue(null);
            txtObsCod.setDisabled(true);
        }

        if (txtRevNro != null) {
            txtRevNro.setValue(null);
            txtRevNro.setDisabled(true);
        }

        if (txtEstFecRec != null) {
            txtEstFecRec.setValue(null);
            txtEstFecRec.setDisabled(true);
        }

        if (txtTraCed != null) {
            txtTraCed.setValue(null);
            txtTraCed.setDisabled(false);
        }

        if (txtEmpCod != null) {
            txtEmpCod.setValue(null);
            txtEmpCod.setDisabled(false);
        }

        if (txtDosCod != null) {
            txtDosCod.setValue(null);
            txtDosCod.setDisabled(false);
        }

        if (txtEstAni != null) {
            txtEstAni.setValue(null);
            txtEstAni.setDisabled(false);
        }

        if (txtEstMes != null) {
            txtEstMes.setValue(null);
            txtEstMes.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtEstFecRec() {
        Date inputDate = (Date) txtEstFecRec.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            EstudiosId id = new EstudiosId();
            id.setTraCed((((txtTraCed.getValue()) == null) ||
                (txtTraCed.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtTraCed));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            id.setDosCod((((txtDosCod.getValue()) == null) ||
                (txtDosCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDosCod));
            id.setEstAni((((txtEstAni.getValue()) == null) ||
                (txtEstAni.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstAni));
            id.setEstMes((((txtEstMes.getValue()) == null) ||
                (txtEstMes.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstMes));
            entity = (id != null) ? businessDelegatorView.getEstudios(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstDos.setDisabled(false);
            txtEstMesAct.setDisabled(false);
            txtEstRec.setDisabled(false);
            txtObsCod.setDisabled(false);
            txtRevNro.setDisabled(false);
            txtEstFecRec.setDisabled(false);
            txtTraCed.setDisabled(false);
            txtEmpCod.setDisabled(false);
            txtDosCod.setDisabled(false);
            txtEstAni.setDisabled(false);
            txtEstMes.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstDos.setValue(entity.getEstDos());
            txtEstDos.setDisabled(false);
            txtEstFecRec.setValue(entity.getEstFecRec());
            txtEstFecRec.setDisabled(false);
            txtEstMesAct.setValue(entity.getEstMesAct());
            txtEstMesAct.setDisabled(false);
            txtEstRec.setValue(entity.getEstRec());
            txtEstRec.setDisabled(false);
            txtObsCod.setValue(entity.getObsCod());
            txtObsCod.setDisabled(false);
            txtRevNro.setValue(entity.getRevNro());
            txtRevNro.setDisabled(false);
            txtTraCed.setValue(entity.getId().getTraCed());
            txtTraCed.setDisabled(true);
            txtEmpCod.setValue(entity.getId().getEmpCod());
            txtEmpCod.setDisabled(true);
            txtDosCod.setValue(entity.getId().getDosCod());
            txtDosCod.setDisabled(true);
            txtEstAni.setValue(entity.getId().getEstAni());
            txtEstAni.setDisabled(true);
            txtEstMes.setValue(entity.getId().getEstMes());
            txtEstMes.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEstudios = (EstudiosDTO) (evt.getComponent().getAttributes()
                                             .get("selectedEstudios"));
        txtEstDos.setValue(selectedEstudios.getEstDos());
        txtEstDos.setDisabled(false);
        txtEstFecRec.setValue(selectedEstudios.getEstFecRec());
        txtEstFecRec.setDisabled(false);
        txtEstMesAct.setValue(selectedEstudios.getEstMesAct());
        txtEstMesAct.setDisabled(false);
        txtEstRec.setValue(selectedEstudios.getEstRec());
        txtEstRec.setDisabled(false);
        txtObsCod.setValue(selectedEstudios.getObsCod());
        txtObsCod.setDisabled(false);
        txtRevNro.setValue(selectedEstudios.getRevNro());
        txtRevNro.setDisabled(false);
        txtTraCed.setValue(selectedEstudios.getTraCed());
        txtTraCed.setDisabled(true);
        txtEmpCod.setValue(selectedEstudios.getEmpCod());
        txtEmpCod.setDisabled(true);
        txtDosCod.setValue(selectedEstudios.getDosCod());
        txtDosCod.setDisabled(true);
        txtEstAni.setValue(selectedEstudios.getEstAni());
        txtEstAni.setDisabled(true);
        txtEstMes.setValue(selectedEstudios.getEstMes());
        txtEstMes.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEstudios == null) && (entity == null)) {
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
            entity = new Estudios();

            EstudiosId id = new EstudiosId();
            id.setTraCed((((txtTraCed.getValue()) == null) ||
                (txtTraCed.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtTraCed));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            id.setDosCod((((txtDosCod.getValue()) == null) ||
                (txtDosCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDosCod));
            id.setEstAni((((txtEstAni.getValue()) == null) ||
                (txtEstAni.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstAni));
            id.setEstMes((((txtEstMes.getValue()) == null) ||
                (txtEstMes.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstMes));

            entity.setEstDos(FacesUtils.checkDouble(txtEstDos));
            entity.setEstFecRec(FacesUtils.checkDate(txtEstFecRec));
            entity.setEstMesAct(FacesUtils.checkLong(txtEstMesAct));
            entity.setEstRec(FacesUtils.checkString(txtEstRec));
            entity.setId(id);
            entity.setObsCod(FacesUtils.checkString(txtObsCod));
            entity.setRevNro(FacesUtils.checkLong(txtRevNro));
            businessDelegatorView.saveEstudios(entity);
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
                EstudiosId id = new EstudiosId();
                id.setTraCed(selectedEstudios.getTraCed());
                id.setEmpCod(selectedEstudios.getEmpCod());
                id.setDosCod(selectedEstudios.getDosCod());
                id.setEstAni(selectedEstudios.getEstAni());
                id.setEstMes(selectedEstudios.getEstMes());
                entity = businessDelegatorView.getEstudios(id);
            }

            entity.setEstDos(FacesUtils.checkDouble(txtEstDos));
            entity.setEstFecRec(FacesUtils.checkDate(txtEstFecRec));
            entity.setEstMesAct(FacesUtils.checkLong(txtEstMesAct));
            entity.setEstRec(FacesUtils.checkString(txtEstRec));
            entity.setObsCod(FacesUtils.checkString(txtObsCod));
            entity.setRevNro(FacesUtils.checkLong(txtRevNro));
            businessDelegatorView.updateEstudios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEstudios = (EstudiosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedEstudios"));

            EstudiosId id = new EstudiosId();
            id.setTraCed(selectedEstudios.getTraCed());
            id.setEmpCod(selectedEstudios.getEmpCod());
            id.setDosCod(selectedEstudios.getDosCod());
            id.setEstAni(selectedEstudios.getEstAni());
            id.setEstMes(selectedEstudios.getEstMes());
            entity = businessDelegatorView.getEstudios(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            EstudiosId id = new EstudiosId();
            id.setTraCed((((txtTraCed.getValue()) == null) ||
                (txtTraCed.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtTraCed));
            id.setEmpCod((((txtEmpCod.getValue()) == null) ||
                (txtEmpCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEmpCod));
            id.setDosCod((((txtDosCod.getValue()) == null) ||
                (txtDosCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDosCod));
            id.setEstAni((((txtEstAni.getValue()) == null) ||
                (txtEstAni.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstAni));
            id.setEstMes((((txtEstMes.getValue()) == null) ||
                (txtEstMes.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtEstMes));
            entity = businessDelegatorView.getEstudios(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEstudios(entity);
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
            selectedEstudios = (EstudiosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedEstudios"));

            EstudiosId id = new EstudiosId();
            id.setTraCed(selectedEstudios.getTraCed());
            id.setEmpCod(selectedEstudios.getEmpCod());
            id.setDosCod(selectedEstudios.getDosCod());
            id.setEstAni(selectedEstudios.getEstAni());
            id.setEstMes(selectedEstudios.getEstMes());
            entity = businessDelegatorView.getEstudios(id);
            businessDelegatorView.deleteEstudios(entity);
            data.remove(selectedEstudios);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long traCed, Long empCod, Long dosCod,
        Long estAni, Long estMes, Double estDos, Date estFecRec,
        Long estMesAct, String estRec, String obsCod, Long revNro)
        throws Exception {
        try {
            entity.setEstDos(FacesUtils.checkDouble(estDos));
            entity.setEstFecRec(FacesUtils.checkDate(estFecRec));
            entity.setEstMesAct(FacesUtils.checkLong(estMesAct));
            entity.setEstRec(FacesUtils.checkString(estRec));
            entity.setObsCod(FacesUtils.checkString(obsCod));
            entity.setRevNro(FacesUtils.checkLong(revNro));
            businessDelegatorView.updateEstudios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EstudiosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstDos() {
        return txtEstDos;
    }

    public void setTxtEstDos(InputText txtEstDos) {
        this.txtEstDos = txtEstDos;
    }

    public InputText getTxtEstMesAct() {
        return txtEstMesAct;
    }

    public void setTxtEstMesAct(InputText txtEstMesAct) {
        this.txtEstMesAct = txtEstMesAct;
    }

    public InputText getTxtEstRec() {
        return txtEstRec;
    }

    public void setTxtEstRec(InputText txtEstRec) {
        this.txtEstRec = txtEstRec;
    }

    public InputText getTxtObsCod() {
        return txtObsCod;
    }

    public void setTxtObsCod(InputText txtObsCod) {
        this.txtObsCod = txtObsCod;
    }

    public InputText getTxtRevNro() {
        return txtRevNro;
    }

    public void setTxtRevNro(InputText txtRevNro) {
        this.txtRevNro = txtRevNro;
    }

    public Calendar getTxtEstFecRec() {
        return txtEstFecRec;
    }

    public void setTxtEstFecRec(Calendar txtEstFecRec) {
        this.txtEstFecRec = txtEstFecRec;
    }

    public InputText getTxtTraCed() {
        return txtTraCed;
    }

    public void setTxtTraCed(InputText txtTraCed) {
        this.txtTraCed = txtTraCed;
    }

    public InputText getTxtEmpCod() {
        return txtEmpCod;
    }

    public void setTxtEmpCod(InputText txtEmpCod) {
        this.txtEmpCod = txtEmpCod;
    }

    public InputText getTxtDosCod() {
        return txtDosCod;
    }

    public void setTxtDosCod(InputText txtDosCod) {
        this.txtDosCod = txtDosCod;
    }

    public InputText getTxtEstAni() {
        return txtEstAni;
    }

    public void setTxtEstAni(InputText txtEstAni) {
        this.txtEstAni = txtEstAni;
    }

    public InputText getTxtEstMes() {
        return txtEstMes;
    }

    public void setTxtEstMes(InputText txtEstMes) {
        this.txtEstMes = txtEstMes;
    }

    public List<EstudiosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEstudios();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EstudiosDTO> estudiosDTO) {
        this.data = estudiosDTO;
    }

    public EstudiosDTO getSelectedEstudios() {
        return selectedEstudios;
    }

    public void setSelectedEstudios(EstudiosDTO estudios) {
        this.selectedEstudios = estudios;
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

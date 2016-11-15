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
import com.agileillutions.gesdos.modelo.Programas;
import com.agileillutions.gesdos.modelo.dto.ProgramasDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ProgramasView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtProgDescri;
    private InputText txtProgCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProgramasDTO> data;
    private ProgramasDTO selectedProgramas;
    private Programas entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProgramasView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ProgramasDTO programasDTO = (ProgramasDTO) e.getObject();

            if (txtProgDescri == null) {
                txtProgDescri = new InputText();
            }

            txtProgDescri.setValue(programasDTO.getProgDescri());

            if (txtProgCodigo == null) {
                txtProgCodigo = new InputText();
            }

            txtProgCodigo.setValue(programasDTO.getProgCodigo());

            String progCodigo = FacesUtils.checkString(txtProgCodigo);
            entity = businessDelegatorView.getProgramas(progCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedProgramas = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProgramas = null;

        if (txtProgDescri != null) {
            txtProgDescri.setValue(null);
            txtProgDescri.setDisabled(true);
        }

        if (txtProgCodigo != null) {
            txtProgCodigo.setValue(null);
            txtProgCodigo.setDisabled(false);
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
            String progCodigo = FacesUtils.checkString(txtProgCodigo);
            entity = (progCodigo != null)
                ? businessDelegatorView.getProgramas(progCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtProgDescri.setDisabled(false);
            txtProgCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtProgDescri.setValue(entity.getProgDescri());
            txtProgDescri.setDisabled(false);
            txtProgCodigo.setValue(entity.getProgCodigo());
            txtProgCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProgramas = (ProgramasDTO) (evt.getComponent().getAttributes()
                                               .get("selectedProgramas"));
        txtProgDescri.setValue(selectedProgramas.getProgDescri());
        txtProgDescri.setDisabled(false);
        txtProgCodigo.setValue(selectedProgramas.getProgCodigo());
        txtProgCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProgramas == null) && (entity == null)) {
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
            entity = new Programas();

            String progCodigo = FacesUtils.checkString(txtProgCodigo);

            entity.setProgCodigo(progCodigo);
            entity.setProgDescri(FacesUtils.checkString(txtProgDescri));
            businessDelegatorView.saveProgramas(entity);
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
                String progCodigo = new String(selectedProgramas.getProgCodigo());
                entity = businessDelegatorView.getProgramas(progCodigo);
            }

            entity.setProgDescri(FacesUtils.checkString(txtProgDescri));
            businessDelegatorView.updateProgramas(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProgramas = (ProgramasDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedProgramas"));

            String progCodigo = new String(selectedProgramas.getProgCodigo());
            entity = businessDelegatorView.getProgramas(progCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String progCodigo = FacesUtils.checkString(txtProgCodigo);
            entity = businessDelegatorView.getProgramas(progCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProgramas(entity);
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
            selectedProgramas = (ProgramasDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedProgramas"));

            String progCodigo = new String(selectedProgramas.getProgCodigo());
            entity = businessDelegatorView.getProgramas(progCodigo);
            businessDelegatorView.deleteProgramas(entity);
            data.remove(selectedProgramas);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String progCodigo, String progDescri)
        throws Exception {
        try {
            entity.setProgDescri(FacesUtils.checkString(progDescri));
            businessDelegatorView.updateProgramas(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProgramasView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtProgDescri() {
        return txtProgDescri;
    }

    public void setTxtProgDescri(InputText txtProgDescri) {
        this.txtProgDescri = txtProgDescri;
    }

    public InputText getTxtProgCodigo() {
        return txtProgCodigo;
    }

    public void setTxtProgCodigo(InputText txtProgCodigo) {
        this.txtProgCodigo = txtProgCodigo;
    }

    public List<ProgramasDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProgramas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProgramasDTO> programasDTO) {
        this.data = programasDTO;
    }

    public ProgramasDTO getSelectedProgramas() {
        return selectedProgramas;
    }

    public void setSelectedProgramas(ProgramasDTO programas) {
        this.selectedProgramas = programas;
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

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
import com.agileillutions.gesdos.modelo.DetalleContrato;
import com.agileillutions.gesdos.modelo.DetalleContratoId;
import com.agileillutions.gesdos.modelo.dto.DetalleContratoDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class DetalleContratoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDetEst;
    private InputText txtDetNumUsu;
    private InputText txtDetObs;
    private InputText txtDetConCod;
    private InputText txtConNro;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DetalleContratoDTO> data;
    private DetalleContratoDTO selectedDetalleContrato;
    private DetalleContrato entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DetalleContratoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            DetalleContratoDTO detalleContratoDTO = (DetalleContratoDTO) e.getObject();

            if (txtDetEst == null) {
                txtDetEst = new InputText();
            }

            txtDetEst.setValue(detalleContratoDTO.getDetEst());

            if (txtDetNumUsu == null) {
                txtDetNumUsu = new InputText();
            }

            txtDetNumUsu.setValue(detalleContratoDTO.getDetNumUsu());

            if (txtDetObs == null) {
                txtDetObs = new InputText();
            }

            txtDetObs.setValue(detalleContratoDTO.getDetObs());

            if (txtDetConCod == null) {
                txtDetConCod = new InputText();
            }

            txtDetConCod.setValue(detalleContratoDTO.getDetConCod());

            if (txtConNro == null) {
                txtConNro = new InputText();
            }

            txtConNro.setValue(detalleContratoDTO.getConNro());

            DetalleContratoId id = new DetalleContratoId();
            id.setDetConCod((((txtDetConCod.getValue()) == null) ||
                (txtDetConCod.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtDetConCod));
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            entity = businessDelegatorView.getDetalleContrato(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedDetalleContrato = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDetalleContrato = null;

        if (txtDetEst != null) {
            txtDetEst.setValue(null);
            txtDetEst.setDisabled(true);
        }

        if (txtDetNumUsu != null) {
            txtDetNumUsu.setValue(null);
            txtDetNumUsu.setDisabled(true);
        }

        if (txtDetObs != null) {
            txtDetObs.setValue(null);
            txtDetObs.setDisabled(true);
        }

        if (txtDetConCod != null) {
            txtDetConCod.setValue(null);
            txtDetConCod.setDisabled(false);
        }

        if (txtConNro != null) {
            txtConNro.setValue(null);
            txtConNro.setDisabled(false);
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
            DetalleContratoId id = new DetalleContratoId();
            id.setDetConCod((((txtDetConCod.getValue()) == null) ||
                (txtDetConCod.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtDetConCod));
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            entity = (id != null)
                ? businessDelegatorView.getDetalleContrato(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDetEst.setDisabled(false);
            txtDetNumUsu.setDisabled(false);
            txtDetObs.setDisabled(false);
            txtDetConCod.setDisabled(false);
            txtConNro.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDetEst.setValue(entity.getDetEst());
            txtDetEst.setDisabled(false);
            txtDetNumUsu.setValue(entity.getDetNumUsu());
            txtDetNumUsu.setDisabled(false);
            txtDetObs.setValue(entity.getDetObs());
            txtDetObs.setDisabled(false);
            txtDetConCod.setValue(entity.getId().getDetConCod());
            txtDetConCod.setDisabled(true);
            txtConNro.setValue(entity.getId().getConNro());
            txtConNro.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDetalleContrato = (DetalleContratoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedDetalleContrato"));
        txtDetEst.setValue(selectedDetalleContrato.getDetEst());
        txtDetEst.setDisabled(false);
        txtDetNumUsu.setValue(selectedDetalleContrato.getDetNumUsu());
        txtDetNumUsu.setDisabled(false);
        txtDetObs.setValue(selectedDetalleContrato.getDetObs());
        txtDetObs.setDisabled(false);
        txtDetConCod.setValue(selectedDetalleContrato.getDetConCod());
        txtDetConCod.setDisabled(true);
        txtConNro.setValue(selectedDetalleContrato.getConNro());
        txtConNro.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDetalleContrato == null) && (entity == null)) {
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
            entity = new DetalleContrato();

            DetalleContratoId id = new DetalleContratoId();
            id.setDetConCod((((txtDetConCod.getValue()) == null) ||
                (txtDetConCod.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtDetConCod));
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));

            entity.setDetEst(FacesUtils.checkString(txtDetEst));
            entity.setDetNumUsu(FacesUtils.checkLong(txtDetNumUsu));
            entity.setDetObs(FacesUtils.checkString(txtDetObs));
            entity.setId(id);
            businessDelegatorView.saveDetalleContrato(entity);
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
                DetalleContratoId id = new DetalleContratoId();
                id.setDetConCod(selectedDetalleContrato.getDetConCod());
                id.setConNro(selectedDetalleContrato.getConNro());
                entity = businessDelegatorView.getDetalleContrato(id);
            }

            entity.setDetEst(FacesUtils.checkString(txtDetEst));
            entity.setDetNumUsu(FacesUtils.checkLong(txtDetNumUsu));
            entity.setDetObs(FacesUtils.checkString(txtDetObs));
            businessDelegatorView.updateDetalleContrato(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDetalleContrato = (DetalleContratoDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedDetalleContrato"));

            DetalleContratoId id = new DetalleContratoId();
            id.setDetConCod(selectedDetalleContrato.getDetConCod());
            id.setConNro(selectedDetalleContrato.getConNro());
            entity = businessDelegatorView.getDetalleContrato(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            DetalleContratoId id = new DetalleContratoId();
            id.setDetConCod((((txtDetConCod.getValue()) == null) ||
                (txtDetConCod.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtDetConCod));
            id.setConNro((((txtConNro.getValue()) == null) ||
                (txtConNro.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtConNro));
            entity = businessDelegatorView.getDetalleContrato(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDetalleContrato(entity);
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
            selectedDetalleContrato = (DetalleContratoDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedDetalleContrato"));

            DetalleContratoId id = new DetalleContratoId();
            id.setDetConCod(selectedDetalleContrato.getDetConCod());
            id.setConNro(selectedDetalleContrato.getConNro());
            entity = businessDelegatorView.getDetalleContrato(id);
            businessDelegatorView.deleteDetalleContrato(entity);
            data.remove(selectedDetalleContrato);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long detConCod, Long conNro,
        String detEst, Long detNumUsu, String detObs) throws Exception {
        try {
            entity.setDetEst(FacesUtils.checkString(detEst));
            entity.setDetNumUsu(FacesUtils.checkLong(detNumUsu));
            entity.setDetObs(FacesUtils.checkString(detObs));
            businessDelegatorView.updateDetalleContrato(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DetalleContratoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDetEst() {
        return txtDetEst;
    }

    public void setTxtDetEst(InputText txtDetEst) {
        this.txtDetEst = txtDetEst;
    }

    public InputText getTxtDetNumUsu() {
        return txtDetNumUsu;
    }

    public void setTxtDetNumUsu(InputText txtDetNumUsu) {
        this.txtDetNumUsu = txtDetNumUsu;
    }

    public InputText getTxtDetObs() {
        return txtDetObs;
    }

    public void setTxtDetObs(InputText txtDetObs) {
        this.txtDetObs = txtDetObs;
    }

    public InputText getTxtDetConCod() {
        return txtDetConCod;
    }

    public void setTxtDetConCod(InputText txtDetConCod) {
        this.txtDetConCod = txtDetConCod;
    }

    public InputText getTxtConNro() {
        return txtConNro;
    }

    public void setTxtConNro(InputText txtConNro) {
        this.txtConNro = txtConNro;
    }

    public List<DetalleContratoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDetalleContrato();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DetalleContratoDTO> detalleContratoDTO) {
        this.data = detalleContratoDTO;
    }

    public DetalleContratoDTO getSelectedDetalleContrato() {
        return selectedDetalleContrato;
    }

    public void setSelectedDetalleContrato(DetalleContratoDTO detalleContrato) {
        this.selectedDetalleContrato = detalleContrato;
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

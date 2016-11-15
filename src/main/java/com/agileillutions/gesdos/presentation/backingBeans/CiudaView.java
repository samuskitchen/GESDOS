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
import com.agileillutions.gesdos.modelo.Ciuda;
import com.agileillutions.gesdos.modelo.CiudaId;
import com.agileillutions.gesdos.modelo.dto.CiudaDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CiudaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtCiuNom;
    private InputText txtDepCod;
    private InputText txtCiuCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CiudaDTO> data;
    private CiudaDTO selectedCiuda;
    private Ciuda entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CiudaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CiudaDTO ciudaDTO = (CiudaDTO) e.getObject();

            if (txtCiuNom == null) {
                txtCiuNom = new InputText();
            }

            txtCiuNom.setValue(ciudaDTO.getCiuNom());

            if (txtDepCod == null) {
                txtDepCod = new InputText();
            }

            txtDepCod.setValue(ciudaDTO.getDepCod());

            if (txtCiuCod == null) {
                txtCiuCod = new InputText();
            }

            txtCiuCod.setValue(ciudaDTO.getCiuCod());

            CiudaId id = new CiudaId();
            id.setDepCod((((txtDepCod.getValue()) == null) ||
                (txtDepCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDepCod));
            id.setCiuCod((((txtCiuCod.getValue()) == null) ||
                (txtCiuCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtCiuCod));
            entity = businessDelegatorView.getCiuda(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCiuda = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCiuda = null;

        if (txtCiuNom != null) {
            txtCiuNom.setValue(null);
            txtCiuNom.setDisabled(true);
        }

        if (txtDepCod != null) {
            txtDepCod.setValue(null);
            txtDepCod.setDisabled(false);
        }

        if (txtCiuCod != null) {
            txtCiuCod.setValue(null);
            txtCiuCod.setDisabled(false);
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
            CiudaId id = new CiudaId();
            id.setDepCod((((txtDepCod.getValue()) == null) ||
                (txtDepCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDepCod));
            id.setCiuCod((((txtCiuCod.getValue()) == null) ||
                (txtCiuCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtCiuCod));
            entity = (id != null) ? businessDelegatorView.getCiuda(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCiuNom.setDisabled(false);
            txtDepCod.setDisabled(false);
            txtCiuCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCiuNom.setValue(entity.getCiuNom());
            txtCiuNom.setDisabled(false);
            txtDepCod.setValue(entity.getId().getDepCod());
            txtDepCod.setDisabled(true);
            txtCiuCod.setValue(entity.getId().getCiuCod());
            txtCiuCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCiuda = (CiudaDTO) (evt.getComponent().getAttributes()
                                       .get("selectedCiuda"));
        txtCiuNom.setValue(selectedCiuda.getCiuNom());
        txtCiuNom.setDisabled(false);
        txtDepCod.setValue(selectedCiuda.getDepCod());
        txtDepCod.setDisabled(true);
        txtCiuCod.setValue(selectedCiuda.getCiuCod());
        txtCiuCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCiuda == null) && (entity == null)) {
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
            entity = new Ciuda();

            CiudaId id = new CiudaId();
            id.setDepCod((((txtDepCod.getValue()) == null) ||
                (txtDepCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDepCod));
            id.setCiuCod((((txtCiuCod.getValue()) == null) ||
                (txtCiuCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtCiuCod));

            entity.setCiuNom(FacesUtils.checkString(txtCiuNom));
            entity.setId(id);
            businessDelegatorView.saveCiuda(entity);
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
                CiudaId id = new CiudaId();
                id.setDepCod(selectedCiuda.getDepCod());
                id.setCiuCod(selectedCiuda.getCiuCod());
                entity = businessDelegatorView.getCiuda(id);
            }

            entity.setCiuNom(FacesUtils.checkString(txtCiuNom));
            businessDelegatorView.updateCiuda(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCiuda = (CiudaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCiuda"));

            CiudaId id = new CiudaId();
            id.setDepCod(selectedCiuda.getDepCod());
            id.setCiuCod(selectedCiuda.getCiuCod());
            entity = businessDelegatorView.getCiuda(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            CiudaId id = new CiudaId();
            id.setDepCod((((txtDepCod.getValue()) == null) ||
                (txtDepCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtDepCod));
            id.setCiuCod((((txtCiuCod.getValue()) == null) ||
                (txtCiuCod.getValue()).equals("")) ? null
                                                   : FacesUtils.checkLong(
                    txtCiuCod));
            entity = businessDelegatorView.getCiuda(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCiuda(entity);
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
            selectedCiuda = (CiudaDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCiuda"));

            CiudaId id = new CiudaId();
            id.setDepCod(selectedCiuda.getDepCod());
            id.setCiuCod(selectedCiuda.getCiuCod());
            entity = businessDelegatorView.getCiuda(id);
            businessDelegatorView.deleteCiuda(entity);
            data.remove(selectedCiuda);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long depCod, Long ciuCod, String ciuNom)
        throws Exception {
        try {
            entity.setCiuNom(FacesUtils.checkString(ciuNom));
            businessDelegatorView.updateCiuda(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CiudaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCiuNom() {
        return txtCiuNom;
    }

    public void setTxtCiuNom(InputText txtCiuNom) {
        this.txtCiuNom = txtCiuNom;
    }

    public InputText getTxtDepCod() {
        return txtDepCod;
    }

    public void setTxtDepCod(InputText txtDepCod) {
        this.txtDepCod = txtDepCod;
    }

    public InputText getTxtCiuCod() {
        return txtCiuCod;
    }

    public void setTxtCiuCod(InputText txtCiuCod) {
        this.txtCiuCod = txtCiuCod;
    }

    public List<CiudaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCiuda();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CiudaDTO> ciudaDTO) {
        this.data = ciudaDTO;
    }

    public CiudaDTO getSelectedCiuda() {
        return selectedCiuda;
    }

    public void setSelectedCiuda(CiudaDTO ciuda) {
        this.selectedCiuda = ciuda;
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

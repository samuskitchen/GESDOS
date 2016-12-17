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
import com.agileillutions.gesdos.modelo.Socios;
import com.agileillutions.gesdos.modelo.dto.SociosDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class SociosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtSocApe1;
    private InputText txtSocApe2;
    private InputText txtSocDes;
    private InputText txtSocDir;
    private InputText txtSocFax;
    private InputText txtSocMail;
    private InputText txtSocNit;
    private InputText txtSocNom;
    private InputText txtSocTel;
    private InputText txtSocCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SociosDTO> data;
    private SociosDTO selectedSocios;
    private Socios entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SociosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SociosDTO sociosDTO = (SociosDTO) e.getObject();

            if (txtSocApe1 == null) {
                txtSocApe1 = new InputText();
            }

            txtSocApe1.setValue(sociosDTO.getSocApe1());

            if (txtSocApe2 == null) {
                txtSocApe2 = new InputText();
            }

            txtSocApe2.setValue(sociosDTO.getSocApe2());

            if (txtSocDes == null) {
                txtSocDes = new InputText();
            }

            txtSocDes.setValue(sociosDTO.getSocDes());

            if (txtSocDir == null) {
                txtSocDir = new InputText();
            }

            txtSocDir.setValue(sociosDTO.getSocDir());

            if (txtSocFax == null) {
                txtSocFax = new InputText();
            }

            txtSocFax.setValue(sociosDTO.getSocFax());

            if (txtSocMail == null) {
                txtSocMail = new InputText();
            }

            txtSocMail.setValue(sociosDTO.getSocMail());

            if (txtSocNit == null) {
                txtSocNit = new InputText();
            }

            txtSocNit.setValue(sociosDTO.getSocNit());

            if (txtSocNom == null) {
                txtSocNom = new InputText();
            }

            txtSocNom.setValue(sociosDTO.getSocNom());

            if (txtSocTel == null) {
                txtSocTel = new InputText();
            }

            txtSocTel.setValue(sociosDTO.getSocTel());

            if (txtSocCod == null) {
                txtSocCod = new InputText();
            }

            txtSocCod.setValue(sociosDTO.getSocCod());

            String socCod = FacesUtils.checkString(txtSocCod);
            entity = businessDelegatorView.getSocios(socCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSocios = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSocios = null;

        if (txtSocApe1 != null) {
            txtSocApe1.setValue(null);
            txtSocApe1.setDisabled(true);
        }

        if (txtSocApe2 != null) {
            txtSocApe2.setValue(null);
            txtSocApe2.setDisabled(true);
        }

        if (txtSocDes != null) {
            txtSocDes.setValue(null);
            txtSocDes.setDisabled(true);
        }

        if (txtSocDir != null) {
            txtSocDir.setValue(null);
            txtSocDir.setDisabled(true);
        }

        if (txtSocFax != null) {
            txtSocFax.setValue(null);
            txtSocFax.setDisabled(true);
        }

        if (txtSocMail != null) {
            txtSocMail.setValue(null);
            txtSocMail.setDisabled(true);
        }

        if (txtSocNit != null) {
            txtSocNit.setValue(null);
            txtSocNit.setDisabled(true);
        }

        if (txtSocNom != null) {
            txtSocNom.setValue(null);
            txtSocNom.setDisabled(true);
        }

        if (txtSocTel != null) {
            txtSocTel.setValue(null);
            txtSocTel.setDisabled(true);
        }

        if (txtSocCod != null) {
            txtSocCod.setValue(null);
            txtSocCod.setDisabled(false);
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
            String socCod = FacesUtils.checkString(txtSocCod);
            entity = (socCod != null) ? businessDelegatorView.getSocios(socCod)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtSocApe1.setDisabled(false);
            txtSocApe2.setDisabled(false);
            txtSocDes.setDisabled(false);
            txtSocDir.setDisabled(false);
            txtSocFax.setDisabled(false);
            txtSocMail.setDisabled(false);
            txtSocNit.setDisabled(false);
            txtSocNom.setDisabled(false);
            txtSocTel.setDisabled(false);
            txtSocCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtSocApe1.setValue(entity.getSocApe1());
            txtSocApe1.setDisabled(false);
            txtSocApe2.setValue(entity.getSocApe2());
            txtSocApe2.setDisabled(false);
            txtSocDes.setValue(entity.getSocDes());
            txtSocDes.setDisabled(false);
            txtSocDir.setValue(entity.getSocDir());
            txtSocDir.setDisabled(false);
            txtSocFax.setValue(entity.getSocFax());
            txtSocFax.setDisabled(false);
            txtSocMail.setValue(entity.getSocMail());
            txtSocMail.setDisabled(false);
            txtSocNit.setValue(entity.getSocNit());
            txtSocNit.setDisabled(false);
            txtSocNom.setValue(entity.getSocNom());
            txtSocNom.setDisabled(false);
            txtSocTel.setValue(entity.getSocTel());
            txtSocTel.setDisabled(false);
            txtSocCod.setValue(entity.getSocCod());
            txtSocCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSocios = (SociosDTO) (evt.getComponent().getAttributes()
                                         .get("selectedSocios"));
        txtSocApe1.setValue(selectedSocios.getSocApe1());
        txtSocApe1.setDisabled(false);
        txtSocApe2.setValue(selectedSocios.getSocApe2());
        txtSocApe2.setDisabled(false);
        txtSocDes.setValue(selectedSocios.getSocDes());
        txtSocDes.setDisabled(false);
        txtSocDir.setValue(selectedSocios.getSocDir());
        txtSocDir.setDisabled(false);
        txtSocFax.setValue(selectedSocios.getSocFax());
        txtSocFax.setDisabled(false);
        txtSocMail.setValue(selectedSocios.getSocMail());
        txtSocMail.setDisabled(false);
        txtSocNit.setValue(selectedSocios.getSocNit());
        txtSocNit.setDisabled(false);
        txtSocNom.setValue(selectedSocios.getSocNom());
        txtSocNom.setDisabled(false);
        txtSocTel.setValue(selectedSocios.getSocTel());
        txtSocTel.setDisabled(false);
        txtSocCod.setValue(selectedSocios.getSocCod());
        txtSocCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSocios == null) && (entity == null)) {
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
            entity = new Socios();

            String socCod = FacesUtils.checkString(txtSocCod);

            entity.setSocApe1(FacesUtils.checkString(txtSocApe1));
            entity.setSocApe2(FacesUtils.checkString(txtSocApe2));
            entity.setSocCod(socCod);
            entity.setSocDes(FacesUtils.checkString(txtSocDes));
            entity.setSocDir(FacesUtils.checkString(txtSocDir));
            entity.setSocFax(FacesUtils.checkString(txtSocFax));
            entity.setSocMail(FacesUtils.checkString(txtSocMail));
            entity.setSocNit(FacesUtils.checkLong(txtSocNit));
            entity.setSocNom(FacesUtils.checkString(txtSocNom));
            entity.setSocTel(FacesUtils.checkString(txtSocTel));
            businessDelegatorView.saveSocios(entity);
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
                String socCod = new String(selectedSocios.getSocCod());
                entity = businessDelegatorView.getSocios(socCod);
            }

            entity.setSocApe1(FacesUtils.checkString(txtSocApe1));
            entity.setSocApe2(FacesUtils.checkString(txtSocApe2));
            entity.setSocDes(FacesUtils.checkString(txtSocDes));
            entity.setSocDir(FacesUtils.checkString(txtSocDir));
            entity.setSocFax(FacesUtils.checkString(txtSocFax));
            entity.setSocMail(FacesUtils.checkString(txtSocMail));
            entity.setSocNit(FacesUtils.checkLong(txtSocNit));
            entity.setSocNom(FacesUtils.checkString(txtSocNom));
            entity.setSocTel(FacesUtils.checkString(txtSocTel));
            businessDelegatorView.updateSocios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSocios = (SociosDTO) (evt.getComponent().getAttributes()
                                             .get("selectedSocios"));

            String socCod = new String(selectedSocios.getSocCod());
            entity = businessDelegatorView.getSocios(socCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String socCod = FacesUtils.checkString(txtSocCod);
            entity = businessDelegatorView.getSocios(socCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSocios(entity);
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
            selectedSocios = (SociosDTO) (evt.getComponent().getAttributes()
                                             .get("selectedSocios"));

            String socCod = new String(selectedSocios.getSocCod());
            entity = businessDelegatorView.getSocios(socCod);
            businessDelegatorView.deleteSocios(entity);
            data.remove(selectedSocios);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String socApe1, String socApe2,
        String socCod, String socDes, String socDir, String socFax,
        String socMail, Long socNit, String socNom, String socTel)
        throws Exception {
        try {
            entity.setSocApe1(FacesUtils.checkString(socApe1));
            entity.setSocApe2(FacesUtils.checkString(socApe2));
            entity.setSocDes(FacesUtils.checkString(socDes));
            entity.setSocDir(FacesUtils.checkString(socDir));
            entity.setSocFax(FacesUtils.checkString(socFax));
            entity.setSocMail(FacesUtils.checkString(socMail));
            entity.setSocNit(FacesUtils.checkLong(socNit));
            entity.setSocNom(FacesUtils.checkString(socNom));
            entity.setSocTel(FacesUtils.checkString(socTel));
            businessDelegatorView.updateSocios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SociosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtSocApe1() {
        return txtSocApe1;
    }

    public void setTxtSocApe1(InputText txtSocApe1) {
        this.txtSocApe1 = txtSocApe1;
    }

    public InputText getTxtSocApe2() {
        return txtSocApe2;
    }

    public void setTxtSocApe2(InputText txtSocApe2) {
        this.txtSocApe2 = txtSocApe2;
    }

    public InputText getTxtSocDes() {
        return txtSocDes;
    }

    public void setTxtSocDes(InputText txtSocDes) {
        this.txtSocDes = txtSocDes;
    }

    public InputText getTxtSocDir() {
        return txtSocDir;
    }

    public void setTxtSocDir(InputText txtSocDir) {
        this.txtSocDir = txtSocDir;
    }

    public InputText getTxtSocFax() {
        return txtSocFax;
    }

    public void setTxtSocFax(InputText txtSocFax) {
        this.txtSocFax = txtSocFax;
    }

    public InputText getTxtSocMail() {
        return txtSocMail;
    }

    public void setTxtSocMail(InputText txtSocMail) {
        this.txtSocMail = txtSocMail;
    }

    public InputText getTxtSocNit() {
        return txtSocNit;
    }

    public void setTxtSocNit(InputText txtSocNit) {
        this.txtSocNit = txtSocNit;
    }

    public InputText getTxtSocNom() {
        return txtSocNom;
    }

    public void setTxtSocNom(InputText txtSocNom) {
        this.txtSocNom = txtSocNom;
    }

    public InputText getTxtSocTel() {
        return txtSocTel;
    }

    public void setTxtSocTel(InputText txtSocTel) {
        this.txtSocTel = txtSocTel;
    }

    public InputText getTxtSocCod() {
        return txtSocCod;
    }

    public void setTxtSocCod(InputText txtSocCod) {
        this.txtSocCod = txtSocCod;
    }

    public List<SociosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSocios();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SociosDTO> sociosDTO) {
        this.data = sociosDTO;
    }

    public SociosDTO getSelectedSocios() {
        return selectedSocios;
    }

    public void setSelectedSocios(SociosDTO socios) {
        this.selectedSocios = socios;
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

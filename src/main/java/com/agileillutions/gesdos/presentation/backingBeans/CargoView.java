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
import com.agileillutions.gesdos.modelo.Cargo;
import com.agileillutions.gesdos.modelo.dto.CargoDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CargoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtCarDes;
    private InputText txtCarCod;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CargoDTO> data;
    private CargoDTO selectedCargo;
    private Cargo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CargoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CargoDTO cargoDTO = (CargoDTO) e.getObject();

            if (txtCarDes == null) {
                txtCarDes = new InputText();
            }

            txtCarDes.setValue(cargoDTO.getCarDes());

            if (txtCarCod == null) {
                txtCarCod = new InputText();
            }

            txtCarCod.setValue(cargoDTO.getCarCod());

            String carCod = FacesUtils.checkString(txtCarCod);
            entity = businessDelegatorView.getCargo(carCod);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCargo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCargo = null;

        if (txtCarDes != null) {
            txtCarDes.setValue(null);
            txtCarDes.setDisabled(true);
        }

        if (txtCarCod != null) {
            txtCarCod.setValue(null);
            txtCarCod.setDisabled(false);
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
            String carCod = FacesUtils.checkString(txtCarCod);
            entity = (carCod != null) ? businessDelegatorView.getCargo(carCod)
                                      : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCarDes.setDisabled(false);
            txtCarCod.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCarDes.setValue(entity.getCarDes());
            txtCarDes.setDisabled(false);
            txtCarCod.setValue(entity.getCarCod());
            txtCarCod.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCargo = (CargoDTO) (evt.getComponent().getAttributes()
                                       .get("selectedCargo"));
        txtCarDes.setValue(selectedCargo.getCarDes());
        txtCarDes.setDisabled(false);
        txtCarCod.setValue(selectedCargo.getCarCod());
        txtCarCod.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCargo == null) && (entity == null)) {
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
            entity = new Cargo();

            String carCod = FacesUtils.checkString(txtCarCod);

            entity.setCarCod(carCod);
            entity.setCarDes(FacesUtils.checkString(txtCarDes));
            businessDelegatorView.saveCargo(entity);
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
                String carCod = new String(selectedCargo.getCarCod());
                entity = businessDelegatorView.getCargo(carCod);
            }

            entity.setCarDes(FacesUtils.checkString(txtCarDes));
            businessDelegatorView.updateCargo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCargo = (CargoDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCargo"));

            String carCod = new String(selectedCargo.getCarCod());
            entity = businessDelegatorView.getCargo(carCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String carCod = FacesUtils.checkString(txtCarCod);
            entity = businessDelegatorView.getCargo(carCod);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCargo(entity);
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
            selectedCargo = (CargoDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCargo"));

            String carCod = new String(selectedCargo.getCarCod());
            entity = businessDelegatorView.getCargo(carCod);
            businessDelegatorView.deleteCargo(entity);
            data.remove(selectedCargo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String carCod, String carDes)
        throws Exception {
        try {
            entity.setCarDes(FacesUtils.checkString(carDes));
            businessDelegatorView.updateCargo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CargoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCarDes() {
        return txtCarDes;
    }

    public void setTxtCarDes(InputText txtCarDes) {
        this.txtCarDes = txtCarDes;
    }

    public InputText getTxtCarCod() {
        return txtCarCod;
    }

    public void setTxtCarCod(InputText txtCarCod) {
        this.txtCarCod = txtCarCod;
    }

    public List<CargoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCargo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CargoDTO> cargoDTO) {
        this.data = cargoDTO;
    }

    public CargoDTO getSelectedCargo() {
        return selectedCargo;
    }

    public void setSelectedCargo(CargoDTO cargo) {
        this.selectedCargo = cargo;
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

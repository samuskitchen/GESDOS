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
import com.agileillutions.gesdos.modelo.Usuarios;
import com.agileillutions.gesdos.modelo.dto.UsuariosDTO;
import com.agileillutions.gesdos.presentation.businessDelegate.IBusinessDelegatorView;
import com.agileillutions.gesdos.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UsuariosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtUsuaApeNom;
    private InputText txtUsuaDias;
    private InputText txtUsuaPassw1;
    private InputText txtUsuaPassw2;
    private InputText txtUsuaPasswo;
    private InputText txtUsuaCodigo;
    private Calendar txtUsuafecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuariosDTO> data;
    private UsuariosDTO selectedUsuarios;
    private Usuarios entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuariosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuariosDTO usuariosDTO = (UsuariosDTO) e.getObject();

            if (txtUsuaApeNom == null) {
                txtUsuaApeNom = new InputText();
            }

            txtUsuaApeNom.setValue(usuariosDTO.getUsuaApeNom());

            if (txtUsuaDias == null) {
                txtUsuaDias = new InputText();
            }

            txtUsuaDias.setValue(usuariosDTO.getUsuaDias());

            if (txtUsuaPassw1 == null) {
                txtUsuaPassw1 = new InputText();
            }

            txtUsuaPassw1.setValue(usuariosDTO.getUsuaPassw1());

            if (txtUsuaPassw2 == null) {
                txtUsuaPassw2 = new InputText();
            }

            txtUsuaPassw2.setValue(usuariosDTO.getUsuaPassw2());

            if (txtUsuaPasswo == null) {
                txtUsuaPasswo = new InputText();
            }

            txtUsuaPasswo.setValue(usuariosDTO.getUsuaPasswo());

            if (txtUsuaCodigo == null) {
                txtUsuaCodigo = new InputText();
            }

            txtUsuaCodigo.setValue(usuariosDTO.getUsuaCodigo());

            if (txtUsuafecha == null) {
                txtUsuafecha = new Calendar();
            }

            txtUsuafecha.setValue(usuariosDTO.getUsuafecha());

            String usuaCodigo = FacesUtils.checkString(txtUsuaCodigo);
            entity = businessDelegatorView.getUsuarios(usuaCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuarios = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuarios = null;

        if (txtUsuaApeNom != null) {
            txtUsuaApeNom.setValue(null);
            txtUsuaApeNom.setDisabled(true);
        }

        if (txtUsuaDias != null) {
            txtUsuaDias.setValue(null);
            txtUsuaDias.setDisabled(true);
        }

        if (txtUsuaPassw1 != null) {
            txtUsuaPassw1.setValue(null);
            txtUsuaPassw1.setDisabled(true);
        }

        if (txtUsuaPassw2 != null) {
            txtUsuaPassw2.setValue(null);
            txtUsuaPassw2.setDisabled(true);
        }

        if (txtUsuaPasswo != null) {
            txtUsuaPasswo.setValue(null);
            txtUsuaPasswo.setDisabled(true);
        }

        if (txtUsuafecha != null) {
            txtUsuafecha.setValue(null);
            txtUsuafecha.setDisabled(true);
        }

        if (txtUsuaCodigo != null) {
            txtUsuaCodigo.setValue(null);
            txtUsuaCodigo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtUsuafecha() {
        Date inputDate = (Date) txtUsuafecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            String usuaCodigo = FacesUtils.checkString(txtUsuaCodigo);
            entity = (usuaCodigo != null)
                ? businessDelegatorView.getUsuarios(usuaCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtUsuaApeNom.setDisabled(false);
            txtUsuaDias.setDisabled(false);
            txtUsuaPassw1.setDisabled(false);
            txtUsuaPassw2.setDisabled(false);
            txtUsuaPasswo.setDisabled(false);
            txtUsuafecha.setDisabled(false);
            txtUsuaCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtUsuaApeNom.setValue(entity.getUsuaApeNom());
            txtUsuaApeNom.setDisabled(false);
            txtUsuaDias.setValue(entity.getUsuaDias());
            txtUsuaDias.setDisabled(false);
            txtUsuaPassw1.setValue(entity.getUsuaPassw1());
            txtUsuaPassw1.setDisabled(false);
            txtUsuaPassw2.setValue(entity.getUsuaPassw2());
            txtUsuaPassw2.setDisabled(false);
            txtUsuaPasswo.setValue(entity.getUsuaPasswo());
            txtUsuaPasswo.setDisabled(false);
            txtUsuafecha.setValue(entity.getUsuafecha());
            txtUsuafecha.setDisabled(false);
            txtUsuaCodigo.setValue(entity.getUsuaCodigo());
            txtUsuaCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuarios = (UsuariosDTO) (evt.getComponent().getAttributes()
                                             .get("selectedUsuarios"));
        txtUsuaApeNom.setValue(selectedUsuarios.getUsuaApeNom());
        txtUsuaApeNom.setDisabled(false);
        txtUsuaDias.setValue(selectedUsuarios.getUsuaDias());
        txtUsuaDias.setDisabled(false);
        txtUsuaPassw1.setValue(selectedUsuarios.getUsuaPassw1());
        txtUsuaPassw1.setDisabled(false);
        txtUsuaPassw2.setValue(selectedUsuarios.getUsuaPassw2());
        txtUsuaPassw2.setDisabled(false);
        txtUsuaPasswo.setValue(selectedUsuarios.getUsuaPasswo());
        txtUsuaPasswo.setDisabled(false);
        txtUsuafecha.setValue(selectedUsuarios.getUsuafecha());
        txtUsuafecha.setDisabled(false);
        txtUsuaCodigo.setValue(selectedUsuarios.getUsuaCodigo());
        txtUsuaCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuarios == null) && (entity == null)) {
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
            entity = new Usuarios();

            String usuaCodigo = FacesUtils.checkString(txtUsuaCodigo);

            entity.setUsuaApeNom(FacesUtils.checkString(txtUsuaApeNom));
            entity.setUsuaCodigo(usuaCodigo);
            entity.setUsuaDias(FacesUtils.checkLong(txtUsuaDias));
            entity.setUsuaPassw1(FacesUtils.checkString(txtUsuaPassw1));
            entity.setUsuaPassw2(FacesUtils.checkString(txtUsuaPassw2));
            entity.setUsuaPasswo(FacesUtils.checkString(txtUsuaPasswo));
            entity.setUsuafecha(FacesUtils.checkDate(txtUsuafecha));
            businessDelegatorView.saveUsuarios(entity);
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
                String usuaCodigo = new String(selectedUsuarios.getUsuaCodigo());
                entity = businessDelegatorView.getUsuarios(usuaCodigo);
            }

            entity.setUsuaApeNom(FacesUtils.checkString(txtUsuaApeNom));
            entity.setUsuaDias(FacesUtils.checkLong(txtUsuaDias));
            entity.setUsuaPassw1(FacesUtils.checkString(txtUsuaPassw1));
            entity.setUsuaPassw2(FacesUtils.checkString(txtUsuaPassw2));
            entity.setUsuaPasswo(FacesUtils.checkString(txtUsuaPasswo));
            entity.setUsuafecha(FacesUtils.checkDate(txtUsuafecha));
            businessDelegatorView.updateUsuarios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuarios = (UsuariosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedUsuarios"));

            String usuaCodigo = new String(selectedUsuarios.getUsuaCodigo());
            entity = businessDelegatorView.getUsuarios(usuaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String usuaCodigo = FacesUtils.checkString(txtUsuaCodigo);
            entity = businessDelegatorView.getUsuarios(usuaCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuarios(entity);
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
            selectedUsuarios = (UsuariosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedUsuarios"));

            String usuaCodigo = new String(selectedUsuarios.getUsuaCodigo());
            entity = businessDelegatorView.getUsuarios(usuaCodigo);
            businessDelegatorView.deleteUsuarios(entity);
            data.remove(selectedUsuarios);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String usuaApeNom, String usuaCodigo,
        Long usuaDias, String usuaPassw1, String usuaPassw2, String usuaPasswo,
        Date usuafecha) throws Exception {
        try {
            entity.setUsuaApeNom(FacesUtils.checkString(usuaApeNom));
            entity.setUsuaDias(FacesUtils.checkLong(usuaDias));
            entity.setUsuaPassw1(FacesUtils.checkString(usuaPassw1));
            entity.setUsuaPassw2(FacesUtils.checkString(usuaPassw2));
            entity.setUsuaPasswo(FacesUtils.checkString(usuaPasswo));
            entity.setUsuafecha(FacesUtils.checkDate(usuafecha));
            businessDelegatorView.updateUsuarios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuariosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtUsuaApeNom() {
        return txtUsuaApeNom;
    }

    public void setTxtUsuaApeNom(InputText txtUsuaApeNom) {
        this.txtUsuaApeNom = txtUsuaApeNom;
    }

    public InputText getTxtUsuaDias() {
        return txtUsuaDias;
    }

    public void setTxtUsuaDias(InputText txtUsuaDias) {
        this.txtUsuaDias = txtUsuaDias;
    }

    public InputText getTxtUsuaPassw1() {
        return txtUsuaPassw1;
    }

    public void setTxtUsuaPassw1(InputText txtUsuaPassw1) {
        this.txtUsuaPassw1 = txtUsuaPassw1;
    }

    public InputText getTxtUsuaPassw2() {
        return txtUsuaPassw2;
    }

    public void setTxtUsuaPassw2(InputText txtUsuaPassw2) {
        this.txtUsuaPassw2 = txtUsuaPassw2;
    }

    public InputText getTxtUsuaPasswo() {
        return txtUsuaPasswo;
    }

    public void setTxtUsuaPasswo(InputText txtUsuaPasswo) {
        this.txtUsuaPasswo = txtUsuaPasswo;
    }

    public Calendar getTxtUsuafecha() {
        return txtUsuafecha;
    }

    public void setTxtUsuafecha(Calendar txtUsuafecha) {
        this.txtUsuafecha = txtUsuafecha;
    }

    public InputText getTxtUsuaCodigo() {
        return txtUsuaCodigo;
    }

    public void setTxtUsuaCodigo(InputText txtUsuaCodigo) {
        this.txtUsuaCodigo = txtUsuaCodigo;
    }

    public List<UsuariosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuarios();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuariosDTO> usuariosDTO) {
        this.data = usuariosDTO;
    }

    public UsuariosDTO getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(UsuariosDTO usuarios) {
        this.selectedUsuarios = usuarios;
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

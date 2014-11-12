package pe.gob.fovipol.sifo.controller.seguridad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import pe.gob.fovipol.sifo.controller.seguridad.util.JsfUtil;
import pe.gob.fovipol.sifo.dao.seguridad.AdmMenuFacade;
import pe.gob.fovipol.sifo.dao.seguridad.AdmMenuModuloFacade;
import pe.gob.fovipol.sifo.dao.seguridad.AdmModuloFacade;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenu;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenuModulo;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;
import pe.gob.fovipol.sifo.util.Constantes;

@ManagedBean(name = "admModuloConfiguracionController")
@ViewScoped
public class AdmModuloConfiguracionController implements Serializable {

    @EJB
    private AdmModuloFacade ejbFacade;
    @EJB
    private AdmMenuFacade ejbMenuFacade;
    @EJB
    private AdmMenuModuloFacade ejbMenuModuloFacade;
    private List<AdmModulo> items;
    private AdmModulo selected;
    private DualListModel<AdmMenu> dualList;

    @PostConstruct
    public void init() {
        items = getFacade().findAll();
    }

    public void cargarMenus() {
        dualList = new DualListModel<>(ejbMenuFacade.findDisponibleByModulo(selected),
                ejbMenuFacade.findByModulo(selected));
    }

    public AdmModulo getSelected() {
        return selected;
    }

    public void setSelected(AdmModulo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdmModuloFacade getFacade() {
        return ejbFacade;
    }

    public AdmModulo prepareCreate() {
        selected = new AdmModulo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Seguridad").getString("AdmModuloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AdmModulo> getItems() {
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Seguridad").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Seguridad").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AdmModulo getAdmModulo(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<AdmModulo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AdmModulo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the dualList
     */
    public DualListModel<AdmMenu> getDualList() {
        return dualList;
    }

    /**
     * @param dualList the dualList to set
     */
    public void setDualList(DualListModel<AdmMenu> dualList) {
        this.dualList = dualList;
    }

    public void onTransfer(TransferEvent event) {
        if (event.isAdd()) {
            try {
                for (Object adm : event.getItems()) {
                    AdmMenuModulo menuModulo = new AdmMenuModulo();
                    menuModulo.setFlagEstaMmd(Constantes.VALOR_ESTADO_ACTIVO);
                    menuModulo.setIdenMenuMnu(new AdmMenu(new BigDecimal(adm + "")));
                    menuModulo.setIdenModuMod(selected);
                    ejbMenuModuloFacade.edit(menuModulo);
                }
                JsfUtil.addSuccessMessage("Menús agregados con éxito");
            } catch (Exception e) {

            }
        }
        if (event.isRemove()) {
            try {
                for (Object adm : event.getItems()) {
                    ejbMenuModuloFacade.updateByModuloMenu(selected,new BigDecimal(adm + ""));
                }
                JsfUtil.addSuccessMessage("Menús desactivados con éxito");
            } catch (Exception e) {

            }
        }
        cargarMenus();
    }
}

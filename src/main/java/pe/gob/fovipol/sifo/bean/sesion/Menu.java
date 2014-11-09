package pe.gob.fovipol.sifo.bean.sesion;

import java.util.List;
import pe.gob.fovipol.sifo.model.seguridad.AdmModulo;



public class Menu {
	private AdmModulo modulo;
	private List<MenuDetalle> menuItems;
	public AdmModulo getModulo() {
		return modulo;
	}
	public void setModulo(AdmModulo modulo) {
		this.modulo = modulo;
	}
	public List<MenuDetalle> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(List<MenuDetalle> menuItems) {
		this.menuItems = menuItems;
	}
}

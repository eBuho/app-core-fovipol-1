package pe.gob.fovipol.sifo.bean.sesion;

import java.util.List;
import pe.gob.fovipol.sifo.model.seguridad.AdmMenu;


public class MenuDetalle {
	private AdmMenu menuItem;
	private List<MenuDetalle> menuDetalles;
	public AdmMenu getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(AdmMenu menuItem) {
		this.menuItem = menuItem;
	}
	public List<MenuDetalle> getMenuDetalles() {
		return menuDetalles;
	}
	public void setMenuDetalles(List<MenuDetalle> menuDetalles) {
		this.menuDetalles = menuDetalles;
	}
}

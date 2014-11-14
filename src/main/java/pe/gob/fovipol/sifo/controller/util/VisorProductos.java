package pe.gob.fovipol.sifo.controller.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import pe.gob.fovipol.sifo.dao.MaeProductoFacade;
import pe.gob.fovipol.sifo.model.maestros.MaeProducto;

/* @author ebuho 14/11/2014, 10:21:09 AM */
@ManagedBean(name = "visorProductos")
@ViewScoped
public class VisorProductos {
    
    @EJB
    MaeProductoFacade ejbProducto;

    List<MaeProducto> lista;
    private List<Producto> listaProductos;
    private String generalidades;

    @PostConstruct
    public void inicializa() {
        lista = ejbProducto.findAll();
        listaProductos = new ArrayList<Producto>();
        int i = 0;
        for (MaeProducto bean : lista) {
            i++;
            Producto producto = new Producto(i, bean.getNombProdPrd(), 
                    bean.getDescRequPrd(), bean.getNombEquiAud());
            listaProductos.add(producto);
        }
        
        generalidades = 
                "Monto del Préstamo             : De acuerdo a la capacidad de crédito.\n" +
                "Plazo Máximo del Descuento     : Siete (07) años.\n" +
                "Tasa de Interés Efectiva anual : 1%\n" +
                "Tiempo de Servicios            : Diez (10) años.\n" +
                "Desembolso                     : Una Armada.\n" +
                "Garantía                       : Primera Hipoteca y Seguro de Desgravamen o "
                + "Beneficios Sociales y Seguro de Desgravamen";
        
        /*
        Producto p1 = new Producto(1, "Compra a terceros", "Toda compra a Terceros debe ser con Hipoteca a favor del FOVIPOL", "producto01.jpg");
        Producto p2 = new Producto(2, "Construcción o mejoramiento", "Si cuentas con vivienda propia FOVIPOL te ofrece un crédito  para  realizar mejoras o culminar la construcción de tu vivienda", "producto02.jpg");
        Producto p3 = new Producto(3, "Compra de deuda", "Si tienes un crédito hipotecario con una entidad financiera particular. FOVIPOL te compra la deuda  con el interés mas bajo del mercado financiado en 20 años", "producto03.jpg");
        Producto p4 = new Producto(4, "Compra de bien futuro", "Esta modalidad de préstamo esta dirigida a los fovipolistas que desean adquirir un inmueble en un proyecto de vivienda próximo a ejecutar (proyectos en planos).", "producto04.jpg");
        Producto p5 = new Producto(5, "Techo propio", "Descripcion 5", "producto05.jpg");
        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        listaProductos.add(p4);
        listaProductos.add(p5);   
        */
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    /**
     * @return the generalidades
     */
    public String getGeneralidades() {
        return generalidades;
    }

    /**
     * @param generalidades the generalidades to set
     */
    public void setGeneralidades(String generalidades) {
        this.generalidades = generalidades;
    }

    public static class Producto {
        private int id;
        private String nombre;
        private String descripcion;
        private String imagen;
        
        public Producto() {
        }

        public Producto(int id, String nombre, String descripcion, String imagen) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.imagen = imagen;
        }

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return the nombre
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * @param nombre the nombre to set
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        /**
         * @return the descripcion
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * @param descripcion the descripcion to set
         */
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        /**
         * @return the imagen
         */
        public String getImagen() {
            return imagen;
        }

        /**
         * @param imagen the imagen to set
         */
        public void setImagen(String imagen) {
            this.imagen = imagen;
        }
    }
    
    
}

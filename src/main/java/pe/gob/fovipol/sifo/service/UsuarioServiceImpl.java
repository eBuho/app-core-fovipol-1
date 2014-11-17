package pe.gob.fovipol.sifo.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.gob.fovipol.sifo.model.seguridad.AdmUsuario;
import pe.gob.fovipol.sifo.util.Constantes;
import java.io.Serializable;

@Repository
public class UsuarioServiceImpl implements Serializable{

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public AdmUsuario findByUsername(String nombre) {
        AdmUsuario lista = null;
        EntityManager em = this.emf.createEntityManager();
        Query query = em.createQuery("select d from AdmUsuario d where d.codiUsuaUsr = :nombre "
                + "and d.flagEstaUsr<>" + Constantes.VALOR_ESTADO_INACTIVO);
        query.setParameter("nombre", nombre);
        try {
            lista = (AdmUsuario) query.getSingleResult();
            return lista;
        } catch (NoResultException nre) {
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.fovipol.sifo.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pe.gob.fovipol.sifo.model.maestros.MaePersona;
import pe.gob.fovipol.sifo.model.maestros.MaeSocio;
import pe.gob.fovipol.sifo.model.recuperaciones.RecAporte;
import pe.gob.fovipol.sifo.model.tramite.TrmTramite;

/**
 *
 * @author ebuho
 */
@Stateless
public class MaeSocioFacade extends AbstractFacade<MaeSocio> {
    @PersistenceContext(unitName = "SIFOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaeSocioFacade() {
        super(MaeSocio.class);
    }
    public List<MaeSocio> findHijos(MaePersona padre) {
        List<MaeSocio> lista = null;
        String sql = "FROM MaeSocio a WHERE a.maePersona=:padre and a.flagEstaSoc<>0";        
        Query q = em.createQuery(sql);
        q.setParameter("padre", padre);
        lista = q.getResultList();
        return lista;
    }
    public List<MaeSocio> findByName(String nombre) {
        List<MaeSocio> lista = null;
        String sql = "FROM MaeSocio a WHERE a.maePersona.nombCompPer like :nombre and a.flagEstaSoc<>0";        
        Query q = em.createQuery(sql);
        q.setParameter("nombre", "%"+nombre+"%");
        lista = q.getResultList();
        return lista;
    }
    public List<MaeSocio> findByDNI(String dni) {
        List<MaeSocio> lista = null;
        String sql = "FROM MaeSocio a WHERE a.maePersona.numeIdenPer =:dni and a.flagEstaSoc<>0";        
        Query q = em.createQuery(sql);
        q.setParameter("dni", dni);
        lista = q.getResultList();
        return lista;
    }
    public List<MaeSocio> findByCIP(String cip) {
        List<MaeSocio> lista = null;
        String sql = "FROM MaeSocio a WHERE a.codiCcipSoc =:cip and a.flagEstaSoc<>0";        
        Query q = em.createQuery(sql);
        q.setParameter("cip", cip);
        lista = q.getResultList();
        return lista;
    }
    public List<TrmTramite> findTramitesSocio(MaeSocio socio) {
        List<TrmTramite> lista = null;
        String sql = "FROM TrmTramite t WHERE t.codiPersTrm =:persona "
                + "order by t.idenExpeTrm desc";        
        Query q = em.createQuery(sql);
        q.setParameter("persona", socio.getMaePersona());
        lista = q.getResultList();
        return lista;
    }

    public List<RecAporte> findAportesSocio(MaeSocio socio) {
        List<RecAporte> lista = null;
        String sql = "FROM RecAporte a WHERE a.idenPersPer =:socio "
                + "order by a.nanoCobrApo desc, a.nmesCobrApo desc";        
        Query q = em.createQuery(sql);
        q.setParameter("socio", socio);
        lista = q.getResultList();
        return lista;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblEstados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevin
 */
@Stateless
public class TblEstadosFacade extends AbstractFacade<TblEstados> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblEstadosFacade() {
        super(TblEstados.class);
    }

    public List<TblEstados> finAllByTipoEstado(String tipoExamen) {
        String consulta;
        consulta = "FROM TblEstados e WHERE e.tipoEstado = ?1";
        Query query = em.createQuery(consulta);
        query.setParameter(1, tipoExamen);
        List<TblEstados> lista = query.getResultList();
        return lista;
    }

    public TblEstados cambioEstadoPromociones(String tipoEstado, String nombEstado) {
        TblEstados tblEstados = null;
        String consulta;
        try {
            consulta = "FROM TblEstados e WHERE e.tipoEstado = ?1 and e.nombreEstado = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, tipoEstado);
            query.setParameter(2, nombEstado);
            List<TblEstados> lista = query.getResultList();
            if (!lista.isEmpty()) {
                tblEstados = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return tblEstados;
    }
}

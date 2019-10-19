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
 * @author 2016
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

}

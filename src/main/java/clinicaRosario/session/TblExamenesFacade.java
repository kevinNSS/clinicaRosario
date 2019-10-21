/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblExamenes;
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
public class TblExamenesFacade extends AbstractFacade<TblExamenes> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblExamenesFacade() {
        super(TblExamenes.class);
    }

    public List<TblExamenes> findAllExamenesByEstado() {
        List<TblExamenes> lista = null;
        String consulta;
        try {
            consulta = "FROM TblExamenes e WHERE e.estadoExamen.nombreEstado = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, "ACTIVO");
            lista = query.getResultList();
        } catch (Exception e) {

        }
        return lista;
    }
}

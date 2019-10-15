/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblEmpleados;
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
public class TblEmpleadosFacade extends AbstractFacade<TblEmpleados> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblEmpleadosFacade() {
        super(TblEmpleados.class);
    }
    
    public TblEmpleados iniciarSesion(TblEmpleados us) {
        TblEmpleados tblEmpleados = null;
        String consulta;
        try {
            consulta = "FROM TblEmpleados u WHERE u.idEmpleado = ?1 and u.contrasena = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getIdEmpleado());
            query.setParameter(2, us.getContrasena());

            List<TblEmpleados> lista = query.getResultList();
            if (!lista.isEmpty()) {
                tblEmpleados = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return tblEmpleados;
    }
    
}

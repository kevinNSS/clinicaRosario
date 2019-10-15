/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaRosario.session;

import clinicaRosario.entity.TblUsuarios;
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
public class TblUsuariosFacade extends AbstractFacade<TblUsuarios> {

    @PersistenceContext(unitName = "com.mycompany_clinicaRosario_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblUsuariosFacade() {
        super(TblUsuarios.class);
    }
    
    public TblUsuarios iniciarSesion(TblUsuarios us){
        TblUsuarios tblUsuarios = null;
        String consulta;
        try{
            consulta = "FROM TblUsuario u WHERE u.usuarioEmpleado.idEmpleado = ?1 and u.contrasena = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsuarioEmpleado().getIdEmpleado());
            query.setParameter(2, us.getContrasena());
            
            List<TblUsuarios> lista  = query.getResultList();
            if(!lista.isEmpty()){
                tblUsuarios = lista.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        return tblUsuarios;
    }
    
}

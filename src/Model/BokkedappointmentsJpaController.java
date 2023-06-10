/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author PC
 */
public class BokkedappointmentsJpaController implements Serializable {

    public BokkedappointmentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bokkedappointments bokkedappointments) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointments appointmentId = bokkedappointments.getAppointmentId();
            if (appointmentId != null) {
                appointmentId = em.getReference(appointmentId.getClass(), appointmentId.getId());
                bokkedappointments.setAppointmentId(appointmentId);
            }
            Users userId = bokkedappointments.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                bokkedappointments.setUserId(userId);
            }
            em.persist(bokkedappointments);
            if (appointmentId != null) {
                appointmentId.getBokkedappointmentsList().add(bokkedappointments);
                appointmentId = em.merge(appointmentId);
            }
            if (userId != null) {
                userId.getBokkedappointmentsList().add(bokkedappointments);
                userId = em.merge(userId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bokkedappointments bokkedappointments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bokkedappointments persistentBokkedappointments = em.find(Bokkedappointments.class, bokkedappointments.getId());
            Appointments appointmentIdOld = persistentBokkedappointments.getAppointmentId();
            Appointments appointmentIdNew = bokkedappointments.getAppointmentId();
            Users userIdOld = persistentBokkedappointments.getUserId();
            Users userIdNew = bokkedappointments.getUserId();
            if (appointmentIdNew != null) {
                appointmentIdNew = em.getReference(appointmentIdNew.getClass(), appointmentIdNew.getId());
                bokkedappointments.setAppointmentId(appointmentIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                bokkedappointments.setUserId(userIdNew);
            }
            bokkedappointments = em.merge(bokkedappointments);
            if (appointmentIdOld != null && !appointmentIdOld.equals(appointmentIdNew)) {
                appointmentIdOld.getBokkedappointmentsList().remove(bokkedappointments);
                appointmentIdOld = em.merge(appointmentIdOld);
            }
            if (appointmentIdNew != null && !appointmentIdNew.equals(appointmentIdOld)) {
                appointmentIdNew.getBokkedappointmentsList().add(bokkedappointments);
                appointmentIdNew = em.merge(appointmentIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getBokkedappointmentsList().remove(bokkedappointments);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getBokkedappointmentsList().add(bokkedappointments);
                userIdNew = em.merge(userIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bokkedappointments.getId();
                if (findBokkedappointments(id) == null) {
                    throw new NonexistentEntityException("The bokkedappointments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bokkedappointments bokkedappointments;
            try {
                bokkedappointments = em.getReference(Bokkedappointments.class, id);
                bokkedappointments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bokkedappointments with id " + id + " no longer exists.", enfe);
            }
            Appointments appointmentId = bokkedappointments.getAppointmentId();
            if (appointmentId != null) {
                appointmentId.getBokkedappointmentsList().remove(bokkedappointments);
                appointmentId = em.merge(appointmentId);
            }
            Users userId = bokkedappointments.getUserId();
            if (userId != null) {
                userId.getBokkedappointmentsList().remove(bokkedappointments);
                userId = em.merge(userId);
            }
            em.remove(bokkedappointments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bokkedappointments> findBokkedappointmentsEntities() {
        return findBokkedappointmentsEntities(true, -1, -1);
    }

    public List<Bokkedappointments> findBokkedappointmentsEntities(int maxResults, int firstResult) {
        return findBokkedappointmentsEntities(false, maxResults, firstResult);
    }

    private List<Bokkedappointments> findBokkedappointmentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bokkedappointments.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bokkedappointments findBokkedappointments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bokkedappointments.class, id);
        } finally {
            em.close();
        }
    }

    public int getBokkedappointmentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bokkedappointments> rt = cq.from(Bokkedappointments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

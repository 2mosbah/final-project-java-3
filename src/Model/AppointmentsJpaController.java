/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author PC
 */
public class AppointmentsJpaController implements Serializable {

    public AppointmentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Appointments appointments) {
        if (appointments.getBokkedappointmentsList() == null) {
            appointments.setBokkedappointmentsList(new ArrayList<Bokkedappointments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Bokkedappointments> attachedBokkedappointmentsList = new ArrayList<Bokkedappointments>();
            for (Bokkedappointments bokkedappointmentsListBokkedappointmentsToAttach : appointments.getBokkedappointmentsList()) {
                bokkedappointmentsListBokkedappointmentsToAttach = em.getReference(bokkedappointmentsListBokkedappointmentsToAttach.getClass(), bokkedappointmentsListBokkedappointmentsToAttach.getId());
                attachedBokkedappointmentsList.add(bokkedappointmentsListBokkedappointmentsToAttach);
            }
            appointments.setBokkedappointmentsList(attachedBokkedappointmentsList);
            em.persist(appointments);
            for (Bokkedappointments bokkedappointmentsListBokkedappointments : appointments.getBokkedappointmentsList()) {
                Appointments oldAppointmentIdOfBokkedappointmentsListBokkedappointments = bokkedappointmentsListBokkedappointments.getAppointmentId();
                bokkedappointmentsListBokkedappointments.setAppointmentId(appointments);
                bokkedappointmentsListBokkedappointments = em.merge(bokkedappointmentsListBokkedappointments);
                if (oldAppointmentIdOfBokkedappointmentsListBokkedappointments != null) {
                    oldAppointmentIdOfBokkedappointmentsListBokkedappointments.getBokkedappointmentsList().remove(bokkedappointmentsListBokkedappointments);
                    oldAppointmentIdOfBokkedappointmentsListBokkedappointments = em.merge(oldAppointmentIdOfBokkedappointmentsListBokkedappointments);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Appointments appointments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointments persistentAppointments = em.find(Appointments.class, appointments.getId());
            List<Bokkedappointments> bokkedappointmentsListOld = persistentAppointments.getBokkedappointmentsList();
            List<Bokkedappointments> bokkedappointmentsListNew = appointments.getBokkedappointmentsList();
            List<Bokkedappointments> attachedBokkedappointmentsListNew = new ArrayList<>();
//            for (Bokkedappointments bokkedappointmentsListNewBokkedappointmentsToAttach : bokkedappointmentsListNew) {
//                bokkedappointmentsListNewBokkedappointmentsToAttach = em.getReference(bokkedappointmentsListNewBokkedappointmentsToAttach.getClass(), bokkedappointmentsListNewBokkedappointmentsToAttach.getId());
//                attachedBokkedappointmentsListNew.add(bokkedappointmentsListNewBokkedappointmentsToAttach);
//            }
            bokkedappointmentsListNew = attachedBokkedappointmentsListNew;
            appointments.setBokkedappointmentsList(bokkedappointmentsListNew);
            appointments = em.merge(appointments);
            for (Bokkedappointments bokkedappointmentsListOldBokkedappointments : bokkedappointmentsListOld) {
                if (!bokkedappointmentsListNew.contains(bokkedappointmentsListOldBokkedappointments)) {
                    bokkedappointmentsListOldBokkedappointments.setAppointmentId(null);
                    bokkedappointmentsListOldBokkedappointments = em.merge(bokkedappointmentsListOldBokkedappointments);
                }
            }
            for (Bokkedappointments bokkedappointmentsListNewBokkedappointments : bokkedappointmentsListNew) {
                if (!bokkedappointmentsListOld.contains(bokkedappointmentsListNewBokkedappointments)) {
                    Appointments oldAppointmentIdOfBokkedappointmentsListNewBokkedappointments = bokkedappointmentsListNewBokkedappointments.getAppointmentId();
                    bokkedappointmentsListNewBokkedappointments.setAppointmentId(appointments);
                    bokkedappointmentsListNewBokkedappointments = em.merge(bokkedappointmentsListNewBokkedappointments);
                    if (oldAppointmentIdOfBokkedappointmentsListNewBokkedappointments != null && !oldAppointmentIdOfBokkedappointmentsListNewBokkedappointments.equals(appointments)) {
                        oldAppointmentIdOfBokkedappointmentsListNewBokkedappointments.getBokkedappointmentsList().remove(bokkedappointmentsListNewBokkedappointments);
                        oldAppointmentIdOfBokkedappointmentsListNewBokkedappointments = em.merge(oldAppointmentIdOfBokkedappointmentsListNewBokkedappointments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = appointments.getId();
                if (findAppointments(id) == null) {
                    throw new NonexistentEntityException("The appointments with id " + id + " no longer exists.");
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
            Appointments appointments;
            try {
                appointments = em.getReference(Appointments.class, id);
                appointments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The appointments with id " + id + " no longer exists.", enfe);
            }
            List<Bokkedappointments> bokkedappointmentsList = appointments.getBokkedappointmentsList();
            for (Bokkedappointments bokkedappointmentsListBokkedappointments : bokkedappointmentsList) {
                bokkedappointmentsListBokkedappointments.setAppointmentId(null);
                bokkedappointmentsListBokkedappointments = em.merge(bokkedappointmentsListBokkedappointments);
            }
            em.remove(appointments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Appointments> findAppointmentsEntities() {
        return findAppointmentsEntities(true, -1, -1);
    }

    public List<Appointments> findAppointmentsEntities(int maxResults, int firstResult) {
        return findAppointmentsEntities(false, maxResults, firstResult);
    }

    private List<Appointments> findAppointmentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Appointments.class));
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

    public Appointments findAppointments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Appointments.class, id);
        } finally {
            em.close();
        }
    }

    public int getAppointmentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Appointments> rt = cq.from(Appointments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

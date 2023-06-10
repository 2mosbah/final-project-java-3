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
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getBokkedappointmentsList() == null) {
            users.setBokkedappointmentsList(new ArrayList<Bokkedappointments>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Bokkedappointments> attachedBokkedappointmentsList = new ArrayList<Bokkedappointments>();
            for (Bokkedappointments bokkedappointmentsListBokkedappointmentsToAttach : users.getBokkedappointmentsList()) {
                bokkedappointmentsListBokkedappointmentsToAttach = em.getReference(bokkedappointmentsListBokkedappointmentsToAttach.getClass(), bokkedappointmentsListBokkedappointmentsToAttach.getId());
                attachedBokkedappointmentsList.add(bokkedappointmentsListBokkedappointmentsToAttach);
            }
            users.setBokkedappointmentsList(attachedBokkedappointmentsList);
            em.persist(users);
            for (Bokkedappointments bokkedappointmentsListBokkedappointments : users.getBokkedappointmentsList()) {
                Users oldUserIdOfBokkedappointmentsListBokkedappointments = bokkedappointmentsListBokkedappointments.getUserId();
                bokkedappointmentsListBokkedappointments.setUserId(users);
                bokkedappointmentsListBokkedappointments = em.merge(bokkedappointmentsListBokkedappointments);
                if (oldUserIdOfBokkedappointmentsListBokkedappointments != null) {
                    oldUserIdOfBokkedappointmentsListBokkedappointments.getBokkedappointmentsList().remove(bokkedappointmentsListBokkedappointments);
                    oldUserIdOfBokkedappointmentsListBokkedappointments = em.merge(oldUserIdOfBokkedappointmentsListBokkedappointments);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getId());
            List<Bokkedappointments> bokkedappointmentsListOld = persistentUsers.getBokkedappointmentsList();
            List<Bokkedappointments> bokkedappointmentsListNew = users.getBokkedappointmentsList();
            List<Bokkedappointments> attachedBokkedappointmentsListNew = new ArrayList<Bokkedappointments>();
            for (Bokkedappointments bokkedappointmentsListNewBokkedappointmentsToAttach : bokkedappointmentsListNew) {
                bokkedappointmentsListNewBokkedappointmentsToAttach = em.getReference(bokkedappointmentsListNewBokkedappointmentsToAttach.getClass(), bokkedappointmentsListNewBokkedappointmentsToAttach.getId());
                attachedBokkedappointmentsListNew.add(bokkedappointmentsListNewBokkedappointmentsToAttach);
            }
            bokkedappointmentsListNew = attachedBokkedappointmentsListNew;
            users.setBokkedappointmentsList(bokkedappointmentsListNew);
            users = em.merge(users);
            for (Bokkedappointments bokkedappointmentsListOldBokkedappointments : bokkedappointmentsListOld) {
                if (!bokkedappointmentsListNew.contains(bokkedappointmentsListOldBokkedappointments)) {
                    bokkedappointmentsListOldBokkedappointments.setUserId(null);
                    bokkedappointmentsListOldBokkedappointments = em.merge(bokkedappointmentsListOldBokkedappointments);
                }
            }
            for (Bokkedappointments bokkedappointmentsListNewBokkedappointments : bokkedappointmentsListNew) {
                if (!bokkedappointmentsListOld.contains(bokkedappointmentsListNewBokkedappointments)) {
                    Users oldUserIdOfBokkedappointmentsListNewBokkedappointments = bokkedappointmentsListNewBokkedappointments.getUserId();
                    bokkedappointmentsListNewBokkedappointments.setUserId(users);
                    bokkedappointmentsListNewBokkedappointments = em.merge(bokkedappointmentsListNewBokkedappointments);
                    if (oldUserIdOfBokkedappointmentsListNewBokkedappointments != null && !oldUserIdOfBokkedappointmentsListNewBokkedappointments.equals(users)) {
                        oldUserIdOfBokkedappointmentsListNewBokkedappointments.getBokkedappointmentsList().remove(bokkedappointmentsListNewBokkedappointments);
                        oldUserIdOfBokkedappointmentsListNewBokkedappointments = em.merge(oldUserIdOfBokkedappointmentsListNewBokkedappointments);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<Bokkedappointments> bokkedappointmentsList = users.getBokkedappointmentsList();
            for (Bokkedappointments bokkedappointmentsListBokkedappointments : bokkedappointmentsList) {
                bokkedappointmentsListBokkedappointments.setUserId(null);
                bokkedappointmentsListBokkedappointments = em.merge(bokkedappointmentsListBokkedappointments);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

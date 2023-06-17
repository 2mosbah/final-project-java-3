/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "bokkedappointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bokkedappointments.findAll", query = "SELECT b FROM Bokkedappointments b"),
    @NamedQuery(name = "Bokkedappointments.findById", query = "SELECT b FROM Bokkedappointments b WHERE b.id = :id"),
    @NamedQuery(name = "Bokkedappointments.findByStatus", query = "SELECT b FROM Bokkedappointments b WHERE b.status = :status"),
    @NamedQuery(name = "Bokkedappointments.findByUserId", query = "SELECT b FROM Bokkedappointments b WHERE b.userId = :userId"),
    @NamedQuery(name = "Bokkedappointments.findByUserIdAndStat", query = "SELECT b FROM Bokkedappointments b WHERE b.userId = :userId and b.status = :status"),
    @NamedQuery(name = "Bokkedappointments.findByDoctorComment", query = "SELECT b FROM Bokkedappointments b WHERE b.doctorComment = :doctorComment")})
public class Bokkedappointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;
    @Column(name = "doctorComment")
    private String doctorComment;
    @JoinColumn(name = "appointmentId", referencedColumnName = "id")
    @ManyToOne
    private Appointments appointmentId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Bokkedappointments() {
    }

    public Bokkedappointments(Integer id) {
        this.id = id;
    }

    public Bokkedappointments(String status, Appointments appointmentId, Users userId) {
        this.status = status;
        this.appointmentId = appointmentId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    public Appointments getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointments appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bokkedappointments)) {
            return false;
        }
        Bokkedappointments other = (Bokkedappointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Bokkedappointments[ id=" + id + " ]";
    }

}

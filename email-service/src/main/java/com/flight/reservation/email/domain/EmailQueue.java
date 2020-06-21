package com.flight.reservation.email.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class EmailQueue {
    @Id
    @GeneratedValue
    private int id;

    @Type(type="uuid-char")
    private UUID userPublicId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date earliestFlightDate;

    private String email;

    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus;

    public EmailQueue() {
    }

    public EmailQueue(UUID userPublicId, Date earliestFlightDate, String email, EmailStatus emailStatus) {
        this.userPublicId = userPublicId;
        this.earliestFlightDate = earliestFlightDate;
        this.email = email;
        this.emailStatus = emailStatus;
    }

    public int getId() {
        return id;
    }

    public EmailStatus getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(EmailStatus emailStatus) {
        this.emailStatus = emailStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUserPublicId() {
        return userPublicId;
    }

    public void setUserPublicId(UUID userPublicId) {
        this.userPublicId = userPublicId;
    }

    public Date getEarliestFlightDate() {
        return earliestFlightDate;
    }

    public void setEarliestFlightDate(Date earliestFlightDate) {
        this.earliestFlightDate = earliestFlightDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

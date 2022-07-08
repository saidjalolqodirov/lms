package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 11:48 PM
 */
@Getter
@Setter
@Entity
public class Mark extends Auditable {

    private int mark;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Journal journal;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Assignment assignment;
}

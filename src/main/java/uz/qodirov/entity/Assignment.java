package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 5:25 PM
 */
@Getter
@Setter
@Entity
public class Assignment extends Auditable {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Subject subject;
}

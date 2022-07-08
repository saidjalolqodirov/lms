package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 8:43 PM
 */
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends Auditable {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty faculty;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    @OneToOne(mappedBy = "group")
    private Journal journal;
    @ManyToMany
    private List<Subject> subjects;

    private Date year;
}


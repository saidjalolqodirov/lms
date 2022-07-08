package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 11:43 PM
 */
@Getter
@Setter
@Entity
public class Student extends Auditable {

    @Column(nullable = false)
    private String firstName;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "student")
    private List<Mark> marks;

    @ManyToOne
    private Group group;

    private String address;
}

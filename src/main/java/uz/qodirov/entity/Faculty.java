package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 6:33 PM
 */
@Getter
@Setter
@Entity
public class Faculty extends Auditable {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private University university;

    @OneToMany(mappedBy = "faculty")
    private List<Group> groups;

    private Date openYear;
}

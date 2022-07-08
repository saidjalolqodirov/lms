package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 11:55 PM
 */
@Getter
@Setter
@Entity
public class Subject extends Auditable {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "subjects")
    private List<Group> groups;
}

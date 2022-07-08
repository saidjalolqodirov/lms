package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 11:50 PM
 */
@Getter
@Setter
@Entity
public class Journal extends Auditable {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private Group group;

    @OneToMany(mappedBy = "journal")
    private List<Mark> marks;
}

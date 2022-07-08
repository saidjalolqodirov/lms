package uz.qodirov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 6:01 PM
 */
@Getter
@Setter
@Entity
public class University extends Auditable {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private Date openYear;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Faculty> faculty;
}

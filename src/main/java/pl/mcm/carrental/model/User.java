package pl.mcm.carrental.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private  String password;

    @Column(name = "email")
    private  String email;

    @Column(name = "phone")
    private  String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthDate")
    private Date birthDate;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_to_roles", joinColumns = @JoinColumn(name = "users_ID"), inverseJoinColumns = @JoinColumn(name = "userroles_ID"))
    private Set<UserRole> userRoleSet = new HashSet<UserRole>();


}

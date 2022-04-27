package com.reloadly.account.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "user", schema="public",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
    @Column(name = "identification_number")
    private Long identificationNumber;
    @Column(name = "holder_name")
    private String holderName;
    @Column(name = "holder_last_name")
    private String holderLastName;
    @Column(name = "holder_sur_name")
    private String holderSurName;
    @Column(name = "address")
    private String address;


    public UserEntity(String username, String email, String password, Long identificationNumber, String holderName,
                      String holderLastName, String holderSurName, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.identificationNumber = identificationNumber;
        this.holderName = holderName;
        this.holderLastName = holderLastName;
        this.holderSurName = holderSurName;
        this.address = address;
    }
}

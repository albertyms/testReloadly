package com.reloadly.notification.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account", schema="public")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "identification_number")
    private String identificationNumber;
    @Column(name = "holder_name")
    private String holderName;
    @Column(name = "holder_last_name")
    private String holderLastName;
    @Column(name = "holder_sur_name")
    private String holderSurName;
    @Column(name = "number_account", unique = true, length = 20)
    private String numberAccount;
    @Column(name = "address")
    private String address;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "email")
    private String email;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column(name = "type_account")
    private String typeAccount;
    @Column(name = "creation_date")
    private Date creationDate;

}

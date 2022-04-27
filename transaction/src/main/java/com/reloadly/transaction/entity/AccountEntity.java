package com.reloadly.transaction.entity;

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
    @Column(name = "number_account", unique = true, length = 20)
    private Long numberAccount;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column(name = "type_account")
    private String typeAccount;
    @Column(name = "creation_date")
    private Date creationDate;

}

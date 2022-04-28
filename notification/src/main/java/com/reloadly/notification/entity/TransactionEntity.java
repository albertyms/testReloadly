package com.reloadly.notification.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction", schema="public")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private AccountEntity account;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "type_transaction")
    private String typeTransaction;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "creation_date")
    private Date creationDate;

}

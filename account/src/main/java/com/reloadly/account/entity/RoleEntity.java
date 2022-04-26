package com.reloadly.account.entity;

import com.reloadly.account.util.RoleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

}

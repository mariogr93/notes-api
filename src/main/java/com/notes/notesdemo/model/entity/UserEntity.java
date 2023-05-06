package com.notes.notesdemo.model.entity;

import com.notes.notesdemo.model.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(name = "email",unique = true, nullable = false)

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}

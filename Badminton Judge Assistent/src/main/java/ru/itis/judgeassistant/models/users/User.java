package ru.itis.judgeassistant.models.users;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class User {
    public enum State {
        DELETED,
        CONFIRMED,
        BANNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String login;

    @Column(name = "hash_password")
    @Setter
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private State state;

    public boolean isConfirmed() {
        return this.state.equals(State.CONFIRMED);
    }

    public boolean isBanned() {
        return this.state.equals(State.BANNED);
    }
    public enum Role {
        USER,
        ADMIN,
        JUDGE,
        SUPER_ADMIN;
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;
}


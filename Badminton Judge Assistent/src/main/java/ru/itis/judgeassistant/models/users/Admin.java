package ru.itis.judgeassistant.models.users;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class Admin extends User {
}

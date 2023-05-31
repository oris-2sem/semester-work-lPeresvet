package ru.itis.judgeassistant.models.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itis.judgeassistant.models.Game;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "judges")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Judge extends User{

    private String name;
    private String surname;

    @OneToMany(mappedBy = "judge")
    private List<Game> games;

}

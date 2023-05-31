package ru.itis.judgeassistant.models.simpleentities;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itis.judgeassistant.models.Team;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Player extends SimpleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Sex sex;

    private Byte age;

    private Integer points;

    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    public enum Sex {
        @JsonEnumDefaultValue
        MALE("male"),
        FEMALE("female");

        private final String type;
        Sex(String type) {
            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }
}



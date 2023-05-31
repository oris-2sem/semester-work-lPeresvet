package ru.itis.judgeassistant.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import ru.itis.judgeassistant.models.users.Judge;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date time;

    private Byte turn;

    @ManyToOne
    @JoinColumn(name = "cort_id", referencedColumnName = "id")
    private Cort cort;

    @ManyToMany
    @JoinTable(
            name = "teams_game",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;

    @OneToMany(mappedBy = "game")
    private List<Score> scores;

    @ManyToOne
    @JoinColumn(name = "judge_id", referencedColumnName = "id")
    private Judge judge;

    private Status status;

    public enum Status {
        PREPARED("prepared"),
        STARTED("started"),
        FINISHED("finished");

        private String type;
        Status(String status) {
            this.type = status;
        }
        @JsonValue
        public String getType() {
            return type;
        }
    }
}

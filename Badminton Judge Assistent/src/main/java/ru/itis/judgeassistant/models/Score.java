package ru.itis.judgeassistant.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "score")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_team_points")
    private Integer firstTeamPoints;

    @Column(name = "second_team_points")
    private Integer secondTeamPoints;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    public enum Status {
        @JsonEnumDefaultValue
        ACTIVE("active"),
        STORED("stored");

        private final String type;
        Status(String stored) {
            this.type = stored;
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }
}

package ru.itis.judgeassistant.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cort")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @OneToMany(mappedBy = "cort")
    private List<Game> games;
}

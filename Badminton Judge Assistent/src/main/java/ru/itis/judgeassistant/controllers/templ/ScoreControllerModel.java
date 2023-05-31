package ru.itis.judgeassistant.controllers.templ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.judgeassistant.dto.cort.CortDto;
import ru.itis.judgeassistant.dto.game.PlayersScoresDto;
import ru.itis.judgeassistant.services.CortService;
import ru.itis.judgeassistant.services.GameService;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreControllerModel {
    private final GameService gameService;
    private final CortService cortService;
    @GetMapping("/template")
    public String activeScore(@RequestParam("cortId") long cortId,
                        Model model) {
        PlayersScoresDto result = gameService.findActiveScoreByCortId(cortId);
        CortDto cort = cortService.findById(cortId);

        model.addAttribute("cort", cort);
        model.addAttribute("scores", result);

        return "score";
    }
}

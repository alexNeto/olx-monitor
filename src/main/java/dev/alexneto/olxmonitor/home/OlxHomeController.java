package dev.alexneto.olxmonitor.home;

import dev.alexneto.olxmonitor.home.model.dto.HomeResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home-monitor")
@RequiredArgsConstructor
public class OlxHomeController {

    private final OlxHomeService olxHomeService;

    @GetMapping("/{id}/results")
    public List<HomeResultDTO> getAllResult(@PathVariable long id) {
        return olxHomeService.findAllHomeResultByOlxModelId(id);
    }

    @GetMapping("/check-new-results")
    public String checkNewResults() {
        olxHomeService.verifyNewItems();
        return "ok";
    }
}

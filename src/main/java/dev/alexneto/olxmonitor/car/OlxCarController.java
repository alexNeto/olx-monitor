package dev.alexneto.olxmonitor.car;

import dev.alexneto.olxmonitor.car.model.dto.CarResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car-monitor")
@RequiredArgsConstructor
public class OlxCarController {

    private final OlxCarService olxCarService;

    @GetMapping("/{id}/results")
    public List<CarResultDTO> getAllResult(@PathVariable long id) {
        return olxCarService.findAllCarResultByOlxModelId(id);
    }

    @GetMapping("/check-new-results")
    public String checkNewResults() {
        olxCarService.verifyNewItems();
        return "ok";
    }
}

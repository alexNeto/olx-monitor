package dev.alexneto.olxmonitor.olx;

import dev.alexneto.olxmonitor.olx.model.OlxMonitor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/olx-monitor")
@RequiredArgsConstructor
public class OlxMonitorController {

    private final OlxMonitorService olxMonitorService;

    @GetMapping
    public List<OlxMonitor> getAll() {
        return olxMonitorService.findAll();
    }

    @PostMapping
    public OlxMonitor saveNewMonitor(@RequestBody OlxMonitor olxModel) {
        return olxMonitorService.save(olxModel);
    }


}

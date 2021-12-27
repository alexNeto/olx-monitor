package dev.alexneto.olxmonitor.olx;

import dev.alexneto.olxmonitor.olx.model.OlxMonitor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @GetMapping("/types")
    public List<MonitorType> getAllMonitorType() {
        return Arrays.asList(MonitorType.values());
    }

    @PostMapping
    public OlxMonitor saveNewMonitor(@RequestBody OlxMonitor olxModel) {
        return olxMonitorService.save(olxModel);
    }

    @PutMapping
    public OlxMonitor updateMonitor(@RequestBody OlxMonitor olxModel) {
        return olxMonitorService.update(olxModel);
    }
}

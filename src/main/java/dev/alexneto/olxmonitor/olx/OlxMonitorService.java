package dev.alexneto.olxmonitor.olx;

import dev.alexneto.olxmonitor.olx.model.OlxMonitor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OlxMonitorService {

    private final OlxMonitorRepository monitorRepository;

    @Cacheable(cacheNames = "olx-monitor-find-all")
    public List<OlxMonitor> findAll() {
        return monitorRepository.findAll();
    }

    public OlxMonitor save(OlxMonitor olxMonitor) {
        return monitorRepository.save(olxMonitor);
    }
}

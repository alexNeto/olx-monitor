package dev.alexneto.olxmonitor.olx;

import dev.alexneto.olxmonitor.olx.model.OlxMonitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OlxMonitorService {

    private final OlxMonitorRepository monitorRepository;

    private static final String LOGTAG = "[OLX-MONITOR-SERVICE]";

    @Cacheable(cacheNames = "olx-monitor-find-all")
    public List<OlxMonitor> findAll() {
        return monitorRepository.findAll();
    }

    public OlxMonitor save(OlxMonitor olxMonitor) {
        return monitorRepository.save(olxMonitor);
    }

    public OlxMonitor update(OlxMonitor olxModel) {
        OlxMonitor olxMonitor = monitorRepository.findById(olxModel.getId()).orElse(new OlxMonitor());
        updateSavedOlxMonitor(olxModel, olxMonitor);
        log.info("{} Updating olxModel={} newOlxModel={}", LOGTAG, olxModel, olxMonitor);
        return monitorRepository.save(olxMonitor);
    }

    private void updateSavedOlxMonitor(OlxMonitor olxModel, OlxMonitor olxMonitor) {
        if (olxModel.getUsername() != null)
            olxMonitor.setUsername(olxModel.getUsername());
        if (olxModel.getChatId() != null)
            olxMonitor.setChatId(olxModel.getChatId());
        if (olxModel.getMessageId() != null)
            olxMonitor.setMessageId(olxModel.getMessageId());
        if (olxModel.getUrlToMonitor() != null)
            olxMonitor.setUrlToMonitor(olxModel.getUrlToMonitor());
        if (olxModel.getMonitorType() != null)
            olxMonitor.setMonitorType(olxModel.getMonitorType());
    }
}

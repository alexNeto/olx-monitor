package dev.alexneto.olxmonitor.olx;

import dev.alexneto.olxmonitor.olx.model.OlxMonitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OlxMonitorRepository extends JpaRepository<OlxMonitor, Long> {
}

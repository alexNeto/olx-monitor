package dev.alexneto.olxmonitor.home;

import dev.alexneto.olxmonitor.home.model.HomeResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeResultRepository extends JpaRepository<HomeResult, Long> {

    List<HomeResult> getHomeResultByOlxMonitorId(long id);

    boolean existsByInternalId(String id);
}

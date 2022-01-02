package dev.alexneto.olxmonitor.car;

import dev.alexneto.olxmonitor.car.model.CarResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarResultRepository extends JpaRepository<CarResult, Long> {

    List<CarResult> getCarResultByOlxMonitorId(long id);

    boolean existsByInternalId(String id);
}

package dev.alexneto.olxmonitor.olx.model;

import dev.alexneto.olxmonitor.olx.MonitorType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OlxMonitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private Long chatId;
    private Long messageId;
    @Column(unique = true)
    private String urlToMonitor;
    @Enumerated(EnumType.STRING)
    private MonitorType monitorType;
}


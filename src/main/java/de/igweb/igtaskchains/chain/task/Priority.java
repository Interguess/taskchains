package de.igweb.igtaskchains.chain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Priority {
    LOW(2),
    NORMAL(1),
    HIGH(0);

    @Getter private int value;
}

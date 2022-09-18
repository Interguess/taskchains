package de.igweb.igtaskchains.chain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {

    LOW(2), NORMAL(1), HIGH(0);

    private final int value;

}

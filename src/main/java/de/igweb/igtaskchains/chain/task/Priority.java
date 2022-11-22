package de.igweb.igtaskchains.chain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {

    LOW(2), NORMAL(1), HIGH(0), CUSTOM(999);

    private int value;

    public Priority priority(int priority) {
        value = priority;
        return this;
    }

}

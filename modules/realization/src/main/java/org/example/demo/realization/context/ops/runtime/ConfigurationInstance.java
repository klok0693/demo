package org.example.demo.realization.context.ops.runtime;

import lombok.Data;

@Data
public class ConfigurationInstance implements MutableConfiguration {
    private boolean isCustomSkinsAllowed = true;
}

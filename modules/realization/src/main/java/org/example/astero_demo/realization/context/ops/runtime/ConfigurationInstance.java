package org.example.astero_demo.realization.context.ops.runtime;

import lombok.Data;

@Data
public class ConfigurationInstance implements MutableConfiguration {
    private boolean isCustomSkinsAllowed = true;
}

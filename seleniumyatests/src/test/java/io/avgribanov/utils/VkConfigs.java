package io.avgribanov.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:VkConfigs.properties"})
public interface VkConfigs extends Config {
    String email();

    String password();
}


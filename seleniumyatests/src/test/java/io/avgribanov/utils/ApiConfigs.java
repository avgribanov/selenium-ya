package io.avgribanov.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:ApiConfigs.properties"})
public interface ApiConfigs extends Config {
    String xauthorization();

    String xsessionid();
}


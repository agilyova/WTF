package com.teamcity.example.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  private static final String CONFIG_PROPERTIES = "config.properties";
  private static Config config;
  private Properties properties;

  private Config() {
        properties = new Properties();
        loadProperties(CONFIG_PROPERTIES);
  }

  public static Config getConfig() {
    if (config == null) {
      config = new Config();
    }
    return config;
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  private void  loadProperties(String fileName) {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
      if (inputStream == null) {
        System.out.println("File not found " + fileName);
      }
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("",e);
    }
  }
}

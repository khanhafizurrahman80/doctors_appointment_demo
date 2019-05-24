// this package name must be same like the module name
package com.example.riad.doctorsappointment.data;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.example.riad.doctorsappointment.data")
public class DataSourceConfig {
}

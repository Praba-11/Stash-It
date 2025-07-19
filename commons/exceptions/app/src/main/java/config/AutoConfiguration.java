package config;

import handler.GlobalExceptionHandler;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.autoconfigure.AutoConfiguration
@ComponentScan(basePackageClasses = GlobalExceptionHandler.class)
public class AutoConfiguration {
    // This will automatically scan and register GlobalExceptionHandler
}

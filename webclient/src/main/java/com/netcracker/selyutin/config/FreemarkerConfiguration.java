package com.netcracker.selyutin.config;

import no.api.freemarker.java8.Java8ObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FreemarkerConfiguration extends FreeMarkerAutoConfiguration.FreeMarkerNonWebConfiguration {

    @Qualifier("freeMarkerConfiguration")
    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void postConstruct() {
        configuration.setObjectWrapper(
                new Java8ObjectWrapper(freemarker.template.Configuration.getVersion())); // VERSION_2_3_26
    }


}

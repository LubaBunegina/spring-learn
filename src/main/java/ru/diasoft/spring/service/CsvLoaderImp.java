package ru.diasoft.spring.service;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class CsvLoaderImp implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;
    private String location;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getResource(){
        return resourceLoader.getResource(location);
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

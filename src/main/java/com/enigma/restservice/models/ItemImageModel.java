package com.enigma.restservice.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ItemImageModel {

    private String name;
    private String url;
    private String type;
    private Long size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public static ItemImageModel from(Integer id, Path path) throws IOException {
        
        String name = path.getFileName().toString();
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/items/" + id + "/images/")
                .path(name)
                .toUriString();
        ItemImageModel model = new ItemImageModel();
        model.setName(name);
        model.setUrl(uri);
        model.setType(Files.probeContentType(path));
        model.setSize(Files.size(path));
        
        return model;

    }

}

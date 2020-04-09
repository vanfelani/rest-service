package com.enigma.restservice.services.impl;

import com.enigma.restservice.configs.ApplicationProperties;
import com.enigma.restservice.entities.Item;
import com.enigma.restservice.services.ItemImageService;
import com.enigma.restservice.services.ItemService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemImageServiceImpl implements ItemImageService {

    @Autowired
    private ApplicationProperties applicationProperties;

    private Path parentDir;

    @PostConstruct
    public void init() throws IOException {
        parentDir = Paths.get(applicationProperties.getDataDir(), "item").toAbsolutePath().normalize();
        Files.createDirectories(parentDir);
    }

    @Override
    public Path save(Item entity, MultipartFile file) throws IOException {
        Path dir = parentDir.resolve(entity.getId().toString());
        Files.createDirectories(dir);

        Path target = dir.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return target;
    }

    @Override
    public Resource load(Item entity, String filename) throws IOException {
        Path target = parentDir.resolve(entity.getId().toString()).resolve(filename);
        Resource resource = new UrlResource(target.toUri());
        return resource;
    }

    @Override
    public void delete(Item entity, String filename) throws IOException {
        Path target = parentDir.resolve(entity.getId().toString()).resolve(filename);
        Files.delete(target);
    }

    @Override
    public List<Path> list(Item entity) throws IOException {

        Path dir = parentDir.resolve(entity.getId().toString());
        return Files.isDirectory(dir) ? Files.list(dir).collect(Collectors.toList())
                : Collections.EMPTY_LIST;
    }

}

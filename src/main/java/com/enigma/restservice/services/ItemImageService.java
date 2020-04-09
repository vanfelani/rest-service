package com.enigma.restservice.services;

import com.enigma.restservice.entities.Item;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ItemImageService {

    public Path save(Item entity, MultipartFile file) throws IOException;

    public Resource load(Item entity, String filename) throws IOException;

    public void delete(Item entity, String filename) throws IOException;

    public List<Path> list(Item entity) throws IOException;

}

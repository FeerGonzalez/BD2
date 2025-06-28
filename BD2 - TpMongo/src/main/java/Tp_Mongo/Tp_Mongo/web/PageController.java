package Tp_Mongo.Tp_Mongo.web;

import Tp_Mongo.Tp_Mongo.dto.PageDTO;
import Tp_Mongo.Tp_Mongo.model.Page;
import Tp_Mongo.Tp_Mongo.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private PageRepository pageRepository;

    @GetMapping("/{id}")
    public ResponseEntity<List<PageDTO>> getPageById(@PathVariable String id) {
        List<PageDTO> listPages = new ArrayList<>();
        Optional<Page> page = pageRepository.findById(id);
        PageDTO pageDTO = new PageDTO(page.get());
        listPages.add(pageDTO);
        return ResponseEntity.ok(listPages);
    }

    @PostMapping("/crear")
    public ResponseEntity<Page> createPage(@RequestBody Page page) {
        Page savedPage = pageRepository.save(page);
        return ResponseEntity.ok(savedPage);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PageDTO>> getAllPages() {
        List<Page> pages = pageRepository.findAll();
        List<PageDTO> pageDTOs = pages.stream()
                .map(PageDTO::new)
                .toList();
        return ResponseEntity.ok(pageDTOs);
    }
}
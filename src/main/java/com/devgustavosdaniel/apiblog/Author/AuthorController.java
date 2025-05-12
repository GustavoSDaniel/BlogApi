package com.devgustavosdaniel.apiblog.Author;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    public final AuthorService authorService;
    public final AuthorMapper authorMapper;

    


}

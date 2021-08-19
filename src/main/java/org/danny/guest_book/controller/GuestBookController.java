package org.danny.guest_book.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping(value = "/guestbook")
@Controller
public class GuestBookController {

    @GetMapping({"/", "/list"})
    public String list() {
        log.info("list method call");
        return "/guestbook/list";
    }
}

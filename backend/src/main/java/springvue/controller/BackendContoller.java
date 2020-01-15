package springvue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BackendContoller {
    @RequestMapping("/api")
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        //LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }
}

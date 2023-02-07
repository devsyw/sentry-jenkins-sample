package devsyw.sentrymonitoring.controller;

import io.sentry.Sentry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

    @GetMapping("/")
    public String init() {
        return "init";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("error")
    public String error() {
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
        return "error";
    }

}

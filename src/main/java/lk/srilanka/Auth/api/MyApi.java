package lk.srilanka.Auth.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyApi {

    @GetMapping("/user/sayHello")
    public String userSayHello() {
        return "Hello..user or admin";
    }

    @GetMapping("/admin/sayHello")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminSayHello() {
        return "Hello..admin";
    }

}

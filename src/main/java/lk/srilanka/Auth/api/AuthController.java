package lk.srilanka.Auth.api;

import lk.srilanka.Auth.dto.AuthRequest;
import lk.srilanka.Auth.entity.UserInfo;
import lk.srilanka.Auth.service.JwtService;
import lk.srilanka.Auth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }


    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        //
        if (authentication.isAuthenticated()) {
            // Todo: currently the role is hardcoded as "ROLE_ADMIN"
            //Todo: find the role of the user (from the database) and pass to the generateToken( - , )
            return jwtService.generateToken(authRequest.getUsername(), "ROLE_ADMIN");
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
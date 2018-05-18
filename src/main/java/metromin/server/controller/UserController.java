package metromin.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import metromin.server.model.User;
import metromin.server.repo.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/api/user")
    public Page<User> listUsers(Pageable p) {
        return repo.findAll(p);
    }

    @PatchMapping("/api/user/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        User user = repo.findOne(id);
        boolean loggedIn = (Boolean) updates.get("loggedIn");
        user.setLoggedIn(loggedIn);
        return repo.save(user);
    }

}

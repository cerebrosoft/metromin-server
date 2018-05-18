package metromin.server.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import metromin.server.model.Case;
import metromin.server.model.User;
import metromin.server.repo.CaseRepository;
import metromin.server.repo.UserRepository;

@RestController
public class CaseController {

    @Autowired
    private CaseRepository repo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/api/case")
    public Page<Case> listCases(Pageable p) {
        return repo.findAll(p);
    }
    
    @GetMapping("/api/case/new")
    public Page<Case> listNewCases(Pageable p) {
        return repo.findByAssigneeIdIsNull(p);
    }

    @PostMapping("/api/case")
    public Case listCases(@RequestBody Case c) {
        c.setDateCreated(new Date());
        return repo.save(c);
    }

    @PatchMapping("/api/case/{id}")
    public Case updateCase(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        Case c = repo.findOne(id);
        if (updates.containsKey("assigneeId")) {
            Integer userId = (Integer) updates.get("assigneeId");
            User user = userRepo.findOne(userId);
            c.setAssignee(user);
        }
        else {
            c.setAssignee(null);
        }
        return repo.save(c);
    }
}

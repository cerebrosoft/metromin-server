package metromin.server.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import metromin.server.model.Message;
import metromin.server.model.User;
import metromin.server.repo.MessageRepository;
import metromin.server.repo.UserRepository;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/api/message")
    public Page<Message> listMessages(Pageable p) {
        return messageRepo.findAll(p);
    }

    @GetMapping("/api/case/{id}/message")
    public Page<Message> listMessagesForCase(Pageable p, @PathVariable Integer id) {
        return messageRepo.findByCaseId(p, id);
    }

    @PostMapping("/api/message")
    public Message addMessage(@RequestBody Map<String, Object> updates) {
        Integer userId = (Integer) updates.get("metminUserId");
        Message message = new Message();
        if (userId != null) {
            User user = userRepo.findOne(userId);
            message.setMetminUser(user);
        }
        message.setCaseId((Integer) updates.get("caseId"));
        message.setContent((String) updates.get("content"));
        message.setDateCreated(new Date());
        return messageRepo.save(message);
    }

}

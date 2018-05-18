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

import metromin.server.model.Campaign;
import metromin.server.repo.CampaignRepository;

@RestController
public class CampaignController {
    
    @Autowired
    private CampaignRepository repo;
    
    @GetMapping("/api/campaign")
    public Page<Campaign> listCampaigns(Pageable p) {
        return repo.findAll(p);
    }
    
    @GetMapping("/api/campaign/active")
    public Page<Campaign> listActiveCampaigns(Pageable p) {
        return repo.findAllActive(p);
    }
    
    @GetMapping("/api/campaign/complete")
    public Page<Campaign> listCompleteCampaigns(Pageable p) {
        return repo.findAllComplete(p);
    }
    
    @PostMapping("/api/campaign")
    public Campaign addCampaign(@RequestBody Campaign campaign) {
        campaign.setDateCreated(new Date());
        return repo.save(campaign);
    }
    
    @PatchMapping("/api/campaign/{id}")
    public Campaign updateCampaign(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        Campaign campaign = repo.findOne(id);
        campaign.setFundsReceived((Integer)updates.get("fundsReceived"));
        return repo.save(campaign);
    }

}

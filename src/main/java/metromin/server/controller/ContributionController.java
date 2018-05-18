package metromin.server.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import metromin.server.model.Campaign;
import metromin.server.model.Contribution;
import metromin.server.repo.CampaignRepository;
import metromin.server.repo.ContributionRepository;

@RestController
public class ContributionController {
    
    @Autowired
    private ContributionRepository repo;
    @Autowired
    private CampaignRepository campaignRepo;
    
    @GetMapping("/api/contribution")
    public Page<Contribution> listContributions(Pageable p) {
        return repo.findAll(p);
    }
    
    @PostMapping("/api/contribution")
    public Contribution addContribution(@RequestBody Contribution contrib) {
        Campaign campaign = campaignRepo.findOne(contrib.getCampaignId());
        campaign.setFundsReceived(campaign.getFundsReceived() + contrib.getAmount());
        campaignRepo.save(campaign);
        
        contrib.setDateCreated(new Date());
        return repo.save(contrib);
    }

}

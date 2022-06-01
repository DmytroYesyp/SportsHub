package com.sportshub.controller.socialNetworks.socialShare;

import com.sportshub.entity.socialNetworks.socialFollow.socialFollow;
import com.sportshub.entity.socialNetworks.socialShare.socialShare;
import com.sportshub.service.socialNetworks.impl.SocialNetworksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/socialShare/")
public class socialShareController {

    SocialNetworksServiceImpl socialNetworksService;

    @Autowired
    public socialShareController(SocialNetworksServiceImpl socialNetworksService) {
        this.socialNetworksService = socialNetworksService;
    }

    @GetMapping(path = "getAllShares")
    public ResponseEntity<List<socialShare>> AllShares() {
        return socialNetworksService.getAllShare();
    }

    @PostMapping(path = "addShares")
    public ResponseEntity<socialShare> addShares(@RequestBody socialShare share) {
        return socialNetworksService.addNewShare(share);
    }

    @DeleteMapping(path = "deleteShares{Id}")
    public ResponseEntity<String> deleteShares(@RequestParam long Id) {
        return socialNetworksService.deleteShare(Id);
    }


}

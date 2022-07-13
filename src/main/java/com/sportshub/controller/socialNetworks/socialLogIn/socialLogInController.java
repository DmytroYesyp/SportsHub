package com.sportshub.controller.socialNetworks.socialLogIn;

import com.sportshub.entity.socialNetworks.socialLogIn.socialLogIn;
import com.sportshub.entity.socialNetworks.socialShare.socialShare;
import com.sportshub.service.socialNetworks.impl.SocialNetworksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/socialLogIn/")
public class socialLogInController {
    SocialNetworksServiceImpl socialNetworksService;

    @Autowired
    public socialLogInController(SocialNetworksServiceImpl socialNetworksService) {
        this.socialNetworksService = socialNetworksService;
    }
    @PostMapping(path = "addLogIn")
    public ResponseEntity<socialLogIn> addLogIn(@RequestBody socialLogIn share) {
        return socialNetworksService.addNewLogIn(share);
    }

    @PutMapping(path = "editLogInByPic{pic}{url}")
    public ResponseEntity<String> editLogInByPic(@RequestParam String pic,
                                                   @RequestParam String url) {
        return socialNetworksService.editLogInByPic(pic,url);
    }

    @GetMapping(path = "getAllLogIn")
    public ResponseEntity<List<socialLogIn>> AllLogIn() {
        return socialNetworksService.getAllLogIn();
    }



    @DeleteMapping(path = "deleteLogIn{Id}")
    public ResponseEntity<String> deleteShares(@RequestParam long Id) {
        return socialNetworksService.deleteLogIn(Id);
    }
}

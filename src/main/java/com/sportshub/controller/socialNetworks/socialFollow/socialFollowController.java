package com.sportshub.controller.socialNetworks.socialFollow;

import com.sportshub.entity.socialNetworks.socialFollow.socialFollow;
import com.sportshub.service.socialNetworks.impl.SocialNetworksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/socialFollow/")
public class socialFollowController {

    SocialNetworksServiceImpl socialNetworksService;

    @Autowired
    public socialFollowController(SocialNetworksServiceImpl socialNetworksService) {
        this.socialNetworksService = socialNetworksService;
    }
    @PostMapping(path = "addFollows")
    public ResponseEntity<socialFollow> addFollows(@RequestBody socialFollow follow) {
        return socialNetworksService.addNewFollow(follow);
    }

    @PutMapping(path = "editFollowsByPic{pic}{isVisible}{url}")
    public ResponseEntity<String> editFollowsByPic(@RequestParam String pic,
                                                   @RequestParam int isVisible,
                                                   @RequestParam String url) {
        return socialNetworksService.editFollowsByPic(pic,isVisible,url);
    }


    @GetMapping(path = "getAllFollows")
    public ResponseEntity <List<socialFollow>> allFollows() {
        return socialNetworksService.getAllFollow();
    }


    @DeleteMapping(path = "deleteFollows{Id}")
    public ResponseEntity<String> deleteFollows(@RequestParam long Id) {
        return socialNetworksService.deleteFollow(Id);
    }
}

package com.sportshub.controller.pictures;

import com.sportshub.dto.team.TeamDto;
import com.sportshub.entity.user.User;
import com.sportshub.service.fireBase.fireBaseService;
import com.sportshub.service.team.TeamService;
import com.sportshub.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/pictures/")
public class pictureController {

    @Autowired
    private final fireBaseService fireBase;
    private final UsersService usersService;
    private final TeamService teamService;


    public pictureController(fireBaseService fireBase, UsersService usersService, TeamService teamService) {
        this.fireBase = fireBase;
        this.usersService = usersService;
        this.teamService = teamService;
    }



    @GetMapping(path = "getUserProfileImage")
    public ResponseEntity<String> getUserProfileImage(HttpServletRequest request){
        return ResponseEntity.ok().body(fireBase.getImage(usersService.getUserLogo(request)));
    }

    @GetMapping(path = "getImage{imagePath}")
    public String uploadImage(@RequestParam String imagePath)  {
        return fireBase.getImage(imagePath);
    }


    @PutMapping(path = "updateUserImage")
    public ResponseEntity<String> updateUserImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {

        User user = usersService.uploadImageToUserProfile(request);
        fireBase.updateUserProfilePic(user,file);
        return ResponseEntity.ok().body(null);
    }


    @PutMapping(path = "updateTeamImage{teamId}")
    public ResponseEntity<String> updateTeamImage(@RequestParam("file") MultipartFile file,@RequestParam long teamId) throws IOException {
        fireBase.updateTeamPic(teamId,file);
        return ResponseEntity.ok().body(null);
    }


    @PostMapping(path = "uploadImageToUserLogo")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        String blobString = "image/" + java.time.LocalTime.now() +"_"+ file.getOriginalFilename();

        return fireBase.uploadImageToStorage(file,blobString);

    }


//    @GetMapping(path = "getTestPicture")
//    public String getUsers() throws FileNotFoundException {
//        return fireBase.uploadImageToStorage();
//    }


}

package com.sportshub.service.fireBase;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.sportshub.entity.team.TeamEntity;
import com.sportshub.entity.user.User;
import com.sportshub.mapper.team.TeamMapper;
import com.sportshub.repository.team.TeamRepository;
import com.sportshub.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class fireBaseService {

    UserRepository userRepository;
    TeamRepository teamRepository;
    TeamMapper teamMapper;
    @Autowired
    public fireBaseService(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }
    public String getImage(String imagePath){

        String base = "https://firebasestorage.googleapis.com/v0/b/sportshub-623db.appspot.com/o/image%2F";
        return base + imagePath + "?alt=media";
    }


    private void deleteImageFromStorage(String imagePath){
        StorageClient storageClient = StorageClient.getInstance();

        System.out.println(imagePath);

        Bucket bucket = storageClient.bucket();

        System.out.println(bucket.get("image/" + imagePath));


        bucket.get("image/" +imagePath).delete();

    }



    public ResponseEntity<String> updateTeamPic(Long id, MultipartFile file) throws IOException {

        String blobString = java.time.LocalTime.now() +"_"+ file.getOriginalFilename()+"."+ file.getContentType().split("/")[1];
        TeamEntity team = teamRepository.findById(id).orElseThrow(RuntimeException::new);
        team.setImage_url(blobString);
        teamRepository.save(team);
        uploadImageToStorage(file,blobString);

        return ResponseEntity.ok().body(null);
    }


    public ResponseEntity<String> updateUserProfilePic(User user,MultipartFile file) throws IOException {

        String blobString = java.time.LocalTime.now() +"_"+ file.getOriginalFilename()+"."+ file.getContentType().split("/")[1];
//            deleteImageFromStorage(user.getLogo_url());
        user.setLogo_url(blobString);
        userRepository.save(user);
        uploadImageToStorage(file,blobString);
        return ResponseEntity.ok().body(null);
    }

    public ResponseEntity<String> uploadImageToStorage(MultipartFile file,String newName) throws IOException {

        StorageClient storageClient = StorageClient.getInstance();


        storageClient.bucket().create("image/" + newName, file.getInputStream() , Bucket.BlobWriteOption.userProject("sportshub-623db"));
        return null;
    }



}

package com.sportshub.service.socialNetworks.impl;

import com.sportshub.entity.socialNetworks.socialFollow.socialFollow;
import com.sportshub.entity.socialNetworks.socialLogIn.socialLogIn;
import com.sportshub.entity.socialNetworks.socialShare.socialShare;
import com.sportshub.repository.socialNetworks.socialFollowRepository;
import com.sportshub.repository.socialNetworks.socialLogInRepository;
import com.sportshub.repository.socialNetworks.socialShareRepository;
import com.sportshub.service.socialNetworks.SocialNetworksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SocialNetworksServiceImpl implements SocialNetworksService {

    private final socialFollowRepository followRepository;
    private final socialShareRepository shareRepository;
    private final socialLogInRepository logInRepository;

    @Autowired
    public SocialNetworksServiceImpl(socialFollowRepository followRepository, socialShareRepository shareRepository, socialLogInRepository logInRepository) {
        this.followRepository = followRepository;
        this.shareRepository = shareRepository;
        this.logInRepository = logInRepository;
    }


    public ResponseEntity<List<socialFollow>> getAllFollow(){
        return  ResponseEntity.ok().body(followRepository.findAll());
    }
    public ResponseEntity<List<socialShare>> getAllShare(){
        return  ResponseEntity.ok().body(shareRepository.findAll());
    }
    public ResponseEntity<List<socialLogIn>> getAllLogIn(){
        return ResponseEntity.ok().body(logInRepository.findAll());
    }


    public ResponseEntity<String> editSharesByPic(String str, String val){

        socialShare sh = shareRepository.findShareByPictogram(str).orElseThrow();
        sh.setUrl(val);
        shareRepository.save(sh);
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<String> editLogInByPic (String pic, String url){
        if (logInRepository.findLogInByPictogram(pic).isEmpty())
            return ResponseEntity.notFound().build();
        socialLogIn entity = logInRepository.findLogInByPictogram(pic).get();

        if (!url.equals(""))
            entity.setUrl(url);
        logInRepository.save(entity);
        return ResponseEntity.ok().body(null);
    }
    public ResponseEntity<socialShare> addNewShare(socialShare share){
        shareRepository.save(share);
        return ResponseEntity.ok(share);
    }
    public ResponseEntity<socialFollow>addNewFollow(socialFollow follow){
        followRepository.save(follow);
        return ResponseEntity.ok(follow);
    }
    public ResponseEntity<socialLogIn>addNewLogIn(socialLogIn login){
        logInRepository.save(login);
        return ResponseEntity.ok(login);
    }


    public ResponseEntity<String> editFollowsByPic(String pic,int isVisible, String url){
        if (followRepository.findShareByPictogram(pic).isEmpty())
            return ResponseEntity.notFound().build();
        socialFollow entity = followRepository.findShareByPictogram(pic).get();

        if (isVisible == 1 || isVisible == 0)
            entity.setIsVisible(isVisible == 1);

        if (!url.equals(""))
            entity.setUrl(url);

        followRepository.save(entity);

        return ResponseEntity.ok().body(null);
    }


    public ResponseEntity<String> deleteShare(long id){

        if (shareRepository.findAll().isEmpty())
            return new ResponseEntity<String>(
                "There is no social follow with such id", new HttpHeaders(), HttpStatus.NOT_FOUND);

        shareRepository.deleteById(id);
        return ResponseEntity.ok("deleted social share entity by id " + id);
    }

    public ResponseEntity<String> deleteFollow(long id){
        if (followRepository.findAll().isEmpty())
            return new ResponseEntity<String>(
                    "There is no social follow with such id", new HttpHeaders(), HttpStatus.NOT_FOUND);

        followRepository.deleteById(id);
        return ResponseEntity.ok("deleted social follow entity by id " + id);
    }

    public ResponseEntity<String> deleteLogIn(long id){
        if (logInRepository.findAll().isEmpty())
            return new ResponseEntity<String>(
                    "There is no social follow with such id", new HttpHeaders(), HttpStatus.NOT_FOUND);

        logInRepository.deleteById(id);
        return ResponseEntity.ok("deleted social login entity by id " + id);
    }
}

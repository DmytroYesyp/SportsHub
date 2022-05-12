package com.sportshub.service.fireBase;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

@Service
public class fireBaseInit {

    @PostConstruct
    public void init() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("serviceAcc.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://sportshub-623db-default-rtdb.europe-west1.firebasedatabase.app")
                .setStorageBucket("sportshub-623db.appspot.com")
                .build();



        StorageClient.getInstance(FirebaseApp.initializeApp(options));

    }

    public void uploadImage(StorageClient strc) throws IOException {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("server/saving-data/fireblog");

//        FileInputStream serviceAccount =
//                new FileInputStream("serviceAcc.json");
//
//
//        FirebaseOptions options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://mytestcloud-92d09-default-rtdb.firebaseio.com")
//                .setStorageBucket("mytestcloud-92d09.appspot.com")
//                .build();





//        https://stackoverflow.com/questions/38779713/how-to-store-direct-link-to-an-image-using-firebase-storage




//        https://storage.googleapis.com/mytestcloud-92d09.appspot.com/Bleat/-v-gostyah-u-dmitriya-gordona.jpg

//        https://firebasestorage.googleapis.com/v0/b/nobs-f32f2.appspot.com/o/profile%2F1s_by_wlop-dc1sdan.jpg?alt=media

//        https://firebasestorage.googleapis.com/v0/b/mytestcloud-92d09.appspot.com/o/Bleat%2F18:01:36.003335200agaga.jpg?alt=media
//        https://storage.googleapis.com/mytestcloud-92d09.appspot.com/Bleat/-v-gostyah-u-dmitriya-gordona.jpg


//        strc.bucket().create(blobString, testFile , Bucket.BlobWriteOption.userProject("mytestcloud-92d09"));

    }
}

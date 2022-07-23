package com.sportshub.service.fireBase;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.FirebaseService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.text.html.parser.Entity;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

@Service
public class fireBaseInit {

    @PostConstruct
    public void init() throws IOException {

        String json = "{ \"type\": \"service_account\", \"project_id\": \"sportshub-623db\", \"private_key_id\": \"5c21ff2c27407e76fd9190e226b0c0758c05e709\", \"private_key\": \"-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC4QnHCEATJkTkn\nnmz/UetvgYSlsFrJ/+ftc/cPl0DsyfZak22WxDsyWY0ixX3IbyE9DRCAQucboEh2\nniu6/nRn4Ca04Rocnr9DMFxxEPr6i3ot2sKLeECRJpVeGrvVPRJvlSavGhEje87Q\nsvgPO0ZDd0vW1zS5a0yF5tQTMClmmegv3SVkx/PQ3KapsokKeJp4xFbMkrDYuMUB\nTUATGRZxOhtruDekXOqdnoLYGu6RpE+0OKDc7kdkLFAT1ZVPhOutx5oV/5Qsd7OX\nXJIaYokKzWdJJl1SJSiM6AVM3ZEwPdby0YtU4qqzUWtU2924XGLAPD74LZcSsfFz\nf2c4U745AgMBAAECggEARrAG4EIw8/soiBB5FEIjJjXz43SSf+gS27+fSnXmTHD2\nd60TX8GMTDaatHo7VQd9IuS+LdZ8vtZwtkDb2fMASVFux/mLgOtNyMLm0+GFx1R2\n3NyNwd+yNX0t1FpBMP8q8W+w1YAUjx5SKzJdg8pjnEJJ7/PDoFaPltcFCQ9y8o71\nKicEGp3nYg7GP0Xj7/+NIec7hIjJ8VYREqpIha5h4WZUKyEj2Wn/oIp/Sx4k3hvP\nprOciad54ap/qJFbTw5NXQPb5vmkGkGFSE2rjq9pGOPfQrAKncKiFZnPJWxFidnK\ntLEFDjTu7YdtUcU9HS5DTc0XmOGwmIG3j85gWt7/mQKBgQDyGgg/fghEMcuTdlWu\nci2o80HHMS31WBQ1PG/NIBgEeebhm37ILlLIDvKTpP/ngksWfgwZMgwTODEo/JBj\ns43E3tOCCadUrBCszfS7Guys5TaohS/sYyFhQBNRYIw3udiQJbDCErcvBEUs5CTK\n0MAEr/YhQRy0KZozTjtx20x3UwKBgQDC1lqrMdozTClysEF4zneSDyDhERNprWdN\nMMg813Eo8HXPEa6u6SPtDgIt0ukDT3LS6pbMfG6zuOsxpi1EVxjkGhCn33yblxJg\nbqw+uuywIu5+WO9SGqSOdfz/CNqoBt3zLi/wlwH9TEwt5GtpTmqSW8fiOy037foc\nqUm9gv5+wwKBgEDufHHS8pKfrFLjdP1xgA+FFoo/BW187waMLAwe5HIm46Z0lt9H\nlUo0wBibM0s7JbplmgcT5istcfnlKzCiUo6PmLiSsD9EWsCOCGo6QWnpm+w8tGq4\n95Xmooe71j+H565ZfXu/m0PP6GGu2rNOo4FeVCv9E566XbIO9GCICY0jAoGBAKNz\nuHTOScOnLU5t1z9F1sHNt0OZU7PgKT10o0eHZgN2Vx70MpvCMsV4bxOKULjaOfX/\nn6rE6QoVJBpu0qL2foc95qMuthbBUkVcE1w87LuHUpTkf0Fd4JczHJ/ICcfUmvab\n1NP9m0mobHBDpGifxIAsRy/ffTvihWTuEFLQRjiJAoGAWYCWU76U30n/YNeYrcms\nIoqkGGO9U/tRwbLIoYTs2ffqE4p+QsmSHDCizTYc844ekl07GJMTEYQBtt8EvleX\nBqxv3iENSVESOZ+4pEO7cCvyo6BPwnbugNjJHSoj6dUCZrHYY2SJUyUCPAJ1b6N/\n/IVx87naNvYx1wqOAuoNYYM=\n-----END PRIVATE KEY-----\n\", \"client_email\": \"firebase-adminsdk-qe9tv@sportshub-623db.iam.gserviceaccount.com\", \"client_id\": \"110059222709204933034\", \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\"token_uri\": \"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-qe9tv%40sportshub-623db.iam.gserviceaccount.com\" }";

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String str = json.toString();
        InputStream is = new ByteArrayInputStream(str.getBytes());
//        FileInputStream serviceAccount =
//                new FileInputStream(jsonObject);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
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

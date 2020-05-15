package com.example.ma130584.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int request_code_for_calling = 1;
    private static final int request_code_for_capture = 2;
    private static final int request_code_for_saving = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //gledamo koje smo permisije dobili
        switch (requestCode){
            case request_code_for_calling: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //imamo permisiju i ovde pisemo kod odozdo da bi pokrenuli aktivnost
                } else{
                    //nemamo permisiju! i ovde isto pisemo kod odozdo ako nismo dobili permisiju

                }
            }
        }
    }

    public void onIntentButtonClick(View view) {
//3: poziv
        Intent callIntent = new Intent();//OVO NE RADI ZBOG PERMISION DENIAL, ne moze aplikacija da pozove, nema dozvolu, zato se u MANIFEST MORA DODATI PERMISIJA i mora se traziti permisija onda kada je neophodno a ne u manifest izjeba nas micko
        callIntent.setAction(Intent.ACTION_CALL);//akcija poziva
        callIntent.setData(Uri.parse("tel:0641322668"));// broj telefona koji se poziva

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){//OVAKO SE TRAZI PERMISIJA!
            //permision NOT GRANTED
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, request_code_for_calling);//trazimo permisiju opet, tj dozvolu za poziv, kada se pokrene prvi put i daje permisiju onda se vrati na prvi poziv, da se ne bi vracao na prvi poziv onda se radi kao gore sto se radilo
        }
        else {
            //imamo dozvolu(permission) za poziv
            if (callIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(callIntent);
            }
        }



// 2: slanje poruke
//        Intent smsIntent = new Intent();
//        smsIntent.setAction(Intent.ACTION_SENDTO);//ova akcija salje poruku
//        smsIntent.setData(Uri.parse("smsto:0641322668"));//prosledjujemo broj kojem saljemo poruku
//        smsIntent.putExtra("sms_body", "Sadrzaj poruke");//sms_body znaci da saljemo tekst sms
//
//        if(smsIntent.resolveActivity(getPackageManager()) != null){
//            startActivity(smsIntent);
//        }

// 1:   trazenje preko google mapa
//      EditText editText = findViewById(R.id.edit_text_location);
//        String stringLocation = editText.getText().toString();
//
//        Uri urilocation = Uri.parse("geo:0,0?q=" + stringLocation);//prosledjujemo da trazimo lokaciju(podrazumevano 0,0), procitati na netu o URIju
//
//        Intent intentLocation = new Intent();
//        intentLocation.setAction(Intent.ACTION_VIEW);// postavljamo akciju koju ce da odradi intent, posto treba da nam prikaze nesto onda je action_view, intent filter se definise u AndroidManifest!
//        intentLocation.setData(urilocation);
//
//        if(intentLocation.resolveActivity(getPackageManager()) != null) {
//            startActivity(intentLocation);
//        }
    }


// 4: trazimo permisiju za koriscenje kamere i postavljamo sliku u ImageView
    public void onCaptuButtonClick(View view) {
        Intent captureIntent = new Intent();
        captureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if(captureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(captureIntent, request_code_for_capture);//pokrecemo aktivnost i ocekujemo rezultat sto je slika
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //kada dobijemo sliku postavljamo je u IMAGEVIEW
        if(resultCode == RESULT_OK && requestCode == request_code_for_capture){//posto se prosledjuje kroz bundle slika je veoma loseg kvaliteta, pogledati na androidu ACTION_IMAGE_CAPTURE ali to cemo raditi tek posle 2 kolokvijuma
            ImageView imageView = findViewById(R.id.image_view);
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");//iz bundle vadimo sliku! Slika je izgleda tipa Bitmap, i u bundle se nalazi pod kljucem data
            imageView.setImageBitmap(bitmap);


            //cuvamo sliku u fajlovima (na external)
            //uvek proveravati state external memorije da vidimo da nije slucajno neko izvadio usb ili slicno

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//nije sigurna garancija ako ima vise external file storage tipa 2 usba tu je problem ako zelimo da biramo na koji ces da smestis

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) { //TREBAJU NAM PERMISIJE I ZA UPIS U EKSTERNI STORAGE
                    //nemamo permisiju
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},request_code_for_saving);
                } else {
                    //imamo permisiju

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "JPEG" + timeStamp + "_";
                    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                    try {
                        File imageFile = File.createTempFile(imageFileName,".jpg", storageDir); // pravimo novi fajl
                        FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream); // kompresija fajla quality 100 je najbolji kvalitet
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            }
        }
    }

    //file sistem
    // postoji internal file storage i external file storage, internal vidi samo nasa aplikacija, oba se prisu kada se aplikacija obrise
    //ne treba pristupati preko apsolutnih putanja
    //izgled se moze videti u device file explorer donji desni ugao na bocnoj iviici
    //putanje :    internal file storage : /data/data/app_name           external se nalazi u storagu i mora se ici u sdcard da bi se video/storage





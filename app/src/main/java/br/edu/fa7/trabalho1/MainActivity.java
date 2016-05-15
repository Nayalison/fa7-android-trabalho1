package br.edu.fa7.trabalho1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBrowser;
    private Button btnCamera;
    private Button btnFone;
    private ImageView imageView;

    private String imgPath;

    static final int CAPTURE_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBrowser = (Button) findViewById(R.id.btn_browser);
        btnBrowser.setOnClickListener(this);
        btnCamera = (Button) findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(this);
        btnFone = (Button) findViewById(R.id.btn_fone);
        btnFone.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_browser:
                starBrowser();
                break;
            case R.id.btn_camera:
                starCamera();
                break;
            case R.id.btn_fone:
                starFoneCall();
                break;
        }
    }

    private void starFoneCall() {
        String phone = "+8540067600";
        Intent itFone = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(itFone);
    }

    private void starCamera() {
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/");
        File fileImage = new File(file + "/image.png");
        Uri imgUri = Uri.fromFile(fileImage);
        imgPath = fileImage.getAbsolutePath();

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgPath);
        startActivityForResult(intent, CAPTURE_IMAGE);
    }

    private void starBrowser() {
        Intent itBrowser = new Intent(Intent.ACTION_VIEW);
        String URL = "http://www.fa7.edu.br";
        itBrowser.setData(Uri.parse(URL));
        startActivity(itBrowser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == CAPTURE_IMAGE) {
                imageView.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }
}

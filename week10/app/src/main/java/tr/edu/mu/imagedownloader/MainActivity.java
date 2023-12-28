package tr.edu.mu.imagedownloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.Manifest;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    EditText txtUrl;
    ImageView imgView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = findViewById(R.id.btnDownload);
        txtUrl = findViewById(R.id.txtURL);
        imgView = findViewById(R.id.imgView);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ActivityCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE
                    );
                }
                /*
                String fileName = "temp.jpg";
                String imagePath = (Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS)).toString()
                        + "/" + fileName;
                downloadFile(txtUrl.getText().toString(),imagePath);
                preview(imagePath);
                 */

                else{
                    DownloadTask backgroundTask = new DownloadTask();
                    String urls [] = new String[1];
                    urls[0] = txtUrl.getText().toString();
                    backgroundTask.execute(urls);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_EXTERNAL_STORAGE){
            if(grantResults .length== 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_DENIED){
                //String fileName = "temp.jpg";
                //String imagePath = (Environment.getExternalStoragePublicDirectory
                        //(Environment.DIRECTORY_DOWNLOADS)).toString()
                        //+ "/" + fileName;
                //downloadFile(txtUrl.getText().toString(),imagePath);
                //preview(imagePath);
                DownloadTask backgroundTask = new DownloadTask();
                String urls [] = new String[1];
                urls[0] = txtUrl.getText().toString();
                backgroundTask.execute(urls);

            }
            else{
                Toast.makeText(this, "External storage permission not granted",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    void downloadFile(String url, String imagePath){
        try{
            URL strUrl = new URL(url);
            URLConnection connection = strUrl.openConnection();
            connection.connect();
            InputStream inputStream = new BufferedInputStream(strUrl.openStream(), 8192);
            OutputStream outputStream = new FileOutputStream(imagePath);

            byte data [] = new byte[1024];
            int count = 0;
            while((count = inputStream.read(data))!= -1){
                outputStream.write(data,0,count);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();



        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    void preview(String imagePath){
        Bitmap image = BitmapFactory.decodeFile(imagePath);
        float w = image.getWidth();
        float h = image.getHeight();
        int W = 400;
        int H = (int) ( (h*W)/w);
        Bitmap b = Bitmap.createScaledBitmap(image, W, H, false);
        imgView.setImageBitmap(b);
    }





    class DownloadTask extends AsyncTask<String, Integer, Bitmap>{


        ProgressDialog PD;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PD = new ProgressDialog(MainActivity.this);
            PD.setMax(100);
            PD.setIndeterminate(false);
            PD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            PD.setTitle("Downloading");
            PD.setMessage("Please wait..");
            PD.show();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String fileName = "temp.jpg";
            File path = (Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS));
            path.mkdirs();

            String imagePath = (Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS)).toString()
                    + "/" + fileName;
            downloadFile(urls[0],imagePath);
            return scaleBitmap(imagePath);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }
    }

    private Bitmap scaleBitmap(String imagePath){
        Bitmap image = BitmapFactory.decodeFile(imagePath);
        float w = image.getWidth();
        float h = image.getHeight();
        int W = 400;
        int H = (int) ( (h*W)/w);
        Bitmap bitmap = Bitmap.createScaledBitmap(image, W, H, false);
        return bitmap;
    }

    class DownloadRunnable implements Runnable{
        String url;

        public DownloadRunnable(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            String fileName = "temp.jpg";
            String imagePath = (Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS)).toString()
                    + "/" + fileName;
            downloadFile(url,imagePath);
            Bitmap bitmap = scaleBitmap(imagePath);
            runOnUiThread(new UpdateBitmap(bitmap));
        }

        private Bitmap scaleBitmap(String imagePath) {
            Bitmap image = BitmapFactory.decodeFile(imagePath);
            float w = image.getWidth();
            float h = image.getHeight();
            int W = 400;
            int H = (int) ( (h*W)/w);
            Bitmap bitmap = Bitmap.createScaledBitmap(image, W, H, false);
            return bitmap;
        }
    }


    class UpdateBitmap implements Runnable{
        Bitmap bitmap;

        public UpdateBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        public void run() {
            imgView.setImageBitmap(bitmap);
        }
    }
}

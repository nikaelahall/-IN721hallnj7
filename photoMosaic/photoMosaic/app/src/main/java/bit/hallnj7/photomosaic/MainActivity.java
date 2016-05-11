package bit.hallnj7.photomosaic;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity
{
    String mPhotoFileName;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fetch system image folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Make the subdirectory
        File imageStorageDirectory = new File(imageRootPath, "CameraDemo1");
        if(!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs();
        }

        //Get timestamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        //make file name
        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        //Make file object from directory and filename
        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);


    }
}

package pk.com.aptech.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.net.MalformedURLException;
import java.net.URL;

public class MyIntentService extends IntentService {

    private Thread thread = new Thread();

    public MyIntentService() {
        super("MyIntentServiceName");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        thread.start();
        try {
            int result =
                    DownloadFile(new URL("http://www.amazon.com/somefile.pdf"));
            Log.d("IntentService", "Downloaded " + result + " bytes");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private int DownloadFile(URL url) {
        try {

            //---simulate taking some time to download a file---
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100;
    }
}

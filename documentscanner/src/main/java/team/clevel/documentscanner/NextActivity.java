package team.clevel.documentscanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import team.clevel.documentscanner.helpers.ScannerConstants;


public class NextActivity extends AppCompatActivity {
    private ImageView view;
    private Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);

        if(ScannerConstants.selectedImageBitmap!=null){
            view=findViewById(R.id.imageView2);
          selectedImageBitmap=ScannerConstants.selectedImageBitmap;
          view.setImageBitmap(selectedImageBitmap);}
        else
        {
            Toast.makeText(this,ScannerConstants.imageError,Toast.LENGTH_LONG).show();
            finish();
        }
        String my_string=getIntent().getStringExtra("Image");
     /*   String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("image");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
*/
       // view.setImageURI(Uri.parse(my_string));
    }

}

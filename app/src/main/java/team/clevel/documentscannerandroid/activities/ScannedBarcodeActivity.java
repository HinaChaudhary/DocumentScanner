/*
package team.clevel.documentscannerandroid.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.Objects;

import team.clevel.documentscannerandroid.R;


public class ScannedBarcodeActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;
    BarcodeDetector barcodeDetector;
    AlertDialog.Builder builder;
    private Boolean firstDectetion = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanned_barcode);

        initViews();

    }


    private void initViews() {
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        surfaceView = findViewById(R.id.surfaceView);
        // view=findViewById(R.id.view);
        btnAction = findViewById(R.id.btnAction);
        builder = new AlertDialog.Builder(this);
        initialiseDetectorsAndSources();
 */
/*btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentData.length() > 0) {
                    if (isEmail)
                        startActivity(new Intent(ScannedBarcodeActivity.this, EmailActivity.class).putExtra("email_address", intentData));
                    else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intentData)));
                    }
                }
            }
        });*//*


    }


    private void initialiseDetectorsAndSources() {

        Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScannedBarcodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());

                    } else {
                        ActivityCompat.requestPermissions(ScannedBarcodeActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

      */
/*  animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
               //bar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });*//*

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            @Override
            public void release() {
                Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if ( barcodes.size()==1) {

                    txtBarcodeValue.post(new Runnable() {

                        @Override
                        public void run() {
                            surfaceView.setVisibility(View.GONE);

                           // cameraSource.stop();


                                    AlertDialog alert = builder.create();
                                    //Setting the title manually

                                    // alert.setView(txtBarcodeValue);

                                    intentData = barcodes.valueAt(0).displayValue;
                                    //txtBarcodeValue.setText(intentData);
                                    final SpannableString s = new SpannableString(intentData);
                                    Linkify.addLinks(s, Linkify.ALL);

                                    txtBarcodeValue.setText(s);
                                    //Setting message manually and performing action on button click
                                    builder.setMessage(s);
                                    builder.setCancelable(false);
                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(intentData));
                                            startActivity(intent);
                                        }
                                    });
                                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dia, int arg1) {
                                            //  Action for 'NO' Button

                                            dia.cancel();
                                            surfaceView.setVisibility(View.VISIBLE);
                                          */
/*  try {
                                                cameraSource.start(surfaceView.getHolder());
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }*//*

                                        }
                                    });
                                    alert.show();
                                    TextView msgTxt = (TextView) alert.findViewById(android.R.id.message);
                                    msgTxt.setMovementMethod(LinkMovementMethod.getInstance());
                                }




                         */
/*   if (barcodes.valueAt(0).email != null) {
                                txtBarcodeValue.removeCallbacks(null);

                                intentData = barcodes.valueAt(0).email.address;
                                txtBarcodeValue.setText(intentData);
                                isEmail = true;
                                btnAction.setText("ADD CONTENT TO THE MAIL");
                            } else {
                                isEmail = false;
                                btnAction.setText("LAUNCH URL");
                                intentData = barcodes.valueAt(0).displayValue;
                                txtBarcodeValue.setText(intentData);
                            }*//*



                    });

                }

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();

    }

*/
/*
    public class RectangleView extends SurfaceView implements SurfaceHolder.Callback  {
        private float x = 0, y = 0;
        private Paint paint = null;

        public RectangleView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.RED);
            getHolder().addCallback(this);
        }

        public void draw() {
            Canvas canvas = getHolder().lockCanvas();
           // canvas.drawColor(Color.WHITE);
            canvas.save();
            canvas.translate(x, y);
            canvas.drawRect(0, 0, 100, 100, paint);
            canvas.restore();
            getHolder().unlockCanvasAndPost(canvas);
            y++;

        }

        private Timer timer = null;
        private TimerTask task = null;

        public void startTimer() {
            timer = new Timer();
            task = new TimerTask() {

                @Override
                public void run() {
                   draw();
                }
            };
            timer.schedule(task, 100, 100);
        }

        public void stopTimer() {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }


        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }*//*

}
*/

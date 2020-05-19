package team.clevel.documentscannerandroid;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.Objects;



public class QR_Fragment extends Fragment {

    private SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private CameraSource sourceCam;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;
    BarcodeDetector barcodeDetector;
    View scan_view;
    private Context myContext;
    Button btnScanBarcode;

    private OnFragmentInteractionListener mListener;

    public QR_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       initViews();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        scan_view= inflater.inflate(R.layout.scanned_barcode, container, false);
       /* btnScanBarcode = scan_view.findViewById(R.id.btnScanBarcode);
        btnScanBarcode.setOnClickListener(view ->
            startActivity(new Intent(getActivity(), ScannedBarcodeActivity.class)));*/
     //  closeCam();
initViews();
        return scan_view;
    }


    public void setMenuVisibility(final boolean visible) {
        if (visible) {
            if (surfaceView != null) {
                Log.v("FragmentQR", "Visible");
                try {
                    sourceCam.start(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (surfaceView != null) {
                sourceCam.stop();
                Log.v("FragmentQR", "InVisible");
            }
        }
    }
    private void initViews() {
        txtBarcodeValue = scan_view.findViewById(R.id.txtBarcodeValue);
        surfaceView = scan_view.findViewById(R.id.surfaceView);
        btnAction = scan_view.findViewById(R.id.btnAction);

        initialiseDetectorsAndSources();
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
        });*/

    }

    private void initialiseDetectorsAndSources() {

        Toast.makeText(getContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
        barcodeDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        sourceCam = new CameraSource.Builder(Objects.requireNonNull(getActivity()), barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        sourceCam.start(surfaceView.getHolder());

                    } else {
                      /*  ActivityCompat.requestPermissions(getActivity(), new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);*/
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
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
            //    cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {

            @Override
            public void release() {
                Toast.makeText(getContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    txtBarcodeValue.post( new Runnable() {
                        @Override
                        public void run() {




                    if (barcodes.valueAt(0).email != null) {
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
                            }
                        }
                    });
                }
            }
        });
    }
private void closeCam(){
    if(sourceCam!=null){
        sourceCam.stop();
        sourceCam=null;
    }
}

    @Override
    public void onPause() {
        super.onPause();
        closeCam();
    }

    @Override
    public void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();

    }
  /*  @Override
    public void onDestroy() {
        super.onDestroy();
cameraSource.release();
    }*/


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        /*throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener");*/
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

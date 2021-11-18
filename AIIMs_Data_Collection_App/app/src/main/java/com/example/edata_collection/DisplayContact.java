package com.example.edata_collection;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class DisplayContact extends Activity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb ;

    private TextView gender;
    private TextView age ;
    private TextView height;
    private TextView weight;
    private TextView food_type;
    private TextView health_status;
    private TextView genetic_disease;
    private TextView allergy;
    private TextView caste;
    private TextView sugarLevel;
    private TextView HB_level;
    private ImageView frontLE;
    private ImageView frontRE;
    private ImageView lowerLE;
    private ImageView lowerRE;
    private ImageView upperLE;
    private ImageView upperRE;
    private ImageView nailBed;

    private Button LE_button, RE_button;
    private Button ULE_button, URE_button;
    private Button LLE_button, LRE_button, nailbed_button;

    //CBC report
    private ImageView cbcP1, cbcP2, cbcP3, cbcP4, cbcP5;

    int id_To_Update = 0;
    //front eye
    private static final int CAM_REQ_CODE_11 = 11;
    private static final int CAM_REQ_CODE_12 = 12;
    //lower eye

    private static final int CAM_REQ_CODE_21 = 21;
    private static final int CAM_REQ_CODE_22 = 22;
    //upper eye
    private static final int CAM_REQ_CODE_31 = 31;
    private static final int CAM_REQ_CODE_32 = 32;
    //nailbeds
    private static final int CAM_REQ_CODE_41 = 41;
    //cbc pictures
    private static final int CBC_CODE_1=81;
    private static final int CBC_CODE_2=82;
    private static final int CBC_CODE_3=83;
    private static final int CBC_CODE_4=84;
    private static final int CBC_CODE_5=85;

    Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);


        selectDate = findViewById(R.id.datebutton);
        date = findViewById(R.id.DisplayDate);
        gender = findViewById(R.id.editTextGender);
        age = (TextView) findViewById(R.id.editTextAge);
        height = (TextView) findViewById(R.id.editTextHeight);
        weight = (TextView) findViewById(R.id.editTextWeight);
        food_type = (TextView) findViewById(R.id.editTextFoodType);
        health_status = (TextView) findViewById(R.id.editTextHealthStatus);
        genetic_disease = (TextView) findViewById(R.id.editTextGeneticDisease);
        allergy = (TextView) findViewById(R.id.editTextAllergy);
        caste = (TextView) findViewById(R.id.editTextCaste);
        sugarLevel = (TextView) findViewById(R.id.editTextSugarLevel);
        HB_level = (TextView) findViewById(R.id.editTextHBvalue);

        //front eye images and button
        frontLE = findViewById(R.id.left_eye_image);
        frontRE = findViewById(R.id.right_eye_image);
        LE_button =findViewById(R.id.leftEye_button);
        RE_button =findViewById(R.id.rightEye_button);

        //lower eye images and button
        lowerLE =findViewById(R.id.LowerLE_image);
        lowerRE =findViewById(R.id.LowerRE_image);
        LLE_button =findViewById(R.id.LowerLeftEye_button);
        LRE_button =findViewById(R.id.LowerRightEye_button);

        //upper eye images and buttons
        upperLE =findViewById(R.id.UpperLE_image);
        upperRE =findViewById(R.id.UpperRE_image);
        ULE_button =findViewById(R.id.UpperLeftEye_button);
        URE_button =findViewById(R.id.UpperRightEye_button);

        nailbed_button =findViewById(R.id.nailbed_image_button);
        nailBed =findViewById(R.id.nailbed_image);
        //CBC reports images

        cbcP1 =findViewById(R.id.CBCP1);
        cbcP2 =findViewById(R.id.CBCP2);
        cbcP3 =findViewById(R.id.CBCP3);
        cbcP4 =findViewById(R.id.CBCP4);
        cbcP5 =findViewById(R.id.CBCP5);

        //Datebase intialization
        mydb = new DBHelper(this);

        LE_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(1);
            }
        });

        RE_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(2);
            }
        });

        LLE_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(3);
            }
        });
        LRE_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(4);
            }
        });
        ULE_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(5);
            }
        });
        URE_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(6);
            }
        });

        nailbed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(7);
            }
        });

        cbcP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(8);
            }
        });

        cbcP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(9);
            }
        });

        cbcP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(10);
            }
        });
        cbcP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(11);
            }
        });
        cbcP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage(12);
            }
        });
        //Date picker
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(DisplayContact.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        //Data Extraction methods
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String d = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_DATE));
                String ge = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_GENDER));
                String a = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_AGE));
                String h = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_HEIGHT));
                String we = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_WEIGHT));
                String ftype = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_FOODTYPE));
                String hstatus = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_HEALTHSTATUS));
                String gdisease = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_GENETICDISORDERS));
                String alrgy = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_ALLERGY));
                String cst = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_CASTE));
                String sgr = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_SUGAR));
                String hbl = rs.getString(rs.getColumnIndex(DBHelper.PATIENTS_COLUMN_HBLEVEL));

                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.submit_button);
                b.setVisibility(View.INVISIBLE);

                date.setText((CharSequence)d);
                date.setFocusable(false);
                date.setClickable(false);

                gender.setText((CharSequence)ge);
                gender.setFocusable(false);
                gender.setClickable(false);

                age.setText((CharSequence)a);
                age.setFocusable(false);
                age.setClickable(false);

                height.setText((CharSequence)h);
                height.setFocusable(false);
                height.setClickable(false);


                weight.setText((CharSequence)we);
                weight.setFocusable(false);
                weight.setClickable(false);

                food_type.setText((CharSequence)ftype);
                food_type.setFocusable(false);
                food_type.setClickable(false);

                health_status.setText((CharSequence)hstatus);
                health_status.setFocusable(false);
                health_status.setClickable(false);

                genetic_disease.setText((CharSequence)gdisease);
                genetic_disease.setFocusable(false);
                genetic_disease.setClickable(false);

                allergy.setText((CharSequence)alrgy);
                allergy.setFocusable(false);
                allergy.setClickable(false);

                caste.setText((CharSequence)cst);
                caste.setFocusable(false);
                caste.setClickable(false);

                sugarLevel.setText((CharSequence)sgr);
                sugarLevel.setFocusable(false);
                sugarLevel.setClickable(false);

                HB_level.setText((CharSequence)hbl);
                HB_level.setFocusable(false);
                HB_level.setClickable(false);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_contact, menu);
            } else{
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button)findViewById(R.id.submit_button);
                b.setVisibility(View.VISIBLE);
                age.setEnabled(true);
                age.setFocusableInTouchMode(true);
                age.setClickable(true);

                height.setEnabled(true);
                height.setFocusableInTouchMode(true);
                height.setClickable(true);

                food_type.setEnabled(true);
                food_type.setFocusableInTouchMode(true);
                food_type.setClickable(true);

                health_status.setEnabled(true);
                health_status.setFocusableInTouchMode(true);
                health_status.setClickable(true);

                genetic_disease.setEnabled(true);
                genetic_disease.setFocusableInTouchMode(true);
                genetic_disease.setClickable(true);

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Delete contact")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(mydb.insertpatients(date.getText().toString(),gender.getText().toString(), age.getText().toString(),
                    height.getText().toString(), weight.getText().toString(), food_type.getText().toString(),
                    health_status.getText().toString(), genetic_disease.getText().toString(), allergy.getText().toString(),
                    caste.getText().toString(), sugarLevel.getText().toString(),
                    HB_level.getText().toString(),
                    imageViewToByte(frontLE),imageViewToByte(frontRE),imageViewToByte(lowerLE),imageViewToByte(lowerRE),imageViewToByte(upperLE),imageViewToByte(upperRE),
                    imageViewToByte(nailBed),imageViewToByte(cbcP1),imageViewToByte(cbcP2),imageViewToByte(cbcP3),imageViewToByte(cbcP4),imageViewToByte(cbcP5))){
                Toast.makeText(getApplicationContext(), "done",
                        Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getApplicationContext(), "not done",
                        Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    private void pickImage(int val) {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        CardView camCV = (CardView) view.findViewById(R.id.card_view_cam);
        //CardView galleryCV = (CardView) view.findViewById(R.id.card_view_gallery);

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

        camCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cam activity
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(cameraIntent, CAM_REQ_CODE);
                Toast.makeText(DisplayContact.this, "Camera Selected", Toast.LENGTH_SHORT).show();
                switch (val){
                    case 1:
                        startActivityForResult(cameraIntent, CAM_REQ_CODE_11);
                        break;
                    case 2:
                        startActivityForResult(cameraIntent, CAM_REQ_CODE_12);
                        break;
                    case 3:
                        startActivityForResult(cameraIntent, CAM_REQ_CODE_21);
                        break;
                    case 4:
                        startActivityForResult(cameraIntent, CAM_REQ_CODE_22);
                        break;
                    case 5:
                        startActivityForResult(cameraIntent, CAM_REQ_CODE_31);
                        break;
                    case 6:
                        startActivityForResult(cameraIntent, CAM_REQ_CODE_32);
                        break;
                    case 7:
                        startActivityForResult(cameraIntent, CAM_REQ_CODE_41);
                        break;
                    case 8:
                        startActivityForResult(cameraIntent, CBC_CODE_1);
                        break;
                    case 9:
                        startActivityForResult(cameraIntent, CBC_CODE_2);
                        break;
                    case 10:
                        startActivityForResult(cameraIntent, CBC_CODE_3);
                        break;
                    case 11:
                        startActivityForResult(cameraIntent, CBC_CODE_4);
                        break;
                    case 12:
                        startActivityForResult(cameraIntent, CBC_CODE_5);
                        break;
                }
                alertDialog.dismiss();
                /*
                if (left)
                    startActivityForResult(cameraIntent, CAM_REQ_CODE_11);
                else
                    startActivityForResult(cameraIntent, CAM_REQ_CODE_12);
                alertDialog.dismiss();

                 */
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //front eye
        if(requestCode == CAM_REQ_CODE_11){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CAM_REQ_CODE_11);
            }
        }
        else if(requestCode == CAM_REQ_CODE_12){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CAM_REQ_CODE_12);
            }
        }
        //lower
        else if(requestCode == CAM_REQ_CODE_21){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CAM_REQ_CODE_21);
            }
        }
        else if(requestCode == CAM_REQ_CODE_22){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CAM_REQ_CODE_22);
            }
        }

        //upper
        else if(requestCode == CAM_REQ_CODE_31){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CAM_REQ_CODE_31);
            }
        }
        else if(requestCode == CAM_REQ_CODE_32){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CAM_REQ_CODE_32);
            }
        }
        //nail beds
        else if(requestCode == CAM_REQ_CODE_41){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CAM_REQ_CODE_41);
            }
        }

        else if(requestCode == CBC_CODE_1){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CBC_CODE_1);
            }
        }
        else if(requestCode == CBC_CODE_2){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CBC_CODE_2);
            }
        }
        else if(requestCode == CBC_CODE_3){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CBC_CODE_3);
            }
        }
        else if(requestCode == CBC_CODE_4){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CBC_CODE_4);
            }
        }
        else if(requestCode == CBC_CODE_5){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data, CBC_CODE_5);
            }
        }
    }

    private void onCaptureImageResult(Intent data, int code) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        switch (code){
            case 11:
                frontLE.setMaxWidth(400);
                frontLE.setImageBitmap(thumbnail);
                break;
            case 12:
                frontRE.setMaxWidth(400);
                frontRE.setImageBitmap(thumbnail);
                break;
            case 21:
                lowerLE.setMaxWidth(400);
                lowerLE.setImageBitmap(thumbnail);
                break;
            case 22:
                lowerRE.setMaxWidth(400);
                lowerRE.setImageBitmap(thumbnail);
                break;
            case 31:
                upperLE.setMaxWidth(400);
                upperLE.setImageBitmap(thumbnail);
                break;
            case 32:
                upperRE.setMaxWidth(400);
                upperRE.setImageBitmap(thumbnail);
                break;
            case 41:
                nailBed.setMaxWidth(400);
                nailBed.setImageBitmap(thumbnail);
                break;
            case 81:
                cbcP1.setMaxWidth(400);
                cbcP1.setImageBitmap(thumbnail);
                break;
            case 82:
                cbcP2.setMaxWidth(400);
                cbcP2.setImageBitmap(thumbnail);
                break;
            case 83:
                cbcP3.setMaxWidth(400);
                cbcP3.setImageBitmap(thumbnail);
                break;
            case 84:
                cbcP4.setMaxWidth(400);
                cbcP4.setImageBitmap(thumbnail);
                break;
            case 85:
                cbcP5.setMaxWidth(400);
                cbcP5.setImageBitmap(thumbnail);
                break;
        }

    }
    public static byte[] imageViewToByte(ImageView image) {
        if(image.getDrawable() == null){
            return null;
        }
        else{
            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }
    }
}
/*

    private void pickImage(boolean left) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayContact.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,2);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
        /*
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayContact.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,CAM_REQ_CODE);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,CAM_REQ_CODE_2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();




        AlertDialog alertDialog;
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        CardView camCV = (CardView) view.findViewById(R.id.card_view_cam);
        CardView galleryCV = (CardView) view.findViewById(R.id.card_view_gallery);

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();


        /*
        camCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cam activity
//                Toast.makeText(CreateNewRecord.this, "Camera Selected", Toast.LENGTH_SHORT).show();
                if (left)
                    startActivityForResult(new Intent(DisplayContact.this, CameraActivity.class), CAM_REQ_CODE);
                else
                    startActivityForResult(new Intent(DisplayContact.this, CameraActivity.class), CAM_REQ_CODE_2);
                alertDialog.dismiss();
            }
        });





        galleryCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setType("image/*");

                Intent chooser = Intent.createChooser(gallery, "Select an image");
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { });
                if (left)
                    startActivityForResult(chooser, REQ_CODE);
                else
                    startActivityForResult(chooser, REQ_CODE_2);

                alertDialog.dismiss();
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_CODE) {
                if (data != null)
                    leftEyeUri = data.getData();
                else
                    leftEyeUri = Uri.fromFile(rawImgFile);
                frontLE.setImageURI(leftEyeUri);
            } else if (requestCode == REQ_CODE_2) {
                if (data != null)
                    rightEyeUri = data.getData();
                else
                    rightEyeUri = Uri.fromFile(rawImgFile);
                frontRE.setImageURI(rightEyeUri);
            } else if (requestCode == CAM_REQ_CODE) {
                String path = data.getStringExtra("Image");
                rawImgFile = new File(path);
                leftEyeUri = Uri.fromFile(rawImgFile);
                //saveImgGallery(leftEyeUri, rawImgFile.getName());
                frontLE.setImageURI(leftEyeUri);
            } else if (requestCode == CAM_REQ_CODE_2) {
                String path = data.getStringExtra("Image");
                rawImgFile = new File(path);
                rightEyeUri = Uri.fromFile(rawImgFile);
                //saveImgGallery(rightEyeUri, rawImgFile.getName());
                frontRE.setImageURI(rightEyeUri);
            }
            }
        else {
                Toast.makeText(DisplayContact.this, "Cannot pick image", Toast.LENGTH_SHORT).show();
            }
        }
}


         */
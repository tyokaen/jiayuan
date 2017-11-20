package com.example.jiayuan.bijin.Fragment;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jiayuan.bijin.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by jiayuan on 2017/08/11.
 */

public class BeautyRankFragment extends Fragment implements View.OnClickListener {
    Button Btn_image_choose = null;
    private final int IMAGE_CODE = 0;
    private final String IMAGE_TYPE = "image/*";
    ImageView imageView;
    byte[] image_byte=null;
    Bitmap bm = null;
    AlertDialog alertDialog=null;
    android.support.v4.app.FragmentManager fragmentManager=null;
    FragmentTransaction transaction=null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beauty_ranking_challenge, null);
        Btn_image_choose = (Button) view.findViewById(R.id.choose_image);
        Btn_image_choose.setOnClickListener(this);
        imageView=(ImageView)view.findViewById(R.id.ImageView1);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_image:
                selectImage();
                break;
        }
    }

    private void selectImage() {
        // TODO Auto-generated method stub
        boolean isKitKatO = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        Intent getAlbum;
        if (isKitKatO) {
            getAlbum = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        }
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            Log.e("TAG->onresult", "ActivityResult resultCode error");
            return;
        }

        ContentResolver resolver = getActivity().getContentResolver();
        if (requestCode == IMAGE_CODE) {
            Uri originalUri = data.getData();  //获得图片的uri
            try {
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                Toast.makeText(getContext(),""+bm.getByteCount(),Toast.LENGTH_SHORT).show();
                makeDialog(bm);
            } catch (IOException e) {
                e.printStackTrace();
                //显得到bitmap图片;

            }
        }
    }
    public void makeDialog(Bitmap bitmap){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("この写真を利用しますか");
        ImageView  imageView=new ImageView(getActivity());
        builder.setView(imageView);
        imageView.setImageBitmap(bitmap);
        builder.setPositiveButton("使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fragmentManager=getFragmentManager();
                BeautyRanking1Fragment beautyRanking1Fragment=new BeautyRanking1Fragment();
                transaction=fragmentManager.beginTransaction();
                image_byte=Bitmap2Bytes(bm);
                Bundle bundle=new Bundle();
                bundle.putByteArray("imagebyte",image_byte);
                beautyRanking1Fragment.setArguments(bundle);
                transaction.replace(R.id.frg_root,beautyRanking1Fragment);
                transaction.commit();
            }
        });
        builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                     alertDialog.cancel();
            }
        });
         alertDialog= builder.create();
        alertDialog.show();
    }
    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}

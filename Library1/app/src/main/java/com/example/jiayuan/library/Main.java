package com.example.jiayuan.library;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Provider.Electrical;
import Provider.ElectricalDao;
import Provider.Furniture;
import Provider.FurnitureDao;
import Provider.ProviderDao;
import Provider.provider;
import Tool.DealwithFile;
import Tool.Goods_File;


public class Main extends Activity {
    List<Map<String,Object>> booklist;
    Map<String,Object> bookmap;
    SimpleAdapter adapter;
    ArrayAdapter<String> adapter1;
    ArrayList<String> savelist2=new ArrayList<String>();
    ListView L1,search_list;
    String temp;
    Boolean isFinish=false;
    Handler handler=new Handler();
    CircleRefreshLayout circleRefreshLayout;
    int i=0;
    static int CountPosition=0;
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> savelist1=new ArrayList<String>();
    File saveSearch=new File(Environment.getExternalStorageDirectory(),"search.csv");
    File file=new File(Environment.getExternalStorageDirectory(),"electrical_info.csv");
    File file1=new File(Environment.getExternalStorageDirectory(),"Furniture_info.csv");
    ArrayList<String> savelist=new ArrayList<String>() ;
    DealwithFile dealwithFile=new DealwithFile();
    String[] name = {"java核心技术 ", "head fist in java", "java语言程序设计", "java开发实战1200例", "java编程思想",
            "深入分析java web", "Effective java", "深入分析java虚拟机", "java并发实战", "java从入门到精通"
    };

    static int [] price={14,29,32,21,42,30,28,21,19,22};
    static String[] writer={"张三","张三","张三","张三","张三","张三","张三","张三","张三","张三"};
    static int[] image = {R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11};
SimpleAdapter simpleAdapter_electrical;
ProviderDao dao;
Map<String,Object> map_electrical;
ViewPager viewPager;
RadioGroup radioGroup;
List<View> ViewList=new ArrayList<View>();
ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
ArrayList<provider> providerArrayList=new ArrayList<provider>();
    GridView gridView;
    private List<Map<String,Object>> data_list=new ArrayList<Map<String,Object>>();
    private SimpleAdapter simpleAdapter;
    private Map<String,Object>map;
    ElectricalDao electricalDao;
    ArrayList<String>arrayList=new ArrayList<String>();
    ArrayList<Integer>imageList=new ArrayList<Integer>();
    ArrayList<Electrical> electricalArrayList;
    ArrayList<Furniture> FurnitureList;
    FurnitureDao furnitureDao=null;
    SearchView searchView;
    private ActionBar actionBar;
    RecyclerView recyclerView,recyclerView1;
    MyAdapter myAdapter=new MyAdapter();
    MyAdapter1 myAdapter1=new MyAdapter1();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        search_list=(ListView)findViewById(R.id.Search_list);
        search_list.setTextFilterEnabled(true);
        dao=new ProviderDao(this);
        providerArrayList=dao.find();
        ViewList.add(getLayoutInflater().inflate(R.layout.activity_show,null));
        ViewList.add(getLayoutInflater().inflate(R.layout.activity_electricalshow,null));
        ViewList.add(getLayoutInflater().inflate(R.layout.tab3,null));
        recyclerView=(RecyclerView)ViewList.get(2).findViewById(R.id.furniture_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView1=(RecyclerView)ViewList.get(1).findViewById(R.id.electrical_list);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setHasFixedSize(true);
        if(providerArrayList==null){
            providerArrayList=new ArrayList<provider>();
            providerArrayList=readfromFile();
            for(int i=0;i<providerArrayList.size();i++){
                dao.add(providerArrayList.get(i));
            }
        }
        for(int i=0;i<providerArrayList.size();i++){
            map= new HashMap<String, Object>();
            map.put("ID",providerArrayList.get(i).getID());
            map.put("Image",getDrawable(providerArrayList.get(i).getImage()));
            list.add(map);
        }

    viewPager=(ViewPager)findViewById(R.id.viewpager);
        radioGroup=(RadioGroup)findViewById(R.id.tg_radio) ;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.Radio1:
                    viewPager.setCurrentItem(1);
                    break;
                    case R.id.Radio2:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.Radio3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        circleRefreshLayout=(CircleRefreshLayout)ViewList.get(0).findViewById(R.id.refresh_layout);
        L1=(ListView)circleRefreshLayout.findViewById(R.id.ListView1);
       // autoCompleteTextView=(AutoCompleteTextView)ViewList.get(0).findViewById(R.id.AutoTextView);
       booklist= new ArrayList<Map<String, Object>>();
        for (int i = 0; i < name.length; i++) {
            bookmap = new HashMap<String, Object>();
           bookmap.put("Image", image[i]);
          bookmap.put("Title", name[i]);
           bookmap.put("comment","非常好的一本java书");
            booklist.add(bookmap);
        }
        String[] from={"Image","Title","comment"};
        int[] to={R.id.ImageView1,R.id.TextView12,R.id.TextView13};
        adapter=new SimpleAdapter(this,booklist,R.layout.simple_adapter_item,from,to);
        L1.setAdapter(adapter);

        if(saveSearch.length()>0) {
            //ReadFromFile(savelist, saveSearch);
            dealwithFile.ReadFromFile(savelist,saveSearch);
            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, savelist);
            //autoCompleteTextView.setAdapter(adapter1);
        }
        handler.postDelayed(runnable,1000);
        L1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInfo(position);

            }
        });
        circleRefreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        // do something when refresh starts
                    }
                    @Override
                    public void completeRefresh() {
                        // do something when refresh complete

                    }
                });
        electricalDao=new ElectricalDao(this);
        electricalArrayList=electricalDao.findElectrical();
        System.out.println("nihao"+electricalArrayList.size());
        if(electricalArrayList==null){
            electricalArrayList=new ArrayList<Electrical>();
            electricalArrayList= Goods_File.getGoods_file().readObject1(file);
            for(int i=0;i<electricalArrayList.size();i++){
                electricalDao.addElectrical(electricalArrayList.get(i));
            }
        }

        recyclerView1.setAdapter(myAdapter1);
        myAdapter1.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                ImageView img=new ImageView(Main.this);
                img.setImageResource(R.drawable.electrical);
                new AlertDialog.Builder(Main.this).setView(img)
                        .setTitle("详情" + position)
                        .setMessage("作者：" + electricalArrayList.get(position).getStyle0() + "价格:" + electricalArrayList.get(position).getPrice()+"元")
                        .setPositiveButton("仔细瞅瞅", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Main.this, book_info.class);
                                intent.putExtra("Image",R.drawable.electrical);
                                intent.putExtra("Price",electricalArrayList.get(position).getPrice());
                                intent.putExtra("Address",electricalArrayList.get(position).getAddress());
                                intent.putExtra("Kinds",electricalArrayList.get(position).getAddress());
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });

        furnitureDao=new FurnitureDao(this);
        FurnitureList=furnitureDao.findFurniture();
        if(FurnitureList==null){
            FurnitureList=new ArrayList<Furniture>();
            FurnitureList= Goods_File.getGoods_file().readObject(file1);
            for(int i=0;i<FurnitureList.size();i++){
                furnitureDao.addFurniture(FurnitureList.get(i));
            }
        }
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                    ImageView img=new ImageView(Main.this);
                    img.setImageResource(R.drawable.furniture);
                    new AlertDialog.Builder(Main.this).setView(img)
                            .setTitle("详情" + position)
                            .setMessage("作者：" + FurnitureList.get(position).get_F_Style0() + "价格:" + FurnitureList.get(position).get_F_Price()+"元")
                            .setPositiveButton("仔细瞅瞅", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Main.this, book_info.class);
                                    intent.putExtra("Image",R.drawable.furniture);
                                    intent.putExtra("Price",FurnitureList.get(position).get_F_Price());
                                    intent.putExtra("Address",FurnitureList.get(position).get_F_Address());
                                    intent.putExtra("Kinds",FurnitureList.get(position).get_F_Style0());
                                    intent.putExtra("Time",FurnitureList.get(position).get_F_Time());
                                    startActivity(intent);
                                }
                            })
                            .show();
            }
        });
        viewPager.setAdapter(new innerPager());

    }
    public Runnable runnable=new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this,1000);
            i++;
            if(i==2){
                circleRefreshLayout.finishRefreshing();
                i=0;
            }
        }
    };
    public void showInfo(final int position){
        ArrayList<String> Randomsavelist=new ArrayList<String>() ;
        if(!isFinish){
            savelist1=savelist;
            savelist2=savelist;
            isFinish=true;
        }
        dealwithFile.WriteToFile(name[position],savelist1,saveSearch,savelist2);
        dealwithFile.ReadFromFile(Randomsavelist,saveSearch);
        savelist1=Randomsavelist;
        for(int i=0;i<savelist2.size();i++){
            System.out.println(savelist2.get(i));
        }
        adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Randomsavelist);
        //autoCompleteTextView.setAdapter(adapter1);
        ImageView img=new ImageView(this);
        img.setImageResource(image[position]);
        new AlertDialog.Builder(this).setView(img)
                .setTitle("详情" + position)
                .setMessage("作者：" + writer[position] + "价格:" + price[position]+"元")
                .setPositiveButton("仔细瞅瞅", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Main.this, book_info.class);
                        intent.putExtra("Image",image[position]);
                        intent.putExtra("Price",price[position]);
                        intent.putExtra("Address","平砂");
                        intent.putExtra("Kinds","図書");
                        startActivity(intent);
                    }
                })
                .show();
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position );
    }
    private class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder>implements View.OnClickListener {
        private MyAdapter1() {
        }
        public MyAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab2, parent, false);
            v.setOnClickListener(this);
            return new MyAdapter1().new MyViewHolder(v);
        }
        public void onBindViewHolder(MyAdapter1.MyViewHolder holder, int position) {
            holder.imageView.setImageResource(R.drawable.electrical);
            holder.mTextView.setText(electricalArrayList.get(position).getStyle0());
            holder.mTextView1.setText(electricalArrayList.get(position).getPrice());
            holder.v.setTag(position);
        }
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,(Integer)v.getTag());
            }
        }
        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            mOnItemClickListener = (OnRecyclerViewItemClickListener) listener;
        }

        @Override
        public int getItemCount() {
            return electricalArrayList.size();
        }

        public class MyViewHolder
                extends RecyclerView.ViewHolder {
            public TextView mTextView, mTextView1;

            public ImageView imageView;
            public View v;

            public MyViewHolder(View v) {
                super(v);
                this.v=v;
                mTextView = (TextView) v.findViewById(R.id.El_Address);
                imageView = (ImageView) v.findViewById(R.id.El_img);
                mTextView1 = (TextView) v.findViewById(R.id.El_price);
            }
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>implements View.OnClickListener {
        private MyAdapter() {
        }
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_info,parent,false);
            v.setOnClickListener(this);
            return  new MyViewHolder(v);
        }
        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            holder.imageView.setImageResource(R.drawable.furniture);
            holder.mTextView.setText(FurnitureList.get(position).get_F_Style0());
            holder.mTextView1.setText(FurnitureList.get(position).get_F_Address());
            holder.v.setTag(position);
        }
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v,(Integer) v.getTag());
            }
        }
        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            mOnItemClickListener = (OnRecyclerViewItemClickListener) listener;
        }

        @Override
        public int getItemCount() {
            return FurnitureList.size();
        }
        public class MyViewHolder
                extends RecyclerView.ViewHolder
        {
            public TextView mTextView,mTextView1;

            public ImageView imageView;
            public View v;

            public MyViewHolder( View v )
            {
                super(v);
                this.v=v;
                mTextView = (TextView) v.findViewById(R.id.Fur_price);
                imageView = (ImageView) v.findViewById(R.id.Fur_img);
                mTextView1=(TextView)v.findViewById(R.id.Fur_Address);

            }
        }
    }

    class innerPager extends PagerAdapter{
        @Override
        public int getCount() {
            return 3;
        }
        public void startUpdate(View view) {

        }
        public Object instantiateItem(View view, int i) {
            ((ViewPager) view).addView(ViewList.get(i), 0);
            return ViewList.get(i);
        }
        @Override
        public void destroyItem(View view, int i, Object o) {
            ((ViewPager) view).removeView(ViewList.get(i));
        }

        @Override
        public void finishUpdate(View view) {
        }
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }
        @Override
        public Parcelable saveState() {
            return null;
        }
        @Override
        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {

        }
    }
    public Drawable getDrawable(byte[] in){
        Bitmap map= BitmapFactory.decodeByteArray(in,0,in.length);
        BitmapDrawable drawable=new BitmapDrawable(getResources(),map);
        return  drawable;

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.showProvider) {
           show();
        }
        if (id==android.R.id.home){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if(id==R.id.action_search){
            Intent intent=new Intent(this,search_result.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void show(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("卖家信息");
        LayoutInflater layoutInflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout=layoutInflater.inflate(R.layout.providerlist,(ViewGroup)this.findViewById(R.id.LinearLayout12));
        final ListView listview=(ListView)layout.findViewById(R.id.show_providerInfo);
        builder.setView(layout);
        String[] from={"ID","Image"};
        int[] to={R.id.show_proviername,R.id.circleImageview,};
        simpleAdapter=new SimpleAdapter(this,list,R.layout.show_provider,from,to);
        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Main.this,provider_show_goods.class);
                intent.putExtra("pro_name",providerArrayList.get(position).getID());
                startActivity(intent);
            }
        });

        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {

                if (view instanceof CircleImageView && data instanceof Drawable) {
                   CircleImageView iv = (CircleImageView) view;
                   iv.setImageDrawable((Drawable)data);
                    return true;
                } else {
                    return false;
                }
            }
        });
        AlertDialog alertDialog=builder.create();
        WindowManager.LayoutParams params=alertDialog.getWindow().getAttributes();
        params.width=1200;
        params.height=1400;
        alertDialog.show();
    }
    public ArrayList<provider> readfromFile(){
        File file=new File(Environment.getExternalStorageDirectory(),"provider_info.csv");
        ArrayList<provider> providerArrayList1=null;
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
            try {
                providerArrayList1=(ArrayList<provider>) objectInputStream.readObject();
                objectInputStream.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
          return providerArrayList1;
    }
}

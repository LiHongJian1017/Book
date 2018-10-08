package com.example.book;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by LI on 2017/10/29.
 */

public class bookActivity extends AppCompatActivity {

    private List<Book> booklist = new ArrayList<>();
    private boolean morenbool=true;
    private boolean jiagebool=true;
    private boolean shangjiabool=true;
    private String sql;
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        TextView toolbartitle= (TextView)findViewById(R.id.toolbartitle);
        toolbartitle.setText(Myuser.toolbartitle);

        Toolbar toolbar=(Toolbar)findViewById(R.id.booktoolbar);
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.mune_bookxinxi);
        setSupportActionBar(toolbar);
        progressDialog= new ProgressDialog(bookActivity.this);
        progressDialog.setMessage("正在加载.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

       // mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_lauout);

        //给btn1绑定监听事件


        //排序按钮
        final  Button moren =(Button)findViewById(R.id.moren);
        final  Button jiage =(Button)findViewById(R.id.jiage);
        final  Button shangjia =(Button)findViewById(R.id.shangjia);

        //给btn1绑定监听事件
        moren.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(morenbool==true)
                {
                    //morenbool=true;
                    jiagebool=true;
                    shangjiabool=true;

                    jiage.setText("价格");
                    shangjia.setText("上架时间");
                    //moren.setText("默认");
                    jiage.setTextColor(Color.rgb(0, 0, 0));
                    shangjia.setTextColor(Color.rgb(0, 0, 0));
                    //moren.setTextColor(Color.rgb(0, 0, 0));

                    moren.setTextColor(Color.rgb(185, 22, 41));
                    morenbool=false;
                }
                else
                {
                    //morenbool=true;
                    jiagebool=true;
                    shangjiabool=true;

                    jiage.setText("价格");
                    shangjia.setText("上架时间");
                    moren.setText("默认");
                    jiage.setTextColor(Color.rgb(0, 0, 0));
                    shangjia.setTextColor(Color.rgb(0, 0, 0));
                    jiage.setTextColor(Color.rgb(0, 0, 0));
                    //moren.setTextColor(Color.rgb(185, 22, 41));

                    moren.setTextColor(Color.rgb(185, 22, 41));
                    morenbool=true;
                }
            }
        });

        jiage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(jiagebool==true)
                {
                    morenbool=true;
                    //jiagebool=true;
                    shangjiabool=true;

                    //jiage.setText("价格");
                    shangjia.setText("上架时间");
                    moren.setText("默认");
                    //jiage.setTextColor(Color.rgb(0, 0, 0));
                    shangjia.setTextColor(Color.rgb(0, 0, 0));
                    moren.setTextColor(Color.rgb(0, 0, 0));

                    jiage.setText("价格∧");
                    jiage.setTextColor(Color.rgb(185, 22, 41));
                    jiagebool=false;
                }
                else
                {
                    morenbool=true;
                    //jiagebool=true;
                    shangjiabool=true;

                    //jiage.setText("价格");
                    shangjia.setText("上架时间");
                    moren.setText("默认");
                    //jiage.setTextColor(Color.rgb(0, 0, 0));
                    shangjia.setTextColor(Color.rgb(0, 0, 0));
                    moren.setTextColor(Color.rgb(0, 0, 0));

                    jiage.setText("价格∨");
                    jiage.setTextColor(Color.rgb(185, 22, 41));
                    jiagebool=true;
                }
            }
        });


        shangjia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(shangjiabool==true)
                {
                    morenbool=true;
                    jiagebool=true;
                    //shangjiabool=true;

                    jiage.setText("价格");
                    //shangjia.setText("上架时间");
                    moren.setText("默认");
                    jiage.setTextColor(Color.rgb(0, 0, 0));
                    //shangjia.setTextColor(Color.rgb(0, 0, 0));
                    moren.setTextColor(Color.rgb(0, 0, 0));

                    shangjia.setText("上架时间∧");
                    shangjia.setTextColor(Color.rgb(185, 22, 41));
                    shangjiabool=false;
                }
                else
                {
                    morenbool=true;
                    jiagebool=true;
                    //shangjiabool=true;

                    jiage.setText("价格");
                    //shangjia.setText("上架时间");
                    moren.setText("默认");
                    jiage.setTextColor(Color.rgb(0, 0, 0));
                    //shangjia.setTextColor(Color.rgb(0, 0, 0));
                    moren.setTextColor(Color.rgb(0, 0, 0));

                    shangjia.setText("上架时间∨");
                    shangjia.setTextColor(Color.rgb(185, 22, 41));
                    shangjiabool=true;
                }
            }
        });

        //返回按钮事件**************
        ImageButton bookfanhui = (ImageButton) findViewById(R.id.bookfanhui);
        bookfanhui.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //finish();
                    Intent i = new Intent(bookActivity.this, gerenzhongxinActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(), "返回成功！", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        //初始化
        Intent seachintent=getIntent();
        String searchtiaojian=seachintent.getStringExtra("searchtiaojian");
        String searchname=seachintent.getStringExtra("searchname");
        if(searchtiaojian.equals("1"))
        {
            sql="select from Book where bookname='"+searchname+"'";
        }
        else if(searchtiaojian.equals("2"))
        {
            sql="select from Book where ISBN='"+searchname+"'";
        }
        else if(searchtiaojian.equals("3"))
        {
            sql="select from Book where zuozhe='"+searchname+"'";
        }
        else if(searchtiaojian.equals("4"))
        {
            sql="select from Book where fenlei='"+searchname+"'";
        }

        chushihua();


    }

   /* private void init()
    {
        listrexiaoClass rexiao = new listrexiaoClass("数据结构","30元","2017-9-9",R.drawable.shu);
        booklist.add(rexiao);
        listrexiaoClass rexiao1 = new listrexiaoClass("数据结构","30元","2017-9-9",R.drawable.shu);
        booklist.add(rexiao1);
        listrexiaoClass rexiao2 = new listrexiaoClass("数据结构","30元","2017-9-9",R.drawable.shu);
        booklist.add(rexiao2);
        listrexiaoClass rexiao3 = new listrexiaoClass("数据结构","30元","2017-9-9",R.drawable.shu);
        booklist.add(rexiao3);

    }*/
    private void chushihua()
    {
        new BmobQuery<Book>().doSQLQuery(sql,new SQLQueryListener<Book>(){

            @Override
            public void done(BmobQueryResult<Book> result, BmobException e) {
                if(e ==null)
                {
                    List<Book> list = (List<Book>) result.getResults();
                    if(list!=null && list.size()>0)
                    {
                      for(int i=0;i<list.size();i++)
                      {
                          Book linshibook=new Book();
                          linshibook=list.get(i);
                          linshibook.setPicurl1(linshibook.getPic1().getFileUrl());
                          linshibook.setPicurl2(linshibook.getPic2().getFileUrl());
                          String date1=linshibook.getCreatedAt();
                          date1=date1.substring(0,10);
                          linshibook.setDate(stringToDate(date1));
                          booklist.add(linshibook);
                      }
                        progressDialog.dismiss();
                        rexiaoAdapter adapter = new rexiaoAdapter(bookActivity.this,R.layout.listrexiao,booklist);
                        ListView listView = (ListView)findViewById(R.id.booklist);
                        listView.setAdapter(adapter);
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "没有改类书籍信息！", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "查找失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mune_bookxinxi, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Bookxinxizhuye:
                Toast.makeText(bookActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigeren:
                Toast.makeText(bookActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinximydianpu:
                Toast.makeText(bookActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigouwuche:
                Toast.makeText(bookActivity.this, "字号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxidingdan:
                Toast.makeText(bookActivity.this, "模式", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}


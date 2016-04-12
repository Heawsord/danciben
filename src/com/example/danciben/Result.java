package com.example.danciben;

import java.util.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
 
public class Result extends Activity{
     
    ContentResolver contentResolver;
 
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
         
        final ListView listView=(ListView)findViewById(R.id.show);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
         
        @SuppressWarnings("unchecked")
         
        final List<Map<String, String>> list=(List<Map<String,String>>)bundle.getSerializable("data");
         
        final SimpleAdapter adapter=new SimpleAdapter(Result.this, list, R.layout.line, new String[]{"word","detail"}, new int[]{R.id.word_list,R.id.detail_list});
         
        listView.setAdapter(adapter);
         
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
 
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    final int arg2, long arg3) {
                // TODO Auto-generated method stub
                 
                final String word=((TextView)arg1.findViewById(R.id.word_list)).getText().toString();
                final String detail=((TextView)arg1.findViewById(R.id.detail_list)).getText().toString();
                 
 
                AlertDialog.Builder builder=new Builder(Result.this);
 
                builder.setItems(R.array.array, new DialogInterface.OnClickListener() {
                     
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        String select[] =getResources().getStringArray(R.array.array);
                         
                        if(select[which].equals("ɾ��")){
                             
                            AlertDialog.Builder alert=new Builder(Result.this);
                             
                            alert.setTitle("ȷ��ɾ��?");
                            alert.setPositiveButton("ȷ��", new OnClickListener() {
                                 
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    // TODO Auto-generated method stub
                                    contentResolver=getContentResolver();
                                    contentResolver.delete(Words.Word.DICT_CONTENT_URI, "word like ?",new String[]{"%"+word+"%"} );
                                    list.remove(arg2);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                             
                            alert.setNegativeButton("ȡ��", new OnClickListener() {
                                 
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    // TODO Auto-generated method stub
                                    Toast.makeText(Result.this, "����ȡ��", Toast.LENGTH_SHORT).show();
                                }
                            });
                             
                            alert.show();
                             
                        }
                        else if(select[which].equals("�༭")){
                             
                            Bundle bundle=new Bundle();
                            Intent intent=new Intent();
                             
                            bundle.putString("word", word);
                            bundle.putString("detail", detail);
                             
                            intent.putExtras(bundle);
                            intent.setClass(Result.this, EditItem.class);
                            startActivity(intent);
                        }
                         
                    }
                });
                 
                builder.show();
                /*
                builder.setNegativeButton("ȡ��", new OnClickListener() {
                     
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(Result.this, "���Ѿ�ȡ��", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                builder.setPositiveButton("ȷ��", new OnClickListener() {
                     
                    @Override
                    public void onClick(DialogInterface arg0, int arg) {
                        // TODO Auto-generated method stub
                        contentResolver=getContentResolver();
                        contentResolver.delete(Words.Word.DICT_CONTENT_URI, "word like ?",new String[]{"%"+word+"%"} );
                        list.remove(arg2);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                */    
                return false;
            }
        });
    }
 
    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
         
        System.out.println("ˢ��");
    }
     
     
}

package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.volley.toolbox.ImageLoader;

import com.example.activity.BaseActivity;
import com.example.activity.R;
import com.example.bean.Deal;
import com.example.bean.Exchange;
import com.example.bean.MySaleItem;
import com.example.bean.Rent;

public class IssuanceAdapter extends BaseAdapter{
	private ArrayList<MySaleItem> mData;
	private Context mContext;
	private ViewHolder holder = null;
	private MySaleItem mySaleItem;
	private Integer currentuserid;
	private ListView issuance_sale_listView;
	
	public IssuanceAdapter(ArrayList<MySaleItem> arrayList,Context context,int userid,ListView issuance_sale_listView){
		mContext = context;
		mData = arrayList;
		Log.d("info", "mData = "+mData.size());
		currentuserid = userid;
		this.issuance_sale_listView = issuance_sale_listView;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		if(mData==null)
			return 0;
		else 
			return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		if(mData==null)
			return null;
		else return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_my_issuance,parent, false);
			holder.item_imageView_type = (ImageView) convertView.findViewById(R.id.item_imageView_type);
			holder.item_textView_goodsName = (TextView) convertView.findViewById(R.id.item_textView_goodsName);
			holder.item_textView_time = (TextView) convertView.findViewById(R.id.item_textView_time);
			holder.item_button_remove = (Button) convertView.findViewById(R.id.item_button_remove);
			holder.item_button_update = (Button) convertView.findViewById(R.id.item_button_update);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.item_button_remove.setTag(position);
		
		if(mData!=null){
			mySaleItem = mData.get(position);
			Log.d("info", "mData size = "+mData.size());
			if(holder.item_imageView_type!=null){
				if(mySaleItem.getType()==0){//到租表中标记已经完成
					holder.item_imageView_type.setTag(0);
					holder.item_imageView_type.setImageResource(R.drawable.renticon);
					BmobQuery<Rent> query_rent = new BmobQuery<Rent>();
					Log.d("info", "currentuserid = "+currentuserid+" mySaleItem.ggoodsid = "+mySaleItem.ggoodsid);
					query_rent.addWhereEqualTo("userid", currentuserid);
					query_rent.addWhereEqualTo("goodsid", mySaleItem.ggoodsid);
					query_rent.findObjects(mContext, new FindListener<Rent>() {
						@Override
						public void onSuccess(List<Rent> rents) {
							// TODO 自动生成的方法存根
							Log.d("info", "94rent = "+rents.size());
							if(rents.get(0).renttag==1){
								Log.d("info", "1111111111111 "+mySaleItem.goodsname);
								Log.d("info", "11111111111111"+"position = "+position);
								View view = issuance_sale_listView.getChildAt(position);
								Button button = (Button) view.findViewById(R.id.item_button_remove);
								button.setText("已下架");
								button.setBackgroundColor(Color.GRAY);
								button.setClickable(false);
							}
						}
						@Override
						public void onError(int arg0, String arg1) {
							// TODO 自动生成的方法存根
							Log.i("info", arg0+arg1);
						}
					});
				}
				else if(mySaleItem.getType()==1){//卖
					holder.item_imageView_type.setTag(1);
					holder.item_imageView_type.setImageResource(R.drawable.saleicon);
					BmobQuery<Deal> query_deal = new BmobQuery<Deal>();
					Log.d("info", "currentuserid = "+currentuserid+" mySaleItem.ggoodsid = "+mySaleItem.ggoodsid);
					query_deal.addWhereEqualTo("userid", currentuserid);
					query_deal.addWhereEqualTo("goodsid", mySaleItem.ggoodsid);
					query_deal.findObjects(mContext, new FindListener<Deal>() {
						@Override
						public void onSuccess(List<Deal> deals) {
							// TODO 自动生成的方法存根
							Log.d("info", "94rent = "+deals.size());
							if(deals.get(0).dealtag==1){
								//Log.d("info", "tag = "+((ViewHolder)convertView.getTag()).item_textView_goodsName.getText().toString());
								View view = issuance_sale_listView.getChildAt(position);
								Button button = (Button) view.findViewById(R.id.item_button_remove);
								button.setText("已下架");
								button.setBackgroundColor(Color.GRAY);
								button.setClickable(false);
							}
						}
						@Override
						public void onError(int arg0, String arg1) {
							// TODO 自动生成的方法存根
							Log.i("info", arg0+arg1);
						}
					});
				}
				else if(mySaleItem.getType()==2){//换
					holder.item_imageView_type.setTag(2);
					holder.item_imageView_type.setImageResource(R.drawable.exchangeicon);
					BmobQuery<Exchange> query_exchange = new BmobQuery<Exchange>();
					Log.d("info", "currentuserid = "+currentuserid+" mySaleItem.ggoodsid = "+mySaleItem.ggoodsid);
					query_exchange.addWhereEqualTo("userid", currentuserid);
					query_exchange.addWhereEqualTo("goodsid", mySaleItem.ggoodsid);
					query_exchange.findObjects(mContext, new FindListener<Exchange>() {
						@Override
						public void onSuccess(List<Exchange> exchanges) {
							// TODO 自动生成的方法存根
							Log.d("info", "94rent = "+exchanges.size());
							if(exchanges.get(0).exchangetag==1){
								View view = issuance_sale_listView.getChildAt(position);
								Button button = (Button) view.findViewById(R.id.item_button_remove);
								button.setText("已下架");
								button.setBackgroundColor(Color.GRAY);
								button.setClickable(false);
							}
						}
						@Override
						public void onError(int arg0, String arg1) {
							// TODO 自动生成的方法存根
							Log.i("info", arg0+arg1);
						}
					});
				}
			}
			if(holder.item_textView_goodsName!=null)
				holder.item_textView_goodsName.setText(mySaleItem.goodsname);
			if(holder.item_textView_time!=null)
				holder.item_textView_time.setText(mySaleItem.time);
			
		}
		/*holder.item_button_remove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if((int)holder.item_imageView_type.getTag()==1){
					Log.i("info", "goodsname = "+holder.item_textView_goodsName.getText().toString());
				}
				if((int)holder.item_imageView_type.getTag()==0){
					Log.i("info", "goodsname = "+holder.item_textView_goodsName.getText().toString());
				}
				if((int)holder.item_imageView_type.getTag()==2){
					Log.i("info", "goodsname = "+holder.item_textView_goodsName.getText().toString());
				}
				
			}
		});*/
		return convertView;
	}
	private class ViewHolder{
		ImageView item_imageView_type;
		TextView item_textView_goodsName;
		TextView item_textView_time;
		Button item_button_remove;
		Button item_button_update;
	}
}

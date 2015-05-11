package org.example.myapp.client.view;
import java.util.List;
import org.example.myapp.R;
import org.example.myapp.client.model.Doctor;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint({ "InflateParams", "ViewHolder" }) public class BuddyAdapter extends BaseAdapter{
	private Context context;
	private List<Doctor> list;
	LayoutInflater inflater;
	
	public BuddyAdapter(Context context,List<Doctor> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public View getView(int position, View convertView, ViewGroup root) {
		convertView = inflater.inflate(R.layout.buddy_listview_item,null);
		
		TextView name = (TextView) convertView.findViewById(R.id.doc_name);
		TextView major = (TextView) convertView.findViewById(R.id.doc_major);
		TextView tel = (TextView) convertView.findViewById(R.id.doc_tel);
		TextView online = (TextView) convertView.findViewById(R.id.doc_isonline);
		TextView weidu = (TextView)convertView.findViewById(R.id.weidu_mes_no);
		TextView dept = (TextView) convertView.findViewById(R.id.doc_dept);
		
		Doctor be=list.get(position);
		tel.setText(Long.toString(be.getDoc_id()));
		if (be.getMes_to_read() != 0) {
			weidu.setText("("+ Integer.toString(be.getMes_to_read()) +")");
			weidu.setTextColor(Color.rgb(255,0,0));
			name.setTextColor(Color.rgb(255,0,0));
		} else {
			weidu.setTextColor(Color.rgb(0,0,0));
			name.setTextColor(Color.rgb(0,0,0));
		}
		
		
		name.setText(be.getName());
		major.setText(be.getMajor());
		dept.setText(be.getHospital() + " | " + be.getDepartment());
		if (be.getIsOnline() == 1) {
			online.setText("‘⁄œﬂ");
		} else {
			online.setText("¿Îœﬂ");
		}
			
		return convertView;
	}
	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
}

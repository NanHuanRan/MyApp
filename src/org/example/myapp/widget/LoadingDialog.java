package org.example.myapp.widget;

import org.example.myapp.R;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

/**
 * 加载对话框控�?
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class LoadingDialog extends Dialog {

	private Context mContext;
	private LayoutInflater inflater;
	private LayoutParams lp;
	private TextView loadtext;

	public LoadingDialog(Context context) {
		super(context, R.style.Dialog);
		
		this.mContext = context;
		
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.loadingdialog, null);
		loadtext = (TextView) layout.findViewById(R.id.loading_text);
		setContentView(layout);
		
		// 设置window属�??
		lp = getWindow().getAttributes();
		lp.gravity = Gravity.CENTER;
		lp.dimAmount = 0; // 去背景遮�?
		lp.alpha = 1.0f;
		getWindow().setAttributes(lp);

	}

	public void setLoadText(String content){
		loadtext.setText(content);
	}
}
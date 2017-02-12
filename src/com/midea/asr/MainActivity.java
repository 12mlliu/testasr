package com.midea.asr;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import com.midea.asr.R;
import com.midea.asr.R.id;
import com.midea.asr.R.layout;

import com.midea.asr.Config;
import com.midea.asr.Decoder;
//import com.midea.asr.Hypothesis;


public class MainActivity extends Activity implements OnClickListener {

	private Button mButtonStart = null;
	private Button mButtonStop = null;

	static {
		System.loadLibrary("pocketsphinx_jni");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mButtonStart = (Button) this.findViewById(R.id.btn_start);
		mButtonStop = (Button) this.findViewById(R.id.btn_stop);
		mButtonStart.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_start:
			onStartRecord();
			break;
		default:
			break;
		}
	}
	private void onStartRecord() {
		Config c = Decoder.defaultConfig();
        c.setString("-hmm", "/mnt/sdcard/edu.cmu.pocketsphinx/hmm/zh_CN/tdt_sc_8k");
        c.setString("-lm", "/mnt/sdcard/edu.cmu.pocketsphinx/hmm/zh_CN/ask.lm.DMP");
        c.setString("-dict", "/mnt/sdcard/edu.cmu.pocketsphinx/hmm/zh_CN/ask.dic");
        c.setFloat("-samprate",8000);
        
        Decoder d = new Decoder(c);
		

        @SuppressWarnings("resource")
		FileInputStream ais = null;
		try {
			ais = new FileInputStream(new File("/mnt/sdcard/midea/test.raw"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Decoder d = new Decoder();
        d.startUtt();
        //d.setRawdataSize(300000);
        byte[] b = new byte[4096];
        int nbytes;
        try {
			while ((nbytes = ais.read(b)) >= 0) {
			    ByteBuffer bb = ByteBuffer.wrap(b, 0, nbytes);
			    bb.order(ByteOrder.LITTLE_ENDIAN);
			    short[] s = new short[nbytes/2];
			    bb.asShortBuffer().get(s);
			    //System.out.println(s);
			    d.processRaw(s, nbytes/2, false, false);
			    //System.out.println(nbytes/2);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        d.endUtt();
        int thre = 40;
        String orderfilename = "/mnt/sdcard/edu.cmu.pocketsphinx/hmm/zh_CN/order.txt";
        System.out.println("+++INFO: " + d.segThreshold(thre,orderfilename)+ d.getscore(d.segThreshold(thre,orderfilename)));
	
	}
}

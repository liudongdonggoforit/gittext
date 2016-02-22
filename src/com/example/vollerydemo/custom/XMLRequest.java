package com.example.vollerydemo.custom;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.vollerydemo.utils.LogUtils;

/**
 * 
 * @类名称: XMLRequest
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 下午4:48:37
 */
public class XMLRequest extends Request<XmlPullParser> {
	private Listener<XmlPullParser> mListener;

	public XMLRequest(int method, String url, Listener<XmlPullParser> listener,
			ErrorListener elistener) {
		super(method, url, elistener);
		this.mListener = listener;
	}

	public XMLRequest(String url, Listener<XmlPullParser> listener,
			ErrorListener elistener) {
		this(Method.GET, url, listener, elistener);
	}

	@Override
	protected Response<XmlPullParser> parseNetworkResponse(
			NetworkResponse response) {
		try {
			String parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser newPullParser = factory.newPullParser();
			newPullParser.setInput(new StringReader(parsed));
			return Response.success(newPullParser,
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (XmlPullParserException e) {
			return Response.error(new ParseError(e));
		}

	}

	@Override
	protected void deliverResponse(XmlPullParser response) {
		mListener.onResponse(response);
	}

}

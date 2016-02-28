package com.geewaza.study.test.web.lucene;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by wangh on 2016/2/28.
 */
public class MySameTokenFilter extends TokenFilter{
	private CharTermAttribute cta = null;
	private PositionIncrementAttribute pia = null;
	private Stack<String> sames = new Stack<String>();
	private AttributeSource.State current = null;
	protected MySameTokenFilter(TokenStream input) {
		super(input);
		cta = this.addAttribute(CharTermAttribute.class);
		pia = this.addAttribute(PositionIncrementAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {

		if (sames.size() > 0) {
			String str = sames.pop();
			restoreState(current);
			cta.setEmpty();
			cta.append(str);
			pia.setPositionIncrement(0);
			return true;
		}
		if (!this.input.incrementToken()){
			return false;
		}

		if (getSameWords(cta.toString())) {
			current = captureState();
		}
		return true;
	}

	private boolean getSameWords(String name) {
		Map<String, String[]> maps = new HashMap<String, String[]>();
		maps.put("眼前", new String[]{"目前","面前"});
		maps.put("田野", new String[]{"田地","耕地"});
		String[] sws = maps.get(name);
		if (sws != null) {
			for (String str : sws ) {
				sames.push(str);
			}
			return true;
		}
		return false;
	}
}

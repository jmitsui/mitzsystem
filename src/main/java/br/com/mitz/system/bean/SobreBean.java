package br.com.mitz.system.bean;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class SobreBean {

	private String[] values;
	
	public SobreBean() {
		values = new String[]{"sobre.title", "sobre.built", 
				"sobre.site", "sobre.build"};
	}
	
	public String[] getValues() {
		return values;
	}
}

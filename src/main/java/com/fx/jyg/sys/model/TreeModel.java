package com.fx.jyg.sys.model;

import java.util.ArrayList;
import java.util.List;

public class TreeModel {
	 	private Integer id;

	    private String text;

	    private String icon;

	    private String label;

	    private String translate;

	    private String sref;

	    private Integer pid;

	    private Integer type;
	    
	    private List<TreeModel> submenu = new ArrayList<TreeModel>();

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getTranslate() {
			return translate;
		}

		public void setTranslate(String translate) {
			this.translate = translate;
		}

		public String getSref() {
			return sref;
		}

		public void setSref(String sref) {
			this.sref = sref;
		}

		public Integer getPid() {
			return pid;
		}

		public void setPid(Integer pid) {
			this.pid = pid;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public List<TreeModel> getSubmenu() {
			return submenu;
		}

		public void setSubmenu(List<TreeModel> submenu) {
			this.submenu = submenu;
		}
	    
	    
}

package com.xiakee.domain.analy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighchartsColumnJson {
	private static final int[] margin = {50, 50, 100, 80};
	private Map<String, Object> chart = new HashMap<String, Object>(){
		{
			put("type", "column");
			put("margin", margin);
		}
	};
	private List<String> colors;
	private Map<String, Boolean> credits = new HashMap<String, Boolean>(){
		{
		      put("enabled", false); 
		}
	};
	private Map<String, String> title;
	private Map<String, String> subtitle;
	private List<Series> series;
	private Xaxis xAxis;
	private Yaxis yAxis;	
	
	public HighchartsColumnJson(){
	}
	
	public HighchartsColumnJson(final String title){
		this.title = new HashMap<String, String>(){
			{
				put("text", title);
			}
		};
	}
	
	public HighchartsColumnJson(final String title,final String subtitle,final String yAxis){
		this.title = new HashMap<String, String>(){
			{
				put("text", title);
			}
		};
		this.subtitle = new HashMap<String, String>(){
			{
				put("text", subtitle);
			}
		};
		this.yAxis = new Yaxis(yAxis);
	}
	

	public Map<String, String> getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Map<String, String> subtitle) {
		this.subtitle = subtitle;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public Map<String, Boolean> getCredits() {
		return credits;
	}

	public void setCredits(Map<String, Boolean> credits) {
		this.credits = credits;
	}

	public Map<String, String> getTitle() {
		return title;
	}

	public void setTitle(Map<String, String> title) {
		this.title = title;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public Xaxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(Xaxis xAxis) {
		this.xAxis = xAxis;
	}

	public Map<String, Object> getChart() {
		return chart;
	}

	public void setChart(Map<String, Object> chart) {
		this.chart = chart;
	}

	public Yaxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(Yaxis yAxis) {
		this.yAxis = yAxis;
	}

	public static int[] getMargin() {
		return margin;
	}

	public static class Series{
		private String name;
		private List<DataInfo> data;
		private DataLabels dataLabels = new DataLabels();
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<DataInfo> getData() {
			return data;
		}
		public void setData(List<DataInfo> data) {
			this.data = data;
		}
		public DataLabels getDataLabels() {
			return dataLabels;
		}
		public void setDataLabels(DataLabels dataLabels) {
			this.dataLabels = dataLabels;
		}
		
	}
	
	public static class DataInfo{
		private String name;
		private String color;
		private int y;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	
	public static class DataLabels{
		private Boolean enabled = true;
		private Integer rotation = -90;
		private String color = "#FFFFFF";
		private String align = "right";
		private int x = 4;
		private int y = 10;	
		private DataLabelType style = new DataLabelType();
		public Boolean getEnabled() {
			return enabled;
		}
		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}
		public Integer getRotation() {
			return rotation;
		}
		public void setRotation(Integer rotation) {
			this.rotation = rotation;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getAlign() {
			return align;
		}
		public void setAlign(String align) {
			this.align = align;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public DataLabelType getStyle() {
			return style;
		}
		public void setStyle(DataLabelType style) {
			this.style = style;
		}
		
	}
	
	public static class DataLabelType{
		private String fontSize = "13px";
		private String fontFamily = "Verdana, sans-serif";
		private String textShadow = "0 0 3px black";
		public String getFontSize() {
			return fontSize;
		}
		public void setFontSize(String fontSize) {
			this.fontSize = fontSize;
		}
		public String getFontFamily() {
			return fontFamily;
		}
		public void setFontFamily(String fontFamily) {
			this.fontFamily = fontFamily;
		}
		public String getTextShadow() {
			return textShadow;
		}
		public void setTextShadow(String textShadow) {
			this.textShadow = textShadow;
		}
		
	}
	
	public static class Yaxis{
		private int min = 0;
		private Map<String, String> title = new HashMap<String, String>(){
			{
			      put("text", "Population (millions)"); 
			}
		};
		
		public Yaxis() {
			super();
		}
		
		public Yaxis(final String title) {
			this.title = new HashMap<String, String>(){
				{
				      put("text", title); 
				}
			};
		}
		
		public int getMin() {
			return min;
		}
		public void setMin(int min) {
			this.min = min;
		}
		public Map<String, String> getTitle() {
			return title;
		}
		public void setTitle(Map<String, String> title) {
			this.title = title;
		}
		
	}
	
	public static class Xaxis{
		private List<String> categories;
		private XaxisLabels labels = new XaxisLabels();
		
		public Xaxis() {
			super();
		}
		public Xaxis(List<String> categories) {
			super();
			this.categories = categories;
		}
		public List<String> getCategories() {
			return categories;
		}
		public void setCategories(List<String> categories) {
			this.categories = categories;
		}
		public XaxisLabels getLabels() {
			return labels;
		}
		public void setLabels(XaxisLabels labels) {
			this.labels = labels;
		}
	}
	
	public static class XaxisLabels{
		private int rotation = -45;
		private String align = "right";
		private Map<String, String> style = new HashMap<String, String>(){
			{
			      put("fontSize", "13px"); 
			      put("fontFamily", "Verdana, sans-serif"); 
			}
		};
		public int getRotation() {
			return rotation;
		}
		public void setRotation(int rotation) {
			this.rotation = rotation;
		}
		public String getAlign() {
			return align;
		}
		public void setAlign(String align) {
			this.align = align;
		}
		public Map<String, String> getStyle() {
			return style;
		}
		public void setStyle(Map<String, String> style) {
			this.style = style;
		}
		
	}
}

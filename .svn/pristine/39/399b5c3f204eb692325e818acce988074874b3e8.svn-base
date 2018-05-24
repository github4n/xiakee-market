package com.xiakee.domain.analy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.NewConstructorTypeMunger;

public class HighchartsJson {
	private Map<String, String> chart;
	private List<String> colors;
	private Map<String, Boolean> credits = new HashMap<String, Boolean>(){
		{
		      put("enabled", false); 
		}
	};
	private Map<String, String> title;
	private Map<String, String> subtitle;
	private List<Series> series;
	private Map<String, List<String>> xAxis;
	private Map<String, Map<String, String>> yAxis;	
	private PlotOptions plotOptions = new PlotOptions();
	
	public HighchartsJson(){
	}
	
	public HighchartsJson(final String title,final String chart){
		this.chart = new HashMap<String, String>(){
			{
				put("type", chart);
//				put("backgroundColor", "#6D6D6D");
			}
		};
		this.title = new HashMap<String, String>(){
			{
				put("text", title);
			}
		};
	}
	
	public HighchartsJson(final String title,final String subtitle,final String chart,final String yAxis){
		this.chart = new HashMap<String, String>(){
			{
				put("type", chart);
//				put("backgroundColor", "#6D6D6D");
			}
		};
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
		this.yAxis = new HashMap<String, Map<String, String>>(){
			{
				put("title", new HashMap<String, String>(){
					{
						put("text", yAxis);
					}
				});
			}
		};
	}
	
	
	public PlotOptions getPlotOptions() {
		return plotOptions;
	}

	public void setPlotOptions(PlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}

	public Map<String, String> getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Map<String, String> subtitle) {
		this.subtitle = subtitle;
	}

	public Map<String, String> getChart() {
		return chart;
	}

	public void setChart(Map<String, String> chart) {
		this.chart = chart;
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


	public Map<String, List<String>> getxAxis() {
		return xAxis;
	}

	public void setxAxis(Map<String, List<String>> xAxis) {
		this.xAxis = xAxis;
	}

	public Map<String, Map<String, String>> getyAxis() {
		return yAxis;
	}

	public void setyAxis(Map<String, Map<String, String>> yAxis) {
		this.yAxis = yAxis;
	}

	public static class Series{
		private String name;
		private List<DataInfo> data;
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
	
	public static class PlotOptions{
		private OptionLine line = new OptionLine();
		private OptionPie pie = new OptionPie();

		public OptionLine getLine() {
			return line;
		}

		public void setLine(OptionLine line) {
			this.line = line;
		}

		public OptionPie getPie() {
			return pie;
		}

		public void setPie(OptionPie pie) {
			this.pie = pie;
		}
		
	}
	
	public static class OptionLine{
		private Map<String, Boolean> dataLabels = new HashMap<String, Boolean>(){
			{
			      put("enabled", true); 
			}
		};
		private Boolean enableMouseTracking = false;
		public Map<String, Boolean> getDataLabels() {
			return dataLabels;
		}
		public void setDataLabels(Map<String, Boolean> dataLabels) {
			this.dataLabels = dataLabels;
		}
		public Boolean getEnableMouseTracking() {
			return enableMouseTracking;
		}
		public void setEnableMouseTracking(Boolean enableMouseTracking) {
			this.enableMouseTracking = enableMouseTracking;
		}
	}
	
	public static class OptionPie{
		private Boolean allowPointSelect = true;
		private String cursor = "pointer";
		private DataLabels dataLabels = new DataLabels();
		public Boolean getAllowPointSelect() {
			return allowPointSelect;
		}
		public void setAllowPointSelect(Boolean allowPointSelect) {
			this.allowPointSelect = allowPointSelect;
		}
		public String getCursor() {
			return cursor;
		}
		public void setCursor(String cursor) {
			this.cursor = cursor;
		}
		public DataLabels getDataLabels() {
			return dataLabels;
		}
		public void setDataLabels(DataLabels dataLabels) {
			this.dataLabels = dataLabels;
		}
		
	}
	
	public static class DataLabels{
		private Boolean enabled = true;
		private String format = "<b>{point.name}</b>: {point.percentage:.1f} %";
		public Boolean getEnabled() {
			return enabled;
		}
		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}
		
	}
}

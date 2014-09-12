package com.realty.base.action;

public class Mapconvert {
	private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
	double lngitude;
	double latitude; 	
	
	public double getLng() {
		return lngitude;
	}
	public void setLng(double lng) {
		this.lngitude = lng;
	}
	public double getLat() {
		return latitude;
	}
	public void setLat(double lat) {
		this.latitude= lat;
	}

	/// �й���������ϵGCJ02Э������꣬ת�� �ٶȵ�ͼ��Ӧ�� BD09 Э������
	public void Convert_GCJ02_To_BD09(double lat,double lng){
	    double x = lng, y = lat;
	    double z =Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
	    lng = z * Math.cos(theta) + 0.0065;
	    lat = z * Math.sin(theta) + 0.006;
	    lngitude = lng;
	    latitude = lat;
	}

	/// �ٶȵ�ͼ��Ӧ�� BD09 Э�����꣬ת���й���������ϵGCJ02Э�������
	/// <param name="lat">ά��</param>
	/// <param name="lng">����</param>
	public void Convert_BD09_To_GCJ02(double lat, double lng){
	    double x = lng - 0.0065, y = lat - 0.006;
	    double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
	    double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
	    lng = z * Math.cos(theta);
	    lat = z * Math.sin(theta);
	    lngitude = lng;
	    latitude = lat;
	 } 
}

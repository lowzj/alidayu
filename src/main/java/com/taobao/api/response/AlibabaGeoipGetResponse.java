package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: alibaba.geoip.get response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class AlibabaGeoipGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 8848857929672626384L;

	/** 
	 * 城市名称
	 */
	@ApiField("city")
	private String city;

	/** 
	 * 城市id
	 */
	@ApiField("cityid")
	private String cityid;

	/** 
	 * 国家
	 */
	@ApiField("country")
	private String country;

	/** 
	 * 国家编码
	 */
	@ApiField("countryid")
	private String countryid;

	/** 
	 * 县
	 */
	@ApiField("county")
	private String county;

	/** 
	 * 县ID
	 */
	@ApiField("countyid")
	private String countyid;

	/** 
	 * IP地址字符串
	 */
	@ApiField("ip")
	private String ip;

	/** 
	 * 运营商名称
	 */
	@ApiField("isp")
	private String isp;

	/** 
	 * 运营商id
	 */
	@ApiField("ispid")
	private String ispid;

	/** 
	 * 语言
	 */
	@ApiField("lang")
	private String lang;

	/** 
	 * 经度
	 */
	@ApiField("latitude")
	private String latitude;

	/** 
	 * IP地址的整数值
	 */
	@ApiField("lip")
	private Long lip;

	/** 
	 * 纬度
	 */
	@ApiField("longitude")
	private String longitude;

	/** 
	 * 地区名称
	 */
	@ApiField("region")
	private String region;

	/** 
	 * 地区编码
	 */
	@ApiField("regionid")
	private String regionid;

	public void setCity(String city) {
		this.city = city;
	}
	public String getCity( ) {
		return this.city;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCityid( ) {
		return this.cityid;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry( ) {
		return this.country;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getCountryid( ) {
		return this.countryid;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	public String getCounty( ) {
		return this.county;
	}

	public void setCountyid(String countyid) {
		this.countyid = countyid;
	}
	public String getCountyid( ) {
		return this.countyid;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp( ) {
		return this.ip;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getIsp( ) {
		return this.isp;
	}

	public void setIspid(String ispid) {
		this.ispid = ispid;
	}
	public String getIspid( ) {
		return this.ispid;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getLang( ) {
		return this.lang;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLatitude( ) {
		return this.latitude;
	}

	public void setLip(Long lip) {
		this.lip = lip;
	}
	public Long getLip( ) {
		return this.lip;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLongitude( ) {
		return this.longitude;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegion( ) {
		return this.region;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getRegionid( ) {
		return this.regionid;
	}
	


}

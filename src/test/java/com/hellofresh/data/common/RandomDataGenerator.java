package com.hellofresh.data.common;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;


public class RandomDataGenerator {
	
	Random r= new Random();
	
	String timestamp = String.valueOf(new Date().getTime());
	

    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStrcity() {
		return strcity;
	}
	public void setStrcity(String strcity) {
		this.strcity = strcity;
	}
	public String getStrcompany() {
		return strcompany;
	}
	public void setStrcompany(String strcompany) {
		this.strcompany = strcompany;
	}
	public String getStraddress1() {
		return straddress1;
	}
	public void setStraddress1(String straddress1) {
		this.straddress1 = straddress1;
	}
	public String getStraddress2() {
		return straddress2;
	}
	public void setStraddress2(String straddress2) {
		this.straddress2 = straddress2;
	}
	public String getStrpostcode() {
		return strpostcode;
	}
	public void setStrpostcode(String strpostcode) {
		this.strpostcode = strpostcode;
	}
	public String getStrother() {
		return strother;
	}
	public void setStrother(String strother) {
		this.strother = strother;
	}
	public String getStrphone() {
		return strphone;
	}
	public void setStrphone(String strphone) {
		this.strphone = strphone;
	}
	public String getStrphonemobile() {
		return strphonemobile;
	}
	public void setStrphonemobile(String strphonemobile) {
		this.strphonemobile = strphonemobile;
	}
	public String getStralias() {
		return stralias;
	}
	public void setStralias(String stralias) {
		this.stralias = stralias;
	}
	String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
    String name = RandomStringUtils.random(10, true, false);
    String surname = RandomStringUtils.random(10, true, false);
    String strcity = "Qwerty"+r.nextInt();
    String strcompany = "Company"+r.nextInt();
    String straddress1 = "address1"+r.nextInt();
    String straddress2 = "address2"+r.nextInt();
    String strpostcode = "12345";
    String strother = "other"+r.nextInt();
    String  strphone= "0"+r.nextInt();
    String strphonemobile= "0"+r.nextInt();;
    String stralias = "alias" + r.nextInt();

}

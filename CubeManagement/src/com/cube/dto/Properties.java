package com.cube.dto;

public class Properties {
private String bank;
private int merchantid;
private double value;
private String mode;
private String text;
public Properties(){}
public Properties(String bank, int merchantid, double value, String mode) {
	this.bank = bank;
	this.merchantid = merchantid;
	this.value = value;
	this.mode = mode;
}
public Properties(String text)
{
	this.text=text;
	}
public String getBank() {
	return bank;
}
public void setBank(String bank) {
	this.bank = bank;
}
public int getMerchantid() {
	return merchantid;
}
public void setMerchantid(int merchantid) {
	this.merchantid = merchantid;
}
public double getValue() {
	return value;
}
public void setValue(double value) {
	this.value = value;
}
public String getMode() {
	return mode;
}
public void setMode(String mode) {
	this.mode = mode;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
@Override
public String toString()
{
	if(bank != null)
return "Properties [bank=" + bank + ", merchantid=" + merchantid + ", value=" + value
+ ", mode=" + mode + "]";
	else
		return "Properties [text= "+ text+"]";
}
}

package com.pk.db.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
//출력할 태그와 순서를 설정
@XmlType(name="", 
propOrder= {"itemid", "itemname", "price", "description", "pictureurl"})
public class Item {
	private int itemid;
	private String itemname;
	private int price;
	private String description;
	private String pictureurl;	
}

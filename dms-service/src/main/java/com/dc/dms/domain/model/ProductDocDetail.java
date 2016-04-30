package com.dc.dms.domain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDocDetail {

	ProductDocConfiguration docConf;
	ProductDocument document;

	public ProductDocConfiguration getDocConf() {
		return docConf;
	}

	public void setDocConf(ProductDocConfiguration docConf) {
		this.docConf = docConf;
	}

	public ProductDocument getDocument() {
		return document;
	}

	public void setDocument(ProductDocument document) {
		this.document = document;
	}
}

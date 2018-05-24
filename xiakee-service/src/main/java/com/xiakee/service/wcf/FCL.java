
package com.xiakee.service.wcf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyRecordByJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boxesByJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyThingsByJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boxServiceChargeTermByJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buyRecordServiceChargeTermByJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userName",
    "buyRecordByJson",
    "boxesByJson",
    "buyThingsByJson",
    "boxServiceChargeTermByJson",
    "buyRecordServiceChargeTermByJson"
})
@XmlRootElement(name = "FCL")
public class FCL {

    @XmlElementRef(name = "userName", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> userName;
    @XmlElementRef(name = "buyRecordByJson", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> buyRecordByJson;
    @XmlElementRef(name = "boxesByJson", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> boxesByJson;
    @XmlElementRef(name = "buyThingsByJson", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> buyThingsByJson;
    @XmlElementRef(name = "boxServiceChargeTermByJson", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> boxServiceChargeTermByJson;
    @XmlElementRef(name = "buyRecordServiceChargeTermByJson", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> buyRecordServiceChargeTermByJson;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUserName(JAXBElement<String> value) {
        this.userName = value;
    }

    /**
     * Gets the value of the buyRecordByJson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBuyRecordByJson() {
        return buyRecordByJson;
    }

    /**
     * Sets the value of the buyRecordByJson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBuyRecordByJson(JAXBElement<String> value) {
        this.buyRecordByJson = value;
    }

    /**
     * Gets the value of the boxesByJson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBoxesByJson() {
        return boxesByJson;
    }

    /**
     * Sets the value of the boxesByJson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBoxesByJson(JAXBElement<String> value) {
        this.boxesByJson = value;
    }

    /**
     * Gets the value of the buyThingsByJson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBuyThingsByJson() {
        return buyThingsByJson;
    }

    /**
     * Sets the value of the buyThingsByJson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBuyThingsByJson(JAXBElement<String> value) {
        this.buyThingsByJson = value;
    }

    /**
     * Gets the value of the boxServiceChargeTermByJson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBoxServiceChargeTermByJson() {
        return boxServiceChargeTermByJson;
    }

    /**
     * Sets the value of the boxServiceChargeTermByJson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBoxServiceChargeTermByJson(JAXBElement<String> value) {
        this.boxServiceChargeTermByJson = value;
    }

    /**
     * Gets the value of the buyRecordServiceChargeTermByJson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBuyRecordServiceChargeTermByJson() {
        return buyRecordServiceChargeTermByJson;
    }

    /**
     * Sets the value of the buyRecordServiceChargeTermByJson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBuyRecordServiceChargeTermByJson(JAXBElement<String> value) {
        this.buyRecordServiceChargeTermByJson = value;
    }

}

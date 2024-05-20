
package org.oorsprong.websamples;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tCountryInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>{@code
 * <complexType name="tCountryInfo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="sISOCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="sName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="sCapitalCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="sPhoneCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="sContinentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="sCurrencyISOCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="sCountryFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="Languages" type="{http://www.oorsprong.org/websamples.countryinfo}ArrayOftLanguage"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tCountryInfo", propOrder = {
    "sisoCode",
    "sName",
    "sCapitalCity",
    "sPhoneCode",
    "sContinentCode",
    "sCurrencyISOCode",
    "sCountryFlag",
    "languages"
})
public class TCountryInfo {

    @XmlElement(name = "sISOCode", required = true)
    protected String sisoCode;
    @XmlElement(required = true)
    protected String sName;
    @XmlElement(required = true)
    protected String sCapitalCity;
    @XmlElement(required = true)
    protected String sPhoneCode;
    @XmlElement(required = true)
    protected String sContinentCode;
    @XmlElement(required = true)
    protected String sCurrencyISOCode;
    @XmlElement(required = true)
    protected String sCountryFlag;
    @XmlElement(name = "Languages", required = true)
    protected ArrayOftLanguage languages;

    /**
     * Recupera il valore della proprietà sisoCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSISOCode() {
        return sisoCode;
    }

    /**
     * Imposta il valore della proprietà sisoCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSISOCode(String value) {
        this.sisoCode = value;
    }

    /**
     * Recupera il valore della proprietà sName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSName() {
        return sName;
    }

    /**
     * Imposta il valore della proprietà sName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSName(String value) {
        this.sName = value;
    }

    /**
     * Recupera il valore della proprietà sCapitalCity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCapitalCity() {
        return sCapitalCity;
    }

    /**
     * Imposta il valore della proprietà sCapitalCity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCapitalCity(String value) {
        this.sCapitalCity = value;
    }

    /**
     * Recupera il valore della proprietà sPhoneCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPhoneCode() {
        return sPhoneCode;
    }

    /**
     * Imposta il valore della proprietà sPhoneCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPhoneCode(String value) {
        this.sPhoneCode = value;
    }

    /**
     * Recupera il valore della proprietà sContinentCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSContinentCode() {
        return sContinentCode;
    }

    /**
     * Imposta il valore della proprietà sContinentCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSContinentCode(String value) {
        this.sContinentCode = value;
    }

    /**
     * Recupera il valore della proprietà sCurrencyISOCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCurrencyISOCode() {
        return sCurrencyISOCode;
    }

    /**
     * Imposta il valore della proprietà sCurrencyISOCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCurrencyISOCode(String value) {
        this.sCurrencyISOCode = value;
    }

    /**
     * Recupera il valore della proprietà sCountryFlag.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCountryFlag() {
        return sCountryFlag;
    }

    /**
     * Imposta il valore della proprietà sCountryFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCountryFlag(String value) {
        this.sCountryFlag = value;
    }

    /**
     * Recupera il valore della proprietà languages.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOftLanguage }
     *     
     */
    public ArrayOftLanguage getLanguages() {
        return languages;
    }

    /**
     * Imposta il valore della proprietà languages.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOftLanguage }
     *     
     */
    public void setLanguages(ArrayOftLanguage value) {
        this.languages = value;
    }

}

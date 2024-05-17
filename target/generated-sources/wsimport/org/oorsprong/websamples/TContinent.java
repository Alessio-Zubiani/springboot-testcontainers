
package org.oorsprong.websamples;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tContinent complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>{@code
 * <complexType name="tContinent">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="sCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="sName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tContinent", propOrder = {
    "sCode",
    "sName"
})
public class TContinent {

    @XmlElement(required = true)
    protected String sCode;
    @XmlElement(required = true)
    protected String sName;

    /**
     * Recupera il valore della proprietà sCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCode() {
        return sCode;
    }

    /**
     * Imposta il valore della proprietà sCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCode(String value) {
        this.sCode = value;
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

}

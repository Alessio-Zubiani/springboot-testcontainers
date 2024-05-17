
package org.oorsprong.websamples;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="ListOfCountryNamesGroupedByContinentResult" type="{http://www.oorsprong.org/websamples.countryinfo}ArrayOftCountryCodeAndNameGroupedByContinent"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listOfCountryNamesGroupedByContinentResult"
})
@XmlRootElement(name = "ListOfCountryNamesGroupedByContinentResponse")
public class ListOfCountryNamesGroupedByContinentResponse {

    @XmlElement(name = "ListOfCountryNamesGroupedByContinentResult", required = true)
    protected ArrayOftCountryCodeAndNameGroupedByContinent listOfCountryNamesGroupedByContinentResult;

    /**
     * Recupera il valore della proprietà listOfCountryNamesGroupedByContinentResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOftCountryCodeAndNameGroupedByContinent }
     *     
     */
    public ArrayOftCountryCodeAndNameGroupedByContinent getListOfCountryNamesGroupedByContinentResult() {
        return listOfCountryNamesGroupedByContinentResult;
    }

    /**
     * Imposta il valore della proprietà listOfCountryNamesGroupedByContinentResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOftCountryCodeAndNameGroupedByContinent }
     *     
     */
    public void setListOfCountryNamesGroupedByContinentResult(ArrayOftCountryCodeAndNameGroupedByContinent value) {
        this.listOfCountryNamesGroupedByContinentResult = value;
    }

}

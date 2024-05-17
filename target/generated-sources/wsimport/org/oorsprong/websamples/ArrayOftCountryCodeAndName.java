
package org.oorsprong.websamples;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArrayOftCountryCodeAndName complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>{@code
 * <complexType name="ArrayOftCountryCodeAndName">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="tCountryCodeAndName" type="{http://www.oorsprong.org/websamples.countryinfo}tCountryCodeAndName" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOftCountryCodeAndName", propOrder = {
    "tCountryCodeAndName"
})
public class ArrayOftCountryCodeAndName {

    @XmlElement(nillable = true)
    protected List<TCountryCodeAndName> tCountryCodeAndName;

    /**
     * Gets the value of the tCountryCodeAndName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the tCountryCodeAndName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTCountryCodeAndName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCountryCodeAndName }
     * 
     * 
     * @return
     *     The value of the tCountryCodeAndName property.
     */
    public List<TCountryCodeAndName> getTCountryCodeAndName() {
        if (tCountryCodeAndName == null) {
            tCountryCodeAndName = new ArrayList<>();
        }
        return this.tCountryCodeAndName;
    }

    public void setTCountryCodeAndName(List<TCountryCodeAndName> value) {
        this.tCountryCodeAndName = null;
        if (value!= null) {
            List<TCountryCodeAndName> draftl = this.getTCountryCodeAndName();
            draftl.addAll(value);
        }
    }

}

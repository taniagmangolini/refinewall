//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementa��o de Refer�ncia (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modifica��es neste arquivo ser�o perdidas ap�s a recompila��o do esquema de origem. 
// Gerado em: 2018.09.04 �s 10:42:06 PM BRT 
//


package br.com.usp.lafieco.bean.org.uniprot.uniprot;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes the subcellular location and optionally the topology and orientation of a molecule.
 * 
 * <p>Classe Java de subcellularLocationType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="subcellularLocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded"/>
 *         &lt;element name="topology" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orientation" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subcellularLocationType", propOrder = {
    "location",
    "topology",
    "orientation"
})
public class SubcellularLocationType {

    @XmlElement(required = true)
    protected List<EvidencedStringType> location;
    protected List<EvidencedStringType> topology;
    protected List<EvidencedStringType> orientation;

    /**
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidencedStringType }
     * 
     * 
     */
    public List<EvidencedStringType> getLocation() {
        if (location == null) {
            location = new ArrayList<EvidencedStringType>();
        }
        return this.location;
    }

    /**
     * Gets the value of the topology property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topology property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopology().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidencedStringType }
     * 
     * 
     */
    public List<EvidencedStringType> getTopology() {
        if (topology == null) {
            topology = new ArrayList<EvidencedStringType>();
        }
        return this.topology;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orientation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrientation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidencedStringType }
     * 
     * 
     */
    public List<EvidencedStringType> getOrientation() {
        if (orientation == null) {
            orientation = new ArrayList<EvidencedStringType>();
        }
        return this.orientation;
    }

}

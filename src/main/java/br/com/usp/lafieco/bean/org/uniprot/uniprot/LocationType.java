//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementa��o de Refer�ncia (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modifica��es neste arquivo ser�o perdidas ap�s a recompila��o do esquema de origem. 
// Gerado em: 2018.09.04 �s 10:42:06 PM BRT 
//


package br.com.usp.lafieco.bean.org.uniprot.uniprot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes a sequence location as either a range with a begin and end or as a position. The 'sequence' attribute is only used when the location is not on the canonical sequence displayed in the current entry.
 * 
 * <p>Classe Java de locationType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="locationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="begin" type="{http://uniprot.org/uniprot}positionType"/>
 *           &lt;element name="end" type="{http://uniprot.org/uniprot}positionType"/>
 *         &lt;/sequence>
 *         &lt;element name="position" type="{http://uniprot.org/uniprot}positionType"/>
 *       &lt;/choice>
 *       &lt;attribute name="sequence" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locationType", propOrder = {
    "begin",
    "end",
    "position"
})
public class LocationType {

    protected PositionType begin;
    protected PositionType end;
    protected PositionType position;
    @XmlAttribute(name = "sequence")
    protected String sequence;

    /**
     * Obt�m o valor da propriedade begin.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getBegin() {
        return begin;
    }

    /**
     * Define o valor da propriedade begin.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setBegin(PositionType value) {
        this.begin = value;
    }

    /**
     * Obt�m o valor da propriedade end.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getEnd() {
        return end;
    }

    /**
     * Define o valor da propriedade end.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setEnd(PositionType value) {
        this.end = value;
    }

    /**
     * Obt�m o valor da propriedade position.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getPosition() {
        return position;
    }

    /**
     * Define o valor da propriedade position.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setPosition(PositionType value) {
        this.position = value;
    }

    /**
     * Obt�m o valor da propriedade sequence.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * Define o valor da propriedade sequence.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequence(String value) {
        this.sequence = value;
    }

}

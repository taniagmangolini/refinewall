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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java de sequenceType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="sequenceType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="length" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="mass" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="checksum" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="modified" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="precursor" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="fragment">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="single"/>
 *             &lt;enumeration value="multiple"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sequenceType", propOrder = {
    "value"
})
public class SequenceType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "length", required = true)
    protected int length;
    @XmlAttribute(name = "mass", required = true)
    protected int mass;
    @XmlAttribute(name = "checksum", required = true)
    protected String checksum;
    @XmlAttribute(name = "modified", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar modified;
    @XmlAttribute(name = "version", required = true)
    protected int version;
    @XmlAttribute(name = "precursor")
    protected Boolean precursor;
    @XmlAttribute(name = "fragment")
    protected String fragment;

    /**
     * Obt�m o valor da propriedade value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Define o valor da propriedade value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obt�m o valor da propriedade length.
     * 
     */
    public int getLength() {
        return length;
    }

    /**
     * Define o valor da propriedade length.
     * 
     */
    public void setLength(int value) {
        this.length = value;
    }

    /**
     * Obt�m o valor da propriedade mass.
     * 
     */
    public int getMass() {
        return mass;
    }

    /**
     * Define o valor da propriedade mass.
     * 
     */
    public void setMass(int value) {
        this.mass = value;
    }

    /**
     * Obt�m o valor da propriedade checksum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Define o valor da propriedade checksum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChecksum(String value) {
        this.checksum = value;
    }

    /**
     * Obt�m o valor da propriedade modified.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModified() {
        return modified;
    }

    /**
     * Define o valor da propriedade modified.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModified(XMLGregorianCalendar value) {
        this.modified = value;
    }

    /**
     * Obt�m o valor da propriedade version.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Define o valor da propriedade version.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

    /**
     * Obt�m o valor da propriedade precursor.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrecursor() {
        return precursor;
    }

    /**
     * Define o valor da propriedade precursor.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrecursor(Boolean value) {
        this.precursor = value;
    }

    /**
     * Obt�m o valor da propriedade fragment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFragment() {
        return fragment;
    }

    /**
     * Define o valor da propriedade fragment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFragment(String value) {
        this.fragment = value;
    }

}

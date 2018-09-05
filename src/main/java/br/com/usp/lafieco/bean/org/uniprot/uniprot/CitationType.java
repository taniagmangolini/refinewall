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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes different types of citations.
 *             Equivalent to the flat file RX-, RG-, RA-, RT- and RL-lines.
 * 
 * <p>Classe Java de citationType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="citationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editorList" type="{http://uniprot.org/uniprot}nameListType" minOccurs="0"/>
 *         &lt;element name="authorList" type="{http://uniprot.org/uniprot}nameListType" minOccurs="0"/>
 *         &lt;element name="locator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbReference" type="{http://uniprot.org/uniprot}dbReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="book"/>
 *             &lt;enumeration value="journal article"/>
 *             &lt;enumeration value="online journal article"/>
 *             &lt;enumeration value="patent"/>
 *             &lt;enumeration value="submission"/>
 *             &lt;enumeration value="thesis"/>
 *             &lt;enumeration value="unpublished observations"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="date">
 *         &lt;simpleType>
 *           &lt;union memberTypes=" {http://www.w3.org/2001/XMLSchema}date {http://www.w3.org/2001/XMLSchema}gYearMonth {http://www.w3.org/2001/XMLSchema}gYear">
 *           &lt;/union>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="volume" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="first" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="last" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="publisher" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="city" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="db" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="number" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="institute" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="country" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "citationType", propOrder = {
    "title",
    "editorList",
    "authorList",
    "locator",
    "dbReference"
})
public class CitationType {

    protected String title;
    protected NameListType editorList;
    protected NameListType authorList;
    protected String locator;
    protected List<DbReferenceType> dbReference;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "date")
    protected String date;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "volume")
    protected String volume;
    @XmlAttribute(name = "first")
    protected String first;
    @XmlAttribute(name = "last")
    protected String last;
    @XmlAttribute(name = "publisher")
    protected String publisher;
    @XmlAttribute(name = "city")
    protected String city;
    @XmlAttribute(name = "db")
    protected String db;
    @XmlAttribute(name = "number")
    protected String number;
    @XmlAttribute(name = "institute")
    protected String institute;
    @XmlAttribute(name = "country")
    protected String country;

    /**
     * Obt�m o valor da propriedade title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Define o valor da propriedade title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Obt�m o valor da propriedade editorList.
     * 
     * @return
     *     possible object is
     *     {@link NameListType }
     *     
     */
    public NameListType getEditorList() {
        return editorList;
    }

    /**
     * Define o valor da propriedade editorList.
     * 
     * @param value
     *     allowed object is
     *     {@link NameListType }
     *     
     */
    public void setEditorList(NameListType value) {
        this.editorList = value;
    }

    /**
     * Obt�m o valor da propriedade authorList.
     * 
     * @return
     *     possible object is
     *     {@link NameListType }
     *     
     */
    public NameListType getAuthorList() {
        return authorList;
    }

    /**
     * Define o valor da propriedade authorList.
     * 
     * @param value
     *     allowed object is
     *     {@link NameListType }
     *     
     */
    public void setAuthorList(NameListType value) {
        this.authorList = value;
    }

    /**
     * Obt�m o valor da propriedade locator.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocator() {
        return locator;
    }

    /**
     * Define o valor da propriedade locator.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocator(String value) {
        this.locator = value;
    }

    /**
     * Gets the value of the dbReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dbReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDbReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DbReferenceType }
     * 
     * 
     */
    public List<DbReferenceType> getDbReference() {
        if (dbReference == null) {
            dbReference = new ArrayList<DbReferenceType>();
        }
        return this.dbReference;
    }

    /**
     * Obt�m o valor da propriedade type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Define o valor da propriedade type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obt�m o valor da propriedade date.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Define o valor da propriedade date.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Obt�m o valor da propriedade name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define o valor da propriedade name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obt�m o valor da propriedade volume.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Define o valor da propriedade volume.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolume(String value) {
        this.volume = value;
    }

    /**
     * Obt�m o valor da propriedade first.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirst() {
        return first;
    }

    /**
     * Define o valor da propriedade first.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirst(String value) {
        this.first = value;
    }

    /**
     * Obt�m o valor da propriedade last.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLast() {
        return last;
    }

    /**
     * Define o valor da propriedade last.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLast(String value) {
        this.last = value;
    }

    /**
     * Obt�m o valor da propriedade publisher.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Define o valor da propriedade publisher.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisher(String value) {
        this.publisher = value;
    }

    /**
     * Obt�m o valor da propriedade city.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Define o valor da propriedade city.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Obt�m o valor da propriedade db.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDb() {
        return db;
    }

    /**
     * Define o valor da propriedade db.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDb(String value) {
        this.db = value;
    }

    /**
     * Obt�m o valor da propriedade number.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Define o valor da propriedade number.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Obt�m o valor da propriedade institute.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitute() {
        return institute;
    }

    /**
     * Define o valor da propriedade institute.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitute(String value) {
        this.institute = value;
    }

    /**
     * Obt�m o valor da propriedade country.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Define o valor da propriedade country.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

}

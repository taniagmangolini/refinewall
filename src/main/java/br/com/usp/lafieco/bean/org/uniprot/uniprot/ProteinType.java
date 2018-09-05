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
 * Describes the names for the protein and parts thereof.
 *             Equivalent to the flat file DE-line.
 * 
 * <p>Classe Java de proteinType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="proteinType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
 *         &lt;element name="domain" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="component" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proteinType", propOrder = {
    "recommendedName",
    "alternativeName",
    "submittedName",
    "allergenName",
    "biotechName",
    "cdAntigenName",
    "innName",
    "domain",
    "component"
})
public class ProteinType {

    protected ProteinType.RecommendedName recommendedName;
    protected List<ProteinType.AlternativeName> alternativeName;
    protected List<ProteinType.SubmittedName> submittedName;
    protected EvidencedStringType allergenName;
    protected EvidencedStringType biotechName;
    protected List<EvidencedStringType> cdAntigenName;
    protected List<EvidencedStringType> innName;
    protected List<ProteinType.Domain> domain;
    protected List<ProteinType.Component> component;

    /**
     * Obt�m o valor da propriedade recommendedName.
     * 
     * @return
     *     possible object is
     *     {@link ProteinType.RecommendedName }
     *     
     */
    public ProteinType.RecommendedName getRecommendedName() {
        return recommendedName;
    }

    /**
     * Define o valor da propriedade recommendedName.
     * 
     * @param value
     *     allowed object is
     *     {@link ProteinType.RecommendedName }
     *     
     */
    public void setRecommendedName(ProteinType.RecommendedName value) {
        this.recommendedName = value;
    }

    /**
     * Gets the value of the alternativeName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternativeName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternativeName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProteinType.AlternativeName }
     * 
     * 
     */
    public List<ProteinType.AlternativeName> getAlternativeName() {
        if (alternativeName == null) {
            alternativeName = new ArrayList<ProteinType.AlternativeName>();
        }
        return this.alternativeName;
    }

    /**
     * Gets the value of the submittedName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the submittedName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubmittedName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProteinType.SubmittedName }
     * 
     * 
     */
    public List<ProteinType.SubmittedName> getSubmittedName() {
        if (submittedName == null) {
            submittedName = new ArrayList<ProteinType.SubmittedName>();
        }
        return this.submittedName;
    }

    /**
     * Obt�m o valor da propriedade allergenName.
     * 
     * @return
     *     possible object is
     *     {@link EvidencedStringType }
     *     
     */
    public EvidencedStringType getAllergenName() {
        return allergenName;
    }

    /**
     * Define o valor da propriedade allergenName.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidencedStringType }
     *     
     */
    public void setAllergenName(EvidencedStringType value) {
        this.allergenName = value;
    }

    /**
     * Obt�m o valor da propriedade biotechName.
     * 
     * @return
     *     possible object is
     *     {@link EvidencedStringType }
     *     
     */
    public EvidencedStringType getBiotechName() {
        return biotechName;
    }

    /**
     * Define o valor da propriedade biotechName.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidencedStringType }
     *     
     */
    public void setBiotechName(EvidencedStringType value) {
        this.biotechName = value;
    }

    /**
     * Gets the value of the cdAntigenName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cdAntigenName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCdAntigenName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidencedStringType }
     * 
     * 
     */
    public List<EvidencedStringType> getCdAntigenName() {
        if (cdAntigenName == null) {
            cdAntigenName = new ArrayList<EvidencedStringType>();
        }
        return this.cdAntigenName;
    }

    /**
     * Gets the value of the innName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the innName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInnName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidencedStringType }
     * 
     * 
     */
    public List<EvidencedStringType> getInnName() {
        if (innName == null) {
            innName = new ArrayList<EvidencedStringType>();
        }
        return this.innName;
    }

    /**
     * Gets the value of the domain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProteinType.Domain }
     * 
     * 
     */
    public List<ProteinType.Domain> getDomain() {
        if (domain == null) {
            domain = new ArrayList<ProteinType.Domain>();
        }
        return this.domain;
    }

    /**
     * Gets the value of the component property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the component property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProteinType.Component }
     * 
     * 
     */
    public List<ProteinType.Component> getComponent() {
        if (component == null) {
            component = new ArrayList<ProteinType.Component>();
        }
        return this.component;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="fullName" type="{http://uniprot.org/uniprot}evidencedStringType" minOccurs="0"/>
     *         &lt;element name="shortName" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ecNumber" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
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
        "fullName",
        "shortName",
        "ecNumber"
    })
    public static class AlternativeName {

        protected EvidencedStringType fullName;
        protected List<EvidencedStringType> shortName;
        protected List<EvidencedStringType> ecNumber;

        /**
         * Obt�m o valor da propriedade fullName.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getFullName() {
            return fullName;
        }

        /**
         * Define o valor da propriedade fullName.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setFullName(EvidencedStringType value) {
            this.fullName = value;
        }

        /**
         * Gets the value of the shortName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the shortName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getShortName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getShortName() {
            if (shortName == null) {
                shortName = new ArrayList<EvidencedStringType>();
            }
            return this.shortName;
        }

        /**
         * Gets the value of the ecNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ecNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEcNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getEcNumber() {
            if (ecNumber == null) {
                ecNumber = new ArrayList<EvidencedStringType>();
            }
            return this.ecNumber;
        }

    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "recommendedName",
        "alternativeName",
        "submittedName",
        "allergenName",
        "biotechName",
        "cdAntigenName",
        "innName"
    })
    public static class Component {

        protected ProteinType.RecommendedName recommendedName;
        protected List<ProteinType.AlternativeName> alternativeName;
        protected List<ProteinType.SubmittedName> submittedName;
        protected EvidencedStringType allergenName;
        protected EvidencedStringType biotechName;
        protected List<EvidencedStringType> cdAntigenName;
        protected List<EvidencedStringType> innName;

        /**
         * Obt�m o valor da propriedade recommendedName.
         * 
         * @return
         *     possible object is
         *     {@link ProteinType.RecommendedName }
         *     
         */
        public ProteinType.RecommendedName getRecommendedName() {
            return recommendedName;
        }

        /**
         * Define o valor da propriedade recommendedName.
         * 
         * @param value
         *     allowed object is
         *     {@link ProteinType.RecommendedName }
         *     
         */
        public void setRecommendedName(ProteinType.RecommendedName value) {
            this.recommendedName = value;
        }

        /**
         * Gets the value of the alternativeName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the alternativeName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAlternativeName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProteinType.AlternativeName }
         * 
         * 
         */
        public List<ProteinType.AlternativeName> getAlternativeName() {
            if (alternativeName == null) {
                alternativeName = new ArrayList<ProteinType.AlternativeName>();
            }
            return this.alternativeName;
        }

        /**
         * Gets the value of the submittedName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the submittedName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSubmittedName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProteinType.SubmittedName }
         * 
         * 
         */
        public List<ProteinType.SubmittedName> getSubmittedName() {
            if (submittedName == null) {
                submittedName = new ArrayList<ProteinType.SubmittedName>();
            }
            return this.submittedName;
        }

        /**
         * Obt�m o valor da propriedade allergenName.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getAllergenName() {
            return allergenName;
        }

        /**
         * Define o valor da propriedade allergenName.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setAllergenName(EvidencedStringType value) {
            this.allergenName = value;
        }

        /**
         * Obt�m o valor da propriedade biotechName.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getBiotechName() {
            return biotechName;
        }

        /**
         * Define o valor da propriedade biotechName.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setBiotechName(EvidencedStringType value) {
            this.biotechName = value;
        }

        /**
         * Gets the value of the cdAntigenName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cdAntigenName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCdAntigenName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getCdAntigenName() {
            if (cdAntigenName == null) {
                cdAntigenName = new ArrayList<EvidencedStringType>();
            }
            return this.cdAntigenName;
        }

        /**
         * Gets the value of the innName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the innName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInnName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getInnName() {
            if (innName == null) {
                innName = new ArrayList<EvidencedStringType>();
            }
            return this.innName;
        }

    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;group ref="{http://uniprot.org/uniprot}proteinNameGroup"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "recommendedName",
        "alternativeName",
        "submittedName",
        "allergenName",
        "biotechName",
        "cdAntigenName",
        "innName"
    })
    public static class Domain {

        protected ProteinType.RecommendedName recommendedName;
        protected List<ProteinType.AlternativeName> alternativeName;
        protected List<ProteinType.SubmittedName> submittedName;
        protected EvidencedStringType allergenName;
        protected EvidencedStringType biotechName;
        protected List<EvidencedStringType> cdAntigenName;
        protected List<EvidencedStringType> innName;

        /**
         * Obt�m o valor da propriedade recommendedName.
         * 
         * @return
         *     possible object is
         *     {@link ProteinType.RecommendedName }
         *     
         */
        public ProteinType.RecommendedName getRecommendedName() {
            return recommendedName;
        }

        /**
         * Define o valor da propriedade recommendedName.
         * 
         * @param value
         *     allowed object is
         *     {@link ProteinType.RecommendedName }
         *     
         */
        public void setRecommendedName(ProteinType.RecommendedName value) {
            this.recommendedName = value;
        }

        /**
         * Gets the value of the alternativeName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the alternativeName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAlternativeName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProteinType.AlternativeName }
         * 
         * 
         */
        public List<ProteinType.AlternativeName> getAlternativeName() {
            if (alternativeName == null) {
                alternativeName = new ArrayList<ProteinType.AlternativeName>();
            }
            return this.alternativeName;
        }

        /**
         * Gets the value of the submittedName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the submittedName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSubmittedName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProteinType.SubmittedName }
         * 
         * 
         */
        public List<ProteinType.SubmittedName> getSubmittedName() {
            if (submittedName == null) {
                submittedName = new ArrayList<ProteinType.SubmittedName>();
            }
            return this.submittedName;
        }

        /**
         * Obt�m o valor da propriedade allergenName.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getAllergenName() {
            return allergenName;
        }

        /**
         * Define o valor da propriedade allergenName.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setAllergenName(EvidencedStringType value) {
            this.allergenName = value;
        }

        /**
         * Obt�m o valor da propriedade biotechName.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getBiotechName() {
            return biotechName;
        }

        /**
         * Define o valor da propriedade biotechName.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setBiotechName(EvidencedStringType value) {
            this.biotechName = value;
        }

        /**
         * Gets the value of the cdAntigenName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cdAntigenName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCdAntigenName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getCdAntigenName() {
            if (cdAntigenName == null) {
                cdAntigenName = new ArrayList<EvidencedStringType>();
            }
            return this.cdAntigenName;
        }

        /**
         * Gets the value of the innName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the innName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInnName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getInnName() {
            if (innName == null) {
                innName = new ArrayList<EvidencedStringType>();
            }
            return this.innName;
        }

    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="fullName" type="{http://uniprot.org/uniprot}evidencedStringType"/>
     *         &lt;element name="shortName" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ecNumber" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
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
        "fullName",
        "shortName",
        "ecNumber"
    })
    public static class RecommendedName {

        @XmlElement(required = true)
        protected EvidencedStringType fullName;
        protected List<EvidencedStringType> shortName;
        protected List<EvidencedStringType> ecNumber;

        /**
         * Obt�m o valor da propriedade fullName.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getFullName() {
            return fullName;
        }

        /**
         * Define o valor da propriedade fullName.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setFullName(EvidencedStringType value) {
            this.fullName = value;
        }

        /**
         * Gets the value of the shortName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the shortName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getShortName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getShortName() {
            if (shortName == null) {
                shortName = new ArrayList<EvidencedStringType>();
            }
            return this.shortName;
        }

        /**
         * Gets the value of the ecNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ecNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEcNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getEcNumber() {
            if (ecNumber == null) {
                ecNumber = new ArrayList<EvidencedStringType>();
            }
            return this.ecNumber;
        }

    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="fullName" type="{http://uniprot.org/uniprot}evidencedStringType"/>
     *         &lt;element name="ecNumber" type="{http://uniprot.org/uniprot}evidencedStringType" maxOccurs="unbounded" minOccurs="0"/>
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
        "fullName",
        "ecNumber"
    })
    public static class SubmittedName {

        @XmlElement(required = true)
        protected EvidencedStringType fullName;
        protected List<EvidencedStringType> ecNumber;

        /**
         * Obt�m o valor da propriedade fullName.
         * 
         * @return
         *     possible object is
         *     {@link EvidencedStringType }
         *     
         */
        public EvidencedStringType getFullName() {
            return fullName;
        }

        /**
         * Define o valor da propriedade fullName.
         * 
         * @param value
         *     allowed object is
         *     {@link EvidencedStringType }
         *     
         */
        public void setFullName(EvidencedStringType value) {
            this.fullName = value;
        }

        /**
         * Gets the value of the ecNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ecNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEcNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidencedStringType }
         * 
         * 
         */
        public List<EvidencedStringType> getEcNumber() {
            if (ecNumber == null) {
                ecNumber = new ArrayList<EvidencedStringType>();
            }
            return this.ecNumber;
        }

    }

}

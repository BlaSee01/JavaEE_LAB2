/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trwałość;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author student
 */
@Entity
@Table(name = "CLOTHES")
@NamedQueries({
    @NamedQuery(name = "Clothes.findAll", query = "SELECT c FROM Clothes c"),
    @NamedQuery(name = "Clothes.findByClothesId", query = "SELECT c FROM Clothes c WHERE c.clothesId = :clothesId"),
    @NamedQuery(name = "Clothes.findByName", query = "SELECT c FROM Clothes c WHERE c.name = :name"),
    @NamedQuery(name = "Clothes.findByGendre", query = "SELECT c FROM Clothes c WHERE c.gendre = :gendre"),
    @NamedQuery(name = "Clothes.findByType", query = "SELECT c FROM Clothes c WHERE c.type = :type"),
    @NamedQuery(name = "Clothes.findByPricePln", query = "SELECT c FROM Clothes c WHERE c.pricePln = :pricePln"),
    @NamedQuery(name = "Clothes.findByManufacturer", query = "SELECT c FROM Clothes c WHERE c.manufacturer = :manufacturer"),
    @NamedQuery(name = "Clothes.findByIsPolishManufacturer", query = "SELECT c FROM Clothes c WHERE c.isPolishManufacturer = :isPolishManufacturer"),
    @NamedQuery(name = "Clothes.findByClothesIdAndType", query = "SELECT c FROM Clothes c WHERE c.clothesId = :clothesId AND c.type = :type")})

public class Clothes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLOTHES_ID")
    private Integer clothesId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "GENDRE")
    private String gendre;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "PRICE_PLN")
    private Integer pricePln;
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Column(name = "IS_POLISH_MANUFACTURER")
    private Boolean isPolishManufacturer;

    public Clothes() {
    }

    public Clothes(Integer clothesId) {
        this.clothesId = clothesId;
    }

    public Integer getClothesId() {
        return clothesId;
    }

    public void setClothesId(Integer clothesId) {
        this.clothesId = clothesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGendre() {
        return gendre;
    }

    public void setGendre(String gendre) {
        this.gendre = gendre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPricePln() {
        return pricePln;
    }

    public void setPricePln(Integer pricePln) {
        this.pricePln = pricePln;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getIsPolishManufacturer() {
        return isPolishManufacturer;
    }
    
    public String getListaHTML (List<Trwałość.Clothes> lista) {
    String wiersz;
    wiersz = ("<table><tr>");
    wiersz = wiersz.concat(
    "<td><b>ID</b></td>"
    + "<td><b>NAME</b></td>" 
    + "<td><b>Gendre</b></td>"
    + "<td><b>TYPE</b></td>"
    + "<td><b>PRICE [PLN]</b></td>"
    + "<td><b>MANUFACTURER</b></td>"
    + "<td><b>IsPolishManufacturer</b></td>");
    
    wiersz.concat("</tr>");
    
    for (Trwałość.Clothes ldz : lista) {
        wiersz = wiersz.concat("<tr>");
        wiersz = wiersz.concat("<td>"+ ldz.getClothesId() + "</td>"); 
        wiersz = wiersz.concat("<td>" + ldz.getName() + "</td>"); 
        wiersz = wiersz.concat("<td>" + ldz.getGendre() + "</td>"); 
        wiersz = wiersz.concat("<td>" + ldz.getType() + "</td>"); 
        wiersz = wiersz.concat("<td>" + ldz.getPricePln() + "</td>"); 
        wiersz = wiersz.concat("<td>" + ldz.getManufacturer() + "</td>");
        wiersz = wiersz.concat("<td>" + ldz.getIsPolishManufacturer() + "</td>"); 
        wiersz = wiersz.concat("</tr>");
    }
    wiersz = wiersz.concat("</table>");
    return wiersz;
    }

    public void setIsPolishManufacturer(Boolean isPolishManufacturer) {
        this.isPolishManufacturer = isPolishManufacturer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clothesId != null ? clothesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clothes)) {
            return false;
        }
        Clothes other = (Clothes) object;
        if ((this.clothesId == null && other.clothesId != null) || (this.clothesId != null && !this.clothesId.equals(other.clothesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trwa\u0142o\u015b\u0107.Clothes[ clothesId=" + clothesId + " ]";
    }
    
    
    
}

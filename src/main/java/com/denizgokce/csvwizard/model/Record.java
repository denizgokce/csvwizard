package com.denizgokce.csvwizard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

@Entity
public class Record {

    @CsvBindByName(column = "source")
    @Column(name = "source")
    private String source;

    @CsvBindByName(column = "codeListCode")
    @Column(name = "code_list_code")
    private String codeListCode;

    @CsvBindByName(column = "code")
    @Id
    @Column(name = "code")
    private String code;

    @CsvBindByName(column = "displayValue")
    @Column(name = "display_value")
    private String displayValue;

    @CsvBindByName(column = "longDescription")
    @Column(name = "long_description")
    private String longDescription;

    @CsvBindByName(column = "fromDate")
    @CsvDate("dd-MM-yyyy")
    @Column(name = "from_date")
    private LocalDate fromDate;

    @CsvBindByName(column = "toDate")
    @CsvDate("dd-MM-yyyy")
    @Column(name = "to_date")
    private LocalDate toDate;

    @CsvBindByName(column = "sortingPriority")
    @Column(name = "sorting_priority")
    private Integer sortingPriority;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Integer getSortingPriority() {
        return sortingPriority;
    }

    public void setSortingPriority(Integer sortingPriority) {
        this.sortingPriority = sortingPriority;
    }
}
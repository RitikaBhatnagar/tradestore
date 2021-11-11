package com.example.tradestore.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "TRADE_ID")
    private String tradeId;

    @NonNull
    @Column(name = "VERSION")
    private Integer version;

    @NonNull
    @Column(name = "COUNTER_PARTY_ID")
    private String counterPartyId;

    @NonNull
    @Column(name = "BOOK_ID")
    private String bookId;

    @NonNull
    @Column(name = "MATURITY_DATE")
    private LocalDate maturityDate;

    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @Column(name = "EXPIRY")
    private String expiry;

    public Trade(){

    }

    public Trade(String tradeId, Integer version, String counterPartyId, String bookId, LocalDate maturityDate, LocalDate createdDate, String expiry) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.expiry = expiry;
    }

    public String getTradeId() {
        return tradeId;
    }

    public Integer getVersion() {
        return version;
    }

    public String getCounterPartyId() {
        return counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trade)) return false;
        Trade trade = (Trade) o;
        return Objects.equals(getTradeId(), trade.getTradeId()) &&
                Objects.equals(getVersion(), trade.getVersion()) &&
                Objects.equals(getCounterPartyId(), trade.getCounterPartyId()) &&
                Objects.equals(getBookId(), trade.getBookId()) &&
                Objects.equals(getMaturityDate(), trade.getMaturityDate()) &&
                Objects.equals(getCreatedDate(), trade.getCreatedDate()) &&
                Objects.equals(getExpiry(), trade.getExpiry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTradeId(), getVersion(), getCounterPartyId(), getBookId(), getMaturityDate(), getCreatedDate(), getExpiry());
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", version=" + version +
                ", counterPartyId='" + counterPartyId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", maturityDate=" + maturityDate +
                ", createdDate=" + createdDate +
                ", expiry='" + expiry + '\'' +
                '}';
    }
}

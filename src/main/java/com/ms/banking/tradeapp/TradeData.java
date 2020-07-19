package com.ms.banking.tradeapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.apache.commons.lang.StringUtils;

public class TradeData {
	
	private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private LocalDate maturityDate;
    private LocalDate createdDate;
    private String expiredFlag;
    
    public TradeData() {
    	
    }
    
	public TradeData(String[] dataColumns) {
		int columnCount=0;
    	this.tradeId = StringUtils.trimToEmpty(dataColumns[columnCount++]);
    	try {	
            this.version = Integer.parseInt(dataColumns[columnCount++]);
        } catch (NumberFormatException nfe) {
            this.version = 0;
        }
        this.counterPartyId = StringUtils.trimToEmpty(dataColumns[columnCount++]);
        this.bookId = StringUtils.trimToEmpty(dataColumns[columnCount++]);
        this.maturityDate = LocalDate.parse(dataColumns[columnCount++], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.createdDate = LocalDate.parse(dataColumns[columnCount++], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.expiredFlag = StringUtils.trimToEmpty(dataColumns[columnCount++]);
        
        LocalDate presentDate = LocalDate.now();
        if(maturityDate.isBefore(presentDate)) {
        	this.expiredFlag = "Y";
        }
        
			
	}
	
    
	public String getTradeId() {
		return tradeId;
	}



	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}



	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}


	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	public LocalDate getMaturityDate() {
		return maturityDate;
	}


	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public String getExpiredFlag() {
		return expiredFlag;
	}


	public void setExpiredFlag(String expiredFlag) {
		this.expiredFlag = expiredFlag;
	}


	public static Function<String, TradeData> transformStringtoDomain = (line) -> {
		String[] row = line.split(FileConstants.TRADE_DATA_FILE.getDelimiter());
		return new TradeData(row);
	};
	

}

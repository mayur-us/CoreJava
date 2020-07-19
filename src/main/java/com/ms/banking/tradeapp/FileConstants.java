package com.ms.banking.tradeapp;



public enum FileConstants {
	
	TRADE_DATA_FILE("data/TRADE.DAT", Constants.TAB);
	

    private final String filePath;
    private final String delimiter;


    FileConstants(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

}

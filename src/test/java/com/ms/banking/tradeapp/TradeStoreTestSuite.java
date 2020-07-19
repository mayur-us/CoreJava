package com.ms.banking.tradeapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class TradeStoreTestSuite extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	/*
	 * Test Case - Trade Data Load from File
	 */
	@Test
    public void testTradeFileDataLoad() throws IOException {
		TradeFileLoader<TradeData> dataFileLoader = new TradeFileLoader<>(FileConstants.TRADE_DATA_FILE.getFilePath());
		List<TradeData> tradetDataList = dataFileLoader.parseInputFile(TradeData.transformStringtoDomain);
		
		//The original input TradeData file contains 7 records
		assertEquals(7, tradetDataList.size());

    }
	/*
	 * Test Case - Only valid trades as per validation rules are stored in Trade Store.
	 */
	@Test
    public void testValidTradesStore() throws Exception {
		
		//Loads the data
		TradeFileLoader<TradeData> dataFileLoader = new TradeFileLoader<>(FileConstants.TRADE_DATA_FILE.getFilePath());
		List<TradeData> tradetDataList = dataFileLoader.parseInputFile(TradeData.transformStringtoDomain);
		
		TradeStore tradeStore = new TradeStore();
		Map<String,HashMap<Integer,TradeData>> tradeStoreMap = tradeStore.createTradeStore(tradetDataList);

		assertEquals(3, tradeStoreMap.size());

    }
	//Further Test Cases can be written to test trade level data


}

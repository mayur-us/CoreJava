package com.ms.banking.tradeapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TradeFileLoader<T> {

	private String inputDataFile = null;

	public TradeFileLoader(String inputDataFile) {
		this.inputDataFile = inputDataFile;
	}

	public List<T> parseInputFile(Function<String, T> transformStringtoDomain) throws IOException {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputDataFile);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			return br.lines().skip(1).map(transformStringtoDomain).collect(Collectors.toList());
		}

	}

//	public static void main(String[] args) throws Exception {
//		TradeFileLoader<TradeData> dataFileLoader = new TradeFileLoader<>(FileConstants.TRADE_DATA_FILE.getFilePath());
//		List<TradeData> tradetDataList = dataFileLoader.parseInputFile(TradeData.transformStringtoDomain);
//
//		System.out.println(tradetDataList.size());
//		
//		TradeStore tradeStore = new TradeStore();
//		Map<String,HashMap<Integer,TradeData>> tradeStoreMap = tradeStore.createTradeStore(tradetDataList);
//		System.out.println(tradeStoreMap.size());
//
//	}

}
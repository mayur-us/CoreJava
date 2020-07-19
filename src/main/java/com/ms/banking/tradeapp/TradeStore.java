package com.ms.banking.tradeapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TradeStore {
	
	Map<String,HashMap<Integer,TradeData>> tradeStoreMap = new HashMap<String,HashMap<Integer,TradeData>>();
	
	public Map<String,HashMap<Integer,TradeData>> createTradeStore(List<TradeData> tradeList) throws Exception{
		
		for(TradeData tradeData : tradeList) {
						
			String isTradeExpired = tradeData.getExpiredFlag();
			if(isTradeExpired.equals("Y")) {
				continue;
			}
			String tradeId = tradeData.getTradeId();
			Integer tradeVersion = tradeData.getVersion();
			
			if(tradeStoreMap.containsKey(tradeId)){
				HashMap<Integer,TradeData> tradeVersionMap = tradeStoreMap.get(tradeId);
				Set<Integer> versionKeys = tradeVersionMap.keySet();
				
				Integer maxKey = versionKeys
								.stream()
								.mapToInt(k->k)
								.max()
								.getAsInt();
				if(tradeVersion == maxKey) {
					tradeVersionMap.put(tradeVersion,tradeData);
				}
				
				if(tradeVersion < maxKey) {
					throw new Exception("Trade with version lower than existing is not allowed");
				}
				
				if(tradeVersion > maxKey) {
					tradeVersionMap.put(tradeVersion,tradeData);
				}
			
				
			}
			else {
				HashMap<Integer,TradeData> tradeVersionMap = new HashMap<Integer,TradeData>();
				
				tradeVersionMap.put(tradeVersion,tradeData);
				tradeStoreMap.put(tradeId, tradeVersionMap);
			}
			
		}
		return tradeStoreMap;
		
	}

}

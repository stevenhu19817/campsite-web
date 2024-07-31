/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Campsite {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if (columnName != null && "CAMP_NAME".equals(columnName)) { // 用於varchar
			aCondition = "(" + columnName + " like '%" + value + "%' OR LOCATION LIKE '%" + value + "%')";
		}
//		else if (columnName != null && "CAMP_OPENING_TIME".equals(columnName)) {
//			aCondition = columnName + " BETWEEN " + "'" + value.substring(0, 10) + "' AND '" + value.substring(13)
//					+ "'";
//		} 
		else if (columnName != null && "CAMP_PRICE".equals(columnName)) {
			String priceLow = value.substring(0, value.indexOf("~")); // 用於後面方便
			aCondition = columnName + " BETWEEN " + priceLow + " AND " + value.substring(priceLow.length() + 1);
		} else if (columnName != null && "EMPTY_CAMP_LEFT".equals(columnName)) {
			aCondition = columnName + " > " + value;
		}
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1 && "EMPTY_CAMP_LEFT".equals(key)) {
					whereCondition.append("");
				} else if ("CAMP_OPENING_TIME".equals(key)) {
					whereCondition.append("");
				} else if (count == 1) {
					whereCondition.append(" where " + aCondition);
				} else {
					whereCondition.append(" and " + aCondition);
				}
				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}

		return whereCondition.toString();
	}

}

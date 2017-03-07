package com.iyihua.demos.cassandra.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;

import com.datastax.driver.core.utils.UUIDs;
import com.iyihua.demos.cassandra.domain.Label;
import com.iyihua.demos.cassandra.domain.MLabel0307_2;

public class QParamOfLabel {

	private String[] strings_q_keys = {"kigyou_cd","attibute_id","label_id"};
	private List<String> list_q_keys = new ArrayList<String>();
	private Map<String,String> map_q_param = new TreeMap<String,String>();
	private Map<String,Label> map_q_param_label = new TreeMap<String,Label>();

	public String[] getStrings_q_keys() {
		return strings_q_keys;
	}

	public void setStrings_q_keys(String[] strings_q_keys) {
		this.strings_q_keys = strings_q_keys;
	}

	public List<String> getList_q_keys() {
		return list_q_keys;
	}

	public void setList_q_keys(List<String> list_q_keys) {
		this.list_q_keys = list_q_keys;
	}

	public Map<String, String> getMap_q_param() {
		return map_q_param;
	}

	public void setMap_q_param(Map<String, String> map_q_param) {
		this.map_q_param = map_q_param;
	}

	public Map<String, Label> getMap_q_param_label() {
		return map_q_param_label;
	}

	public void setMap_q_param_label(Map<String, Label> map_q_param_label) {
		this.map_q_param_label = map_q_param_label;
	}

	public static void main(String[] args) {
		QParamOfLabel obj = new QParamOfLabel();
		String input = "{g1:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2],g2:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2],g3:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2]}";
		obj.initListQKeys(3);
		obj.initMapQParam(input);
		obj.initMapQParamLabel();

		testes();
	}

	private static void testes() {
		// TODO 自動生成されたメソッド・スタブ
			//debug
			System.out.println("findOne");
			String id = "AABBCC0000000001_2017-01-01.00:00:06+0000_2017-03-07.01:00:06+0000_1";
	    	String[] pathvals = id.split("_", -1);
	    	String companyCode = pathvals[0];
	    	String parentLabelCode = pathvals[1];
	    	String labelCode = pathvals[2];
	    	int labelDisplayOrder = Integer.parseInt(pathvals[3]);
			System.out.println(companyCode + "/" + parentLabelCode + "/" + labelCode + "/" + labelDisplayOrder);

			UUID uuid_parentLabelCode = getUUIDFromStringTime(parentLabelCode);
			UUID uuid_labelCode = getUUIDFromStringTime(labelCode);

			// UUID <=> String
			UUID tmp1 = getUUIDFromString("d09f860f-cf69-11e6-7f7f-7f7f7f7f7f7f");
			System.out.println(tmp1);
			String strTmp1 = getStringFromUUID(tmp1);
			System.out.println(strTmp1);
			Date dateTmp1 = getDateFromUUID(tmp1);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//sf.format(dateTmp1);
			System.out.println(sf.format(dateTmp1));

	}


	private static Date getDateFromUUID(UUID uuid_input){
		return new Date(uuid_input.timestamp());
	}


	/**
	 * UUID -> String
	 * [d09f860f-cf69-11e6-7f7f-7f7f7f7f7f7f] -> [d09f860f-cf69-11e6-7f7f-7f7f7f7f7f7f]
	 *
	 * @param uuid_input
	 * @return
	 */
	private static String getStringFromUUID(UUID uuid_input){
		return uuid_input.toString();
	}

	/**
	 * String -> UUID
	 * [d09f860f-cf69-11e6-7f7f-7f7f7f7f7f7f] -> [d09f860f-cf69-11e6-7f7f-7f7f7f7f7f7f]
	 *
	 * @param parentLabelCode
	 * @return
	 */
	private static UUID getUUIDFromString(String parentLabelCode) {
		return UUID.fromString(parentLabelCode);
	}

	/**
	 * String -> UUID
	 * [2017-01-01.00:00:06+0000] -> [d09f860f-cf69-11e6-7f7f-7f7f7f7f7f7f]
	 *
	 * @param parentLabelCode
	 * @return
	 */
	private static UUID getUUIDFromStringTime(String parentLabelCode) {
		String[] tmpArr1_plcd = parentLabelCode.split("\\.",-1);System.out.println(tmpArr1_plcd[0]);
		String[] tmpArr2_plcd = tmpArr1_plcd[0].split("-",-1);System.out.println(tmpArr1_plcd[1]);
		String[] tmpArr3_plcd = tmpArr1_plcd[1].split(":",-1);System.out.println(tmpArr3_plcd[2]);
		String[] tmpArr4_plcd = tmpArr3_plcd[2].split("\\+",-1);System.out.println(tmpArr4_plcd[1]);
		Calendar cal_parentLabelCode = Calendar.getInstance();
		cal_parentLabelCode.set(
				 Integer.parseInt(tmpArr2_plcd[0])
				,Integer.parseInt(tmpArr2_plcd[1])-1
				,Integer.parseInt(tmpArr2_plcd[2])
				,Integer.parseInt(tmpArr3_plcd[0])
				,Integer.parseInt(tmpArr3_plcd[1])
				,Integer.parseInt(tmpArr4_plcd[0])
		);
		cal_parentLabelCode.set(Calendar.MILLISECOND, Integer.parseInt(tmpArr4_plcd[1]));
		long timestamp_parentLabelCode = cal_parentLabelCode.getTimeInMillis();
		UUID uuid_parentLabelCode = UUIDs.endOf(timestamp_parentLabelCode);

		System.out.println(tmpArr2_plcd[0] + "/" + tmpArr2_plcd[1] + "/" + tmpArr2_plcd[2] + "/" + tmpArr3_plcd[0] + "/" + tmpArr3_plcd[1] + "/" + tmpArr4_plcd[0] + "/" + tmpArr4_plcd[1]);
		System.out.println(uuid_parentLabelCode);

		return uuid_parentLabelCode;
	}

	public void init(int size,String q_param){
		initListQKeys(size);
		initMapQParam(q_param);
		initMapQParamLabel();
	}

	public void initListQKeys(int size){
		for(int i = 0 ; i < size ; i++){
			this.list_q_keys.add("g" + (i+1));
		}
	}

	public void initMapQParam(String input){
		List<Integer> listIndex = new ArrayList<Integer>();
		for(int i = 0 ; i < this.list_q_keys.size() ; i++){
			int index1 = input.indexOf(this.list_q_keys.get(i));
			System.out.println(index1);
			if(index1 != -1){
				listIndex.add(index1);
			}
		}
//		System.out.println(input.substring(1, 58-1));
//		System.out.println(input.substring(58,115-1));
//		System.out.println(input.substring(115,input.length()-1));

		listIndex.add(input.length());
		for(int i = 0 ; i < listIndex.size()-1 ; i++){
			String value = input.substring(listIndex.get(i),listIndex.get(i+1)-1);
			int indexStartValue = value.indexOf("[");
			value = value.substring(indexStartValue, value.length());
			System.out.println(value);
			this.map_q_param.put("g" + (i+1), value);
		}

	}

	public void initMapQParamLabel(){
		Set<String> keyset = this.map_q_param.keySet();
		Iterator<String> ite = keyset.iterator();
		while(ite.hasNext()){
			String key = ite.next();
			String value = this.map_q_param.get(key);
			Label label = getLabelFromValue(value);
			this.map_q_param_label.put(key, label);
		}
	}

	private Label getLabelFromValue(String value) {
		// TODO 自動生成されたメソッド・スタブ
//		Label label = new Label();
		String tmpValue = cutValue(value);
		System.out.println(tmpValue);
		String[] array = tmpValue.split(",");
		Label label = getLabelFromArray(array);
		return label;
	}

	private Label getLabelFromArray(String[] array) {
		// TODO 自動生成されたメソッド・スタブ
		Label label = new Label();
		int KEY = 0;
		int IDENTIFIER = 1;
		int VALUE = 2;
		for(int i = 0 ; i < array.length; i++){
			String[] elements = array[i].split(":");
			if(elements[IDENTIFIER].equals("eq") && elements[KEY].equals("kigyou_cd")){
				label.setKigyouCd(elements[VALUE]);
			}
			if(elements[IDENTIFIER].equals("eq") && elements[KEY].equals("attibute_id")){
				label.setAttibuteId(elements[VALUE]);
			}
			if(elements[IDENTIFIER].equals("eq") && elements[KEY].equals("label_id")){
				label.setLabelId(Integer.parseInt(elements[VALUE]));
			}
		}
		return label;
	}

	private String cutValue(String value) {
		// TODO 自動生成されたメソッド・スタブ
		return (value.isEmpty())? "" : value.substring(1, value.length()-1);
	}

}

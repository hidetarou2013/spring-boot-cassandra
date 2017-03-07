package com.iyihua.demos.cassandra.rest;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.iyihua.demos.cassandra.domain.Label;
import com.iyihua.demos.cassandra.domain.MLabel;
import com.iyihua.demos.cassandra.domain.MLabel0307;
import com.iyihua.demos.cassandra.service.MLabel0307Service;
import com.iyihua.demos.cassandra.service.MLabelService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  513  curl http://localhost:8081/api/V1/mlabel0307/all -i -XGET
  514  curl http://localhost:8081/api/V1/mlabel0307/all/byname/A -i -XGET
  515  curl http://localhost:8081/api/V1/mlabel0307/all/byname/A-1 -i -XGET
  516  curl http://localhost:8081/api/V1/mlabel0307/all/byname/A-1 -i -XGET
  517  curl http://localhost:8081/api/V1/mlabel0307/all/bykeys/AABBCC0000000001/0001/0002/2 -i -XGET
  519  curl "http://localhost:8081/api/V1/mlabel0307/all/findOne?companyCode=AABBCC0000000001&parentLabelCode=0000&labelCode=0001&labelDisplayOrder=1" -i -XGET
  520  curl -g -i -XGET http://localhost:8081/api/V1/mlabel0307/all/?c=%7Bg1:[companyCode:eq:AABBCC0000000001,parentLabelCode:eq:0000,labelCode:eq:0001,labelDisplayOrder:eq:1]%7D

  POST
  521  curl http://localhost:8081/api/V1/mlabel0307/all -i -XPOST -H "Content-Type: application/json" -d "{\"companyCode\":\"AABBCC0000000001\",\"parentLabelCode\":\"0000\",\"labelCode\":\"0009\",\"color\":\"#FFFFFF\",\"labelDisplayOrder\":\"9\",\"labelName\":\"D\",\"level1\":\"4\",\"level2\":\"0\",\"level3\":\"0\",\"level4\":\"0\",\"level5\":\"0\",\"analizeFlag\":\"0\",\"logicalDeleteDiv\":\"0\"}"

  PUT
  522  curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0009_9 -i -XPUT -H "Content-Type: application/json" -d "{\"companyCode\":\"AABBCC0000000001\",\"parentLabelCode\":\"0000\",\"labelCode\":\"0009\",\"color\":\"#000000\",\"labelDisplayOrder\":\"9\",\"labelName\":\"D\",\"level1\":\"4\",\"level2\":\"0\",\"level3\":\"0\",\"level4\":\"0\",\"level5\":\"0\",\"analizeFlag\":\"0\",\"logicalDeleteDiv\":\"0\"}"

  DELETE
  524  curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0009_9 -i -XDELETE

  GET
  525  curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0001_1 -i -XGET
  526  curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0004_4 -i -XGET
  528  curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0008_8 -i -XGET
 */

@RestController
@RequestMapping("api/V1/mlabel0307/all")
public class MLabel0307RestController {

	@Autowired
	MLabel0307Service service;

	// 全件抽出
	// curl http://localhost:8081/api/V1/mlabel0307/all -i -XGET
	// OK
	@GetMapping
	List<MLabel0307> getLabels() {
		System.out.println("getLabels");
        List<MLabel0307> Labels = service.findAll();
        return Labels;
    }

	// ラベル名抽出
	// curl http://localhost:8081/api/V1/mlabel0307/all/byname/java -i -XGET
	// OK
	// しかし、このURLパターンは共通仕様にはない
	@GetMapping("byname/{s}")
	List<MLabel0307> findByLabelName(@PathVariable String s) {
		System.out.println("findByLabelName");
        List<MLabel0307> Labels = service.findByLabelName(s);
        return Labels;
    }

	// １件抽出
	// curl http://localhost:8081/api/V1/mlabel0307/all/bykeys/kgyo2/car/2 -i -XGET
	// OK
	// 複合主キーのさばき方の例として、複数のPKを@PathVariableで料理するやりかた
	// しかし、このURLパターンは共通仕様にはない
	@GetMapping(path = "bykeys/{companyCode}/{parentLabelCode}/{labelCode}/{labelDisplayOrder}")
	MLabel0307 findByKeys(@PathVariable String companyCode,@PathVariable String parentLabelCode,@PathVariable String labelCode,@PathVariable int labelDisplayOrder) {
		System.out.println("findByKeys");
		MLabel0307 obj = service.findByKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
        return obj;
    }

	// １件抽出
	// curl "http://localhost:8081/api/V1/mlabel0307/all/findOne?companyCode=AABBCC0000000001&parentLabelCode=0000&labelCode=0001&labelDisplayOrder=1" -i -XGET
	// OK
	// しかし、このURLパターンは共通仕様にはない
	@GetMapping(path = "/findOne")
	MLabel0307 findOne_old(
    		@RequestParam("companyCode") String companyCode
			,@RequestParam("parentLabelCode") String parentLabelCode
			,@RequestParam("labelCode") String labelCode
			,@RequestParam("labelDisplayOrder") int labelDisplayOrder
    		) {
		//debug
		System.out.println("findOne_old");
		System.out.println(companyCode + "/" + parentLabelCode + "/" + labelCode + "/" + labelDisplayOrder);
		MLabel0307 label = service.findOne(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
        return label;
    }

	/**
	 * 主キー抽出
	 * curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0001_1 -i -XGET
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	MLabel0307 findOne(
			@PathVariable String id
    		) {
		//debug
		System.out.println("findOne");
    	String[] pathvals = id.split("_", -1);
    	String companyCode = pathvals[0];
    	String parentLabelCode = pathvals[1];
    	String labelCode = pathvals[2];
    	int labelDisplayOrder = Integer.parseInt(pathvals[3]);
		System.out.println(companyCode + "/" + parentLabelCode + "/" + labelCode + "/" + labelDisplayOrder);
		MLabel0307 label = service.findOne(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
        return label;
    }

	// curl 'http://localhost:8081/api/V1/mlabel0307/all/?c={"g1":["kigyou_cd:eq:kgyo2","attibute_id:eq:car","label_id:eq:2"]}' -i -XGET
	// curl http://localhost:8081/api/V1/mlabel0307/all/?c={g1:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2]} -i -XGET
	// curl http://localhost:8081/api/V1/mlabel0307/all/?c={kigyou_cd:kgyo2,attibute_id:car,label_id:2]} -i -XGET
	// curl -g http://localhost:8081/api/V1/mlabel0307/all/?c={kigyou_cd:kgyo2,attibute_id:car,label_id:2]} -i -XGET
	// curl -g -i -XGET http://localhost:8081/api/V1/mlabel0307/all/?c={g1:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2]}
	// curl -g -i -XGET http://localhost:8081/api/V1/mlabel0307/all/?c=hoge,fuga
	// curl -g -i -XGET http://localhost:8081/api/V1/mlabel0307/all/?c=%7Bg1:[companyCode:eq:AABBCC0000000001,parentLabelCode:eq:0000,labelCode:eq:0001,labelDisplayOrder:eq:1]%7D
	// {g1:[companyCode:eq:AABBCC0000000001,parentLabelCode:eq:0000,labelCode:eq:0001,labelDisplayOrder:eq:1]}

	// 実験的なコード
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private class QParamTmp{
		private String g1 = "";
//		private String g2 = "";
	}


	// 実験的なコード
	@GetMapping(path = "/")
	MLabel0307 findOne2(
    		@RequestParam("c") String c
//    		,@RequestParam("f") String f
    		) throws JsonParseException, JsonMappingException, IOException {
		//debug
		System.out.println("findOne2");
		String companyCode = "";
		String parentLabelCode = "";
		String labelCode = "";
		int labelDisplayOrder = 0;

//		// JSON文字列からJavaBeansオブジェクトへ変換
		// {g1:[kigyou_cd:eq:kgyo2.attibute_id:eq:car.label_id:eq:2]}
		MLabel0307 label = null;
		if(!c.isEmpty()){
			System.out.println(c);
			QParamOfLabel obj = new QParamOfLabel();
			obj.init(1, c);
			Label bean = obj.getMap_q_param_label().get("g1");

			companyCode = label.getCompanyCode();
			parentLabelCode = label.getParentLabelCode();
			labelCode = label.getLabelCode();
			labelDisplayOrder = label.getLabelDisplayOrder();

			System.out.println(companyCode + "/" + parentLabelCode + "/" + labelCode + "/" + labelDisplayOrder);
			// 現在の設計では３つの主キーを渡して一意となる
	    	label = service.findOne(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
		}

        return label;
    }

    private void parseJson(String c) {
		// TODO 自動生成されたメソッド・スタブ
    	// {g1:[kigyou_cd:eq:kgyo2.attibute_id:eq:car.label_id:eq:2]}
    	String tmp = "";
    	if(!c.isEmpty()){
    		tmp = c.substring(1, c.length()-1);
    		System.out.println(tmp);

    	}

	}

	// 新規登録
	/*
curl http://localhost:8081/api/V1/mlabel0307/all -i -XPOST -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"label_id\":\"5\",\"color\":\"white\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"label_name\":\"osaka\",\"parent_label_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
curl http://localhost:8081/api/V1/mlabel0307/all -i -XPOST -H "Content-Type: application/json" -d "{\"kigyouCd\":\"kgyo5\",\"attibuteId\":\"kokyaku\",\"labelId\":\"5\",\"color\":\"white\",\"creatDatetime\":\"1482565802817\",\"creatUserId\":\"user001\",\"deleteId\":\"0\",\"labelName\":\"osaka\",\"parentLabelId\":\"0\",\"serverAssembId\":\"serverassembid\",\"uiAssembId\":\"uiassembid\",\"updateDatetime\":\"1482565802817\",\"updateUserId\":\"user001\"}"
curl http://localhost:8081/api/V1/mlabel0307/all -i -XPOST -H "Content-Type: application/json" -d "{\"kigyouCd\":\"kgyo6\",\"attibuteId\":\"kokyaku\",\"labelId\":\"6\",\"color\":\"white\",\"creatDatetime\":\"1482565802817\",\"creatUserId\":\"user001\",\"deleteId\":\"0\",\"labelName\":\"osaka\",\"parentLabelId\":\"0\",\"serverAssembId\":\"serverassembid\",\"uiAssembId\":\"uiassembid\",\"updateDatetime\":\"1482565802817\",\"updateUserId\":\"user001\"}"
curl http://localhost:8081/api/V1/mlabel0307/all -i -XPOST -H "Content-Type: application/json" -d "{\"companyCode\":\"AABBCC0000000001\",\"parentLabelCode\":\"0000\",\"labelCode\":\"0009\",\"color\":\"#FFFFFF\",\"labelDisplayOrder\":\"9\",\"labelName\":\"D\",\"level1\":\"4\",\"level2\":\"0\",\"level3\":\"0\",\"level4\":\"0\",\"level5\":\"0\",\"analizeFlag\":\"0\",\"logicalDeleteDiv\":\"0\"}"

	 */
	// OK
    @PostMapping
    ResponseEntity<MLabel0307> postLabel(@RequestBody MLabel0307 Label, UriComponentsBuilder uriBuilder) {
    	System.out.println("postLabel");
    	MLabel0307 created = service.create(Label);
        URI location = uriBuilder.path("api/V1/mlabel0307/all/{id}")
                .buildAndExpand(created.getLabelCode()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // 更新
    /*
curl http://localhost:8081/api/V1/mlabel0307/all/edit/5 -i -XPUT -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"label_id\":\"5\",\"color\":\"red\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"label_name\":\"osaka\",\"parent_label_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
curl http://localhost:8081/api/V1/mlabel0307/all/edit/5 -i -XPUT -H "Content-Type: application/json" -d "{\"kigyouCd\":\"kgyo5\",\"attibuteId\":\"kokyaku\",\"labelId\":\"5\",\"color\":\"red\",\"creatDatetime\":\"1482565802817\",\"creatUserId\":\"user001\",\"deleteId\":\"0\",\"labelName\":\"osaka\",\"parentLabelId\":\"0\",\"serverAssembId\":\"serverassembid\",\"ui_assemb_id\":\"uiAssembId\",\"updateDatetime\":\"1482565802817\",\"updateUserId\":\"user001\"}"
curl http://localhost:8081/api/V1/mlabel0307/all/edit/0009 -i -XPUT -H "Content-Type: application/json" -d "{\"companyCode\":\"AABBCC0000000001\",\"parentLabelCode\":\"0000\",\"labelCode\":\"0009\",\"color\":\"#000000\",\"labelDisplayOrder\":\"9\",\"labelName\":\"D\",\"level1\":\"4\",\"level2\":\"0\",\"level3\":\"0\",\"level4\":\"0\",\"level5\":\"0\",\"analizeFlag\":\"0\",\"logicalDeleteDiv\":\"0\"}"
     */
    // OK
    // しかし、このURLパターンは共通仕様にはない
    @PutMapping(path = "edit/{labelId}")
    MLabel0307 putLabel_old(@PathVariable String labelCode, @RequestBody MLabel0307 Label) {
    	System.out.println("putLabel_old");
    	Label.setLabelCode(labelCode);
        return service.update(Label);
    }

    /**
     * 更新
     * curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0009_9 -i -XPUT -H "Content-Type: application/json" -d "{\"companyCode\":\"AABBCC0000000001\",\"parentLabelCode\":\"0000\",\"labelCode\":\"0009\",\"color\":\"#000000\",\"labelDisplayOrder\":\"9\",\"labelName\":\"D\",\"level1\":\"4\",\"level2\":\"0\",\"level3\":\"0\",\"level4\":\"0\",\"level5\":\"0\",\"analizeFlag\":\"0\",\"logicalDeleteDiv\":\"0\"}"
     *
     * @param id → KigyouCd_AttibuteId_LabelId
     * @param label
     * @return
     */
    @PutMapping(path = "/{id}")
    MLabel0307 putLabel(@PathVariable String id, @RequestBody MLabel0307 label) {
    	System.out.println("putLabel");
    	String[] pathvals = id.split("_", -1);
    	label.setCompanyCode(pathvals[0]);
    	label.setParentLabelCode(pathvals[1]);
    	label.setLabelCode(pathvals[2]);
    	label.setLabelDisplayOrder(Integer.parseInt(pathvals[3]));
        return service.update(label);
    }

    /**
     * 複数更新
     *
     * curl http://localhost:8081/api/V1/mlabel0307/all -i -XPUT -H "Content-Type: application/json" -d "[{\"kigyouCd\":\"kgyo1\",\"attibuteId\":\"kokyaku\",\"labelId\":\"1\",\"color\":\"blue3\",\"creatDatetime\":\"1482565802817\",\"creatUserId\":\"user001\",\"deleteId\":\"0\",\"labelName\":\"osaka\",\"parentLabelId\":\"0\",\"serverAssembId\":\"serverassembid\",\"ui_assemb_id\":\"uiAssembId\",\"updateDatetime\":\"1482565802817\",\"updateUserId\":\"user001\"},{\"kigyouCd\":\"kgyo2\",\"attibuteId\":\"car\",\"labelId\":\"2\",\"color\":\"red3\",\"creatDatetime\":\"1482565802817\",\"creatUserId\":\"user001\",\"deleteId\":\"0\",\"labelName\":\"osaka\",\"parentLabelId\":\"0\",\"serverAssembId\":\"serverassembid\",\"ui_assemb_id\":\"uiAssembId\",\"updateDatetime\":\"1482565802817\",\"updateUserId\":\"user001\"}]"
     *
     * consumes=MediaType.APPLICATION_JSON_VALUE
     *
     * refs #1 複数更新機能追加
     *
     * @param id
     * @param labels
     */
    @PutMapping(path = "",consumes=MediaType.APPLICATION_JSON_VALUE)
    void putLabelList(@RequestBody List<MLabel0307> labels) {
    	System.out.println("putLabelList");
    	labels.forEach(label -> service.update(label) );
    }


    // 削除
    // curl http://localhost:8081/api/V1/mlabel0307/all/delete/kgyo5/kokyaku/5 -i -XDELETE
    // OK
    // しかし、このURLパターンは共通仕様にはない
    @DeleteMapping(path = "delete/{companyCode}/{parentLabelCode}/{labelCode}/{labelDisplayOrder}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLabel_old(@PathVariable String companyCode,@PathVariable String parentLabelCode,@PathVariable String labelCode,@PathVariable int labelDisplayOrder) {
    	System.out.println("deleteLabel_old");
    	service.delete(companyCode, parentLabelCode, labelCode,labelDisplayOrder);
    }

    /**
     * 削除
     *
     * curl http://localhost:8081/api/V1/mlabel0307/all/AABBCC0000000001_0000_0009_9 -i -XDELETE
     *
     * @param id
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLabel(@PathVariable String id) {
    	System.out.println("deleteLabel");
    	String[] pathvals = id.split("_", -1);
    	String companyCode = pathvals[0];
    	String parentLabelCode = pathvals[1];
    	String labelCode = pathvals[2];
    	int labelDisplayOrder = Integer.parseInt(pathvals[3]);
    	service.delete(companyCode, parentLabelCode, labelCode,labelDisplayOrder);
    }

    /**
     * 複数削除
     *
     * curl http://localhost:8081/api/V1/mlabel0307/all -i -XDELETE -H "Content-Type: application/json" -d "[{\"kigyouCd\":\"kgyo5\",\"attibuteId\":\"kokyaku\",\"labelId\":\"5\",\"color\":\"white\",\"creatDatetime\":\"1482565802817\",\"creatUserId\":\"user001\",\"deleteId\":\"0\",\"labelName\":\"osaka\",\"parentLabelId\":\"0\",\"serverAssembId\":\"serverassembid\",\"uiAssembId\":\"uiassembid\",\"updateDatetime\":\"1482565802817\",\"updateUserId\":\"user001\"},{\"kigyouCd\":\"kgyo6\",\"attibuteId\":\"kokyaku\",\"labelId\":\"6\",\"color\":\"white\",\"creatDatetime\":\"1482565802817\",\"creatUserId\":\"user001\",\"deleteId\":\"0\",\"labelName\":\"osaka\",\"parentLabelId\":\"0\",\"serverAssembId\":\"serverassembid\",\"uiAssembId\":\"uiassembid\",\"updateDatetime\":\"1482565802817\",\"updateUserId\":\"user001\"}]"
     *
     * refs #1 複数削除機能追加
     *
     * @param labels
     */
    @DeleteMapping(path = "",consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLabelList(@RequestBody List<MLabel0307> labels) {
    	System.out.println("deleteLabelList");
    	labels.forEach(label -> service.delete(label) );
    }

}

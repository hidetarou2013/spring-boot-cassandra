package com.iyihua.demos.cassandra.rest;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyihua.demos.cassandra.domain.Label;
import com.iyihua.demos.cassandra.service.LabelService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@RestController
@RequestMapping("api/1.0/label/all")
public class LabelRestController {

	@Autowired
	LabelService service;

	// 全件抽出
	// curl http://localhost:8081/api/1.0/label/all -i -XGET
	// OK
	@GetMapping
	List<Label> getLabels() {
		System.out.println("getLabels");
        List<Label> Labels = service.findAll();
        return Labels;
    }

	// ラベル名抽出
	// curl http://localhost:8081/api/1.0/label/all/byname/java -i -XGET
	// OK
	// しかし、このURLパターンは共通仕様にはない
	@GetMapping("byname/{s}")
	List<Label> findByLabelName(@PathVariable String s) {
		System.out.println("findByLabelName");
        List<Label> Labels = service.findByLabelName(s);
        return Labels;
    }

	// １件抽出
	// curl http://localhost:8081/api/1.0/label/all/bykeys/kgyo2/car/2 -i -XGET
	// OK
	// 複合主キーのさばき方の例として、複数のPKを@PathVariableで料理するやりかた
	// しかし、このURLパターンは共通仕様にはない
	@GetMapping(path = "bykeys/{kigyoCD}/{attibuteId}/{labelI}")
	Label findByKeys(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int labelI) {
		System.out.println("findByKeys");
        Label obj = service.findByKeys(kigyoCD, attibuteId, labelI);
        return obj;
    }

	// １件抽出
	// curl "http://localhost:8081/api/1.0/label/all/findOne?kigyou_cd=kgyo2&attibute_id=car&label_id=2" -i -XGET
	// OK
	// しかし、このURLパターンは共通仕様にはない
	@GetMapping(path = "/findOne")
    Label findOne(
    		@RequestParam("kigyou_cd") String kigyoCD
			,@RequestParam("attibute_id") String attibuteId
			,@RequestParam("label_id") int labelId
    		) {
		//debug
		System.out.println("findOne");
		System.out.println(kigyoCD + "/" + attibuteId + "/" + labelId);
    	Label label = service.findOne(kigyoCD, attibuteId, labelId);
        return label;
    }

	// curl 'http://localhost:8081/api/1.0/label/all/?c={"g1":["kigyou_cd:eq:kgyo2","attibute_id:eq:car","label_id:eq:2"]}' -i -XGET
	// curl http://localhost:8081/api/1.0/label/all/?c={g1:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2]} -i -XGET
	// curl http://localhost:8081/api/1.0/label/all/?c={kigyou_cd:kgyo2,attibute_id:car,label_id:2]} -i -XGET
	// curl -g http://localhost:8081/api/1.0/label/all/?c={kigyou_cd:kgyo2,attibute_id:car,label_id:2]} -i -XGET
	// curl -g -i -XGET http://localhost:8081/api/1.0/label/all/?c={g1:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2]}
	// curl -g -i -XGET http://localhost:8081/api/1.0/label/all/?c=hoge,fuga
	// curl -g -i -XGET http://localhost:8081/api/1.0/label/all/?c=%7Bg1:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2]%7D
	// {g1:[kigyou_cd:eq:kgyo2,attibute_id:eq:car,label_id:eq:2]}

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
    Label findOne2(
    		@RequestParam("c") String c
    		) throws JsonParseException, JsonMappingException, IOException {
		//debug
		System.out.println("findOne2");
		String kigyoCD = "";
		String attibuteId = "";
		int labelId = 0;

		// Jacksonのマッパーを生成
		ObjectMapper mapper = new ObjectMapper();

//		// JSON文字列からJavaBeansオブジェクトへ変換
		// {g1:[kigyou_cd:eq:kgyo2.attibute_id:eq:car.label_id:eq:2]}
		System.out.println(c);
		parseJson(c);
		QParamOfLabel obj = new QParamOfLabel();
		obj.init(1, c);
		Label bean = obj.getMap_q_param_label().get("g1");

//		// 以下でエラーとなる。
//		QParamTmp bean = mapper.readValue(c, QParamTmp.class);
//		String g1 = bean.getG1();
//		System.out.println(g1);

//		Label bean = mapper.readValue(c, Label.class);
		kigyoCD = bean.getKigyouCd();
		attibuteId = bean.getAttibuteId();
		labelId = bean.getLabelId();

		System.out.println(kigyoCD + "/" + attibuteId + "/" + labelId);
		// 現在の設計では３つの主キーを渡して一意となる
    	Label label = service.findOne(kigyoCD, attibuteId, labelId);
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
curl http://localhost:8081/api/1.0/label/all -i -XPOST -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"label_id\":\"5\",\"color\":\"white\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"label_name\":\"osaka\",\"parent_label_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
	 */
	// OK
	// 初回登録時に成功していたが、２回目にやり直してみて、エラーとなった。
    @PostMapping
    ResponseEntity<Label> postLabel(@RequestBody Label Label, UriComponentsBuilder uriBuilder) {
    	System.out.println("postLabel");
    	Label created = service.create(Label);
        URI location = uriBuilder.path("api/1.0/label/all/{id}")
                .buildAndExpand(created.getLabelId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // 更新
    /*
curl http://localhost:8081/api/1.0/label/all/edit/5 -i -XPUT -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"label_id\":\"5\",\"color\":\"red\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"label_name\":\"osaka\",\"parent_label_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
     */
    // OK
    // しかし、このURLパターンは共通仕様にはない
    @PutMapping(path = "edit/{labelId}")
    Label putLabel(@PathVariable int labelId, @RequestBody Label Label) {
    	System.out.println("putLabel");
    	Label.setLabelId(labelId);
        return service.update(Label);
    }

    // 削除
    // curl http://localhost:8081/api/1.0/label/all/delete/kgyo5/kokyaku/5 -i -XDELETE
    // OK
    // しかし、このURLパターンは共通仕様にはない
    @DeleteMapping(path = "delete/{kigyoCD}/{attibuteId}/{labelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLabel(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int labelId) {
    	System.out.println("deleteLabel");
    	service.delete(kigyoCD, attibuteId, labelId);
    }

}

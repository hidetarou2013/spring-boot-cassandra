package com.iyihua.demos.cassandra.rest;

import java.net.URI;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.iyihua.demos.cassandra.domain.Label;
import com.iyihua.demos.cassandra.service.LabelService;



@RestController
@RequestMapping("rest/cassandra/label")
public class LabelRestController {

	@Autowired
	LabelService service;

	// NG:Can't Use Pageable
//	@GetMapping
//    Page<Label> getLabels(@PageableDefault Pageable pageable) {
//        Page<Label> Labels = service.findAll(pageable);
//        return Labels;
//    }

	// curl http://localhost:8081/rest/cassandra/Label -i -XGET
	// curl http://localhost:8081/rest/cassandra/Label -i -XGET
	// OK
	@GetMapping
	List<Label> getLabels() {
        List<Label> Labels = service.findAll();
        return Labels;
    }

	// 全件抽出
	// curl http://localhost:8081/rest/cassandra/Label/byname/java -i -XGET
	// OK
	@GetMapping("byname/{s}")
	List<Label> findByLabelName(@PathVariable String s) {
        List<Label> Labels = service.findByLabelName(s);
        return Labels;
    }

	// memo指定検索
	// curl http://localhost:8081/rest/cassandra/Label/bykeys/hoge/hoge/1 -i -XGET
	// ?
	@GetMapping(path = "bykeys/{kigyoCD}/{attibuteId}/{labelI}")
	Label findByKeys(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int labelI) {
        Label obj = service.findByKeys(kigyoCD, attibuteId, labelI);
        return obj;
    }

	// 選択
	// curl http://localhost:8081/rest/cassandra/label/bykeys/kgyo2/car/2 -i -XGET
	// OK
	@GetMapping(path = "findOne/{kigyoCD}/{attibuteId}/{labelI}")
    Label findOne(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int labelI) {
    	Label Label = service.findOne(kigyoCD, attibuteId, labelI);
        return Label;
    }

    // 新規登録
	/*
curl http://localhost:8081/rest/cassandra/label -i -XPOST -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"label_id\":\"5\",\"color\":\"white\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"label_name\":\"osaka\",\"parent_label_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
	 */
	// OK
    @PostMapping
    ResponseEntity<Label> postLabel(@RequestBody Label Label, UriComponentsBuilder uriBuilder) {
    	Label created = service.create(Label);
        URI location = uriBuilder.path("rest/cassandra/Label/{id}")
                .buildAndExpand(created.getLabel_id()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // 更新
    /*
curl http://localhost:8081/rest/cassandra/label/edit/5 -i -XPUT -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"label_id\":\"5\",\"color\":\"red\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"label_name\":\"osaka\",\"parent_label_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
     */
    // OK
    @PutMapping(path = "edit/{labelId}")
    Label putLabel(@PathVariable int labelId, @RequestBody Label Label) {
    	Label.setLabel_id(labelId);
        return service.update(Label);
    }

    // 削除
    // curl http://localhost:8081/rest/cassandra/label/delete/kgyo5/kokyaku/5 -i -XDELETE
    // OK
    @DeleteMapping(path = "delete/{kigyoCD}/{attibuteId}/{labelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int labelId) {
    	service.delete(kigyoCD, attibuteId, labelId);
    }

}

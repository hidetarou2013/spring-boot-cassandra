package com.iyihua.demos.cassandra.rest;

import java.net.URI;
import java.util.List;

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

import com.iyihua.demos.cassandra.domain.Tag;
import com.iyihua.demos.cassandra.service.TagService;



@RestController
@RequestMapping("rest/cassandra/tag")
public class TagRestController {

	@Autowired
	TagService service;

	// curl http://localhost:8081/rest/cassandra/tag -i -XGET
	// curl http://localhost:8081/rest/cassandra/tag -i -XGET
	// OK
	@GetMapping
	List<Tag> getTags() {
        List<Tag> Tags = service.findAll();
        return Tags;
    }

	// 全件抽出
	// curl http://localhost:8081/rest/cassandra/tag/byname/java -i -XGET
	// OK
	@GetMapping("byname/{s}")
	List<Tag> findByTagName(@PathVariable String s) {
        List<Tag> Tags = service.findByTagName(s);
        return Tags;
    }

	// memo指定検索
	// curl http://localhost:8081/rest/cassandra/tag/bykeys/kigyo2/car/2 -i -XGET
	// ?
	@GetMapping(path = "bykeys/{kigyoCD}/{attibuteId}/{TagI}")
	Tag findByKeys(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int TagI) {
        Tag obj = service.findByKeys(kigyoCD, attibuteId, TagI);
        return obj;
    }

	// 選択
	// curl http://localhost:8081/rest/cassandra/tag/bykeys/kgyo2/car/2 -i -XGET
	// OK
	@GetMapping(path = "findOne/{kigyoCD}/{attibuteId}/{TagI}")
    Tag findOne(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int TagI) {
    	Tag Tag = service.findOne(kigyoCD, attibuteId, TagI);
        return Tag;
    }

    // 新規登録
	/*
curl http://localhost:8081/rest/cassandra/tag -i -XPOST -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"Tag_id\":\"5\",\"color\":\"white\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"Tag_name\":\"osaka\",\"parent_Tag_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
	 */
	// OK
    @PostMapping
    ResponseEntity<Tag> postTag(@RequestBody Tag Tag, UriComponentsBuilder uriBuilder) {
    	Tag created = service.create(Tag);
        URI location = uriBuilder.path("rest/cassandra/tag/{id}")
                .buildAndExpand(created.getTagId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // 更新
    /*
curl http://localhost:8081/rest/cassandra/tag/edit/5 -i -XPUT -H "Content-Type: application/json" -d "{\"kigyou_cd\":\"kgyo5\",\"attibute_id\":\"kokyaku\",\"Tag_id\":\"5\",\"color\":\"red\",\"creat_datetime\":\"1482565802817\",\"creat_user_id\":\"user001\",\"delete_id\":\"0\",\"Tag_name\":\"osaka\",\"parent_Tag_id\":\"0\",\"server_assemb_id\":\"serverassembid\",\"ui_assemb_id\":\"uiassembid\",\"update_datetime\":\"1482565802817\",\"update_user_id\":\"user001\"}"
     */
    // OK
    @PutMapping(path = "edit/{TagId}")
    Tag putTag(@PathVariable int TagId, @RequestBody Tag Tag) {
    	Tag.setTagId(TagId);
        return service.update(Tag);
    }

    // 削除
    // curl http://localhost:8081/rest/cassandra/tag/delete/kigyo5/kokyaku/5 -i -XDELETE
    // OK
    @DeleteMapping(path = "delete/{kigyoCD}/{attibuteId}/{TagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable String kigyoCD,@PathVariable String attibuteId,@PathVariable int TagId) {
    	service.delete(kigyoCD, attibuteId, TagId);
    }

}

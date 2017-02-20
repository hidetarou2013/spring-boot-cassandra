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

import com.iyihua.demos.cassandra.domain.MemoBox;
import com.iyihua.demos.cassandra.service.MemoBoxService;



@RestController
@RequestMapping("rest/cassandra/memobox")
public class MemoBoxRestController {

	@Autowired
	MemoBoxService service;

	// NG:Can't Use Pageable
//	@GetMapping
//    Page<MemoBox> getMemoBoxs(@PageableDefault Pageable pageable) {
//        Page<MemoBox> memoboxs = service.findAll(pageable);
//        return memoboxs;
//    }

	// curl http://localhost:8081/rest/cassandra/memobox -i -XGET
	// curl http://localhost:8081/rest/cassandra/memobox -i -XGET
	// OK
	@GetMapping
	List<MemoBox> getMemoBoxs() {
        List<MemoBox> memoboxs = service.findAll();
        return memoboxs;
    }

	// 全件抽出
	// curl http://localhost:8081/rest/cassandra/memobox/byname -i -XGET
	// OK
	@GetMapping("byname")
	List<MemoBox> getMemoBoxsAllByName() {
        List<MemoBox> memoboxs = service.findAllByName();
        return memoboxs;
    }

	// memo指定検索
	// curl http://localhost:8081/rest/cassandra/memobox/bymemo/memo1 -i -XGET
	// ?
	@GetMapping(path = "bymemo/{memo}")
	List<MemoBox> getMemoBoxsByName(@PathVariable String memo) {
        List<MemoBox> memoboxs = service.findByMemo(memo);
        return memoboxs;
    }

	// 選択
	// curl http://localhost:8081/rest/cassandra/memobox/4622f8d0-c8bd-11e6-b218-2f92b65bfc29 -i -XGET
	// OK
    @GetMapping(path = "{id}")
    MemoBox getMemoBoxById(@PathVariable UUID id) {
    	MemoBox MemoBox = service.findOne(id);
        return MemoBox;
    }

    // 新規登録
    // curl http://localhost:8081/rest/cassandra/memobox -i -XPOST -H "Content-Type: application/json"
    // -d "{\"pkey1\":\"a\",\"pkey2\":\"b\",\"id\":\"93d5db10-c9ad-11e6-b26a-27c9d3fa31b8\",\"name\":\"asai\",\"memo\":\"memo1\",\"date\":\"1482565802817\"}"
	// OK
    @PostMapping
    ResponseEntity<MemoBox> postMemoBox(@RequestBody MemoBox memobox, UriComponentsBuilder uriBuilder) {
    	MemoBox created = service.create(memobox);
        URI location = uriBuilder.path("rest/cassandra/memobox/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // 更新
    // curl http://localhost:8081/rest/cassandra/memobox/4622f8d0-c8bd-11e6-b218-2f92b65bfc29 -i -XPUT -H "Content-Type:
    // application/json" -d "{\"pkey1\":\"a\",\"pkey2\":\"b\",\"id\":\"4622f8d0-c8bd-11e6-b218-2f92b65bfc29\",\"name\":\"tanaka\",\"memo\":\"greet messageA\",\"date\":\"1482565233349\"}"
    // OK
    @PutMapping(path = "{id}")
    MemoBox putMemoBox(@PathVariable UUID id, @RequestBody MemoBox memobox) {
    	memobox.setId(id);
        return service.update(memobox);
    }

    // 削除
    // curl http://localhost:8081/rest/cassandra/memobox/4067ef50-c9ac-11e6-b26a-27c9d3fa31b8 -i -XDELETE
    // OK
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable UUID id) {
    	service.delete(id);
    }

}

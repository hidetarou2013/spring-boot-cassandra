package com.iyihua.demos.cassandra.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyihua.demos.cassandra.domain.Tag;
import com.iyihua.demos.cassandra.repository.TagRepository;






@Service
@Transactional
public class TagService {

	@Autowired
	TagRepository repository;

	public List<Tag> findAll() {
		Iterable<Tag> iteObj = repository.findAll();
		return StreamSupport.stream(iteObj.spliterator(), false)
			    .collect(Collectors.toList());
	}

	public List<Tag> findByTagName(String s) {
		return repository.findByTagName(s);
	}

	public Tag findOne(String kigyoCD,String attibuteId,int tagId) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(kigyoCD,attibuteId,tagId);
	}

	public Tag create(Tag Tag) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(Tag);
	}

	public Tag update(Tag Tag) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(Tag);
	}

	public void delete(String kigyou_cd,String attibute_id,int s) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteByTagId(kigyou_cd, attibute_id, s);
	}

	public Tag findByKeys(String kigyoCD,String attibuteId,int tagId) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(kigyoCD,attibuteId,tagId);
	}

}

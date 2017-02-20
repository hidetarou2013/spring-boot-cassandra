package com.iyihua.demos.cassandra.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyihua.demos.cassandra.domain.Label;
import com.iyihua.demos.cassandra.repository.LabelRepository;






@Service
@Transactional
public class LabelService {

	@Autowired
	LabelRepository repository;

	public List<Label> findAll() {
		Iterable<Label> iteObj = repository.findAll();
		return StreamSupport.stream(iteObj.spliterator(), false)
			    .collect(Collectors.toList());
	}

	public List<Label> findByLabelName(String s) {
		return repository.findByLabelName(s);
	}

	public Label findOne(String kigyoCD,String attibuteId,int labelI) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(kigyoCD,attibuteId,labelI);
	}

	public Label create(Label Label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(Label);
	}

	public Label update(Label Label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(Label);
	}

	public void delete(String kigyou_cd,String attibute_id,int s) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteByLabelId(kigyou_cd,attibute_id,s);
	}

	public Label findByKeys(String kigyoCD,String attibuteId,int labelI) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(kigyoCD,attibuteId,labelI);
	}

}

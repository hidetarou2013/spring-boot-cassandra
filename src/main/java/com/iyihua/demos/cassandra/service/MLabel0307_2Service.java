package com.iyihua.demos.cassandra.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyihua.demos.cassandra.domain.MLabel;
import com.iyihua.demos.cassandra.domain.MLabel0307;
import com.iyihua.demos.cassandra.domain.MLabel0307_2;
import com.iyihua.demos.cassandra.repository.MLabel0307Repository;
import com.iyihua.demos.cassandra.repository.MLabel0307_2Repository;
import com.iyihua.demos.cassandra.repository.MLabelRepository;






@Service
@Transactional
public class MLabel0307_2Service {

	@Autowired
	MLabel0307_2Repository repository;

	public List<MLabel0307_2> findAll() {
		Iterable<MLabel0307_2> iteObj = repository.findAll();
		return StreamSupport.stream(iteObj.spliterator(), false)
			    .collect(Collectors.toList());
	}

	public List<MLabel0307_2> findByLabelName(String s) {
		return repository.findByLabelName(s);
	}

	public MLabel0307_2 findOne(String companyCode,UUID parentLabelCode,UUID labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public MLabel0307_2 create(MLabel0307_2 label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(label);
	}

	public MLabel0307_2 update(MLabel0307_2 label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(label);
	}

	public void delete(String companyCode,UUID parentLabelCode,UUID labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteByLabelKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public void delete(MLabel0307_2 label) {
		// TODO 自動生成されたメソッド・スタブ
		String companyCode = label.getCompanyCode();
		UUID parentLabelCode = label.getParentLabelCode();
		UUID labelCode = label.getLabelCode();
		int labelDisplayOrder = label.getLabelDisplayOrder();
		repository.deleteByLabelKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public MLabel0307_2 findByKeys(String companyCode,UUID parentLabelCode,UUID labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

}

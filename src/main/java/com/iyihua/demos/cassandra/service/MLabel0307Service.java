package com.iyihua.demos.cassandra.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyihua.demos.cassandra.domain.MLabel;
import com.iyihua.demos.cassandra.domain.MLabel0307;
import com.iyihua.demos.cassandra.repository.MLabel0307Repository;
import com.iyihua.demos.cassandra.repository.MLabelRepository;






@Service
@Transactional
public class MLabel0307Service {

	@Autowired
	MLabel0307Repository repository;

	public List<MLabel0307> findAll() {
		Iterable<MLabel0307> iteObj = repository.findAll();
		return StreamSupport.stream(iteObj.spliterator(), false)
			    .collect(Collectors.toList());
	}

	public List<MLabel0307> findByLabelName(String s) {
		return repository.findByLabelName(s);
	}

	public MLabel0307 findOne(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public MLabel0307 create(MLabel0307 label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(label);
	}

	public MLabel0307 update(MLabel0307 label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(label);
	}

	public void delete(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteByLabelKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public void delete(MLabel0307 label) {
		// TODO 自動生成されたメソッド・スタブ
		String companyCode = label.getCompanyCode();
		String parentLabelCode = label.getParentLabelCode();
		String labelCode = label.getLabelCode();
		int labelDisplayOrder = label.getLabelDisplayOrder();
		repository.deleteByLabelKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public MLabel0307 findByKeys(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

}

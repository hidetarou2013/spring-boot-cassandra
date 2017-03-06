package com.iyihua.demos.cassandra.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyihua.demos.cassandra.domain.MLabel;
import com.iyihua.demos.cassandra.repository.MLabelRepository;






@Service
@Transactional
public class MLabelService {

	@Autowired
	MLabelRepository repository;

	public List<MLabel> findAll() {
		Iterable<MLabel> iteObj = repository.findAll();
		return StreamSupport.stream(iteObj.spliterator(), false)
			    .collect(Collectors.toList());
	}

	public List<MLabel> findByLabelName(String s) {
		return repository.findByLabelName(s);
	}

	public MLabel findOne(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public MLabel create(MLabel label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(label);
	}

	public MLabel update(MLabel label) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(label);
	}

	public void delete(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteByLabelKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public void delete(MLabel label) {
		// TODO 自動生成されたメソッド・スタブ
		String companyCode = label.getCompanyCode();
		String parentLabelCode = label.getParentLabelCode();
		String labelCode = label.getLabelCode();
		int labelDisplayOrder = label.getLabelDisplayOrder();
		repository.deleteByLabelKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

	public MLabel findByKeys(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrder) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByKeys(companyCode,parentLabelCode,labelCode,labelDisplayOrder);
	}

}

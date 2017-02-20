package com.iyihua.demos.cassandra.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyihua.demos.cassandra.domain.MemoBox;
import com.iyihua.demos.cassandra.repository.MemoBoxRepository;






@Service
@Transactional
public class MemoBoxService {

	@Autowired
	MemoBoxRepository repository;

	public List<MemoBox> findAll() {
//		return cityRepository.findAllOrderByName();
		return repository.findAllOrderById();
	}

	public List<MemoBox> findAllByName() {
		return repository.findAllOrderByName();
	}

	public MemoBox findOne(UUID id) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findById(id);
	}

	public MemoBox create(MemoBox memobox) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(memobox);
	}

	public MemoBox update(MemoBox memobox) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.save(memobox);
	}

	public void delete(UUID id) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteById(id);
	}

	public List<MemoBox> findByMemo(String memo) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findByMemo(memo);
	}

	// neither slice nor page queries are supported yet
//	public Page<MemoBox> findAll(Pageable pageable) {
//        return cityRepository.findAllOrderByName(pageable);
//    }


}

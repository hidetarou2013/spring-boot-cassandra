package com.iyihua.demos.cassandra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.iyihua.demos.cassandra.domain.MemoBox;







@Repository
public interface MemoBoxRepository extends CassandraRepository<MemoBox> {

	@Query("SELECT * FROM memobox where pkey1='a' and pkey2='b' ORDER BY id")
    List<MemoBox> findAllOrderById();

	@Query("SELECT * FROM memobox where pkey1='a' and pkey2='b' ORDER BY id,name")
    List<MemoBox> findAllOrderByName();

	// neither slice nor page queries are supported yet
//	@Query("SELECT x FROM memobox x ORDER BY x.name")
//    Page<MemoBox> findAllOrderByName(Pageable pageable);

//	@Query("SELECT * FROM memobox WHERE name=?0")
	@Query("SELECT * FROM memobox WHERE pkey1='a' and pkey2='b' and name=?0")
	public List<MemoBox> findByName(String s);

	@Query("SELECT * FROM memobox WHERE pkey1='a' and pkey2='b' and memo=?0")
	public List<MemoBox> findByMemo(String s);

//	@Query("SELECT * FROM memobox WHERE id=?0")
	@Query("SELECT * FROM memobox WHERE pkey1='a' and pkey2='b' and id=?0")
	public MemoBox findById(UUID s);

//	@Query("DELETE FROM memobox WHERE name=?0")
	@Query("DELETE FROM memobox WHERE pkey1='a' and pkey2='b' and name=?0")
	public List<MemoBox> deleteByName(String s);

//	@Query("DELETE FROM memobox WHERE id=?0")
	@Query("DELETE FROM memobox WHERE pkey1='a' and pkey2='b' and id=?0")
	public List<MemoBox> deleteById(UUID s);

//	@Query("SELECT * FROM memobox WHERE name=?0 LIMIT ?1")
	@Query("SELECT * FROM memobox WHERE pkey1='a' and pkey2='b' and name=?0 LIMIT ?1")
    Iterable<MemoBox> findByName(String name,Integer limit);


}
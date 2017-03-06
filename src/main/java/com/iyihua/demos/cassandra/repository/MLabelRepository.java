package com.iyihua.demos.cassandra.repository;


import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.iyihua.demos.cassandra.domain.MLabel;




@Repository
public interface MLabelRepository extends CassandraRepository<MLabel> {

	@Query("SELECT * FROM mlabel WHERE labelName=?0")
	public List<MLabel> findByLabelName(String s);

//	@Query("SELECT * FROM MAC000001 WHERE kigyou_cd =?0 and label_id=?1")
//	public Label findByLabelId(String kigyoCD,int labelID);

	//案1－02の対応
	@Query("SELECT * FROM mlabel WHERE companyCode =?0 and parentLabelCode=?1 and labelCode=?2 and labelDisplayOrders=?3")
	public MLabel findByKeys(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrders);


//	//案1－02のタグテーブル対応
//	@Query("SELECT * FROM TAC000001 WHERE label_id=?0")
//	public List<Tag> findTagsByLabel(int labelID);


	@Query("DELETE FROM mlabel WHERE labelName=?0")
	public List<MLabel> deleteByLabelName(String labelName);

	@Query("DELETE FROM mlabel WHERE companyCode =?0 and parentLabelCode=?1 and labelCode=?2 and labelDisplayOrders=?3")
	public List<MLabel> deleteByLabelKeys(String companyCode,String parentLabelCode,String labelCode,int labelDisplayOrders);


}
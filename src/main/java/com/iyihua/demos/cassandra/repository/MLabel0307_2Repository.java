package com.iyihua.demos.cassandra.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.iyihua.demos.cassandra.domain.MLabel;
import com.iyihua.demos.cassandra.domain.MLabel0307;
import com.iyihua.demos.cassandra.domain.MLabel0307_2;




@Repository
public interface MLabel0307_2Repository extends CassandraRepository<MLabel0307_2> {

	@Query("SELECT * FROM mlabel0307_2 WHERE labelName=?0")
	public List<MLabel0307_2> findByLabelName(String s);

//	@Query("SELECT * FROM MAC000001 WHERE kigyou_cd =?0 and label_id=?1")
//	public Label findByLabelId(String kigyoCD,int labelID);

	//案1－02の対応
	@Query("SELECT * FROM mlabel0307_2 WHERE companyCode =?0 and parentLabelCode=?1 and labelCode=?2 and labelDisplayOrder=?3")
	public MLabel0307_2 findByKeys(String companyCode,UUID parentLabelCode,UUID labelCode,int labelDisplayOrder);


//	//案1－02のタグテーブル対応
//	@Query("SELECT * FROM TAC000001 WHERE label_id=?0")
//	public List<Tag> findTagsByLabel(int labelID);


	@Query("DELETE FROM mlabel0307_2 WHERE labelName=?0")
	public List<MLabel0307_2> deleteByLabelName(String labelName);

	@Query("DELETE FROM mlabel0307_2 WHERE companyCode =?0 and parentLabelCode=?1 and labelCode=?2 and labelDisplayOrder=?3")
	public List<MLabel0307_2> deleteByLabelKeys(String companyCode,UUID parentLabelCode,UUID labelCode,int labelDisplayOrder);


}
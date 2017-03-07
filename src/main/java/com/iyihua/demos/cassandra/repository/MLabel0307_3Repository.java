package com.iyihua.demos.cassandra.repository;


import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.iyihua.demos.cassandra.domain.MLabel;
import com.iyihua.demos.cassandra.domain.MLabel0307;
import com.iyihua.demos.cassandra.domain.MLabel0307_2;
import com.iyihua.demos.cassandra.domain.MLabel0307_3;




@Repository
public interface MLabel0307_3Repository extends CassandraRepository<MLabel0307_3> {

	@Query("SELECT * FROM mlabel0307_3 WHERE labelName=?0")
	public List<MLabel0307_3> findByLabelName(String s);

//	@Query("SELECT * FROM MAC000001 WHERE kigyou_cd =?0 and label_id=?1")
//	public Label findByLabelId(String kigyoCD,int labelID);

	//案1－02の対応
	@Query("SELECT * FROM mlabel0307_3 WHERE companyCode =?0 and parentLabelCode=?1 and labelCode=?2 and labelDisplayOrder=?3")
	public MLabel0307_3 findByKeys(String companyCode,int parentLabelCode,int labelCode,int labelDisplayOrder);


//	//案1－02のタグテーブル対応
//	@Query("SELECT * FROM TAC000001 WHERE label_id=?0")
//	public List<Tag> findTagsByLabel(int labelID);


	@Query("DELETE FROM mlabel0307_3 WHERE labelName=?0")
	public List<MLabel0307_3> deleteByLabelName(String labelName);

	@Query("DELETE FROM mlabel0307_3 WHERE companyCode =?0 and parentLabelCode=?1 and labelCode=?2 and labelDisplayOrder=?3")
	public List<MLabel0307_3> deleteByLabelKeys(String companyCode,int parentLabelCode,int labelCode,int labelDisplayOrder);


}
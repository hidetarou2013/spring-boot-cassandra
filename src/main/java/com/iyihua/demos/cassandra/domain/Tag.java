package com.iyihua.demos.cassandra.domain;


import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "TAC000001")
public class Tag {

	@PrimaryKeyColumn(name = "kigyou_cd", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String kigyouCd;

	@PrimaryKeyColumn(name = "attibute_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String attibuteId;


	@PrimaryKeyColumn(name = "tag_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private int tagId;

	@Column(value = "tag_name")
	private String tagName;

	@Column(value = "label_id")
	private int labelId;

	@Column(value = "target_id")
	private String targetId;

	@Column(value = "creat_datetime")
	private Date creatDatetime;

	@Column(value = "creat_user_id")
	private String creatUserId;

	@Column(value = "update_user_id")
	private String updateUserId;

	@Column(value = "update_datetime")
	private Date updateDatetime;

	@Column(value = "ui_assemb_id")
	private String uiAssembId;

//	public String getKigyou_cd() {
//		return kigyouCd;
//	}
//
//	public void setKigyou_cd(String kigyou_cd) {
//		this.kigyouCd = kigyou_cd;
//	}
//
//	public String getAttibute_id() {
//		return attibuteId;
//	}
//
//	public void setAttibute_id(String attibute_id) {
//		this.attibuteId = attibute_id;
//	}
//
//	public int getTag_id() {
//		return tagId;
//	}
//
//	public void setTag_id(int tag_id) {
//		this.tagId = tag_id;
//	}
//
//	public String getTag_name() {
//		return tagName;
//	}
//
//	public void setTag_name(String tag_name) {
//		this.tagName = tag_name;
//	}
//
//	public int getLabel_id() {
//		return labelId;
//	}
//
//	public void setLabel_id(int label_id) {
//		this.labelId = label_id;
//	}
//
//	public String getTarget_id() {
//		return targetId;
//	}
//
//	public void setTarget_id(String target_id) {
//		this.targetId = target_id;
//	}
//
//	public Date getCreat_datetime() {
//		return creatDatetime;
//	}
//
//	public void setCreat_datetime(Date creat_datetime) {
//		this.creatDatetime = creat_datetime;
//	}
//
//	public String getCreat_user_id() {
//		return creatUserId;
//	}
//
//	public void setCreat_user_id(String creat_user_id) {
//		this.creatUserId = creat_user_id;
//	}
//
//	public String getUpdate_user_id() {
//		return updateUserId;
//	}
//
//	public void setUpdate_user_id(String update_user_id) {
//		this.updateUserId = update_user_id;
//	}
//
//	public Date getUpdate_datetime() {
//		return updateDatetime;
//	}
//
//	public void setUpdate_datetime(Date update_datetime) {
//		this.updateDatetime = update_datetime;
//	}
//
//	public String getUi_assemb_id() {
//		return uiAssembId;
//	}
//
//	public void setUi_assemb_id(String ui_assemb_id) {
//		this.uiAssembId = ui_assemb_id;
//	}
//
//	public String getServer_assemb_id() {
//		return serverAssembId;
//	}
//
//	public void setServer_assemb_id(String server_assemb_id) {
//		this.serverAssembId = server_assemb_id;
//	}
//
//	public String getDelete_id() {
//		return deleteId;
//	}
//
//	public void setDelete_id(String delete_id) {
//		this.deleteId = delete_id;
//	}

	@Column(value = "server_assemb_id")
	private String serverAssembId;

	@Column(value = "delete_id")
	private String deleteId;

//	public Tag() {
//
//	}
//
//	public Tag(String kigyou_cd, String attibute_id,int tag_id, String tag_name, int label_id,String target_id, Date creat_datetime,
//			String creat_user_id, String update_user_id, Date update_datetime, String ui_assemb_id,
//			String server_assemb_id, String delete_id) {
//		this.kigyou_cd = kigyou_cd;
//		this.attibute_id = attibute_id;
//		this.tag_id = tag_id;
//		this.tag_name = tag_name;
//		this.label_id=label_id;
//		this.target_id = target_id;
//		this.creat_datetime = creat_datetime;
//		this.creat_user_id = creat_user_id;
//		this.update_user_id = update_user_id;
//		this.update_datetime = update_datetime;
//		this.ui_assemb_id = ui_assemb_id;
//		this.server_assemb_id = server_assemb_id;
//		this.delete_id = delete_id;
//	}

}
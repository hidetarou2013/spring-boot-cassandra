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
@Table(value = "mlabel0307")
public class MLabel0307 {

	@PrimaryKeyColumn(name = "companyCode", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String companyCode;

	@PrimaryKeyColumn(name = "parentLabelCode", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String parentLabelCode;

	@PrimaryKeyColumn(name = "labelCode", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String labelCode;

	@PrimaryKeyColumn(name = "labelDisplayOrder", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private int labelDisplayOrder;

	@Column(value = "labelName")
	private String labelName;

	@Column(value = "color")
	private String color;

	@Column(value = "level1")
	private int level1;

	@Column(value = "level2")
	private int level2;

	@Column(value = "level3")
	private int level3;

	@Column(value = "level4")
	private int level4;

	@Column(value = "level5")
	private int level5;

	@Column(value = "analizeFlag")
	private int analizeFlag;

	@Column(value = "logicalDeleteDiv")
	private int logicalDeleteDiv;

	@Column(value = "createEmployeeCode")
	private String createEmployeeCode;

	@Column(value = "createDateTime")
	private Date createDateTime ;

	@Column(value = "updateEmployeeCode")
	private String updateEmployeeCode ;

	@Column(value = "updateDateTime")
	private Date updateDateTime ;


}


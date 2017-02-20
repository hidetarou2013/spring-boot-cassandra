package com.iyihua.demos.cassandra.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "memobox")
public class MemoBox {

	@PrimaryKeyColumn(name="pkey1",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
	private String pkey1;

	@PrimaryKeyColumn(name="pkey2",ordinal = 1,type = PrimaryKeyType.PARTITIONED)
	private String pkey2;

	@PrimaryKeyColumn(name = "id",ordinal = 2,type = PrimaryKeyType.CLUSTERED)
	private UUID id = UUIDs.timeBased();

	@PrimaryKeyColumn(name="name",ordinal = 3,type = PrimaryKeyType.CLUSTERED)
//	@Column(value = "name")
	private String name;

	@Column(value = "memo")
	private String memo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Column(value = "date")
	private Date date;

	public MemoBox(String pkey1,String pkey2,String name,String memo,Date date){
		this.pkey1 = pkey1;
		this.pkey2 = pkey2;
		this.name = name;
		this.memo = memo;
		this.date = date;
	}

}

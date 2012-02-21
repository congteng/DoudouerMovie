package net.doudouer.movie.datamodel;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;


public class MovieDataModel extends MySQLJDBCDataModel{
	
	private static final long serialVersionUID = -3432564987416179818L;
	
	// 保存用户对电影的评分的数据库表名
	public final static String PERFERENCETABLE = "moviePreference";
	public final static String USERID_COLUMN = "userID"; // 表中用户标识的列名
	public final static String ITEMID_COLUMN = "movieID";// 表中电影标识的列名
	public final static String PERFERENCE_COLUMN = "preference";// 表中评分的列名
	public final static String TIMESTAMP_COLUMN = "timestamp";// 表中评分的列名
	
	public MovieDataModel(DataSource dataSource) {
		super(dataSource, PERFERENCETABLE, USERID_COLUMN, ITEMID_COLUMN, PERFERENCE_COLUMN, TIMESTAMP_COLUMN);
	}

}

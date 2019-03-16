package depends.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import depends.data.bean.CompareRecord;
import depends.data.bean.DependencyRecord;
import depends.data.bean.Record;

public class DependencyDAO {	
	// 这部分代码只负责插入数据，查询在web端实现
	public static final String ADD_FILE_ID_MAPPING = "insert into file_id_mapping(id, filename, project_name) values (?,?,?)";
	public static final String ADD_RECORD = "insert into dependency_record(src, dest, version, project_name, "
			+ "import_amount, contain_amount, implement_amount, extend_amount, call_amount, parameter_amount,"
			+ "return_amount, set_amount, use_amount, create_amount, cast_amount, throw_amount, annotation_amount, "
			+ "total_amount) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	public static final String ADD_COMPARE = "insert into compare(src, dest, version, project_name, import_change, "
			+ "contain_change, implement_change, extend_change, call_change, parameter_change, return_change, "
			+ "set_change, use_change, create_change, cast_change, throw_change, annotation_change, total_change) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	Connection connection;
	
	public int insertFileIdMapping(int id, String filename, String projectName){
		int rowChanged = 0;
		try {
			connection = ConnectionUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_FILE_ID_MAPPING);
			statement.setInt(0, id);
			statement.setString(1, filename);
			statement.setString(2, projectName);
			rowChanged = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowChanged;
	}
	
	public void multiInsertFileIdMapping(String projectName, Map<String, Integer> fileUnionMap) {
		try {
			connection = ConnectionUtils.getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("insert into file_id_mapping(id, filename, project_name) values ");
			for(Map.Entry<String, Integer> entry: fileUnionMap.entrySet()) {
				stringBuilder.append("(");
				stringBuilder.append(entry.getValue()+",");
				stringBuilder.append("'"+entry.getKey()+"',");
				stringBuilder.append("'"+projectName+"'),");
			}
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
			PreparedStatement statement = connection.prepareStatement(String.valueOf(stringBuilder));
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void batchInsertFileIdMapping(String projectName, Map<String, Integer> fileUnionMap) {
		try {
			connection = ConnectionUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_FILE_ID_MAPPING);
			for(Map.Entry<String, Integer> entry: fileUnionMap.entrySet()) {
				statement.setInt(1, entry.getValue());
				statement.setString(2, entry.getKey());
				statement.setString(3, projectName);
				statement.addBatch();
			}
			statement.executeBatch();
			statement.clearBatch();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertDependencyRecord(DependencyRecord dependencyRecord){
		try {
			connection = ConnectionUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_RECORD);
			constructPreparedStatement(statement, dependencyRecord);		
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCompareRecord(CompareRecord compareRecord) {
		try {
			connection = ConnectionUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_COMPARE);
			constructPreparedStatement(statement, compareRecord);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void batchInsertDependencyRecords(List<DependencyRecord> records) {		
		try {
			connection = ConnectionUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_RECORD);
			for(int i=0; i<records.size();i++) {
				DependencyRecord record = records.get(i);
				constructPreparedStatement(statement, record);			
				statement.addBatch();
				if((i+1)%1000 == 0 || i==records.size()-1) {
					new Thread(new Runnable() {		
						@Override
						public void run() {
							try {
								statement.executeBatch();
								statement.clearBatch();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void batchInsertCompareRecords(List<Record> records) {
		try {
			connection = ConnectionUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(ADD_COMPARE);
			for(int i=0; i<records.size();i++) {
				CompareRecord record = (CompareRecord)records.get(i);
				constructPreparedStatement(statement, record);
				statement.addBatch();
				if((i+1)%1000 == 0 || i==records.size()-1) {
					new Thread(new Runnable() {		
						@Override
						public void run() {
							try {
								statement.executeBatch();
								statement.clearBatch();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void multiInsertDependencyRecords(List<DependencyRecord> dependencyRecords) {
		try {
			connection = ConnectionUtils.getConnection();
			String sql = "insert into dependency_record(src, dest, version, project_name, "
					+ "import_amount, contain_amount, implement_amount, extend_amount, call_amount, parameter_amount,"
					+ "return_amount, set_amount, use_amount, create_amount, cast_amount, throw_amount, annotation_amount, "
					+ "total_amount) values ";
			StringBuilder stringBuilder = new StringBuilder(sql);
			for(int i=0; i<dependencyRecords.size(); i++) {
				constructMultiInsertStatement(dependencyRecords.get(i), stringBuilder);
			}
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
			PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(stringBuilder));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void multiInsertCompareRecords(List<CompareRecord> compareRecords) {
		try {
			connection = ConnectionUtils.getConnection();
			String sql = "insert into compare(src, dest, version, project_name, import_change, "
					+ "contain_change, implement_change, extend_change, call_change, parameter_change, return_change, "
					+ "set_change, use_change, create_change, cast_change, throw_change, annotation_change, total_change) "
					+ "values ";
			StringBuilder stringBuilder = new StringBuilder(sql);
			for(int i=0; i<compareRecords.size(); i++) {
				constructMultiInsertStatement(compareRecords.get(i), stringBuilder);
			}
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
			PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(stringBuilder));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void constructMultiInsertStatement(Record record, StringBuilder stringBuilder) {
		stringBuilder.append("(");
		stringBuilder.append(record.getSrc()+",");
		stringBuilder.append(record.getDest()+",");
		stringBuilder.append(record.getVersion()+",");
		stringBuilder.append("'"+record.getProjectName()+"',");
		stringBuilder.append(record.getImportValue()+",");
		stringBuilder.append(record.getContainValue()+",");
		stringBuilder.append(record.getImplementValue()+",");
		stringBuilder.append(record.getExtendValue()+",");
		stringBuilder.append(record.getCallValue()+",");
		stringBuilder.append(record.getParameterValue()+",");
		stringBuilder.append(record.getReturnValue()+",");
		stringBuilder.append(record.getSetValue()+",");
		stringBuilder.append(record.getUseValue()+",");
		stringBuilder.append(record.getCreateValue()+",");
		stringBuilder.append(record.getCastValue()+",");
		stringBuilder.append(record.getThrowValue()+",");
		stringBuilder.append(record.getAnnotationValue()+",");
		stringBuilder.append(record.getTotalValue()+"),");
	}
	
	private void constructPreparedStatement(PreparedStatement statement, Record record){
		try {
			statement.setInt(1, record.getSrc());
			statement.setInt(2, record.getDest());
			statement.setInt(3, record.getVersion());
			statement.setString(4, record.getProjectName());
			statement.setInt(5, record.getImportValue());
			statement.setInt(6, record.getContainValue());
			statement.setInt(7, record.getImplementValue());
			statement.setInt(8, record.getExtendValue());
			statement.setInt(9, record.getCallValue());
			statement.setInt(10, record.getParameterValue());
			statement.setInt(11, record.getReturnValue());
			statement.setInt(12, record.getSetValue());
			statement.setInt(13, record.getUseValue());
			statement.setInt(14, record.getCreateValue());
			statement.setInt(15, record.getCastValue());
			statement.setInt(16, record.getThrowValue());
			statement.setInt(17, record.getAnnotationValue());
			statement.setInt(18, record.getTotalValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}

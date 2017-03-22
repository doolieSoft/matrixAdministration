package DAO;/* See the file "LICENSE" for the full license governing this code. */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DatabaseHelper {
	private static final String CI_TABLE = "CI_T";
	private static final String AGENT_TABLE = "AGENT_T";
	private static final String AGENT_FIELD = "ST_AGENT";
	private static final String CI_FIELD = "ST_CI";
	private static final String RATIO_FIELD = "I_RATIO";
	private static final String RATIO_TABLE = "RATIO_T";
	private static final String CONNECTION_STRING = "jdbc:sqlite:matrix.db";
	private static Connection connection = null;

	public DatabaseHelper() throws SQLException, ClassNotFoundException {
		connection = getConnection();
		if (!databaseExists())
			createDatabase();
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");
		if (connection == null)
			connection = DriverManager.getConnection(CONNECTION_STRING);
		return connection;
	}

	private void createDatabase() {
		try {
			Statement statement = connection.createStatement();
			statement
					.executeUpdate("CREATE TABLE RATIO_T (I_AGENT_ID INTEGER, I_CI_ID INTEGER, "
							+ RATIO_FIELD
							+ " INTEGER, UNIQUE(I_AGENT_ID, I_CI_ID))");
			statement
					.executeUpdate("CREATE TABLE AGENT_T (I_ID INTEGER PRIMARY KEY, ST_AGENT TEXT UNIQUE)");
			statement
					.executeUpdate("CREATE TABLE CI_T (I_ID INTEGER PRIMARY KEY, ST_CI TEXT UNIQUE)");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private boolean databaseExists() {
		try {
			Statement statement = connection.createStatement();
			statement.execute("SELECT * FROM " + RATIO_TABLE);
			statement.execute("SELECT * FROM " + AGENT_TABLE);
			statement.execute("SELECT * FROM " + CI_TABLE);
			statement.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<String> getAgentsList() {
		List<String> result = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = getStatement();
			resultSet = statement.executeQuery("SELECT " + AGENT_FIELD
					+ " FROM " + AGENT_TABLE + " ORDER BY " + AGENT_FIELD);
			while (resultSet.next()) {
				result.add(resultSet.getString(AGENT_FIELD));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Statement getStatement() throws SQLException {
		Statement statement;
		connection = DriverManager.getConnection(CONNECTION_STRING);
		statement = connection.createStatement();
		return statement;
	}

	public void addAgent(String agent) {
		Statement statement = null;
		Statement statementSelectCIs = null;
		ResultSet resultSet = null;
		try {
			statement = getStatement();
			statement.executeUpdate("INSERT INTO " + AGENT_TABLE
					+ " (ST_AGENT) VALUES ('" + agent + "')");
			ResultSet generatedKeys = statement.getGeneratedKeys();
			String agent_id = null;
			if (generatedKeys.next()) {
				agent_id = String.valueOf(generatedKeys.getInt(1));
			}
			statementSelectCIs = connection.createStatement();
			resultSet = statementSelectCIs
					.executeQuery("SELECT I_ID FROM CI_T");
			String ci_id;
			while (resultSet.next()) {
				ci_id = String.valueOf(resultSet.getInt(1));
				try {
					statement.executeUpdate("INSERT INTO " + RATIO_TABLE
							+ " (I_AGENT_ID, I_CI_ID, I_RATIO) VALUES ("
							+ agent_id + "," + ci_id + ",'0')");
				} catch (SQLException e) {
				}
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	public List<String> getCIsList() {
		List<String> result = new ArrayList<>();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = getStatement();
			rs = statement.executeQuery("SELECT ST_CI FROM "
					+ CI_TABLE + " ORDER BY " + CI_FIELD);
			while (rs.next()) {
				result.add(rs.getString(CI_FIELD));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
			return result;
		}
	}

	public void addCI(String ci) {
		Statement statement = null;
		Statement statementAgents = null;
		ResultSet rs = null;
		try {
			statement = getStatement();
			statement.executeUpdate("INSERT INTO " + CI_TABLE
					+ " (ST_CI) VALUES ('" + ci + "')");
			rs = statement.getGeneratedKeys();
			String ci_id = null;
			if (rs.next())
				ci_id = String.valueOf(rs.getInt(1));
			else {
				rs = statement.executeQuery("SELECT MAX(I_ID) FROM CI_TABLE");
				if (rs.next()) {
					ci_id = String.valueOf(rs.getInt(1));
				}
			}
			statementAgents = connection.createStatement();
			rs = statementAgents.executeQuery("SELECT I_ID FROM AGENT_T ");
			while (rs.next()) {
				String agent_id = rs.getString(1);
				try {
					statement.executeUpdate("INSERT INTO " + RATIO_TABLE
							+ " (I_AGENT_ID, I_CI_ID, I_RATIO) VALUES ("
							+ agent_id + "," + ci_id + ",'0')");
				} catch (SQLException e) {
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	public void delAgent(String agent) {
		Statement statement = null;
		Statement statementSelectCI = null;
		ResultSet resultSet = null;
		try {
			statement = getStatement();
			statementSelectCI = connection.createStatement();
			resultSet = statementSelectCI
					.executeQuery("SELECT I_ID FROM AGENT_T WHERE ST_AGENT='"
							+ agent + "'");
			int agent_id = 0;
			if (resultSet.next()) {
				agent_id = resultSet.getInt(1);
			}
			statement.executeUpdate("DELETE FROM " + AGENT_TABLE
					+ " WHERE I_ID ='" + agent_id + "'");
			statement.executeUpdate("DELETE FROM " + RATIO_TABLE
					+ " WHERE I_AGENT_ID =" + agent_id);
		} catch (SQLException e) {
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	public void delCI(String ci) {
		Statement statement = null;
		Statement statementSelectCI = null;
		ResultSet resultSet;
		try {
			statement = getStatement();
			statementSelectCI = connection.createStatement();
			resultSet = statementSelectCI
					.executeQuery("SELECT I_ID FROM CI_T WHERE ST_CI='" + ci
							+ "'");
			int ci_id = 0;
			if (resultSet.next()) {
				ci_id = resultSet.getInt(1);
			}
			statement.executeUpdate("DELETE FROM " + CI_TABLE + " WHERE I_ID ="
					+ ci_id + "");
			statement.executeUpdate("DELETE FROM " + RATIO_TABLE
					+ " WHERE I_CI_ID =" + ci_id);

		} catch (SQLException e) {
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	public void updateRatio(String agent, String ci, Integer ratio) {
		Statement insertStatement = null;
		Statement agentSelectStatement = null;
		Statement ciSelectStatement = null;
		ResultSet agentSelectResult = null;
		ResultSet ciSelectResult = null;
		try {
			agentSelectStatement = getStatement();
			agentSelectResult = agentSelectStatement
					.executeQuery("SELECT I_ID FROM " + AGENT_TABLE + " WHERE "
							+ AGENT_FIELD + "='" + agent + "'");

			if (!agentSelectResult.next())
				return;
			String agent_id = agentSelectResult.getString("I_ID");

			ciSelectStatement = connection.createStatement();
			ciSelectResult = ciSelectStatement.executeQuery("SELECT I_ID FROM "
					+ CI_TABLE + " WHERE " + CI_FIELD + "='" + ci + "'");
			if (!ciSelectResult.next())
				return;
			String ci_id = ciSelectResult.getString("I_ID");

			insertStatement = connection.createStatement();
			insertStatement.executeUpdate("UPDATE " + RATIO_TABLE
					+ " SET I_RATIO = " + ratio + " WHERE I_AGENT_ID='"
					+ agent_id + "' AND I_CI_ID= '" + ci_id + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (insertStatement != null)
					insertStatement.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	public Map<String, Integer> getAgentRatios(String ci) {
		Map<String, Integer> result = new TreeMap<>();
		Statement statement = null;
		try {
			statement = getStatement();
			ResultSet rs = statement.executeQuery("SELECT " + AGENT_FIELD
					+ " , " + RATIO_FIELD + " FROM " + RATIO_TABLE + " r,"
					+ AGENT_TABLE + " a, " + CI_TABLE + " c "
					+ " WHERE a.I_ID=r.I_AGENT_ID " + " AND c.I_ID=r.I_CI_ID "
					+ " AND c." + CI_FIELD + "='" + ci + "' " + " ORDER BY "
					+ AGENT_FIELD);
			while (rs.next()) {
				result.put(rs.getString(AGENT_FIELD), Integer.valueOf(rs.getInt(RATIO_FIELD)));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return result;
	}

	public int getSumAgent(String agent) {
		Statement statement = null;
		ResultSet resultSet = null;
		int sum = 0;
		try {
			statement = getStatement();
			resultSet = statement.executeQuery("SELECT SUM(r.I_RATIO) "
					+ " FROM RATIO_T r, AGENT_T a "
					+ " WHERE r.I_AGENT_ID = a.I_ID AND a.ST_AGENT = '"
					+ agent.toUpperCase() + "'");
			if (resultSet.next()) {
				sum = resultSet.getInt(1);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			return sum;
	}

	public int getSumCiRatios(String ci) {
		Statement statement = null;
		ResultSet resultSet = null;
		int sum = 0;
		try {
			statement = getStatement();
			resultSet = statement.executeQuery("SELECT SUM(r.I_RATIO) "
					+ " FROM RATIO_T r, CI_T c "
					+ " WHERE r.I_CI_ID = c.I_ID AND c.ST_CI = '" + ci + "'");
			if (resultSet.next()) {
				sum = resultSet.getInt(1);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				connection.close();
			} catch (Exception e) {
			}
			return sum;
		}
	}
}

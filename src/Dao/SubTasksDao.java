package Dao;

import Config.Connector;
import Model.ModelSubTasks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SubTasksDao {

    public void insert(ModelSubTasks tasks) {
        try {
            String query = "INSERT INTO subtasks (title, keterangan, is_done, todo_id ) VALUES (?,?,?,?)";

            PreparedStatement statement;
            statement = Connector.connect().prepareStatement(query);
            statement.setString(1, tasks.getTitle());
            statement.setString(2, tasks.getDescription());
            statement.setBoolean(3, tasks.getIsDone());
            statement.setInt(4, tasks.getToDoId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.println("Error: " + e);
        }
    }

    public void delete(int id ) {

        try {
            String query = "DELETE FROM subtasks WHERE id = ?";

            PreparedStatement statement;
            statement = Connector.connect().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void update(ModelSubTasks tasks) {

        try {
            String query ="Update subtasks set title = ?, keterangan = ?, is_done = ? where id = ?";

            PreparedStatement statement;
            statement = Connector.connect().prepareStatement(query);
            statement.setString(1, tasks.getTitle());
            statement.setString(2, tasks.getDescription());
            statement.setBoolean(3, tasks.getIsDone());
            statement.setInt(4, tasks.getId());
            statement.executeUpdate();
            statement.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModelSubTasks> getAll() {
        List<ModelSubTasks> subTasks =  null;
        try {
            subTasks = new ArrayList<>();
            Statement statement = Connector.connect().createStatement();
            String query = "SELECT * FROM subtasks";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ModelSubTasks subTask = new ModelSubTasks();
                subTask.setId(resultSet.getInt("id"));
                subTask.setTitle(resultSet.getString("title"));
                subTask.setDescription(resultSet.getString("keterangan"));
                subTask.setIsDone(resultSet.getBoolean("is_done"));
                subTasks.add(subTask);
            }
            resultSet.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return subTasks;
    }




}

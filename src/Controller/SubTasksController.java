package Controller;

import Dao.SubTasksDao;
import Model.ModelSubTasks;

import java.util.List;

public class SubTasksController {

    private SubTasksDao dao;

    public SubTasksController() {
        dao = new SubTasksDao();
    }

    // Mendapatkan semua subtasks
    public List<ModelSubTasks> getAllSubTasks() {
        return dao.getAll();
    }

    // Menambah subtask baru
    public void addSubTask(String title, String description, boolean isDone) {
        ModelSubTasks subTask = new ModelSubTasks();
        subTask.setTitle(title);
        subTask.setDescription(description);
        subTask.setIsDone(isDone);
        subTask.setToDoId(1);
        dao.insert(subTask);
    }

    // Menghapus subtask berdasarkan id
    public void deleteSubTask(int id) {
        dao.delete(id);
    }

    // Update subtask
    public void updateSubTask(int id, String title, String description, boolean isDone) {
        ModelSubTasks subTask = new ModelSubTasks();
        subTask.setId(id);
        subTask.setTitle(title);
        subTask.setDescription(description);
        subTask.setIsDone(isDone);
        dao.update(subTask);
    }
}

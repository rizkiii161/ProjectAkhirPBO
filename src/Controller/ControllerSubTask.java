package Controller;

import Dao.SubTasksDao;
import Model.ModelSubTasks;

import java.util.List;

public class ControllerSubTask {
    private ViewSubTask view;
    private List<ModelSubTasks> viewSubTaskList;
    private SubTasksDao subTasksDao;
    private int todoId;

    public ControllerSubTask(ViewSubTask view) {
        this.view = view;
        this.subTasksDao = new SubTasksDao();  // ✅ inisialisasi
        loadTable();
    }

    public ControllerSubTask(int todoId) {
        this.todoId = todoId;
        this.subTasksDao = new SubTasksDao();  // ✅ juga perlu inisialisasi di constructor ini
    }

    private void loadTable() {
        viewSubTaskList = subTasksDao.getAll();
        // viewSubTaskList = subTasksDao.getByTodoId(todoId); // kalau mau filter

        // lempar data ke View
//        view.setSubtaskTable(viewSubTaskList); // asumsinya View punya method ini
    }
}


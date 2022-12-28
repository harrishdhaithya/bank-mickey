package mickey.task;


import java.io.File;
import com.adventnet.taskengine.Task;
import com.adventnet.taskengine.TaskContext;
import com.adventnet.taskengine.TaskExecutionException;

public class DeleteFileTask implements Task {

    @Override
    public void executeTask(TaskContext arg0) throws TaskExecutionException {
        File f = new File("../webapps/bank1/records");
        String[] children = f.list();
        boolean success = true;
        for(String child:children){
            File f1 = new File(f, child);
            if(f1.isFile()){
                success = success&&f1.delete();
            }
            if(!success){
                throw new TaskExecutionException("Unable to Execute Task...");
            }
        }
    }

    @Override
    public void stopTask() throws TaskExecutionException {
        // TODO Auto-generated method stub
        
    }
    
}

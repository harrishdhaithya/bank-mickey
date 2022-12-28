package mickey.task;

import java.time.LocalDate;
import java.util.List;
import com.Singleton.Singleton;
import com.adventnet.taskengine.Task;
import com.adventnet.taskengine.TaskContext;
import com.adventnet.taskengine.TaskExecutionException;
import com.dao.TransactionDao;
import com.dao.UserDao;
import com.mailing.Mailer;
import com.model.Transaction;
import com.model.User;

public class MailSummaryTask implements Task {

    @Override
    public void executeTask(TaskContext arg0) throws TaskExecutionException {
        UserDao udao = Singleton.getUserDao();
        List<User> users =  udao.getAllUsers();
        TransactionDao tdao = Singleton.getTransactionDao();
        for(User user:users){
            List<Transaction> transactions = tdao.getTodaysTransByAccno(user.getAccno());
            System.out.println(user.getAccno()+" "+transactions.toString());
            if(transactions.size()>0){
                    StringBuilder sb = new StringBuilder();
                    sb.append("Transaction Summary -"+LocalDate.now().toString()+"\n");
                    sb.append("------------------------------------------------------\n");
                    double sum = 0;
                for(Transaction trans:transactions){
                    sb.append("Transaction id: "+trans.getId()+"\n");
                    sb.append("Destination Account: "+trans.getDest()+"\n");
                    sb.append("Amount Tranferred: "+trans.getAmount()+"\n");
                    sb.append("------------------------------------------------------\n");
                    sum+=trans.getAmount();
                }
                sb.append("Total Amount Transfered today: "+sum);
                boolean success = Mailer.sendMail(user.getEmail(),"Transaction Summary..." , sb.toString());
                if(!success){
                    throw new TaskExecutionException("Not able to Send Email...");
                }
            }
        }
    }

    @Override
    public void stopTask() throws TaskExecutionException {
        // TODO Auto-generated method stub
        
    }
    
}

package DAO;

import model.costumer; 
import java.util.List;

public interface CostumerDao {
    void save(costumer costumer); 
    public List<costumer> show();
    public void update(costumer costumer);
    public  void delete(String id);
    
	List<String> getAllNames();

}

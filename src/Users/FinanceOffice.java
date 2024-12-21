package Users;
import java.util.List;

public class FinanceOffice {
	 protected List<Employee> salaryInfo;
	 protected Integer finance;

	 public FinanceOffice(List<Employee> salaryInfo, Integer finance) {
	        this.salaryInfo = salaryInfo;
	        this.finance = finance;
	    }

	public Integer giveSalary() {
	        return 0;
	    }
}

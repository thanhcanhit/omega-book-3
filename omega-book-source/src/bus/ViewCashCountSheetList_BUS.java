package bus;

import java.util.ArrayList;
import entity.CashCountSheet;

public interface ViewCashCountSheetList_BUS {
	public ArrayList<CashCountSheet> getAll();

	public void GeneratePDF(CashCountSheet cash);

	public CashCountSheet getOne(String id);
}

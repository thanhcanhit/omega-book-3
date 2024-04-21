package bus;

import java.util.ArrayList;
import java.util.List;

import entity.CashCountSheet;

public interface ViewCashCountSheetList_BUS {
	public List<CashCountSheet> getAll();

	public void GeneratePDF(CashCountSheet cash);

	public CashCountSheet getOne(String id);
}

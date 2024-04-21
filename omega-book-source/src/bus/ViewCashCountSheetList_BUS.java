package bus;

import java.io.IOException;
import java.rmi.Remote;
import java.util.List;

import entity.CashCountSheet;

public interface ViewCashCountSheetList_BUS extends Remote{
	public List<CashCountSheet> getAll() throws IOException;

	public void GeneratePDF(CashCountSheet cash) throws IOException;

	public CashCountSheet getOne(String id) throws IOException;
}

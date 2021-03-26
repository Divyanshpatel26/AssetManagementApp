package com.cg.ama.service.admin;

import java.util.List;
import com.cg.ama.exception.DuplicateEntryException;
import com.cg.ama.exception.WarehouseNotFoundException;
import com.cg.ama.model.WarehouseModel;


public interface IAdminWarehouseService {
	
	WarehouseModel getWareHouseById(Long warehouseId) throws WarehouseNotFoundException;
	
	WarehouseModel addWarehouse(WarehouseModel wareHouseModel) throws DuplicateEntryException;
	
	List<WarehouseModel> getWarehouseList() throws WarehouseNotFoundException;
	
	WarehouseModel modifyWarehouse(Long warehouseId, WarehouseModel wareHouseModel) throws WarehouseNotFoundException;
	
	String deleteWarehouseById(Long warehouseId) throws WarehouseNotFoundException;
}

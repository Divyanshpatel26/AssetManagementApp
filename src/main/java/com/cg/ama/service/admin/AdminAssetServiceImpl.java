package com.cg.ama.service.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ama.exception.AssetNotFoundException;
import com.cg.ama.exception.DuplicateEntryException;
import com.cg.ama.model.AssetModel;
import com.cg.ama.repo.AssetRepo;

@Service
public class AdminAssetServiceImpl implements IAdminAssetService{
	
	@Autowired
	private EMParser parser;
	
	@Autowired
	private AssetRepo assetRepo;
	
	@Override
	public AssetModel getAssetById(Long assetId) throws AssetNotFoundException {
		if (!assetRepo.existsById(assetId)) {
			throw new AssetNotFoundException("No Asset present with the given ID");
		}
		return parser.parse((assetRepo.findById(assetId).orElse(null)));
	}
	
	@Transactional
	@Override
	public AssetModel addAsset(AssetModel assetModel) throws DuplicateEntryException {
		if(assetModel != null) {
			if (assetRepo.existsById(assetModel.getAssetId())) {
				throw new DuplicateEntryException("User already present in DB.");
			}
			assetModel = parser.parse((assetRepo.save(parser.parse(assetModel))));
		}
		return assetModel;
	}

	@Override
	public List<AssetModel> getAssetList() throws AssetNotFoundException {
		if (assetRepo.count() == 0) {
			throw new AssetNotFoundException("No asset present with the given ID");
		}
		return assetRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public String deleteAssetById(Long assetId) throws AssetNotFoundException {
		if (!assetRepo.existsById(assetId)) {
			throw new AssetNotFoundException("No Asset present with the given ID");
		}
		assetRepo.deleteById(assetId);
		return "Asset Deleted";
	}

	@Transactional
	@Override
	public AssetModel modifyAsset(Long assetId, AssetModel assetModel) throws AssetNotFoundException {
		if(assetModel != null) {
			if (!assetRepo.existsById(assetId)) {
				throw new AssetNotFoundException("Asset Not present in DB.");
			}
			assetModel = parser.parse((assetRepo.save(parser.parse(assetModel))));
		}
		return assetModel;
	}


}

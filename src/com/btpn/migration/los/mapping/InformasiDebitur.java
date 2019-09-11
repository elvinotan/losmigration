package com.btpn.migration.los.mapping;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.btpn.migration.los.bean.SpecRow;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppContact;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppDetail;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppGroupDebitur;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppLegal;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppManagement;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppProperty;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppSocialMedia;
import com.btpn.migration.los.mapping.informasidebitur.DlosAppVerificationDebitur;
import com.btpn.migration.los.mapping.informasidebitur.DlosLoanProcess;

public class InformasiDebitur implements Mapping {
	final static Logger log = Logger.getLogger(InformasiDebitur.class);
	
	private List<SpecRow> specRows = new ArrayList<SpecRow>();

	// Mencakup
	// dlos_app_detail (Master)
	// dlos_loan_process (1)
	// dlos_app_contact (*)
	// dlos_app_socialmedia (1)
	// dlos_app_groupdebitur (*)
	// dlos_app_verification_debitur (1)
	// dlos_app_legal (1)
	// dlos_app_management (*)
	// dlos_app_property (1)

	@Override
	public String[] clearTable() {
		return new String[] { 
			String.format("delete from dlos_app_property where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_management where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_legal where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_verification_debitur where created_By ='%s';", MIGRATION),
			String.format("delete from dlos_app_groupdebitur where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_socialmedia where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_contact where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_loan_process where createdBy ='%s';", MIGRATION),
			String.format("delete from dlos_app_detail where createdBy ='%s';", MIGRATION) 
		};
	}

	@Override
	public void initMapping(String filename, String lobType) {
		DlosAppDetail.migrate(filename, lobType, MIGRATION, specRows);
		DlosLoanProcess.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppContact.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppSocialMedia.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppGroupDebitur.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppVerificationDebitur.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppLegal.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppManagement.migrate(filename, lobType, MIGRATION, specRows);
		DlosAppProperty.migrate(filename, lobType, MIGRATION, specRows);
	}
	
	@Override
	public List<SpecRow> getSpecRows(String lobType) {
		return this.specRows;
	}
}
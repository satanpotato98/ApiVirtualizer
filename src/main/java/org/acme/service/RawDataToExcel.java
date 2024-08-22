package org.acme.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class RawDataToExcel {
    static String jsonString = "[{\"projectName\":\"SETU\",\"rawDatas\":[{\"reason\":null,\"flagATM\":\"1\",\"tdRenewTerm\":\"0\",\"amtMaturity\":20279,\"final_ipv4\":\"171.51.232.18\",\"flagSWPOut\":\"Y\",\"videos\":[{\"ref_id\":\"nil.nil.agent.2.nil\",\"metadata\":{\"offset\":null},\"location\":{},\"source\":\"needtoconfigure\",\"type\":null,\"attr\":\"agent\",\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/b17004ad-98da-4f81-8596-f1bd29887a3f?Expires\",\"tags\":null},{\"ref_id\":\"nil.nil.customer.2.nil\",\"metadata\":{\"offset\":null},\"location\":{},\"source\":\"needtoconfigure\",\"type\":null,\"attr\":\"customer\",\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/39cc5501-4974-4730-9876-5619b8de0b2d?Expires\",\"tags\":null}],\"Gender\":\"M\",\"PINCODE\":\"15424\",\"datNxtIntPay\":\"20241222\",\"fdaccountNumber\":\"50367969275803\",\"nomineeAge\":\"2\",\"CARE_OF\":\"needtoconfigure\",\"state\":\"WestVirginia\",\"sadatAllocated\":\"2024-08-2016:40:14\",\"locality\":\"KlockoSquare\",\"namCurrency\":\"INDIANRUPEES\",\"version\":\"v1.1\",\"customerName\":null,\"branchCode\":\"3\",\"prodChqThresholdDayLim\":\"2\",\"codProdType\":\"C\",\"codIntAcctXfer\":\"38044996386428\",\"amountHold\":152729,\"activationDate\":\"2024-08-20T16:40:15.381171457\",\"flgCustType\":\"I\",\"odLimit\":\"0\",\"flgTdsWaiver\":\"N\",\"amtTxnFeeAcy\":null,\"amtTxnLcy\":null,\"accountTitle\":null,\"houseNumber\":\"486\",\"flgCumDep\":null,\"flgAtm\":\"1\",\"poi\":\"EastSchowalter\",\"aadharno\":\"321405849499\",\"amtTxnTcy\":null,\"availableBalance\":\"5\",\"accountStatus\":\"2\",\"schema_version\":\"1.0.0\",\"houseName\":\"Brian\",\"DISTRICT\":\"RalphSkyway\",\"unclearAmount\":\"5\",\"customerId\":681815788,\"Negative_PAN_Status\":\"N\",\"email\":[\"hhh@gmail.com\"],\"flgSweepin\":\"Y\",\"tdInterestPaid\":\"0\",\"subLocality\":\"Nicolas\",\"accountLongTitle\":null,\"STREET\":\"LakeLeonardoshire\",\"accountNumber\":\"38044996386428\",\"status2\":\"completed\",\"namCcyShort\":null,\"tdStartDate\":\"20240322\",\"productCode\":\"3\",\"flgSwpoutFac\":\"N\",\"poi_district\":\"Pfefferview\",\"rMBranchCode\":6779,\"mobile_number\":[],\"PAN\":\"BSVPC2244Z\",\"subDistrict\":\"Maldives\",\"sadatAllocatedTs\":\"2024-08-2016:40:12\",\"FatherName\":\"\",\"Middle_Name\":\"Kuvalis\",\"Email\":\"needtoconfigure\",\"notes\":null,\"txtCustAdrAdd3\":null,\"PAN_Status\":\"E\",\"documents\":[{\"ref_id\":\"user.profile_report.nil.4.nil\",\"metadata\":{},\"location\":{},\"source\":\"4\",\"type\":\"profile_report\",\"attr\":null,\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/reports/f82fcade-acb3-4bca-bcbf-c119a8178a61.pdf?Expires\",\"tags\":[\"\"]}],\"txtCustAdrAdd1\":null,\"tdMatInstrCod\":\"4\",\"txtCustAdrAdd2\":null,\"language\":[\"Tamil\"],\"Name\":\"Y\",\"flgEmployee\":\"N\",\"LOCALITY\":\"KlockoSquare\",\"COUNTRY\":null,\"beneName\":null,\"odmaturityDate\":\"20Aug2025\",\"depTermUnit\":\"C\",\"pan\":\"BSVPC2244Z\",\"area\":\"RethaIslands\",\"aadhaarNo\":820216882122,\"ratConvAclcy\":null,\"flgChqGrntyFac\":\"N\",\"SEEDING_STATUS\":\"Y\",\"nomineeDetailsResponseDTO\":null,\"ctrChqThreshholdLimit\":\"2\",\"namCustShrt\":null,\"custAdrCity\":\"Seymourton\",\"tdTaxWithheld\":\"0\",\"customerShortName\":null,\"datDepDate\":\"20240322\",\"prodCtrChqThresholdLim\":\"3\",\"district\":\"RalphSkyway\",\"customerID\":681815788,\"STATE\":\"WestVirginia\",\"operationMode\":null,\"street\":\"LakeLeonardoshire\",\"tdTaxWithheld2\":null,\"ReferenceKey\":824358007617,\"ratConvTcacy\":null,\"tdAccountNo\":\"50367969275803\",\"street_dist\":\"MaggioDale\",\"custExtNo\":\"7\",\"codAutoRenewRedemOpt\":\"3\",\"custAdrZip\":null,\"intPayoutPrevYr\":null,\"flagSI\":\"Y\",\"amtTxnAcy\":null,\"codProd\":\"441\",\"subSubLocality\":\"WinfredGrove\",\"purged_at\":null,\"tdLienAmount\":\"0\",\"custPanNo\":\"BSVPC2244Z\",\"customerFullName\":null,\"mobileNumber\":\"7377877365\",\"OTP\":68924,\"reviewer_action\":\"approved\",\"ctrThreshholdDayLimit\":\"2\",\"existingCustomer\":\"N\",\"stateName\":\"WestVirginia\",\"codIntPayOpt\":\"1\",\"DOB\":\"Y\",\"panNoAvailable\":\"N\",\"projectedInt\":\"5897\",\"text\":[{\"ref_id\":\"nil.nil.location.2.nil\",\"metadata\":{},\"location\":{},\"source\":\"2\",\"type\":\"null\",\"attr\":\"location\",\"value\":null,\"tags\":null},{\"ref_id\":\"nil.nil.name.0.nil\",\"metadata\":{},\"location\":{},\"source\":\"0\",\"type\":\"null\",\"attr\":\"name\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null}],\"datRenewal\":\"20240322\",\"tasks\":[{\"result\":{\"manual_response\":{\"skill_config\":{\"instance\":[\"\\\"Normal\\\"\"],\"language\":[\"\\\"Hindi\\\"\"]},\"performed_by\":{\"performed_at\":\"2024-08-01T06:08:54Z\",\"account_id\":\"ee716670-a6b8-4581-a38d-f2e78863e792\",\"action\":\"video_call\",\"email\":\"mayur.patil12@hdfcbank.com\"},\"status_reason\":null,\"status\":\"verified\"},\"automated_response\":null},\"resources\":[\"\\\"nil.nil.customer.2.nil\\\"\",\"\\\"nil.nil.agent.2.nil\\\"\"],\"task_id\":\"5f2d8604-9676-40f5-b10f-8c0d0e8113a9\",\"task_type\":\"vkyc.assisted_vkyc\",\"tasks\":[{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null}],\"key\":\"vkyc.assisted_vkyc\",\"status\":\"completed\"}],\"referenceKey\":824358007617,\"pincode\":\"15424\",\"images\":[{\"ref_id\":\"nil.wet_signature.nil.2.nil\",\"metadata\":{},\"location\":{},\"source\":\"needtoconfigure\",\"type\":\"wet_signature\",\"attr\":null,\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/bb742457-7dfa-48d4-b3fd-8b757cc15f4e?Expires\",\"tags\":null},{\"ref_id\":\"nil.selfie.nil.2.nil\",\"metadata\":{},\"location\":{},\"source\":\"needtoconfigure\",\"type\":\"selfie\",\"attr\":null,\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/06f54b93-40da-4816-a3a8-c95e2cbc22a4?Expires\",\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"needtoconfigure\",\"type\":\"\",\"attr\":null,\"value\":\"\",\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"needtoconfigure\",\"type\":\"\",\"attr\":null,\"value\":\"\",\"tags\":null}],\"lng\":\"82.138114\",\"currentAccountBalance\":424,\"balBook\":\"4\",\"datAllocated\":\"2024-08-2016:40:12\",\"custAdrState\":\"WestVirginia\",\"completed_at\":\"2024-08-01T06:23:47Z\",\"benefAccountNo\":\"38044996386428\",\"profile_id\":\"7445ee3b-154d-4d68-8465-45c638c01d50\",\"fdaccountNo\":\"50367969275803\",\"micrCode\":\"9\",\"currentLimitAmount\":766,\"frqChqThreshholdLimit\":\"1\",\"amtTdMaturity\":\"105897\",\"city\":\"Seymourton\",\"flgNomineeName\":\"NotRegistered\",\"codRedAcctXfer\":\"38044996386428\",\"ratTdInterest\":9,\"created_at\":\"2024-08-01T05:54:16Z\",\"Value1\":\"\",\"Value2\":\"\",\"datLastIntPay\":\"\",\"ratAcctInt\":54780,\"amtTdsAcy\":\"0\",\"PAN_Title\":\"\",\"tdaccounTNo\":\"50367969275803\",\"custId\":681815788,\"codAcctNo\":923333407028,\"ctrSrlNo\":\"17\",\"intNxtAmt\":\"210\",\"ifscCode\":\"needtoconfigure\",\"interestRate\":64,\"panNo\":\"BSVPC2244Z\",\"customerGender\":\"M\",\"dateOfBirth\":\"06061996\",\"redPayMode\":\"2\",\"datAcctOpen\":\"2024-08-20T16:40:15.484353881\",\"LANDMARK\":\"needtoconfigure\",\"ethnicCode\":\"0\",\"tdBalInterest\":\"0\",\"flgJointAcct\":null,\"instance\":[\"Normal\"],\"amtTxnFeeLcy\":null,\"panStatus\":\"NonOperative\",\"maturityAmount\":57364,\"customerType\":\"N\",\"amtTdsAcy2\":\"0\",\"maturityDate\":\"20250820164012\",\"namCustFull\":null,\"datTdMaturity\":\"20241222\",\"saextRefTxnNo\":76496615,\"tag\":null,\"customerRelationship\":\"SOW\",\"lat\":\"-49.874405\",\"flgSupersaver\":\"N\",\"aadhaarReferenceNo\":824358007617,\"namBranchShrt\":null,\"tdDepositTerm\":\"900000\",\"flgAtmFac\":\"2\",\"status_detail\":null,\"codDepStat\":\"6\",\"amtTxnFeeTcy\":null,\"VILLAGE_TOWN_CITY\":\"Seymourton\",\"custAdrCntry\":null,\"balPrincipal\":40873,\"extRefTxnNo\":76496615,\"frqIntPay\":\"0\",\"indexValue\":\"38044996386428\",\"code\":null,\"depositNo\":\"1\",\"sweepinAcctNo\":null,\"First_Name\":\"Cleopatra\",\"flgChqbkFac\":\"Y\",\"BUILDING\":\"needtoconfigure\",\"cityName\":\"Seymourton\",\"Phone\":\"needtoconfigure\",\"accountNo\":\"38044996386428\",\"village\":\"mouth\",\"odinterestRate\":3,\"flgSwpinFac\":\"N\",\"user_agent\":\"Mozilla/5.0(WindowsNT10.0Win64x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/126.0.0.0Safari/537.36\",\"codDepNo\":null,\"amountUnclear\":490,\"comments\":null,\"customerTypeDesc\":\"SENIORCITIZEN=>80YEARS\",\"performed_by\":[{\"performed_at\":\"2024-08-01T06:08:54Z\",\"account_id\":\"ee716670-a6b8-4581-a38d-f2e78863e792\",\"action\":\"video_call\",\"email\":\"mayur.patil12@hdfcbank.com\"},{\"performed_at\":\"2024-08-01T06:23:47Z\",\"account_id\":\"7e812de4-f9ec-4698-b28b-5f17a19f780b\",\"action\":\"review\",\"email\":\"pratiksha.tendolkar@hdfcbank.com\"}],\"flgSeverity\":\"Y\",\"netBalance\":\"5\",\"tdOrgAmt\":\"100000\",\"branchName\":\"nagpur\",\"saaccountNo\":\"50139987930542\",\"codIntPayout\":\"2\",\"mobileNo\":\"7377877365\",\"serviceBrnCode\":\"3\",\"ethnicCodeDesc\":\"OTHER4\",\"datValueDate\":\"20240322\",\"intPayoutYtd\":\"0\",\"Last_Name\":\"Bauch\",\"balTdPrincipal\":23620,\"datAllocatedTs\":\"2024-08-2016:40:12\"},{\"reason\":null,\"flagATM\":\"1\",\"tdRenewTerm\":\"0\",\"amtMaturity\":13399,\"final_ipv4\":\"171.51.232.18\",\"flagSWPOut\":\"N\",\"videos\":[{\"ref_id\":\"nil.nil.agent.2.nil\",\"metadata\":{\"offset\":null},\"location\":{},\"source\":\"needtoconfigure\",\"type\":null,\"attr\":\"agent\",\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/b17004ad-98da-4f81-8596-f1bd29887a3f?Expires\",\"tags\":null},{\"ref_id\":\"nil.nil.customer.2.nil\",\"metadata\":{\"offset\":null},\"location\":{},\"source\":\"needtoconfigure\",\"type\":null,\"attr\":\"customer\",\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/39cc5501-4974-4730-9876-5619b8de0b2d?Expires\",\"tags\":null}],\"Gender\":\"F\",\"PINCODE\":\"57240-4179\",\"datNxtIntPay\":\"20241222\",\"fdaccountNumber\":\"50344747747060\",\"nomineeAge\":\"2\",\"CARE_OF\":\"needtoconfigure\",\"state\":\"SouthCarolina\",\"sadatAllocated\":\"2024-08-2016:40:23\",\"locality\":\"HoweMission\",\"namCurrency\":\"INDIANRUPEES\",\"version\":\"v1.1\",\"customerName\":null,\"branchCode\":\"3\",\"prodChqThresholdDayLim\":\"2\",\"codProdType\":\"C\",\"codIntAcctXfer\":\"34245323606796\",\"amountHold\":5829698,\"activationDate\":\"2024-08-20T16:40:23.280918294\",\"flgCustType\":\"I\",\"odLimit\":\"0\",\"flgTdsWaiver\":\"N\",\"amtTxnFeeAcy\":null,\"amtTxnLcy\":null,\"accountTitle\":null,\"houseNumber\":\"774\",\"flgCumDep\":null,\"flgAtm\":\"1\",\"poi\":\"WesternArizonaCollege\",\"aadharno\":\"364699179882\",\"amtTxnTcy\":null,\"availableBalance\":\"5\",\"accountStatus\":\"2\",\"schema_version\":\"1.0.0\",\"houseName\":\"Karin\",\"DISTRICT\":\"YongShores\",\"unclearAmount\":\"5\",\"customerId\":574357813,\"Negative_PAN_Status\":\"N\",\"email\":[\"hhh@gmail.com\"],\"flgSweepin\":\"N\",\"tdInterestPaid\":\"0\",\"subLocality\":\"Jacobson\",\"accountLongTitle\":null,\"STREET\":\"Crooksfurt\",\"accountNumber\":\"34245323606796\",\"status2\":\"completed\",\"namCcyShort\":null,\"tdStartDate\":\"20240322\",\"productCode\":\"3\",\"flgSwpoutFac\":\"Y\",\"poi_district\":\"LakeAnderson\",\"rMBranchCode\":9205,\"mobile_number\":[],\"PAN\":\"SEBPX3177S\",\"subDistrict\":\"BouvetIsland(Bouvetoya)\",\"sadatAllocatedTs\":\"2024-08-2016:40:22\",\"FatherName\":\"\",\"Middle_Name\":\"Cassin\",\"Email\":\"needtoconfigure\",\"notes\":null,\"txtCustAdrAdd3\":null,\"PAN_Status\":\"E\",\"documents\":[{\"ref_id\":\"user.profile_report.nil.4.nil\",\"metadata\":{},\"location\":{},\"source\":\"4\",\"type\":\"profile_report\",\"attr\":null,\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/reports/f82fcade-acb3-4bca-bcbf-c119a8178a61.pdf?Expires\",\"tags\":[\"\"]}],\"txtCustAdrAdd1\":null,\"tdMatInstrCod\":\"4\",\"txtCustAdrAdd2\":null,\"language\":[\"Tamil\"],\"Name\":\"Y\",\"flgEmployee\":\"N\",\"LOCALITY\":\"HoweMission\",\"COUNTRY\":null,\"beneName\":null,\"odmaturityDate\":\"20Aug2025\",\"depTermUnit\":\"C\",\"pan\":\"SEBPX3177S\",\"area\":\"JustinMeadow\",\"aadhaarNo\":895915148413,\"ratConvAclcy\":null,\"flgChqGrntyFac\":\"N\",\"SEEDING_STATUS\":\"Y\",\"nomineeDetailsResponseDTO\":null,\"ctrChqThreshholdLimit\":\"2\",\"namCustShrt\":null,\"custAdrCity\":\"Milesberg\",\"tdTaxWithheld\":\"0\",\"customerShortName\":null,\"datDepDate\":\"20240322\",\"prodCtrChqThresholdLim\":\"3\",\"district\":\"YongShores\",\"customerID\":574357813,\"STATE\":\"SouthCarolina\",\"operationMode\":null,\"street\":\"Crooksfurt\",\"tdTaxWithheld2\":null,\"ReferenceKey\":612909298271,\"ratConvTcacy\":null,\"tdAccountNo\":\"50344747747060\",\"street_dist\":\"CharleyManor\",\"custExtNo\":\"7\",\"codAutoRenewRedemOpt\":\"3\",\"custAdrZip\":null,\"intPayoutPrevYr\":null,\"flagSI\":\"Y\",\"amtTxnAcy\":null,\"codProd\":\"441\",\"subSubLocality\":\"SchuppePort\",\"purged_at\":null,\"tdLienAmount\":\"0\",\"custPanNo\":\"SEBPX3177S\",\"customerFullName\":null,\"mobileNumber\":\"7547299600\",\"OTP\":545414,\"reviewer_action\":\"approved\",\"ctrThreshholdDayLimit\":\"2\",\"existingCustomer\":\"N\",\"stateName\":\"SouthCarolina\",\"codIntPayOpt\":\"1\",\"DOB\":\"Y\",\"panNoAvailable\":\"N\",\"projectedInt\":\"5897\",\"text\":[{\"ref_id\":\"nil.nil.location.2.nil\",\"metadata\":{},\"location\":{},\"source\":\"2\",\"type\":\"null\",\"attr\":\"location\",\"value\":null,\"tags\":null},{\"ref_id\":\"nil.nil.name.0.nil\",\"metadata\":{},\"location\":{},\"source\":\"0\",\"type\":\"null\",\"attr\":\"name\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"\",\"type\":\"\",\"attr\":\"\",\"value\":null,\"tags\":null}],\"datRenewal\":\"20240322\",\"tasks\":[{\"result\":{\"manual_response\":{\"skill_config\":{\"instance\":[\"\\\"Normal\\\"\"],\"language\":[\"\\\"Hindi\\\"\"]},\"performed_by\":{\"performed_at\":\"2024-08-01T06:08:54Z\",\"account_id\":\"ee716670-a6b8-4581-a38d-f2e78863e792\",\"action\":\"video_call\",\"email\":\"mayur.patil12@hdfcbank.com\"},\"status_reason\":null,\"status\":\"verified\"},\"automated_response\":null},\"resources\":[\"\\\"nil.nil.customer.2.nil\\\"\",\"\\\"nil.nil.agent.2.nil\\\"\"],\"task_id\":\"5f2d8604-9676-40f5-b10f-8c0d0e8113a9\",\"task_type\":\"vkyc.assisted_vkyc\",\"tasks\":[{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null},{\"tasks\":null}],\"key\":\"vkyc.assisted_vkyc\",\"status\":\"completed\"}],\"referenceKey\":612909298271,\"pincode\":\"57240-4179\",\"images\":[{\"ref_id\":\"nil.wet_signature.nil.2.nil\",\"metadata\":{},\"location\":{},\"source\":\"needtoconfigure\",\"type\":\"wet_signature\",\"attr\":null,\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/bb742457-7dfa-48d4-b3fd-8b757cc15f4e?Expires\",\"tags\":null},{\"ref_id\":\"nil.selfie.nil.2.nil\",\"metadata\":{},\"location\":{},\"source\":\"needtoconfigure\",\"type\":\"selfie\",\"attr\":null,\"value\":\"https://ac90674bc81c.primary.kyc.idfystorage.com/06f54b93-40da-4816-a3a8-c95e2cbc22a4?Expires\",\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"needtoconfigure\",\"type\":\"\",\"attr\":null,\"value\":\"\",\"tags\":null},{\"ref_id\":\"\",\"metadata\":null,\"location\":null,\"source\":\"needtoconfigure\",\"type\":\"\",\"attr\":null,\"value\":\"\",\"tags\":null}],\"lng\":\"45.379249\",\"currentAccountBalance\":411,\"balBook\":\"4\",\"datAllocated\":\"2024-08-2016:40:22\",\"custAdrState\":\"SouthCarolina\",\"completed_at\":\"2024-08-01T06:23:47Z\",\"benefAccountNo\":\"34245323606796\",\"profile_id\":\"7445ee3b-154d-4d68-8465-45c638c01d50\",\"fdaccountNo\":\"50344747747060\",\"micrCode\":\"9\",\"currentLimitAmount\":816,\"frqChqThreshholdLimit\":\"1\",\"amtTdMaturity\":\"105897\",\"city\":\"Milesberg\",\"flgNomineeName\":\"NotRegistered\",\"codRedAcctXfer\":\"34245323606796\",\"ratTdInterest\":9,\"created_at\":\"2024-08-01T05:54:16Z\",\"Value1\":\"\",\"Value2\":\"\",\"datLastIntPay\":\"\",\"ratAcctInt\":82008,\"amtTdsAcy\":\"0\",\"PAN_Title\":\"\",\"tdaccounTNo\":\"50344747747060\",\"custId\":574357813,\"codAcctNo\":46986181656657,\"ctrSrlNo\":\"17\",\"intNxtAmt\":\"210\",\"ifscCode\":\"needtoconfigure\",\"interestRate\":6,\"panNo\":\"SEBPX3177S\",\"customerGender\":\"F\",\"dateOfBirth\":\"15111959\",\"redPayMode\":\"2\",\"datAcctOpen\":\"2024-08-20T16:40:23.372273666\",\"LANDMARK\":\"needtoconfigure\",\"ethnicCode\":\"1\",\"tdBalInterest\":\"0\",\"flgJointAcct\":null,\"instance\":[\"Normal\"],\"amtTxnFeeLcy\":null,\"panStatus\":\"Operative\",\"maturityAmount\":14562,\"customerType\":\"N\",\"amtTdsAcy2\":\"0\",\"maturityDate\":\"20250820164022\",\"namCustFull\":null,\"datTdMaturity\":\"20241222\",\"saextRefTxnNo\":86095675,\"tag\":null,\"customerRelationship\":\"SOW\",\"lat\":\"26.505959\",\"flgSupersaver\":\"N\",\"aadhaarReferenceNo\":612909298271,\"namBranchShrt\":null,\"tdDepositTerm\":\"900000\",\"flgAtmFac\":\"2\",\"status_detail\":null,\"codDepStat\":\"6\",\"amtTxnFeeTcy\":null,\"VILLAGE_TOWN_CITY\":\"Milesberg\",\"custAdrCntry\":null,\"balPrincipal\":89317,\"extRefTxnNo\":86095675,\"frqIntPay\":\"0\",\"indexValue\":\"34245323606796\",\"code\":null,\"depositNo\":\"1\",\"sweepinAcctNo\":null,\"First_Name\":\"Jeramy\",\"flgChqbkFac\":\"N\",\"BUILDING\":\"needtoconfigure\",\"cityName\":\"Milesberg\",\"Phone\":\"needtoconfigure\",\"accountNo\":\"34245323606796\",\"village\":\"town\",\"odinterestRate\":10,\"flgSwpinFac\":\"Y\",\"user_agent\":\"Mozilla/5.0(WindowsNT10.0Win64x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/126.0.0.0Safari/537.36\",\"codDepNo\":null,\"amountUnclear\":433,\"comments\":null,\"customerTypeDesc\":\"SENIORCITIZEN=>80YEARS\",\"performed_by\":[{\"performed_at\":\"2024-08-01T06:08:54Z\",\"account_id\":\"ee716670-a6b8-4581-a38d-f2e78863e792\",\"action\":\"video_call\",\"email\":\"mayur.patil12@hdfcbank.com\"},{\"performed_at\":\"2024-08-01T06:23:47Z\",\"account_id\":\"7e812de4-f9ec-4698-b28b-5f17a19f780b\",\"action\":\"review\",\"email\":\"pratiksha.tendolkar@hdfcbank.com\"}],\"flgSeverity\":\"N\",\"netBalance\":\"5\",\"tdOrgAmt\":\"100000\",\"branchName\":\"pune\",\"saaccountNo\":\"50174252861240\",\"codIntPayout\":\"2\",\"mobileNo\":\"7547299600\",\"serviceBrnCode\":\"3\",\"ethnicCodeDesc\":\"OTHER12\",\"datValueDate\":\"20240322\",\"intPayoutYtd\":\"0\",\"Last_Name\":\"Champlin\",\"balTdPrincipal\":91883,\"datAllocatedTs\":\"2024-08-2016:40:22\"}]}]";


    public static Map rawDataToMap(){

        try {
            // Initialize ObjectMapper and read the JSON string
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonString);

            // Parse the JSON and store key-value pairs in a map
            Map<String, String> keyValueMap = parseJsonNode(rootNode, "");

            // Print the key-value map
            //keyValueMap.forEach((key, value) -> System.out.println(key + ": " + value));
            writeMapToExcel(keyValueMap, "RawDataToExcel.xlsx");
            return keyValueMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Function to parse the JSON nodes and return a map of key-value pairs
    private static Map<String, String> parseJsonNode(JsonNode node, String path) {
        Map<String, String> keyValueMap = new LinkedHashMap<>();

        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                keyValueMap.putAll(parseJsonNode(field.getValue(), buildPath(path, field.getKey())));
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                keyValueMap.putAll(parseJsonNode(node.get(i), buildPath(path, "[" + i + "]")));
            }
        } else {
            keyValueMap.put(path, node.asText());
        }

        return keyValueMap;
    }

    // Helper function to build the JSON path
    private static String buildPath(String path, String key) {
        if (path.isEmpty()) {
            return key;
        } else {
            return path + "." + key;
        }
    }

    private static void writeMapToExcel(Map<String, String> map, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Create the first row for keys
        Row keyRow = sheet.createRow(0);
        // Create the second row for values
        Row valueRow = sheet.createRow(1);

        int col = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            keyRow.createCell(col).setCellValue(entry.getKey());
            valueRow.createCell(col).setCellValue(entry.getValue());
            col++;
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }

        // Close the workbook
        workbook.close();
    }
}
<#list tablesList as table>
DROP STREAM xhh_ssfk_stg.derived_scoring_${table.tableName};
CREATE stream xhh_ssfk_stg.derived_scoring_${table.tableName} AS
SELECT
c.user_id as user_id,
c.order_id as order_id,
c.channel_id as channel_id,
c.batch_id as batch_id,
concat('[',concat_ws(',',collect_list(c.jsonPart)),']') AS jsonResult
FROM (
SELECT user_id,order_id,channel_id,batch_id,
concat('{"${table.resultInfo}'"}'
) AS jsonPart
FROM xhh_ssfk_stg.${table.streamName}) c
GROUP BY c.user_id,c.order_id,c.channel_id,c.batch_id;


</#list>


DROP STREAM xhh_ssfk_stg.drive_stream_in_featurecsriskquota;
CREATE STREAM xhh_ssfk_stg.drive_stream_in_featurecsriskquota AS
SELECT concat('{"audit_object":',t1.audit_object,
',"data" :{',
-- 用户
'"expired_days" :',nvl(t3.expired_days,'null'),',',
'"ocr_age" :',nvl(t3.ocr_age,'null'),',',
'"ocr_issued_org" :"',t3.ocr_issued_org,'",',
'"contact_phone_other_user_number_3hours" :',nvl(t3.contact_phone_other_user_number_3hours,'null'),',',
'"contact_phone_other_user_number_72hours" :',nvl(t3.contact_phone_other_user_number_72hours,'null'),',',
'"contact_phone_other_user_number_1month" :',nvl(t3.contact_phone_other_user_number_1month,'null'),',',
'"consistency_city_live_gps" :',nvl(t3.consistency_city_live_gps,'null'),',',
'"consistency_city_company_gps" :',nvl(t3.consistency_city_company_gps,'null'),',',
'"consistency_city_live_company" :',nvl(t3.consistency_city_live_company,'null'),',',
'"city_numbere_live_company_gps" :',nvl(t3.city_numbere_live_company_gps,'null'),',',
-- 信用卡
'"name_match_tag" :',nvl(t2.name_match_tag,'null'),',',
'"name_illegibility_match_tag" :',nvl(t2.name_illegibility_match_tag,'null'),',',
'"user_number_ralated_email" :',nvl(t2.user_number_ralated_email,'null'),',',
'"user_number_ralated_card_no" :',nvl(t2.user_number_ralated_card_no,'null'),',',
'"max_month_of_bill" :',nvl(t2.max_month_of_bill,'null'),',',
'"max_credit_limit" :',nvl(t2.max_credit_limit,'null'),',',
'"total_card_number_3_month" :',nvl(t2.total_card_number_3_month,'null'),',',
'"total_card_number_6_month" :',nvl(t2.total_card_number_6_month,'null'),',',
'"total_card_number_12_month" :',nvl(t2.total_card_number_12_month,'null'),',',
'"total_bank_number_12_month" :',nvl(t2.total_bank_number_12_month,'null'),',',
'"overdue_cards_1_month" :',nvl(t2.overdue_cards_1_month,'null'),',',
'"overdue_times_6_month" :',nvl(t2.overdue_times_6_month,'null'),',',
'"overdue_times_12_month" :',nvl(t2.overdue_times_12_month,'null'),',',
'"overdue_amount_1_month" :',nvl(t2.overdue_amount_1_month,'null'),',',
'"overdue_amount_max_1_month" :',nvl(t2.overdue_amount_max_1_month,'null'),',',
'"total_overdue_amount_12_month" :',nvl(t2.total_overdue_amount_12_month,'null'),',',
'"stage_tag_1_month" :',nvl(t2.stage_tag_1_month,'null'),',',
'"card_proportion_credit_80pct" :',nvl(t2.card_proportion_credit_80pct,'null'),',',
'"card_proportion_credit_90pct" :',nvl(t2.card_proportion_credit_90pct,'null'),',',
'"average_credit_limit_ratio_3_month" :',nvl(t2.average_credit_limit_ratio_3_month,'null'),',',
'"average_credit_limit_ratio_6_month" :',nvl(t2.average_credit_limit_ratio_6_month,'null'),',',
'"credit_limit_ratio_1_month" :',nvl(t2.credit_limit_ratio_1_month,'null'),',',
'"average_repay_rate_3_month" :',nvl(t2.average_repay_rate_3_month,'null'),',',
'"average_repay_rate_6_month" :',nvl(t2.average_repay_rate_6_month,'null'),',',
'"repay_rate_1_month" :',nvl(t2.repay_rate_1_month,'null'),',',
'"total_overdue_fee_1_month" :',nvl(t2.total_overdue_fee_1_month,'null'),',',
'"total_overdue_fee_3_month" :',nvl(t2.total_overdue_fee_3_month,'null'),',',
'"total_overdue_fee_6_month" :',nvl(t2.total_overdue_fee_6_month,'null'),',',
'"max_overdue_fee_3_month" :',nvl(t2.max_overdue_fee_3_month,'null'),',',
'"credit_limit_available_negative_3_month" :',nvl(t2.credit_limit_available_negative_3_month,'null'),',',
'"credit_limit_available_negative_6_month" :',nvl(t2.credit_limit_available_negative_6_month,'null'),',',
'"credit_limit_available_negative_proportion_1_month" :',nvl(t2.credit_limit_available_negative_proportion_1_month,'null'),',',
'"credit_limit_available_negative_proportion_3_month" :',nvl(t2.credit_limit_available_negative_proportion_3_month,'null'),',',
'"non_full_payment_proportion_3_month" :',nvl(t2.non_full_payment_proportion_3_month,'null'),',',
'"non_full_payment_proportion_6_month" :',nvl(t2.non_full_payment_proportion_6_month,'null'),',',
'"non_full_payment_proportion_1_month" :',nvl(t2.non_full_payment_proportion_1_month,'null'),',',
'"m2_above_times_12_month" :',nvl(t2.m2_above_times_12_month,'null'),',',
'"m3_above_times_12_month" :',nvl(t2.m3_above_times_12_month,'null'),',',
-- 设备基本信息
'"gps_province" :"',t4.gps_province,'",',
'"gps_city" :"',t4.gps_city,'",',
'"gps_country" :"',t4.gps_country,'",',
'"simulation" :',nvl(t4.simulation,'null'),',',
'"udid_number_relate_user_7days" :',nvl(t4.udid_number_relate_user_7days,'null'),',',
'"user_number_relate_imei_3hours" :',nvl(t4.user_number_relate_imei_3hours,'null'),',',
'"user_number_relate_imei_6hours" :',nvl(t4.user_number_relate_imei_6hours,'null'),',',
'"user_number_relate_imei_7days" :',nvl(t4.user_number_relate_imei_7days,'null'),',',
'"user_number_relate_imei_30days" :',nvl(t4.user_number_relate_imei_30days,'null'),',',
'"user_number_relate_udid_3hours" :',nvl(t4.user_number_relate_udid_3hours,'null'),',',
'"user_number_relate_udid_6hours" :',nvl(t4.user_number_relate_udid_6hours,'null'),',',
'"user_number_relate_udid_7days" :',nvl(t4.user_number_relate_udid_7days,'null'),',',
'"user_number_relate_udid_30days" :',nvl(t4.user_number_relate_udid_30days,'null'),',',
'"user_number_relate_udid_3month" :',nvl(t4.user_number_relate_udid_3month,'null'),',',
'"user_number_relate_ip_24hours" :',nvl(t4.user_number_relate_ip_24hours,'null'),',',
'"user_number_relate_ip_7days" :',nvl(t4.user_number_relate_ip_7days,'null'),',',
'"user_number_relate_ip_30days" :',nvl(t4.user_number_relate_ip_30days,'null'),',',
-- 通讯录
'"contactor_number" :',nvl(t5.contactor_number,'null'),',',
'"anonymity_contactor_proportion" :',nvl(t5.anonymity_contactor_proportion,'null'),',',
'"short_no_contactor_proportion" :',nvl(t5.short_no_contactor_proportion,'null'),',',
'"sensitive_contactor_proportion" :',nvl(t5.sensitive_contactor_proportion,'null'),',',
'"emergency_number_contactlist" :',nvl(t5.emergency_number_contactlist,'null'),',',
'"emergency_name_inconsistency_contactlist" :',nvl(t5.emergency_name_inconsistency_contactlist,'null'),',',
-- 电话邦
'"conclusion_of_user_name_check" :',nvl(t6.conclusion_of_user_name_check,'null'),',',
'"conclusion_of_ocr_name_check" :',nvl(t6.conclusion_of_ocr_name_check,'null'),',',
'"conclusion_of_user_idcard_check" :',nvl(t6.conclusion_of_user_idcard_check,'null'),',',
'"conclusion_of_ocr_idcard_check" :',nvl(t6.conclusion_of_ocr_idcard_check,'null'),',',
'"conclusion_of_user_address_check" :',nvl(t6.conclusion_of_user_address_check,'null'),',',
'"conclusion_of_gps_address_check" :',nvl(t6.conclusion_of_gps_address_check,'null'),',',
'"len_open_time" :',nvl(t6.len_open_time,'null'),',',
'"age_openlength_diff" :',nvl(t6.age_openlength_diff,'null'),',',
'"tel_is_abnormal" :',nvl(t6.tel_is_abnormal,'null'),',',
'"mergency_tel_is_abnormal" :',nvl(t6.mergency_tel_is_abnormal,'null'),',',
'"tel_mergency_tel_both_0_times" :',nvl(t6.tel_mergency_tel_both_0_times,'null'),',',
'"mergency_tel_not0times_counts" :',nvl(t6.mergency_tel_not0times_counts,'null'),',',
'"tel_mergency_call_times" :',nvl(t6.tel_mergency_call_times,'null'),',',
'"tel_mergency_avg_length" :',nvl(t6.tel_mergency_avg_length,'null'),',',
'"blank_times_count_30day" :',nvl(t6.blank_times_count_30day,'null'),',',
'"blank_times_count_90day" :',nvl(t6.blank_times_count_90day,'null'),',',
'"blank_times_count_180day" :',nvl(t6.blank_times_count_180day,'null'),',',
'"blank_times_count_max" :',nvl(t6.blank_times_count_max,'null'),',',
'"times_per_mon_10_times" :',nvl(t6.times_per_mon_10_times,'null'),',',
'"law_call_times" :',nvl(t6.law_call_times,'null'),',',
'"night_calls_percent" :',nvl(t6.night_calls_percent,'null'),',',
'"evening_calls_percent" :',nvl(t6.evening_calls_percent,'null'),',',
'"both_side_calls_count" :',nvl(t6.both_side_calls_count,'null'),',',
'"both_side_calls_percent" :',nvl(t6.both_side_calls_percent,'null'),',',
'"call_tel_sum" :',nvl(t6.call_tel_sum,'null'),',',
'"cuishou_nums_tel" :',nvl(t6.cuishou_nums_tel,'null'),',',
'"cuishou_call_in_times" :',nvl(t6.cuishou_call_in_times,'null'),',',
'"cuishou_call_in_length" :',nvl(t6.cuishou_call_in_length,'null'),',',
'"cuishou_most_times_by_tel_times" :',nvl(t6.cuishou_most_times_by_tel_times,'null'),',',
'"cuishou_up_2_times_by_tel_counts" :',nvl(t6.cuishou_up_2_times_by_tel_counts,'null'),',',
'"cuishou_last7day_times" :',nvl(t6.cuishou_last7day_times,'null'),',',
'"cuishou_last7_30day_times" :',nvl(t6.cuishou_last7_30day_times,'null'),',',
'"cuishou_last30day_times" :',nvl(t6.cuishou_last30day_times,'null'),',',
'"cuishou_last30_60day_times" :',nvl(t6.cuishou_last30_60day_times,'null'),',',
'"cuishou_last60day_times" :',nvl(t6.cuishou_last60day_times,'null'),',',
'"cuishou_last60_90day_times" :',nvl(t6.cuishou_last60_90day_times,'null'),',',
'"cuishou_last90day_times" :',nvl(t6.cuishou_last90day_times,'null'),',',
'"cuishou_last90_120day_times" :',nvl(t6.cuishou_last90_120day_times,'null'),',',
'"cuishou_last120day_times" :',nvl(t6.cuishou_last120day_times,'null'),',',
'"abnormal_vipcontact_counts" :',nvl(t6.abnormal_vipcontact_counts,'null'),',',
'"abnormal_vipcontact_percent" :',nvl(t6.abnormal_vipcontact_percent,'null'),',',
'"dubo_last3month_call_out_times" :',nvl(t6.dubo_last3month_call_out_times,'null'),',',
'"dubo_last3month_call_out_length" :',nvl(t6.dubo_last3month_call_out_length,'null'),',',
'"dubo_last3month_call_times" :',nvl(t6.dubo_last3month_call_times,'null'),',',
'"dubo_last3month_call_length" :',nvl(t6.dubo_last3month_call_length,'null'),',',
'"daikuan_call_times" :',nvl(t6.daikuan_call_times,'null'),',',
'"daikuan_call_length" :',nvl(t6.daikuan_call_length,'null'),',',
'"hujin_call_times" :',nvl(t6.hujin_call_times,'null'),',',
'"hujin_call_length" :',nvl(t6.hujin_call_length,'null'),',',
'"xinyongka_call_out_times" :',nvl(t6.xinyongka_call_out_times,'null'),',',
'"xinyongka_call_out_length" :',nvl(t6.xinyongka_call_out_length,'null'),',',
'"xinyongka_call_times" :',nvl(t6.xinyongka_call_times,'null'),',',
'"xinyongka_call_length" :',nvl(t6.xinyongka_call_length,'null'),',',
'"bank_call_out_times" :',nvl(t6.bank_call_out_times,'null'),',',
'"bank_call_out_length" :',nvl(t6.bank_call_out_length,'null'),',',
'"bank_call_times" :',nvl(t6.bank_call_times,'null'),',',
'"bank_call_length" :',nvl(t6.bank_call_length,'null'),',',
'"xiaohao_call_times" :',nvl(t6.xiaohao_call_times,'null'),',',
'"xiaohao_call_length" :',nvl(t6.xiaohao_call_length,'null'),',',
'"conclusion_of_region_check" :',nvl(t6.conclusion_of_region_check,'null'),',',
'"result_of_region_check" :',nvl(t6.result_of_region_check,'null'),',',
'"mobile_mergency_tel_is_same" :',nvl(t6.mobile_mergency_tel_is_same,'null'),',',
'"mobile_related_tel_counts" :',nvl(t6.mobile_related_tel_counts,'null'),',',
'"related_tel_name_is_same" :',nvl(t6.related_tel_name_is_same,'null'),',',
'"mobile_related_mergency_tel_counts" :',nvl(t6.mobile_related_mergency_tel_counts,'null'),',',
'"related_mergency_name_is_same" :',nvl(t6.related_mergency_name_is_same,'null'),',',
'"detection_result" :',nvl(t6.detection_result,'null'),',',
'"yisicuishou_last6month_tel_counts" :',nvl(t6.yisicuishou_last6month_tel_counts,'null'),',',
'"yisicuishou_last6month_call_in_times" :',nvl(t6.yisicuishou_last6month_call_in_times,'null'),',',
'"yisicuishou_last6month_call_in_length" :',nvl(t6.yisicuishou_last6month_call_in_length,'null'),',',
'"yisicuishou_last6month_most_times_by_tel" :',nvl(t6.yisicuishou_last6month_most_times_by_tel,'null'),',',
'"yisicuishou_last6month_up_2_times_by_tel" :',nvl(t6.yisicuishou_last6month_up_2_times_by_tel,'null'),',',
'"yisicuishou_last7day_times" :',nvl(t6.yisicuishou_last7day_times,'null'),',',
'"yisicuishou_last30day_times" :',nvl(t6.yisicuishou_last30day_times,'null'),',',
'"yisicuishou_last60day_times" :',nvl(t6.yisicuishou_last60day_times,'null'),',',
'"yisicuishou_last90day_times" :',nvl(t6.yisicuishou_last90day_times,'null'),',',
'"yisicuishou_last120day_times" :',nvl(t6.yisicuishou_last120day_times,'null'),',',
'"totalcall_last1month_times" :',nvl(t6.totalcall_last1month_times,'null'),',',
'"totalcall_last3month_times" :',nvl(t6.totalcall_last3month_times,'null'),',',
'"tel_times_top10_avg_length" :',nvl(t6.tel_times_top10_avg_length,'null'),',',
'"totalcall_last3month_avg_times" :',nvl(t6.totalcall_last3month_avg_times,'null'),',',
'"totalcall_last1month_avg_length" :',nvl(t6.totalcall_last1month_avg_length,'null'),',',
'"totalcall_last3month_avg_length" :',nvl(t6.totalcall_last3month_avg_length,'null'),',',
'"totalcall_last3month_times_desc_trend" :',nvl(t6.totalcall_last3month_times_desc_trend,'null'),',',
'"totalcall_last3month_times_asc_trend" :',nvl(t6.totalcall_last3month_times_asc_trend,'null'),',',
'"call_out_times_last6month_percent" :',nvl(t6.call_out_times_last6month_percent,'null'),',',
'"call_in_times_last6month_percent" :',nvl(t6.call_in_times_last6month_percent,'null'),',',
'"bill_change_rate_last6month" :',nvl(t6.bill_change_rate_last6month,'null'),',',
'"valid_calls_percent" :',nvl(t6.valid_calls_percent,'null'),',',
'"len_open_time_null" :',nvl(t6.len_open_time_null,'null'),',',
'"mobile_calls_percent" :',nvl(t6.mobile_calls_percent,'null'),',',
'"mobile_calls_contacts_length" :',nvl(t6.mobile_calls_contacts_length,'null'),',',
'"mobile_contacts_percent" :',nvl(t6.mobile_contacts_percent,'null'),'},',${resultStr},
'}}') as csriskquota_json
FROM (
       SELECT
		get_json_object(json_str,'$.context.user_id') AS user_id,
		get_json_object(json_str,'$.context.order_id') AS order_id,
		get_json_object(json_str,'$.context.channel_id') AS channel_id,
		get_json_object(json_str,'$.context.batch_id') AS batch_id,
		get_json_object(json_str,'$.audit_object') AS audit_object
		FROM xhh_ssfk_stg.input_stream_ssfk
		) t1
LEFT JOIN xhh_ssfk_stg.derived_stream_zeusCreditcardFeature t2
ON t1.order_id = t2.order_id AND t1.user_id = t2.user_id AND t1.channel_id = t2.channel_id AND t1.batch_id = t2.batch_id
LEFT JOIN xhh_ssfk_stg.derived_stream_zeusUserApplyFeature t3
ON t1.order_id = t3.order_id AND t1.user_id = t3.user_id AND t1.channel_id = t3.channel_id AND t1.batch_id = t3.batch_id
LEFT JOIN xhh_ssfk_stg.derived_stream_zeus_equipment_feature t4
ON t1.order_id = t4.order_id AND t1.user_id = t4.user_id AND t1.channel_id = t4.channel_id AND t1.batch_id = t4.batch_id
LEFT JOIN xhh_ssfk_stg.derived_stream_zeus_contactlist_feature t5
ON t1.order_id = t5.order_id AND t1.user_id = t5.user_id AND t1.channel_id = t5.channel_id AND t1.batch_id = t5.batch_id
LEFT JOIN xhh_ssfk_stg.derived_stream_zeusbmpafeature t6
ON t1.order_id = t6.order_id AND t1.user_id = t6.user_id AND t1.channel_id = t6.channel_id
<#list tablesList as table>
LEFT JOIN xhh_ssfk_stg.derived_scoring_${table.tableName} AS derived_scoring_${table.tableName}
ON t1.order_id = derived_scoring_${table.tableName}.order_id
AND t1.user_id = derived_scoring_${table.tableName}.user_id
AND t1.channel_id = derived_scoring_${table.tableName}.channel_id
AND t1.batch_id = derived_scoring_${table.tableName}.batch_id
</#list>;

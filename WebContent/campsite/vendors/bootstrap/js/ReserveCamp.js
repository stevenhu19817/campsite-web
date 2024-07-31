$(function() {
	var date = new Date();
	$('input[name="datefilter"]').daterangepicker(
			{
				autoUpdateInput : false,
				"alwaysShowCalendars" : true,
				opens : "right",
				startDate : date,
				endDate : moment(date).add(2, 'days'),
				minDate : date,
				maxDate : moment(date).add(60, 'days'),
				autoApply : true,
				locale : {
					format : "YYYY/MM/DD",
					separator : " ~ ",
					applyLabel : "確定",
					cancelLabel : "清除",
					fromLabel : "開始日期",
					toLabel : "結束日期",
					daysOfWeek : [ "日", "一", "二", "三", "四", "五", "六" ],
					monthNames : [ "1月", "2月", "3月", "4月", "5月", "6月", "7月",
							"8月", "9月", "10月", "11月", "12月" ],
					firstDay : 1
				}

			});

	$('input[name="datefilter"]').on(
			'apply.daterangepicker',
			function(ev, picker) {
				$(this).val(
						picker.startDate.format('YYYY/MM/DD') + ' - '
								+ picker.endDate.format('YYYY/MM/DD'));
			});

	$('input[name="datefilter"]').on('cancel.daterangepicker',
			function(ev, picker) {
				$(this).val('');
			});

});
$('.feat-btn').click(function() {
    $('nav ul.feat-show').toggleClass("show");
});
$('.serv-btn').click(function() {
    $('nav ul.serv-show').toggleClass("show1");
});
$('.bom-btn').click(function() {
    $('nav ul.bom-show').toggleClass("show2");
});
$('.mky-btn').click(function() {
    $('nav ul.mky-show').toggleClass("show3");
});
//$("a.member_list").on("click", function() {
//    $(window).attr('location', 'member.jsp');
//});
//$("a.camp_list").on("click", function() {
//    $(window).attr('location', 'camp.jsp');
//});
//$("a.manager_list").on("click",function(){
//	 $(window).attr('location', 'manager.jsp');
//});
//$("a.camp_order").on("click", function(){
//	$(window).attr('location', 'campOrder.jsp');
//});
$("h1").on("click", function() {
    $(window).attr('location', 'home.jsp');
});
$(".min_picture").on("click", function() {
    $(window).attr('location', 'home.jsp');
});

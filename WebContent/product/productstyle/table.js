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
$("a.product_list").on("click", function() {

});
$("a.alert_managament").on("click", function() {
    $(window).attr('location', 'alert.html');
});
$("a.camp_list").on("click", function() {
    $(window).attr('location', 'camp.html');
});

$(".min_picture").on("click", function() {
    $(window).attr('location', 'home.jsp');
});

$("button.btny").on("click", function() {
    $("div.pop").show();
});
$("button.btny").on("click", function(){
    $("div.bg").toggleClass("-on");
});

$("button.button_editok").on("click", function() {
    $("div.pop").hide();
});
$("button.button_editok").on("click", function() {
//    alert("123");
	$("div.bg").removeClass("-on");
});
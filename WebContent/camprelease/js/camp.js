function upload(){
  //get input form the text inpot
  var fileinput = document.getElementById("finput");
  var filename = fileinput.value;
  var image = new SimpleImage(fileinput);
  //Get canvas
  var canvas = document.getElementById("can1");
  //draw image on canvas
  image.drawTo(canvas);
  //Alert display text
  alert("you choose " + filename)
}





//window.addEventListener("load", function(e){
//  
//  var c_name_el = document.getElementById("c_name");
//  var c_intr_el = document.getElementById("c_intr");
//  var c_price_el = document.getElementById("c_price");
//  var c_choose_el = document.getElementById("c_choose");
//  var finput_el = document.getElementById("finput");
//  var can1_el = document.getElementById("can1");
//  var the_form = document.getElementById("the_form");
//  var btn_submit_el = document.getElementById("btn_submit");
//  
//
//
//  // reset 按鈕按下去的時候，觸發
//  the_form.addEventListener("reset", function(){
//    // c_price_el.innerHTML = "100";
//    // check_geolocation_enabled();
//    finput_el.innerHTML = '<canvas id="can1"></canvas>';
//    sessionStorage.clear();
//  });
//
//  btn_submit_el.addEventListener("click", function(e){
//    e.preventDefault();
//
//    var send_data = {};
//
//    send_data.c_name = c_name_el.value;
//    send_data.c_price = c_price_el.value;
//    send_data.c_intr = c_intr_el.value;
//    send_data.c_choose = c_choose_el.value;
//    
//
//    var img_base64_el = document.querySelector(".can1");
//    if(img_base64_el){
//      send_data.img_base64 = img_base64_el.getAttribute("src");
//    }
//
//
//    sessionStorage.setItem("form_data", JSON.stringify(send_data));
//    location.href="../campconfirm.html";
//  });
//
//
//
//  // =========================== session 裡的資料回寫到表單中 ========================= //
//  var recovery_data =  function(){
//    if(sessionStorage.getItem("form_data") != null){
//      var form_data = JSON.parse(sessionStorage.getItem("form_data"));
//      console.log(form_data);
//      c_name_el.value = form_data.c_name;
//      c_intr_el.value = form_data.c_intr;
//      c_price_el.innerHTML = form_data.c_price;
//      c_choose_el.innerHTML = form_data.c_choose;
//      // lng_el.value = form_data.position.lng;
//      // lat_el.value = form_data.position.lat;
//      finput_el.innerHTML = "<canvas id='can1' src='" + form_data.img_base64 + "'>";
//    }
//
//  };
//
//  recovery_data();
//});

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺首頁</title>
 <script src="../js/jquery.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/home.css">
    <style type="text/css">
    
    </style>
</head>
<body>
 <div class="container" id="wrapper">
        <nav class="sidebar" id="sidebar-wrapper">
            <div class="min_picture">
                <h1>後臺管理</h1>
            </div>
            <ul>
                <li>
                    <a href="#" class="feat-btn">帳號管理
                        <span class="fas fa-caret-down first"></span>
                    </a>
                    <ul class="feat-show">
                        <li><a href="<%=request.getContextPath()%>/backendLogin/member.jsp" class="member_list">會員帳號管理</a></li>
                    </ul>
                     <ul class="feat-show">
                        <li><a href="<%=request.getContextPath()%>/backendLogin/manager.jsp" class="manager_list">管理員帳號管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="serv-btn">商品管理
                        <span class="fas fa-caret-down second"></span> 
                    </a>
                    <ul class="serv-show">
                 <li><a href="<%=request.getContextPath()%>/product/selectAll.jsp" class="product_list">商品資料表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#1" class="bom-btn">營地管理
                        <span class="fas fa-caret-down second_1"></span> 
                        </a>

                    <ul class="bom-show">
                        <li><a href="<%=request.getContextPath()%>/backendLogin/camp.jsp" class="camp_list">營地列表</a></li>
                       <li><a href="<%=request.getContextPath()%>/backendLogin/campOrder.jsp"class="camp_order">營地訂單</a></li>
                        <li><a href="<%=request.getContextPath()%>/backendLogin/alert.jsp" class="alert_managament">檢舉管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="mky-btn">商城管理
                        <span class="fas fa-caret-down second_2"></span> 
                    </a>
                    <ul class="mky-show">
                        <li><a href="<%=request.getContextPath()%>/emodr/listAllEmodr.jsp" "shopping_list">商城訂單</a></li>
                    </ul>
                </li>
                <li>
                <form METHOD="get" ACTION="<%=request.getContextPath()%>/backendLogin/home.do">
                  <button type="submit" class="btn btn-outline-secondary" style="margin-left: 50px;">logout</button>
                </form>
                </li>
            </ul>
        </nav>
    </div>
    <div class="container-fluid " style="overflow: hidden; padding: 0; " id="smg_comp "></div>
    <div class="rightside " style="width:84.5vw;float: right;padding: 0 !important; ">
        <!-- <h1 class="mx-auto ">asydhgsa</h1> -->
        <img src="../images/1366X768.png " style="width: 84.5vw;height: 100vh; float: right; ">
    </div>
    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js " integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj " crossorigin="anonymous "></script>
    <script src="../js/home.js "></script>
    <script>
    function prohibitpreviouspage(){

    	if(navigator.userAgent.indexOf('Firefox') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Firefox') + 8)) >= 3.6 ){

    	//Firefox
    	setTimeout("fn_forward()",1);
    	window.history.go(1);
    	}else{ //IE.Chrome.Edge
    	window.history.forward();
    	}
    	}
    	function fn_forward() {
    	history.forward();
    	setTimeout("fn_forward()",1)
    	}
    </script>
<script type="text/javascript">prohibitpreviouspage();</script>
</body>
</html>
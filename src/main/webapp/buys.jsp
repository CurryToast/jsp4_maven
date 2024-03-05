<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 관리</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/buys.css">
<link rel="stylesheet" href="assets/css/modal.css">
</head>
<body>
	<h3>구매 관리</h3>
	<hr>
	<!-- form action이 없을때는 url이 자기 자신 -->
	<form style="display: flex; justify-content: space-around; align-items: center;">
		<button id="selectAll" type="button">전체보기</button>
	</form>
	<hr>
	<ul id="list">
		<li class="list-header">
		</li>
	</ul>
	<!-- The Modal -->
    <div class="modal">
       <div class="modal-dialog">
          <div class="modal-content">
             <div class="modal-header">
                <h4 class="modal-title">새 상품 등록</h4>
             </div>
             <div class="modal-body">
                   <ul>
                      <li>
                         <input type="text" id="pcode" placeholder="상품코드를 입력하세요.">
                      </li>
                      <li>
                         <select id="category">
                            <option value="">카테고리 선택</option>
                            <c:forEach items="${cateList }" var="cate">
                               <option value="${cate.code}" >
                                  <c:out value="${cate.name }" />(<c:out value="${cate.code }" />)
                               </option>
                            </c:forEach>
                         </select>
                      </li>
                      <li>
                         <input type="text" id="pname" placeholder="상품명을 입력하세요.">
                      </li>
                      <li>
                         <input type="number" id="price" placeholder="가격을 입력하세요.">
                      </li>
                      <li>
                         <button id="post">저장</button>&nbsp;&nbsp;&nbsp;<button id="clear">다시쓰기</button>
                      </li>
                   </ul>
             </div>
             <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <!--  <button type="button" class="btn btn-primary">Save changes</button>-->
             </div>
          </div>
       </div>
    </div>
    <!-- 모달 끝 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="assets/js/buysApi_list.js"></script>
</body>
</html>
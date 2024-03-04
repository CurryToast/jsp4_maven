<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link rel="stylesheet" href="assets/css/search.css">
</head>
<body>
	<h3>상품 관리</h3>
	<hr>
	<!-- form action이 없을때는 url이 자기 자신 -->
	<form style="display: flex; justify-content: space-around; align-items: center;">
		<select name="category">
			<option value="">카테고리 선택</option>
			<c:forEach items="${categoryList}" var="category">
				<option value="${category.code}">
					<c:out value="${category.name}"/>
				</option>
			</c:forEach>
		</select>
		<input type="text" name="keyword" value="${keyword}" placeholder="상품명 검색어 입력" />
		<span style="padding-left: 10px;">가격대별</span>
		<input type="text" name="from" placeholder="from" />~
		<input type="text" name="to" placeholder="to" />
		<button id="search" type="button">조회</button>
		<button id="selectAll" type="button">전체보기</button>
	</form>
	<hr>
	<ul>
		<li class="list-header">
			<ul>
				<li class="code">상품코드</li>
				<li class="category">카테고리</li>
				<li class="pname">상품명</li>
				<li class="price">상품가격</li>
			</ul>
		</li>
		<c:forEach items="${list}" var="vo" varStatus="status">
			<li>
				<ul>
					<li> <c:out value="${vo.pcode}"></c:out> </li>
					<li> <c:out value="${vo.category}"></c:out> </li>
					<li> <c:out value="${vo.pname}"></c:out> </li>
					<li> <c:out value="${vo.price}"></c:out> </li>
				</ul>
			</li>
		</c:forEach>
	</ul>
	<script type="text/javascript">
		const temp = '${category}';
	</script>
	<!-- 위의 변수 temp는 search.js와 공유합니다. -->
	<script type="text/javascript" src="assets/js/search.js"></script>
</body>
</html>
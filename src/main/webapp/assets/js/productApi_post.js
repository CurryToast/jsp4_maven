/*
	비동기 통신을 지원하는 JS의 xhr 사용하기 (POST)
*/

const clear = function() {
	document.querySelector('#pcode').value = "";
	document.querySelector('#category').value = "";
	document.querySelector('#pname').value = "";
	document.querySelector('#price').value = "";
}

const save = function() {
	// 비동기 통신에 필요한 데이터 JSON 문자열 만들기
	const yn = confirm("상품을 등록하시겠습니까?");
	if (!yn) {
		return;
	}
	
	const pcode = document.querySelector('#pcode').value;
	const category = document.querySelector('#category').value;
	const pname = document.querySelector('#pname').value;
	const price = document.querySelector('#price').value;
	
	const jsObj = {
		pcode, category, pname, price
	};
	console.log('자바스크립트 객체 : ', jsObj);
	
	const jsStr = JSON.stringify(jsObj);
	console.log('json 문자열 : ', jsStr);
	
	const xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/product');
	xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	xhr.send(jsStr);
	xhr.onload = function() {
		if (xhr.status === 200 || xhr.status === 201) {
			console.log("요청 응답 :", xhr.response);
		} else {
			console.error("오류1 ", xhr.status);
			console.error("오류2 ", xhr.response);
		}
	}
}
document.querySelector("#new").addEventListener('click', () => {
	regModal.show();
});
document.querySelector('#post').addEventListener('click', save);
document.querySelector('#clear').addEventListener('click', clear);
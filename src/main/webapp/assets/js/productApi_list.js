/*
	비동기 통신을 지원하는 JS의 xhr 사용하기 (GET)
*/

const clearInput = function() {
	document.querySelector('#search-form select[name="category"]').value = "";
	document.querySelector('#search-form input[name="keyword"]').value = "";
	document.querySelector('#search-form input[name="from"]'). value = "";
	document.querySelector('#search-form input[name="to"]').value = "";
}

const selectAll = function() {
	const xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/product/list');
	xhr.send();
	xhr.onload = function() {
		if (xhr.status === 200 || xhr.status === 201) {
			console.log("요청 응답 :", xhr.response);
			const arr = JSON.parse(xhr.response);
			console.log("get api/product/list", arr);
			
			const list = document.querySelector('#list');
			list.innerHTML = `
				<ul class="row">
					<li class="index">No</li>
					<li class="code">상품코드</li>
					<li class="category">카테고리</li>
					<li class="pname">상품명</li>
					<li class="price">상품가격</li>
				</ul>
			`;
			arr.forEach((el, idx) => {
				const li = document.createElement('li');
				const ul = document.createElement('ul');
				ul.className = 'row';
				ul.innerHTML = `
					<li>${idx + 1}</li>
					<li>${el.pcode}</li>
					<li>${el.category}</li>
					<li>${el.pname}</li>
					<li style=\"text-align: right;\">
						${el.price.toLocaleString('ko-KR')}
					</li>
				`;
				li.appendChild(ul);
				list.appendChild(li);
			});
			
			clearInput();
		} else {
			console.error("오류1 ", xhr.status);
			console.error("오류2 ", xhr.response);
		}
	}
}

document.querySelector('#selectAll').addEventListener("click", selectAll);
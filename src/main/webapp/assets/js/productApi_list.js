/*
	비동기 통신을 지원하는 JS의 xhr 사용하기 (GET)
*/

const clearInput = function() {
	document.querySelector('#search-form select[name="category"]').value = "";
	document.querySelector('#search-form input[name="keyword"]').value = "";
	document.querySelector('#search-form input[name="from"]'). value = "";
	document.querySelector('#search-form input[name="to"]').value = "";
}

const toggleListItem = function() {
	const div = this.querySelector('.button-container');

	if (div.className.includes('hidden')) {
		const newClassName = div.className.replace('hidden', '').trim();
		div.className = newClassName;
	} else {
		div.className += ' hidden';
	}
}

const deleteThisItem = function(pcode) {
	if (!pcode.length) {
		return;
	}

	const yn = confirm("상품을 삭제 하시겠습니까?");
	if (!yn) {
		return;
	}

	const xhr = new XMLHttpRequest();
	xhr.open('DELETE', `api/product?pcode=${pcode}`);
	xhr.send();
	xhr.onload = function() {
		if (xhr.status === 200 || xhr.status === 201) {
			console.log("요청 응답 :", xhr.response);
			selectAll();
		} else {
			console.error("오류1 ", xhr.status);
			console.error("오류2 ", xhr.response);
		}
	}
}

const modifyThisItem = function(obj) {}

const selectAll = function() {
	const xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/product');
	xhr.send();
	xhr.onload = function() {
		if (xhr.status === 200 || xhr.status === 201) {
			console.log("요청 응답 :", xhr.response);
			const arr = JSON.parse(xhr.response);
			console.log("get api/product/list", arr);
			
			const list = document.querySelector('#list');
			list.innerHTML = `
				<li>
					<ul class="row">
						<li class="index">No</li>
						<li class="code">상품코드</li>
						<li class="category">카테고리</li>
						<li class="pname">상품명</li>
						<li class="price">상품가격</li>
					</ul>
				</li>
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
				
				const div = document.createElement('div');
				div.className = 'button-container hidden';
				div.innerHTML = `
					<button onclick="modifyThisItem(\'${el}\')">수정</button>
					<button onclick="deleteThisItem(\'${el.pcode}\')">삭제</button>
				`;
				li.append(div);
				list.appendChild(li);
			});
			
			clearInput();

			document.querySelectorAll('#list > li:not(:first-of-type)').forEach(el => {
				el.addEventListener("click", toggleListItem);
			});
		} else {
			console.error("오류1 ", xhr.status);
			console.error("오류2 ", xhr.response);
		}
	}
}

selectAll();
document.querySelector('#selectAll').addEventListener("click", selectAll);
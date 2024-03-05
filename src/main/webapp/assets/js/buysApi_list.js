/*
	비동기 통신을 지원하는 JS의 xhr 사용하기 (GET)
*/

const selectAll = function() {
	const xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/buy/list');
	xhr.send();
	xhr.onload = function() {
		if (xhr.status === 200 || xhr.status === 201) {
			console.log("요청 응답 :", xhr.response);
			const arr = JSON.parse(xhr.response);
			console.log("get api/buy/list", arr);
			
			const list = document.querySelector('#list');
			list.innerHTML = `
				<ul class="row">
					<li class="buy_id">구매번호</li>
					<li class="customid">고객 ID</li>
					<li class="pname">상품코드</li>
					<li class="pname">상품명</li>
					<li class="price">상품가격</li>
					<li class="quantity">구매수량</li>
					<li class="buy_date">구매일시</li>
				</ul>
			`;

			arr.forEach(el => {
				const li = document.createElement('li');
				const ul = document.createElement('ul');
				ul.className = 'row';
				ul.innerHTML = `
					<li>${el.buy_idx}</li>
					<li>${el.customid}</li>
					<li>${el.pcode}</li>
					<li>${el.pname}</li>
					<li>${el.price}</li>
					<li>${el.quantity}</li>
					<li>
						${el.buy_date}
					</li>
				`;

				li.appendChild(ul);
				list.appendChild(li);
			});
		} else {
			console.error("오류1 ", xhr.status);
			console.error("오류2 ", xhr.response);
		}
	}
}

document.querySelector('#selectAll').addEventListener("click", selectAll);
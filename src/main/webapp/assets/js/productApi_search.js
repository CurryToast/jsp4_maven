/*
	상품 조회 검색어 입력을 확인하기 위한 내용입니다.
*/

const searchProductList = () => {
	const category = document.querySelector('#search-form select[name="category"]').value;
	const keyword = document.querySelector('#search-form input[name="keyword"]').value;
	const from = document.querySelector('#search-form input[name="from"]').value;
	const to = document.querySelector('#search-form input[name="to"]').value;
	
	const jsObj = {
		category: !category.length ? undefined : category,
		keyword: !keyword.length ? undefined : keyword,
		from: !from.length ? undefined : Number(from),
		to: !to.length ? undefined : Number(to)
	};
	const jsStr = JSON.stringify(jsObj);
	
	const xhr = new XMLHttpRequest();

	xhr.open('PUT', 'api/product/search');
	xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	xhr.send(jsStr);
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
		} else {
			console.error("오류1 ", xhr.status);
			console.error("오류2 ", xhr.response);
		}
	}
};


document.querySelector('select[name="category"]').addEventListener("change", searchProductList);
document.querySelector('#search-form #search').addEventListener("click", searchProductList);
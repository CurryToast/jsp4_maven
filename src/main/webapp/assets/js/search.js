/*
	상품 조회 검색어 입력을 확인하기 위한 내용입니다.
*/
if (temp != null) {
	document.querySelector('select[name="category"]').value = temp;
}

const submitCallback = () => {
	const category = document.querySelector('select[name="category"]');
	const keyword = document.querySelector('input[name="keyword"]');
	const from = document.querySelector('input[name="from"]');
	const to = document.querySelector('input[name="to"]');

	let isValid = true;

	if (
		keyword.value.length == 0 && category.value.length == 0 &&
		from.value.length == 0 && to.value.length == 0
	) {
		isValid = false;
		alert("검색어를 입력하세요.");
	}
	
	if (isValid) {
		document.forms[0].submit();
	}
};


document.querySelector('select[name="category"]').addEventListener("change", submitCallback);
document.getElementById("search").addEventListener("click", submitCallback);
document.getElementById("selectAll").addEventListener("click", () => {
	location.href = location.pathname;
});
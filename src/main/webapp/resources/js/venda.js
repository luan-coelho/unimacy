function capturarTab() {
	let index = document.getElementById('form:tabview_activeIndex').value
	console.log(index);

	document.getElementById('form:indexTab').value = index;
	document.getElementById('form:botaoAtualizar').click();

	console.log(document.getElementById('form:indexTab').value);
}
function functionTest()
{
	const servletURL =  '/DoAnCNTT1_KinhDoanhNoiThat/FunctionTestServlet';
	const params = new URLSearchParams();
	window.location.href = `${servletURL}?${params.toString()}`;
}

function colorFilter()
{
	const servletURL = '/DoAnCNTT1_KinhDoanhNoiThat/FunctionTestServlet';
	const params = new URLSearchParams();
	const selectedColors = document.querySelectorAll('input[name="color"]:checked'); 
	selectedColors.forEach ( selectedColor => { params.append('selectedColors', selectedColor.value); } );
	params.append('action', 'filter');
	window.location.href = `${servletURL}?${params.toString()}`;
}
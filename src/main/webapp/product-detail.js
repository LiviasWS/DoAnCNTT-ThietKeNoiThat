function decreaseQuantity()
{
    const quantity = document.getElementById("quantity");
    let quantityValue = parseInt(quantity.value); 
    if(quantityValue > 1)
    {
        quantity.value = quantityValue - 1;
    }
}
function increaseQuantity()
{
    const quantity = document.getElementById("quantity");
    let quantityValue = parseInt(quantity.value);
    quantity.value = quantityValue + 1;
}
function changeProductImage(imageAddress, featureName) 
{
    document.getElementById('feature').value = featureName;
	document.getElementById('image').value = imageAddress;
	const contextPath = document.getElementById('contextPath').value;
	const productImage = document.getElementById('productImage');
	productImage.style.backgroundImage = `url('` + contextPath + imageAddress + `')`;
    const debugElement = document.getElementById('debug-output');
    if (debugElement) {
        debugElement.textContent = `Product Image URL: url('` + contextPath + `${imageAddress}')`;
    }
}
